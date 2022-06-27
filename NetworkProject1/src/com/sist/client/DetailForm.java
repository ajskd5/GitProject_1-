package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DetailForm extends JPanel implements ActionListener {
	public ControllerPanel cp;
	JLabel posterLa = new JLabel();
	JLabel title = new JLabel();
	JTextPane msg = new JTextPane();
	JLabel address = new JLabel();
	JButton b1 = new JButton("목록");
	public DetailForm(ControllerPanel cp) {
		this.cp = cp;
		setLayout(null);
		posterLa.setBounds(200, 15, 550, 450);
		title.setBounds(300, 470, 400, 80);
		msg.setBounds(300, 520, 400, 80);
		address.setBounds(300, 610, 400, 40);

		JPanel p = new JPanel();
		p.add(b1);
		p.setBounds(300, 700, 400, 40);
		add(posterLa); add(title);
		add(msg); add(address);
		add(p);

		b1.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {
			cp.card.show(cp, "HF");
		}
		
	}
	
}
