package com.sist.dao;

import java.sql.*;
import java.util.*;


public class BooksDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static BooksDAO dao;
	
	public BooksDAO() {
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
	
	// 싱글톤 패턴
	public static BooksDAO newInstance() {
		if(dao == null) {
			dao = new BooksDAO();
		}
		return dao;
	}
	
	//기능
	// 페이징 => 인라인뷰  => 자바로 나누기
	public List<BooksVO> booksListData(int page){
		List<BooksVO> list = new ArrayList<BooksVO>();
		long start = System.currentTimeMillis();
		try {
			getConnection();
			String sql = "SELECT no, title "
					+ "FROM books "
					+ "ORDER BY no ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0; // 10개씩 나눠주는 변수
			int j = 0; // while 횟수
			int rowSize = 10; // 갯수
			int pagecnt = (page*rowSize) - rowSize; // 시작 위치
			/*
			 * while / for => 0번부터 시작
			 * 뒷 페이지 선택해도 앞 부분을 스킵하려면 반복문을 돌려야 함
			 *  => 느림
			 */
			while(rs.next()) {
				if(i<rowSize && j>=pagecnt) {
					BooksVO vo = new BooksVO();
					vo.setNo(rs.getInt(1));
					vo.setTitle(rs.getString(2));
					list.add(vo);
					i++;
				}
				j++;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		long end = System.currentTimeMillis();
		System.out.println("걸린 시간 : " + (end-start));
		return list;
	}
	// 인라인뷰로 페이지 나누기
	// => 1. 페이징, 원하는 갯수만큼
	public List<BooksVO> booksListData2(int page){
		List<BooksVO> list = new ArrayList<BooksVO>();
		long start = System.currentTimeMillis();
		try {
			getConnection();
			String sql = "SELECT no, title "
					+ "FROM books "
					+ "ORDER BY no ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0; // 10개씩 나눠주는 변수
			int j = 0; // while 횟수
			int rowSize = 10; // 갯수
			int pagecnt = (page*rowSize) - rowSize; // 시작 위치
			/*
			 * while / for => 0번부터 시작
			 * 뒷 페이지 선택해도 앞 부분을 스킵하려면 반복문을 돌려야 함
			 *  => 느림
			 */
			while(rs.next()) {
				if(i<rowSize && j>=pagecnt) {
					BooksVO vo = new BooksVO();
					vo.setNo(rs.getInt(1));
					vo.setTitle(rs.getString(2));
					list.add(vo);
					i++;
				}
				j++;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		long end = System.currentTimeMillis();
		System.out.println("걸린 시간 : " + (end-start));
		return list;
	}
	
	// 총 페이지
	public int booksTotalPage() {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) FROM books";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return total;
	}
	
}



