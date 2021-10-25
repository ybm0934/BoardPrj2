package com.bp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

	private Properties props;
	String configFile;
	String realConfigFile;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 실행");

		configFile = config.getInitParameter("config");
		realConfigFile = config.getServletContext().getRealPath(configFile);
		System.out.println("realConfigFile : " + realConfigFile);

		props = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(realConfigFile);
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userProcess(req, resp);
	}

	public void userProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("userProcess 실행");

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		if (uri.indexOf(contextPath) == 0) {
			uri = uri.substring(contextPath.length());
		}
		System.out.println("uri : " + uri);

		String command = props.getProperty(uri);
		try {
			Class commandClass = Class.forName(command);
			Controller controller = (Controller) commandClass.newInstance();
			String viewPage = controller.process(req, resp);
			System.out.println("commandClass : " + command);
			System.out.println("viewPage : " + viewPage);

			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
