package com.bp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException;

}
