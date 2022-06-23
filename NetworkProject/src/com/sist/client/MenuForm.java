package com.sist.client;

import javax.swing.*;
import java.awt.*;
public class MenuForm extends JPanel {
	JButton homeBtn, musicBtn, chatBtn, exitBtn, newsBtn;
	// 초기화
	public MenuForm() {
		setLayout(new GridLayout(5, 1, 10, 10));
		homeBtn = new JButton("홈");
		// <input type = button value = "홈"> HTML태그
		musicBtn = new JButton("뮤직");
		chatBtn = new JButton("채팅");
		exitBtn = new JButton("종료");
		newsBtn = new JButton("뉴스"); // JSON => 네이버뉴스 (openApi)
		
		// 배치
		add(homeBtn);
		add(musicBtn);
		add(chatBtn);
		add(newsBtn);
		add(exitBtn);
	}
}
