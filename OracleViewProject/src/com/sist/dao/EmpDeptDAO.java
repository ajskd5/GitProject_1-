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
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	// 드라이버 등록 => 드라이버 / 서버연결 / 배치 / 멤버변수 초기화 => 생성자
	
	// 싱글톤
	private static EmpDeptDAO dao; // static공간은 메모리가 1개 생성
	
	public EmpDeptDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
			// 클래스 등록 => 패키지명.클래스
			// 스프링은 클래스 관리자(생성 ~ 소멸) => 컨테이너 (DI)
			// INDEX => Annotation
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 닫기
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
	
	// 싱글톤 패턴
	public static EmpDeptDAO newInstance() {
		if(dao == null) {
			dao = new EmpDeptDAO();
		}
		return dao;
	}
	// ------------------------------- 공통 기반
	// 기능 -> SQL
	// JOIN
	public List<EmpDeptVO> empDeptListData1(){
		List<EmpDeptVO> list = new ArrayList<EmpDeptVO>();
		try {
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal, dname, loc, grade "
					+ "FROM emp, dept, salgrade "
					+ "WHERE emp.deptno = dept.deptno "
					+ "AND sal BETWEEN losal AND hisal";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpDeptVO vo = new EmpDeptVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.setDname(rs.getString(6));
				vo.setLoc(rs.getString(7));
				vo.setGrade(rs.getInt(8));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	//VIEW
	public List<EmpDeptVO> empDeptListData2(){
		List<EmpDeptVO> list = new ArrayList<EmpDeptVO>();
		try {
			getConnection();
			String sql = "SELECT * FROM empdept_view";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpDeptVO vo = new EmpDeptVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.setDname(rs.getString(6));
				vo.setLoc(rs.getString(7));
				vo.setGrade(rs.getInt(8));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	
	// 스칼라 서브쿼리
	public List<EmpDeptVO> empDeptListData3(){
		List<EmpDeptVO> list = new ArrayList<EmpDeptVO>();
		try {
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal, "
					+ "(SELECT dname FROM dept WHERE deptno = emp.deptno) dname, "
					+ "(SELECT loc FROM dept WHERE deptno = emp.deptno) loc, "
					+ "(SELECT grade FROM salgrade WHERE emp.sal BETWEEN losal AND hisal) grade "
					+ "FROM emp";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpDeptVO vo = new EmpDeptVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.setDname(rs.getString(6));
				vo.setLoc(rs.getString(7));
				vo.setGrade(rs.getInt(8));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
}
