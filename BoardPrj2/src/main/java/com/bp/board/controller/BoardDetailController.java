package com.bp.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bp.board.model.BoardDAO;
import com.bp.board.model.BoardVO;
import com.bp.controller.Controller;

public class BoardDetailController implements Controller {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("BoardDetailController 실행");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo = dao.boardDetail(no);
		
		req.setAttribute("vo", vo);
		
		return "/board/boardDetail.jsp";
	}

}
