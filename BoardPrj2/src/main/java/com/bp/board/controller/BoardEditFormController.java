package com.bp.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bp.board.model.BoardDAO;
import com.bp.board.model.BoardVO;
import com.bp.controller.Controller;

public class BoardEditFormController implements Controller {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("BoardEditFormController 실행");
		
		String ref = req.getHeader("REFERER");
		System.out.println("referer : " + ref);
		
		String msg = "";
		String url = "";
		if(ref != null) {
			int no = Integer.parseInt(req.getParameter("no"));
			
			BoardDAO dao = new BoardDAO();
			BoardVO vo = new BoardVO();
			vo = dao.boardDetail(no);
			
			req.setAttribute("vo", vo);
			
			url = "/board/boardEdit.jsp";
		}else {
			msg = "잘못된 접근입니다.";
			url = "boardList.do";
		}
		
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		
		return "/message/message.jsp";
	}

}
