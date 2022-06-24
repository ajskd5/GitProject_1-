package com.sist.server;
import java.io.*; // 통신 => 메모리 / 파일 / 네트워크
import java.util.*; // StringTokenizer

import com.sist.common.Function;

import java.net.*; // 네트워크
/*
 *  ===================
 *  	접속 담당 => 교환 소켓 => 1개
 *  	접속시 클라이언트의 정보 저장(IP, PORT)
 *  ===================
 *  	통신 담당 => 통신 소켓 => 클라이언트 갯수만큼 (쓰레드)
 *  ===================
 *  	=> 클라이언트 정보 공유 -> 내부클래스 (멤버클래스)
 *  class Server{
 *  	Vector
 *   	class Client{
 *   	}
 *   }
 */
public class Server implements Runnable {
	private ServerSocket ss; //접속 담당 소켓
	private final int PORT = 3355; // 0~65535 => (0~1023)사용중
	private Vector<Client> waitVc = new Vector<Client>(); //Server.Client 인데 클래스 안에 있어서 안써도 됨 (다른 클래스면 써야 함)
	
	// 서버 구동
	public Server() {
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server Start...");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void run() {
		// 클라이언트 접속시마다 처리
		try {
			while(true) {
				Socket s = ss.accept(); // 클라이언트가 접속시에 호출
				//클라이언트에 대한 정보 (IP, PORT => Socket)
				// s => Thread 에 전송 후 통신이 가능하게 만듦
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		
		
	}

	class Client extends Thread{
		String id, name, sex;
		Socket s; // 클라이언트 접속 정보
		BufferedReader in;
		OutputStream out;
		public Client(Socket s) {
			// 클라이언트와 연결
			try {
				this.s = s;
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					//바이트 받아서 캐릭터로 보냄	 한글
				out = s.getOutputStream();
				//서버 => 클라이언트 정보
				//클라이언트 => 서버 정보
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//실제 통신 => Thread가 동작 => 호출시 start() 
		public void run() {
			try {
				while(true) {
					// 클라이언트의 요청사항 받기
					String msg = in.readLine();
					
					// 어떤 요구사항인지 확인
					StringTokenizer st = new StringTokenizer(msg, "|");
					int protocol = Integer.parseInt(st.nextToken());
					
					//login.jsp => 나중에 이렇게 바뀜
					//chat.jsp
					//send.jsp
					//end.jsp
					switch(protocol) {
					case Function.LOGIN: // 로그인 처리
						break;
					case Function.CHAT: // 채팅 처리
						break;
					case Function.SEND: // 쪽지 보내기
						break;
					case Function.END: // 종료하기
						break;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		// 개인마다 보내는 메세지
		public void messageTo(String msg) {
			try {
				out.write((msg+"\n").getBytes());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		// 전체 접속자에게 전송
		public void messageAll(String msg) {
			try {
				for(Client client : waitVc) {
					client.messageTo(msg);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
}
