package com.sist.main;
import com.sist.client.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NetworkMain extends JFrame {
	MenuForm menu = new MenuForm();
	ControllerPanel cp = new ControllerPanel();
	WaitForm wr = new WaitForm();
	
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
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			
		}
		
		new NetworkMain();

	}

}
