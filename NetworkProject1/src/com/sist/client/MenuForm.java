package com.sist.client;

import javax.swing.*;
import java.awt.*;
public class MenuForm extends JPanel {
	JButton homeBtn, locBtn, natureBtn, hotelBtn, chatBtn, newsBtn, exitBtn;
	// 초기화
	public MenuForm() {
		setLayout(new GridLayout(7, 1, 10, 10));
		homeBtn = new JButton("홈");
		// <input type = button value = "홈"> HTML태그
		locBtn = new JButton("명소");
		natureBtn = new JButton("관광지");
		hotelBtn = new JButton("호텔");
		chatBtn = new JButton("채팅");
		exitBtn = new JButton("종료");
		newsBtn = new JButton("뉴스"); // JSON => 네이버뉴스 (openApi)
		
		// 배치
		add(homeBtn);
		add(locBtn);
		add(natureBtn);
		add(hotelBtn);
		add(chatBtn);
		add(newsBtn);
		add(exitBtn);
	}
}
