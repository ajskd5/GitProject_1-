package com.sist.dao;
import java.util.*; // ArrayList
import java.sql.*; // 오라클 연결
public class EmpDAO {
	// 오라클 연결 객체
	private Connection conn;
	
	// SQL문장 전송
	private PreparedStatement ps;
	
	// 연결 => URL(오라클 서버 주소)
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	// mysql => jdbc:mysql://localhost:3306/mydb?serverTimezone=UCT
	
	// 1. 드라이버 등록
	public EmpDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 2. 오라클 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 3. 오라클 닫기
	public void disConnection() {
		try {
			//exit
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
	
	// 여기까지 필수
	
	// 4. 기능
	// 4-1. 목록 출력 ==> NVL (NULL값 존재) => Emp (14개) 튜플 14개 존재
	// 	클래스는 한명, 한개에 대한 정보 저장 => new => 모아서 관리 =>List
	public ArrayList<Emp> empListData(){
		ArrayList<Emp> list = new ArrayList<Emp>();
		try {
			// 1. 연결
			getConnection();
			// 2. SQL문장 만들기
			String sql = "SELECT empno, ename, job, hiredate, sal, NVL(comm, 0) "
					+ "FROM emp";
			// 3. 오라클 전송
			ps = conn.prepareStatement(sql);
			// 4. 결과값 받기
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { // 처음부터 마지막까지
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setHiredate(rs.getDate(4));
				emp.setSal(rs.getInt(5));
				emp.setComm(rs.getInt(6));
				
				list.add(emp);
			}
			rs.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 오라클 닫기
			disConnection();
		}
		return list;
	}
	
	// 4-2. 상세보기 ==> WHERE
	
	
	// 4-3. 검색 ==> LIKE
	
	
	
}
