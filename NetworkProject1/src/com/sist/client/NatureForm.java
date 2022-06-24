package com.sist.client;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sist.data.*;

public class NatureForm extends JPanel{
	public JButton b1, b2; // 이전, 다음 버튼
//	public JButton[] l = new JButton[7]; // NetworkMain에 접근을 위해 (ActionListener)
	public SeoulManager sm;
	public SeoulSystem ss = new SeoulSystem();
	public JLabel pagLa = new JLabel("0 page / 0 pages");
//	public JButton locBtn, natBtn;
	public NatureForm(ControllerPanel cp) {
		sm = new SeoulManager(cp);
		b1 = new JButton("이전");
		b2 = new JButton("다음");
//		JPanel p = new JPanel();
		
//		locBtn = new JButton("명소");
//		natBtn = new JButton("자연");
//		p.add(locBtn); p.add(natBtn);
//		String[] title = {"Top100", "가요", "POP", "OST", "트롯", "JAZZ", "CLASSIC"};
//		for(int i=0; i<l.length; i++) {
//			l[i] = new JButton(title[i]);
//			p.add(l[i]);
//		}
		setLayout(null);
//		p.setBounds(0, 0, 840, 35);
//		add(p);
		
		sm.setBounds(0, 0, 840, 780);
		add(sm);
		
		JPanel p1 = new JPanel();
		p1.add(b1);
		p1.add(pagLa);
		p1.add(b2);
		
		p1.setBounds(0, 790, 840, 35);
		add(p1);
		
		// 시작과 동시에 데이터 받기
		ArrayList<SeoulNatureVO> list = SeoulSystem.seoulNatureListData(1);
		sm.cardNaturePrint(list);
	}
}
