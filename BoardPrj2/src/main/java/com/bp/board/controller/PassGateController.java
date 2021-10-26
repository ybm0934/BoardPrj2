package com.bp.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bp.board.model.BoardDAO;
import com.bp.controller.Controller;

public class PassGateController implements Controller {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("PassGateController 실행");

		req.setCharacterEncoding("UTF-8");

		int no = Integer.parseInt(req.getParameter("no"));
		String process = req.getParameter("process");
		String pwd = req.getParameter("pwd");

		BoardDAO dao = new BoardDAO();
		int cnt = dao.pwdCheck(no, pwd);

		String msg = "";
		String url = "";
		if (cnt == dao.ACCORD_PWD) {
			if (process.equals("edit")) {
				url = "boardEditForm.do?no=" + no;
			} else if (process.equals("delete")) {
				url = "boardDelete.do?no=" + no;
			}
		} else if (cnt == dao.DISCORD_PWD) {
			msg = "비밀번호가 일치하지 않습니다.";
			url = "passGateForm.do?no=" + no + "&process=" + process;
		}

		req.setAttribute("msg", msg);
		req.setAttribute("url", url);

		return "/message/message.jsp";
	}

}
