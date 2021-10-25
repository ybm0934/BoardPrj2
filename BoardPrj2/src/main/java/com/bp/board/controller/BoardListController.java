package com.bp.board.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bp.board.model.BoardDAO;
import com.bp.board.model.BoardVO;
import com.bp.controller.Controller;

public class BoardListController implements Controller {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("BoardListController 실행");
		
		req.setCharacterEncoding("UTF-8");
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		list = dao.boardList();
		
		req.setAttribute("list", list);
		
		return "/board/boardList.jsp";
	}

}
