package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DetailForm extends JPanel implements ActionListener {
	public ControllerPanel cp;
	JLabel posterLa = new JLabel();
	JLabel title = new JLabel();
	JLabel msg = new JLabel();
	JLabel address = new JLabel();
	JButton b1 = new JButton("목록");
	public DetailForm(ControllerPanel cp) {
		this.cp = cp;
		
		setLayout(null);
		posterLa.setBounds(10, 15, 350, 250);
		title.setBounds(365, 15, 400, 35);
		msg.setBounds(365, 55, 400, 35);
		address.setBounds(365, 95, 400, 35);

		JPanel p = new JPanel();
		p.add(b1);
		p.setBounds(365, 220, 400, 35);
		
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
