package com.sist.client;
import java.awt.*;
import javax.swing.*;
import com.sist.data.Music;
import com.sist.main.NetworkMain;

import java.net.*;
public class PosterCard extends JPanel {
	//3개 세트로 만들어서 처리
	JLabel poster = new JLabel();
	JLabel title = new JLabel();
	JLabel singer = new JLabel();
	
	public PosterCard(Music m) {
		setLayout(null);

		poster.setBounds(5,  5, 168, 170);
//		poster.setOpaque(true);
//		poster.setBackground(Color.pink);
		try {
			URL url = new URL("http:" + m.getPoster());
			Image img = NetworkMain.getImage(new ImageIcon(url), 168, 170);
			poster.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		title.setBounds(5, 180, 168, 30);
//		title.setOpaque(true);
//		title.setBackground(Color.orange);
		title.setText(m.getTitle());
		
		singer.setBounds(5,  215, 168, 30);
//		singer.setOpaque(true);
//		singer.setBackground(Color.cyan);
		singer.setText(m.getSinger());
		
		add(poster);
		add(title);
		add(singer);
	}
}
