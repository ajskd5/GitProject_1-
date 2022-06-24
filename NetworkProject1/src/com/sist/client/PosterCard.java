package com.sist.client;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

import com.sist.data.*;
import com.sist.main.NetworkMain;

public class PosterCard extends JPanel {
	JLabel poster = new JLabel();
	JLabel title = new JLabel();
	JLabel msg = new JLabel();
	public PosterCard(SeoulLocationVO l) {
		setLayout(null);

		poster.setBounds(5,  5, 168, 170);
//		poster.setOpaque(true);
//		poster.setBackground(Color.pink);
		try {
			URL url = new URL(l.getPoster());
			Image img = NetworkMain.getImage(new ImageIcon(url), 168, 170);
			poster.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		title.setBounds(5, 180, 168, 30);
//		title.setOpaque(true);
//		title.setBackground(Color.orange);
		title.setText(l.getTitle());
		
		msg.setBounds(5,  215, 168, 30);
//		msg.setOpaque(true);
//		msg.setBackground(Color.cyan);
		msg.setText(l.getMsg());
		
		add(poster);
		add(title);
		add(msg);
	}
	public PosterCard(SeoulNatureVO l) {
		setLayout(null);

		poster.setBounds(5,  5, 168, 170);
//		poster.setOpaque(true);
//		poster.setBackground(Color.pink);
		try {
			URL url = new URL(l.getPoster());
			Image img = NetworkMain.getImage(new ImageIcon(url), 168, 170);
			poster.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		title.setBounds(5, 180, 168, 30);
//		title.setOpaque(true);
//		title.setBackground(Color.orange);
		title.setText(l.getTitle());
		
		msg.setBounds(5,  215, 168, 30);
//		msg.setOpaque(true);
//		msg.setBackground(Color.cyan);
		msg.setText(l.getMsg());
		
		add(poster);
		add(title);
		add(msg);
	}
	
}
