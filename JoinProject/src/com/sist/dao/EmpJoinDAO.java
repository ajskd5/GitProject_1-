package com.sist.dao;
import java.util.*;
import java.sql.*;
public class EmpJoinDAO {
	private Connection conn;
	private PreparedStatement ps; // SQL 문장 전송   ERP => CallableStatement(함수)
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static EmpJoinDAO dao;
	
	public EmpJoinDAO() {
		try { // thin dirver
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// SingleTon (객체 한번만 생성) 메모리 공간 1개만 사용 => static
	public static EmpJoinDAO newInstance() {
		if(dao == null) {
			dao = new EmpJoinDAO(); // 맨 처음 한번만 수행
		}
		return dao; //  기존에 생성된 거 사용
	}
	
	//연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//해제
	public void disConnection() {
		try {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 기능
	public List<Emp> empJoinData(){
		List<Emp> list = new ArrayList<Emp>();
		try {
			getConnection();
			// ORACLE JOIN
//			String sql = "SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, "
//					+ "sal, dname, loc "
//					+ "FROM emp, dept "
//					+ "WHERE emp.deptno = dept.deptno";
			
			// ANSI JOIN
//			String sql = "SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, "
//					+ "sal, dname, loc "
//					+ "FROM emp JOIN dept "
//					+ "ON emp.deptno = dept.deptno";
			//FROM emp JOIN dept ==>> JOIN=,  WHERE=ON
			
			// NATURAL JOIN
//			String sql = "SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, "
//					+ "sal, dname, loc "
//					+ "FROM emp NATURAL JOIN dept";
			
			// JOIN USING
			String sql = "SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, "
					+ "sal, dname, loc "
					+ "FROM emp JOIN dept USING(deptno)";
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setJob(rs.getNString(3));
				emp.setDbday(rs.getNString(4));
				emp.setSal(rs.getInt(5));
				emp.getDvo().setDname(rs.getString(6)); // JOIN
				emp.getDvo().setLoc(rs.getString(7)); // JOIN
				list.add(emp);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	public List<Emp> empNonEquiJoinData(){
		List<Emp> list = new ArrayList<Emp>();
		try {
			getConnection();
			// ORACLE JOIN
//			String sql = "SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY/MM/DD') as dbday, "
//					+ "sal, dname, loc, grade "
//					+ "FROM emp, dept, salgrade "
//					+ "WHERE emp.deptno = dept.deptno "
//					+ "AND sal BETWEEN losal AND hisal";
			
			// ANSI JOIN
			String sql = "SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY/MM/DD') as dbday, "
					+ "sal, dname, loc, grade "
					+ "FROM emp JOIN dept "
					+ "ON emp.deptno = dept.deptno "
					+ "JOIN salgrade "
					+ "ON sal BETWEEN losal AND hisal";
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno")); //1
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setDbday(rs.getString("dbday"));
				// 함수 => 별칭 사용
				emp.setSal(rs.getInt("sal"));
				emp.getDvo().setDname(rs.getString("dname"));
				emp.getDvo().setLoc(rs.getString("loc"));
				emp.getSvo().setGrade(rs.getInt("grade"));
				
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	// outer join
	public List<Emp> empOuterJoin(){
		List<Emp> list = new ArrayList<Emp>();
		try {
			getConnection();
			//ORACLE JOIN
//			String sql = "SELECT empno, ename, job, dname, loc "
//					+ "FROM emp, dept "
//					+ "WHERE emp.deptno(+) = dept.deptno";
			// ANSI JOIN
			String sql = "SELECT empno, ename, job, dname, loc "
					+ "FROM emp RIGHT OUTER JOIN dept "
					+ "ON emp.deptno = dept.deptno";
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.getDvo().setDname(rs.getString(4));
				emp.getDvo().setLoc(rs.getString(5));
				list.add(emp);
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
