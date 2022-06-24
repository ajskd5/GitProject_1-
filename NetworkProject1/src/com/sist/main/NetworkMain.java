package com.sist.main;
import com.sist.client.*;
import com.sist.data.SeoulLocationVO;
import com.sist.data.SeoulSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class NetworkMain extends JFrame implements ActionListener{
	ControllerPanel cp = new ControllerPanel();
	MenuForm menu = new MenuForm();
	WaitForm wr = new WaitForm();
	int curpage = 1;
	int totalpage = 0;

	public NetworkMain() {
		setTitle("네트워크 명소 프로그램");
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
		

		cp.hf.b1.addActionListener(this); // 이전
		cp.hf.b2.addActionListener(this); // 다음
		
		totalpage = SeoulSystem.seoulLocationTotalPage();
		cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
	}
	
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cp.hf.b1) {
			if(curpage>1) {
				curpage--;
				ArrayList<SeoulLocationVO> list = cp.hf.ss.seoulLocationListData(curpage);
				cp.hf.sm.cardInit(list);
				cp.hf.sm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
		} else if(e.getSource() == cp.hf.b2) {
			if(curpage<totalpage) {
				curpage++;
				ArrayList<SeoulLocationVO> list = cp.hf.ss.seoulLocationListData(curpage);
				cp.hf.sm.cardInit(list);
				cp.hf.sm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
		}
		
	}
}
