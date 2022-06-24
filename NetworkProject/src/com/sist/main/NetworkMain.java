package com.sist.main;
import com.sist.client.*;
import com.sist.data.Music;
import com.sist.data.MusicSystem;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

public class NetworkMain extends JFrame implements ActionListener{
	MenuForm menu = new MenuForm();
	ControllerPanel cp = new ControllerPanel();
	WaitForm wr = new WaitForm();
	LoginForm lf = new LoginForm();
	
	int curpage = 1;
	int totalpage = 0;
	int cno = 1;
	
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		
		//
		cp.mf.btn.addActionListener(this);
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
		} else if(e.getSource() == menu.exitBtn) {
			System.exit(0);
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

}
