package com.sist.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
 *  오라클 (SQL) => 데이터를 저장하는 장소 (서버 => 모든 데이터 공유)
 *  
 *  DML => 데이터를 조작하는 언어
 *  	오라클 (명령어)
 *  	1) 데이터 검색 (SELECT)
 *  		= 형식
 *  			SELECT [ALL | DISTINCT] * | 컬럼명1, 컬럼명2, ...
 *  			FROM table명 (관련된 데이터 저장한 장소) = 파일
 *  			----------------------------------------------  필수
 *  			[
 *  			WHERE 조건문 (컬럼명 연산자 값) 결과가 true / false
 *  			GROUP BY 그룹컬럼명(함수)
 *  			HAVING	그룹조건(GROUP BY 가 나와야 쓸 수 있음)
 *  			ORDER BY 컬럼명 | 함수  ASC / DESC
 *  			]
 *  		= 연산자
 *  			1. 산술연산자 ( +, -, *, /)
 *  				+ => 문자열 결합 못함, 산술만 가능
 *  					문자열 결합 => || 
 *  				/ => 0으로 나눌 수 없음, 정수/정수 => 실수
 *  				
 *  				사용 위치 => SELECT 뒤에 붙음 (통계, 평균, ...)
 *  
 *  			2. 비교연산자 (=, !=(<>), <, >, <=, >=)
 *  				결과값 => boolean( true / false)
 *  			
 *  			3. 논리연산자 (AND, OR)
 *  				AND : 직렬
 *  				OR : 병렬
 *  			---------------------------
 *  			4. IN => OR가 많은 경우 사용
 *  			5. BETWEEN ~ AND => 기간, 범위    BETWEEN  >= AND <=
 *  			6. NULL => 데이터가 없는 경우 => 연산처리가 안 됨
 *  				IS NULL, IS NOT NULL
 *  			7. LIKE => 문자 찾기
 *  				% => 문자 갯수를 알 수 없을 때
 *  				_ => 한글자
 *  				
 *  			8. NOT => 부정연산자
 *  				NOT IN, NOT LIKE, NOT BETWEEN ~ AND
 *  
 *  		자바 (응용프로그램) ======> 오라클 서버
 *  							SQL 전송
 *  							실행된 결과 =====> 브라우저로 전송
 *  		1) 드라이버 등록
 *  		2) 오라클 연결
 *  		3) SQL 문장 제작
 *  		4) SQL 문장 오라클로 전송
 *  		5) 실행 후 결과 값 받기
 *  		6) 출력
 */
import java.util.*;
public class MainClass1 {
	//전체 데이터 읽기
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		String name = scan.next();
		name = name.toUpperCase(); // emp ename에 이름은 다 대문자로 되어 있음
		
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 연결
			String url= "jdbc:oracle:thin:@localhost:1521:xe";
			Connection conn = DriverManager.getConnection(url, "hr", "happy");

			// SQL 문장
//			String sql = "SELECT * FROM emp";
			String sql = "SELECT empno, ename, job, hiredate, sal " // 띄어쓰기 주의
					+ "FROM emp "
					+ "WHERE ename IN('WARD', 'SCOTT')";
//					+ "WHERE ename LIKE '%'||?||'%'"; // LIKE문장이 다름
			// SQL 문장 전송
			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, name); // ?에 값 넣음
			
			// 실행 후 결과값 가져옴
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(
						rs.getInt(1) + " "
						+ rs.getString(2) + " "
						+ rs.getString(3) + " "
						+ rs.getDate(4) + " "
						+ rs.getInt(5)
						);
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
