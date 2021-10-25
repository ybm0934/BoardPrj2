package com.bp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.bp.connection.ConnectionPoolMgr;

public class BoardDAO {

	private ConnectionPoolMgr pool;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public BoardDAO() {
		pool = new ConnectionPoolMgr();
	}

	public ArrayList<BoardVO> boardList() throws SQLException {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			System.out.println("boardList 실행");

			con = pool.getConnection();

			String sql = "select * from board order by no desc";
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
			System.out.println("파라미터 ");
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

}
