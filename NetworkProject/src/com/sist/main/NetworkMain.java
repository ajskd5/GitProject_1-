package com.sist.main;
import com.sist.client.*;
import com.sist.common.Function;
import com.sist.data.Music;
import com.sist.data.MusicSystem;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class NetworkMain extends JFrame implements ActionListener, Runnable{
	MenuForm menu = new MenuForm();
	ControllerPanel cp = new ControllerPanel();
	WaitForm wr = new WaitForm();
	LoginForm lf = new LoginForm();
	int curpage = 1;
	int totalpage = 0;
	int cno = 1;
	//서버 관련 클래스
	Socket s;
	BufferedReader in; // 쓰레드
	OutputStream out; // 일반유저
	// 서버 연결 시점 => 로그인 버튼
	
	public NetworkMain() {
		setTitle("네트워크 뮤직 프로그램");
		setLayout(null); // 사용자 정의(직접 배치)
		menu.setBounds(10, 15, 100, 350);
		add(menu);
		
		cp.setBounds(120, 15, 850, 820);
		add(cp);
		
		wr.setBounds(980, 15, 250, 700);
		add(wr);
		
		
		setSize(1250, 900);
//		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		// 이벤트 등록
		for(int i=0; i<cp.hf.m.length; i++) {
			cp.hf.m[i].addActionListener(this);
		}
		cp.hf.b1.addActionListener(this); // 이전
		cp.hf.b2.addActionListener(this); // 다음
		
//		for(int i=0; i<cp.hf.mm.musics.length; i++) {
//			cp.hf.mm.musics[i].addMouseListener(this);
//		}
		
		totalpage = MusicSystem.musicTotalPage();
		cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
		
		// 1. 메뉴 클릭
		menu.chatBtn.addActionListener(this);
		menu.exitBtn.addActionListener(this);
		menu.homeBtn.addActionListener(this);
		menu.newsBtn.addActionListener(this);
		menu.musicBtn.addActionListener(this);
		
		//검색 버튼
		cp.mf.btn.addActionListener(this);
		
		//로그인 처리 로그인/취소
		lf.b1.addActionListener(this);
		lf.b2.addActionListener(this);
		
		cp.cf.tf.addActionListener(this);//채팅
	}
	
	// 이미지 축소 => 화면에 맞게 들어가게
	public static Image getImage(ImageIcon ii, int width, int height) {
		Image dimg = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return dimg;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			
		}
		
		new NetworkMain();

	}
	
	// 버튼 클릭시 처리 => 구현 안 됨 => 클릭시 자동 시스템(JVM)에 의해 자동 호출
	@Override
	public void actionPerformed(ActionEvent e) { 
		// 이전
		if(e.getSource() == cp.hf.b1) {
			if(curpage>1) {
				curpage--;
				ArrayList<Music> list = cp.hf.ms.musicListData(cno, curpage);
				cp.hf.mm.cardInit(list);
				cp.hf.mm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
		} else if(e.getSource() == cp.hf.b2) { // 다음
			if(curpage<totalpage) {
				curpage++;
				ArrayList<Music> list = cp.hf.ms.musicListData(cno, curpage);
				cp.hf.mm.cardInit(list);
				cp.hf.mm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
			
		}
		// 로그인처리
		else if(e.getSource() == lf.b1) {
			// id
			// name => 반드시 입력 => 유효성 검사
			// => 기본 (보안) => Spring Security
			String id = lf.tf1.getText();
			if(id.length()<1) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				lf.tf1.requestFocus();
				return;
			}
			String name = lf.tf2.getText();
			if(name.length()<1) {
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
				lf.tf2.requestFocus();
				return;
			}
			
			String sex = "";
			if(lf.rb1.isSelected()) { //남자 버튼 체크
				sex = "남자";
			} else {
				sex = "여자";
			}
			// 서버 연결
			try {
				// TCP
				s = new Socket("localhost", 3355);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				// 서버가 보내준 데이터가 저장된 위치(inputStream)
				out = s.getOutputStream(); // 보내는 위치
				
				//로그인 요청
				out.write((Function.LOGIN + "|" + id + "|" + name + "|" + sex + "\n").getBytes());
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			//서버에서 들어오는 데이터 읽어서 출력
			new Thread(this).start();
			
		} else if(e.getSource() == lf.b2) {
			System.exit(0);
		}
		// 메뉴
		else if(e.getSource() == menu.chatBtn){
			cp.card.show(cp, "CF");
		} else if(e.getSource() == menu.exitBtn) {
			try {
				out.write((Function.END + "|\n").getBytes());
			} catch (Exception e2) {
				// TODO: handle exception
			}
		} else if(e.getSource() == menu.newsBtn) {
			cp.card.show(cp, "NF");
		} else if(e.getSource() == menu.musicBtn) {
			cp.card.show(cp, "MF");
		} else if(e.getSource() == menu.homeBtn) {
			cp.card.show(cp, "HF");
		} 
		//검색버튼 클릭
		else if(e.getSource() == cp.mf.btn) {
			// 1. 입력값 읽어 옴
			String fd = cp.mf.tf.getText();
				// 입력이 안 된 상태
			if(fd.length()<1) {
				JOptionPane.showMessageDialog(this, "검색어를 입력하세요");
				cp.mf.tf.requestFocus();
				return;
			}
			ArrayList<Music> fList = MusicSystem.musicFind(fd);
			
			//출력된 내용 지우고 다시 검색
			for(int i=cp.mf.model.getRowCount()-1; i>=0; i--) {
				cp.mf.model.removeRow(i);
			}//=> 밑에서부터 지움
			
			try {
				for(Music m : fList) {
					URL url = new URL("http:" + m.getPoster());
					Image img = getImage(new ImageIcon(url), 30, 30);
					Object[] data = {m.getMno(), new  ImageIcon(img), m.getTitle(), m.getSinger()};
					cp.mf.model.addRow(data);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		} else if(e.getSource() == cp.cf.tf) {
			// 1. 채팅 문자열 읽기
			String msg = cp.cf.tf.getText();
			if(msg.length()<1) {
				return;
			}
			try {
				out.write((Function.CHAT + "|" + msg + "\n").getBytes());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			cp.cf.tf.setText("");
		}
		
		//메뉴 버튼
		for(int i=0; i<cp.hf.m.length; i++) {
			if(e.getSource()==cp.hf.m[i]) {
				curpage = 1;
				cno = i + 1;
				ArrayList<Music> list = cp.hf.ms.musicListData(cno, curpage);
				cp.hf.mm.cardInit(list);
				cp.hf.mm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
		}
		
	}
	// 서버로부터 실시간 데이터 읽어옴
	@Override
	public void run() {
		try {
			while(true) {
				//서버에서 보내주는 데이터 받기
				String msg = in.readLine();
				System.out.println(msg);
				StringTokenizer st = new StringTokenizer(msg, "|");
				int protocol = Integer.parseInt(st.nextToken());
				switch(protocol) {
				case Function.LOGIN:{
					String[] data = {st.nextToken(), st.nextToken(), st.nextToken()};
					cp.cf.model.addRow(data);
				}
				break;
				case Function.MYLOG:{
					lf.setVisible(false); // 로그인창 사라짐
					setVisible(true); // 메인창
				}
				break;
				case Function.CHAT:{
					cp.cf.ta.append(st.nextToken() + "\n");
				}
				break;
				case Function.SEND:
					break;
				case Function.END:{ // 남아있는 사람 처리
					String myId = st.nextToken();
					for(int i=0; i<cp.cf.model.getRowCount(); i++) {
						String you = cp.cf.model.getValueAt(i, 0).toString();
						if(myId.equals(you)) {
							cp.cf.model.removeRow(i);
							break;
						}
					}
				}
				break;
				case Function.MYEND:{
					System.exit(0);
				}
				break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
