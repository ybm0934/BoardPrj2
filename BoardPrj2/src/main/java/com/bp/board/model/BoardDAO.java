package com.bp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.bp.connection.ConnectionPoolMgr;

public class BoardDAO {

	public static final int ACCORD_PWD = 1;
	public static final int DISCORD_PWD = 2;

	private ConnectionPoolMgr pool;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public BoardDAO() {
		pool = new ConnectionPoolMgr();
	}

	public ArrayList<BoardVO> boardList(String category, String keyword) throws SQLException {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			System.out.println("boardList 실행");

			con = pool.getConnection();

			String sql = "select * from board";
			if(keyword != null && !keyword.isEmpty()) {
				sql += " where " + category + " like '%" + keyword + "%'";
			}
			sql += " order by no desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");

				BoardVO vo = new BoardVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setPwd(pwd);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegdate(regdate);

				list.add(vo);
			}

			System.out.println("게시판 불러오기 결과 list.size() = " + list.size());
			System.out.println("파라미터 category = " + category + ", keyword = " + keyword);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.dbClose(rs, ps, con);
		}

		return list;
	}

	public int boardWrite(BoardVO vo) throws SQLException {
		int cnt = 0;
		try {
			System.out.println("boardWrite 실행");

			con = pool.getConnection();

			String sql = "insert into board values(board_seq.nextval, ?, ?, ?, ?, default)";
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getContent());
			cnt = ps.executeUpdate();

			System.out.println("게시판 글쓰기 결과 cnt = " + cnt);
			System.out.println("파라미터 vo = " + vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.dbClose(ps, con);
		}

		return cnt;
	}

	public BoardVO boardDetail(int no) throws SQLException {
		BoardVO vo = new BoardVO();
		try {
			System.out.println("boardDetail 실행");

			con = pool.getConnection();

			String sql = "select * from board where no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");

				vo.setNo(no);
				vo.setName(name);
				vo.setPwd(pwd);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegdate(regdate);
			}

			System.out.println("글 상세보기 결과 vo = " + vo);
			System.out.println("파라미터 no = " + no);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.dbClose(rs, ps, con);
		}

		return vo;
	}

	public int pwdCheck(int no, String pwd) throws SQLException {
		int cnt = 0;
		try {
			System.out.println("pwdCheck 실행");

			con = pool.getConnection();

			String sql = "select pwd from board where no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				String realPwd = rs.getString("pwd");
				if (!pwd.equals(realPwd)) {
					cnt = DISCORD_PWD;
				} else {
					cnt = ACCORD_PWD;
				}
			}

			System.out.println("비밀번호 확인 결과 cnt = " + cnt);
			System.out.println("파라미터 no = " + no + ", pwd = " + pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.dbClose(rs, ps, con);
		}

		return cnt;
	}

	public int boardEdit(BoardVO vo) throws SQLException {
		int cnt = 0;
		try {
			System.out.println("boardEdit 실행");

			con = pool.getConnection();

			String sql = "update board set name = ?, pwd = ?, title = ?, content = ? where no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getContent());
			ps.setInt(5, vo.getNo());
			cnt = ps.executeUpdate();

			System.out.println("글 수정하기 결과 cnt = " + cnt);
			System.out.println("파라미터 vo = " + vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.dbClose(ps, con);
		}

		return cnt;
	}

	public int boardDelete(int no) throws SQLException {
		int cnt = 0;
		try {
			System.out.println("boardDelete 실행");

			con = pool.getConnection();

			String sql = "delete from board where no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			cnt = ps.executeUpdate();

			System.out.println("글 삭제 결과 cnt = " + cnt);
			System.out.println("파라미터 no = " + no);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.dbClose(ps, con);
		}

		return cnt;
	}

}
