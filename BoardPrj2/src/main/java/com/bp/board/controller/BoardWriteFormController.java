package com.bp.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bp.controller.Controller;

public class BoardWriteFormController implements Controller {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {

		return "/board/boardWrite.jsp";
	}

}
