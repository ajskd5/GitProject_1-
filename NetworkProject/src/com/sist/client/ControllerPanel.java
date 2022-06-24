package com.sist.client;

import java.awt.*;
import javax.swing.*;
//화면 이동
public class ControllerPanel extends JPanel {
	public CardLayout card = new CardLayout();
	public HomeForm hf;
	public DetailForm df;
	public MusicFindFrom mf = new MusicFindFrom();
	public ChatForm cf = new ChatForm();
	public NewsForm nf = new NewsForm();
//	public LoginForm lf = new LoginForm();
	
	public ControllerPanel() {
		hf = new HomeForm(this);
		df = new DetailForm(this);
		setLayout(card);
		//순서대로 띄워줌
//		add("LF", lf);
		add("HF", hf);
		add("DF", df);
		add("MF", mf);
		add("CF", cf);
		add("NF", nf);
	}
}
