package com.sist.client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DetailForm extends JPanel implements ActionListener {
	public ControllerPanel cp;
	JLabel posterLa = new JLabel();
	JLabel title = new JLabel();
	JLabel singer = new JLabel();
	JLabel album = new JLabel();
	JLabel idcrement = new JLabel();
	JLabel movie = new JLabel();
	JButton b1, b2;
	public DetailForm(ControllerPanel cp) {
		b1 = new JButton("목록");
		b2 = new JButton("동영상 실행");
		
		this.cp = cp;
//		setBackground(Color.cyan);
		// 배치
		setLayout(null);
		posterLa.setBounds(10, 15, 350, 250);
		title.setBounds(365, 15, 400, 35);
		singer.setBounds(365, 55, 400, 35);
		album.setBounds(365, 95, 400, 35);
		idcrement.setBounds(365, 135, 400, 35);
		movie.setBounds(365, 175, 400, 35);
		movie.setVisible(false);
		
		JPanel p = new JPanel();
		p.add(b1); p.add(b2);
		p.setBounds(365, 220, 400, 35);
		
		add(posterLa); add(title);
		add(singer); add(album);
		add(idcrement); add(p); add(movie);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {
			cp.card.show(cp, "HF");
		} else if(e.getSource() == b2) {
			try {
				Runtime.getRuntime().exec("C:\\Program Files (x86)\\Naver\\Naver Whale\\Application\\whale.exe " + "http://youtube.com/embed/" + movie.getText());
			} catch (Exception ex) {
				// TODO: handle exception
			}
		}
		
	}
}
