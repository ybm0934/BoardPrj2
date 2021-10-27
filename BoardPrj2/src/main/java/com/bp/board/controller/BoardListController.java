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
import com.bp.model.PagingVO;

public class BoardListController implements Controller {
	
	private static final int PAGESIZE = 10; // 한 페이지에 보여질 레코드 수
	private static final int BLOCKSIZE = 10; // 한 블럭에 보여질 페이지 수
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("BoardListController 실행");
		
		req.setCharacterEncoding("UTF-8");
		
		String category = req.getParameter("category");
		String keyword = "";
		if(keyword != null) {
			keyword = req.getParameter("keyword");
		}
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		list = dao.boardList(category, keyword);
		
		// 페이징 처리
		int currentPage = 1;
		if(req.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		}
		int totalRecord = list.size();
		int pageSize = PAGESIZE;
		int blockSize = BLOCKSIZE;
		
		PagingVO pvo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);
		
		req.setAttribute("list", list);
		req.setAttribute("pvo", pvo);
		req.setAttribute("category", category);
		req.setAttribute("keyword", keyword);
		
		return "/board/boardList.jsp";
	}

}
