package com.sist.client;

import javax.swing.*;

import com.sist.data.SeoulLocationVO;
import com.sist.data.SeoulSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MenuForm extends JPanel {
	public JButton homeBtn, locBtn, natureBtn, chatBtn, newsBtn, exitBtn;

	int totalpage = 0;
	int curpage = 1;
	// 초기화
	public MenuForm() {
		setLayout(new GridLayout(6, 1, 10, 10));
		homeBtn = new JButton("홈");
		// <input type = button value = "홈"> HTML태그
		locBtn = new JButton("명소");
		natureBtn = new JButton("자연");
		chatBtn = new JButton("채팅");
		exitBtn = new JButton("종료");
		newsBtn = new JButton("뉴스"); // JSON => 네이버뉴스 (openApi)
		
		// 배치
		add(homeBtn);
		add(locBtn);
		add(natureBtn);
		add(chatBtn);
		add(newsBtn);
		add(exitBtn);
		
	}

}
