package com.sist.dao;

import java.sql.*;
import java.util.*;
/*
 * EmpDeptDAO <=============> Oracle (서버)
 *   client			TCP
 */

public class EmpDeptDAO {
	private Connection conn; // Socket
	private PreparedStatement ps; // BufferedReader / OutputStream
	// 웹 ==> HttpServletRequest(Socket) / HttpServletResponse(서버)
	// Spring => Model
	private final String URL = "jdbc:orcle:thin:@localhost:1521:XE";
	// 드라이버 등록 => 드라이버 / 서버연결 / 배치 / 멤버변수 초기화 => 생성자
	public EmpDeptDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void disConnection() {
		try {
			if(conn != null) {
				conn.close();
			}
			if(ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
