package com.sist.client;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import com.sist.data.*;
import com.sist.main.NetworkMain;

import javax.swing.*;

public class SeoulManager extends JPanel implements MouseListener{
	public PosterCard[] seouls = new PosterCard[15];
	public JPanel pan = new JPanel();
	public ControllerPanel cp;
	public int cno;
	public SeoulManager(ControllerPanel cp) {
		this.cp = cp;
	}
	
	public void cardPrint(ArrayList<SeoulLocationVO> list) {
		setLayout(null);
//		JPanel p = new JPanel();
		pan.setLayout(new GridLayout(3, 5));
		int i = 0;
		cno = 1;
		for(SeoulLocationVO l : list) {
			seouls[i] = new PosterCard(l);
			pan.add(seouls[i]);
			seouls[i].addMouseListener(this);
			i++;
		}
		
		pan.setBounds(10, 35, 840, 730);
		add(pan);
	}
	
	public void cardInit(ArrayList<SeoulLocationVO> list) {

		for(int i=0; i<list.size(); i++) {
			seouls[i].poster.setIcon(null);
			seouls[i].title.setText("");
			seouls[i].msg.setText("");
		}
		pan.removeAll();
		pan.validate();
	}
	
	public void cardNaturePrint(ArrayList<SeoulNatureVO> list2) {
		setLayout(null);
//		JPanel p = new JPanel();
		pan.setLayout(new GridLayout(3, 5));
		int i = 0;
		cno = 2;
		for(SeoulNatureVO l : list2) {
			seouls[i] = new PosterCard(l);
			pan.add(seouls[i]);
			seouls[i].addMouseListener(this);
			i++;
		}
		
		pan.setBounds(10, 35, 840, 730);
		add(pan);
	}
	public void cardNatureInit(ArrayList<SeoulNatureVO> list2) {

		for(int i=0; i<list2.size(); i++) {
			seouls[i].poster.setIcon(null);
			seouls[i].title.setText("");
			seouls[i].msg.setText("");
		}
		pan.removeAll();
		pan.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0; i<seouls.length; i++) {
			if(e.getSource() == seouls[i]) {
				String title = seouls[i].title.getText();
				for(int j=0; j<SeoulSystem.getList().size(); j++) {
					if(cno == 1) {
						SeoulLocationVO l = SeoulSystem.getList().get(j);
						if(l.getTitle().equals(title)) {
							cp.df.title.setText("명소 : " + l.getTitle());
							cp.df.msg.setText("설명 : " + l.getMsg());
							cp.df.address.setText("주소 : " + l.getAddress());
							
							try {
								URL url = new URL(l.getPoster());
								Image img = NetworkMain.getImage(new ImageIcon(url), 550, 450);
								cp.df.posterLa.setIcon(new ImageIcon(img));
							} catch (Exception ex) {
								// TODO: handle exception
							}
							break;
						}
					} else if(cno == 2){
						SeoulNatureVO l = SeoulSystem.getList2().get(j);
						if(l.getTitle().equals(title)) {
							cp.df.title.setText("관광지 : " + l.getTitle());
							cp.df.msg.setText("설명 : " + l.getMsg());
							cp.df.address.setText("주소 : " + l.getAddress());
							
							try {
								URL url = new URL(l.getPoster());
								Image img = NetworkMain.getImage(new ImageIcon(url), 550, 450);
								cp.df.posterLa.setIcon(new ImageIcon(img));
							} catch (Exception ex) {
								// TODO: handle exception
							}
							break;
						}
					}
					
					//원래 여기 하나
				}
				cp.card.show(cp, "DF");
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
