package com.sist.curd;
import java.util.*; // VO(게시물 1개) => List (Collection)
import java.sql.*; // Connection, Statement, ResultSet
/*
 *  JDBC
 *  자바 오라클 연동 => 라이브러리
 *  	1) 드라이버 설정 (ojdbc8.jar)
 *  	2) 오라클 연결
 *  	3) SQL문장 전송
 *  	4) 결과값 받기
 *  	5) 오라클 닫기
 */
public class BoardDAO {
	// 연결 객체
	private Connection conn;
	
	// SQL문장 전송 => 결과값을 가져오는 객체
	private PreparedStatement ps;
	
	// 오라클 서버 주소
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 등록 ==> 한번만 서렂ㅇ
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 리플렉션 (이름등록 => 모든 정보 처리)
			// 변수값 주입, 메소드 호출, 생성자 제어 가능, 매개변수 확인
			// DI (값 주입)
			// 스프링 => 클래스 관리
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 오라클 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
			// conn hr/happy
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 오라클 닫기
	public void disConnection() {
		try {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
			// exit
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 기능
	// 1. 로그인 =====> COUNT
	public String isLogin(String id, String pwd) {
		// no id / no pwd / ok
		String result = "";
		try {
			// 1. 연결
			getConnection();
			// 2. SQL문장 전송
			// 2-1 id 존재 여부 확인
			String sql = "SELECT COUNT(*) "
					+ "FROM boardMember "
					+ "WHERE id = ?";
			// 0(X), 1(0)
			// 3. 결과값 받기
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next(); //커서 위치 변경 (sql 쓰고 입력하면 커서가 새 창으로 내려가는데 결과값의 맨위로 올리기) => 반복문으로 아래로 내리면서 결과값 읽음)
			// previous는 커서를 결과값 맨 아래로 가서 반복문으로 위로 올라감
			int count = rs.getInt(1);
			rs.close();
			
			// => 처리
			if(count == 0) { // id 없음
				result = "NOID";
			} else { // id 존재
				// 비밀번호 검색
				sql = "SELECT pwd "
						+"FROM boardMember "
						+ "WHERE id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1,  id);
				rs = ps.executeQuery();
				rs.next();
				String db_pwd = rs.getString(1);
				rs.close();
				
				if(db_pwd.equals(pwd)) { // 로그인
					result = "OK";
				} else { // 비밀번호 틀림
					result = "NOPWD";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 오라클 해제
			disConnection();
		}
		return result;
	}
	
	// 2. 목록 읽기 ==> 페이징 => 인라인뷰(서브쿼리)
	public List<BoardVO> boardListData(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			// 1. 연결
			getConnection();
			// 2. SQL문장
			// 정렬 /* + INDEX_DESC() */
			String sql = "SELECT no, subject, name, regdate, hit "
					+ "From freeboard "
					+ "ORDER BY 1 DESC";// 1-> no
			// 3. 오라클 전송
			ps = conn.prepareStatement(sql);
			// 4. 결과값 저장
			ResultSet rs = ps.executeQuery();
			// 5. List에 값 채움
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
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
	// 3. 상세 보기 ==> WHERE
	// 4. 글쓰기 =====> INSERT
	public void boardInsert(BoardVO vo) {
		try {
			// 연결
			getConnection();
			// SQL 문장
			String sql = "INSERT INTO freeBoard(no, name, subject, content, pwd) VALUES ("
					+ "(SELECT NVL(MAX(no)+1, 1) FROM freeboard), ?, ?, ?, ?)";
			// 오라클로 전송 => 네트웤
			ps = conn.prepareStatement(sql);
			// ?에 값 채우기
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			// 실행 명령
			ps.executeUpdate(); // COMMIT
			// commit => 데이터 변경 => INSERT, UPDATE, DELETE (DML)
			// 데이터 읽는 기능이 없음(리턴값 : void) executeQuery는 있음
			// conn.setAutoCommit(false); => 자동 커밋 해제
			// 나중에 conn.commit(); 함
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	// 5. 수정 =======> UPDATE
	// 6. 삭제 =======> DELETE
	// 7. 찾기 =======> LIKE
	// JOIN
}
