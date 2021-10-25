package com.bp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPoolMgr {

	public ConnectionPoolMgr() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ybm";
		String pwd = "1234";

		Connection con = DriverManager.getConnection(url, user, pwd);
		System.out.println("DB 연동 con : " + con);

		return con;
	}

	public void dbClose(PreparedStatement ps, Connection con) throws SQLException {
		if (ps != null)
			ps.close();
		if (con != null)
			con.close();
	}

	public void dbClose(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (con != null)
			con.close();
	}

}
