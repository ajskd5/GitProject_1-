package com.sist.common;
// 공유 -> 서버 거쳐서 작업 => 서버에서 지시
// 서버 = master, 클라이언트 = 슬레이브
//기능별 분리 => 서버가 처리 (숫자) => 내부 프로토콜 (약속)
// 웹 => 구분이 이미 되어 있음
public class Function {
	// 로그인 처리
	public static final int LOGIN = 100; //로그인된 사람들
	public static final int MYLOG = 110; // 로그인 하는 사람
	
	public static final int CHAT = 200; // 채팅
	public static final int SEND = 300; // 쪽지 보내기
	public static final int END = 900;  // 남이있는 사람
	public static final int MYEND = 910; // 나가는 사람
	
}
