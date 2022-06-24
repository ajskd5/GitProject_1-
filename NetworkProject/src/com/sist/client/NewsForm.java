package com.sist.client;
import java.awt.*;
import java.util.ArrayList;
import com.sist.data.*;

import javax.swing.*;
public class NewsForm extends JPanel {
	public NewsCard[] nc = new NewsCard[10];
	JLabel la = new JLabel("실시간 네이버 뉴스", JLabel.CENTER);
	String data = "뮤직";
	public NewsForm() {
		ArrayList<News> list = NaverNewsMain.newsAllData();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(10, 1, 5, 10));
		for(int i=0; i<nc.length; i++) {
			nc[i] = new NewsCard();
			nc[i].la.setText(list.get(i).getTitle());
			nc[i].ta.setText(list.get(i).getDescription());
			add(nc[i]);
		}
		setLayout(new BorderLayout());
		la.setFont(new Font("궁서체", Font.BOLD, 45));
		add("North", la);
		add("Center", p);
	}
}
