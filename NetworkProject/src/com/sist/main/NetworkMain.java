package com.sist.main;
import com.sist.client.*;
import com.sist.data.Music;
import com.sist.data.MusicSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class NetworkMain extends JFrame implements ActionListener{
	MenuForm menu = new MenuForm();
	ControllerPanel cp = new ControllerPanel();
	WaitForm wr = new WaitForm();
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
		setVisible(true);
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
		if(e.getSource() == cp.hf.b1) {
			if(curpage>1) {
				curpage--;
				ArrayList<Music> list = cp.hf.ms.musicListData(cno, curpage);
				cp.hf.mm.cardInit(list);
				cp.hf.mm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
		} else if(e.getSource() == cp.hf.b2) {
			if(curpage<totalpage) {
				curpage++;
				ArrayList<Music> list = cp.hf.ms.musicListData(cno, curpage);
				cp.hf.mm.cardInit(list);
				cp.hf.mm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
		}
		
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
