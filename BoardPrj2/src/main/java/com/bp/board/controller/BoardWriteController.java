package com.bp.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bp.board.model.BoardDAO;
import com.bp.board.model.BoardVO;
import com.bp.controller.Controller;

public class BoardWriteController implements Controller {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("BoardWriteController 실행");

		req.setCharacterEncoding("UTF-8");

		String name = (String) req.getParameter("name");
		String pwd = (String) req.getParameter("pwd");
		String title = (String) req.getParameter("title");
		String content = (String) req.getParameter("content");

		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setPwd(pwd);
		vo.setTitle(title);
		vo.setContent(content);

		BoardDAO dao = new BoardDAO();
		int cnt = dao.boardWrite(vo);

		String msg = (cnt > 0) ? "글쓰기 성공" : "글쓰기 실패";
		String url = "boardList.do";

		req.setAttribute("msg", msg);
		req.setAttribute("url", url);

		return "/message/message.jsp";
	}

}
