package com.sist.client;

import java.awt.*;
import javax.swing.*;

public class ControllerPanel extends JPanel {
		public CardLayout card = new CardLayout();
		public HomeForm hf;
		public DetailForm df;
		public NatureForm ntf;
		public LocationFindForm lff = new LocationFindForm();
		public NatureFindForm nff = new NatureFindForm();
		public ChatForm cf = new ChatForm();
		public NewsForm nf=new NewsForm();
		
		public ControllerPanel() {
			hf = new HomeForm(this);
			df = new DetailForm(this);
			ntf = new NatureForm(this);
			setLayout(card);
			add("HF", hf);
			add("DF", df);
			add("LFF", lff);
			add("NFF", nff);
			add("CF", cf);
			add("NTF", ntf);
			add("NF", nf);
		}
}
