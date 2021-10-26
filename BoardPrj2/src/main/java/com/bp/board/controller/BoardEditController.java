package com.bp.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bp.board.model.BoardDAO;
import com.bp.board.model.BoardVO;
import com.bp.controller.Controller;

public class BoardEditController implements Controller {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("BoardEditController 실행");
		
		req.setCharacterEncoding("UTF-8");
		
		String msg = "";
		String url = "";
		if(!req.getMethod().equals("GET")) {
			int no = Integer.parseInt(req.getParameter("no"));
			String name = req.getParameter("name");
			String pwd = req.getParameter("pwd");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
	
			BoardVO vo = new BoardVO();
			vo.setNo(no);
			vo.setName(name);
			vo.setPwd(pwd);
			vo.setTitle(title);
			vo.setContent(content);
	
			BoardDAO dao = new BoardDAO();
			int cnt = dao.boardEdit(vo);
	
			msg = (cnt > 0) ? "글 수정 성공" : "글 수정 실패";
			url = (cnt > 0) ? "boardDetail.do?no=" + no : "boardEditForm.do?no=" + no;
	
		}else {
			msg = "잘못된 접근입니다.";
			url = "boardList.do";
		}
		
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);

		return "/message/message.jsp";
	}

}
