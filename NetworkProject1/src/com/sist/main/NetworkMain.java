package com.sist.main;
import com.sist.client.*;
import com.sist.common.Function;
import com.sist.data.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class NetworkMain extends JFrame implements ActionListener, Runnable{
	ControllerPanel cp = new ControllerPanel();
//	MenuForm menu = new MenuForm();
	JMenuItem loc = new JMenuItem("명소");
	JMenuItem nat = new JMenuItem("관광지");
	JMenuItem locfind = new JMenuItem("명소 검색");
	JMenuItem natfind = new JMenuItem("관광지 검색");
	JMenuItem chat = new JMenuItem("채팅");
	JMenuItem news = new JMenuItem("뉴스");
	JMenuItem exit = new JMenuItem("종료");
	
	WaitForm wr = new WaitForm();
	LoginForm lf = new LoginForm();
	SendForm sf=new SendForm();
    RecvForm rf=new RecvForm();
	
	int curpage = 1;
	int totalpage = 0;
	int totalpage2 = 0;
	Socket s;
	BufferedReader in;
	OutputStream out;
	public NetworkMain() {
		setTitle("네트워크 명소 프로그램");
		setLayout(null); // 사용자 정의(직접 배치
		JMenuBar bar =new JMenuBar();
		JMenu menu = new JMenu("메뉴");
		menu.add(loc);
		menu.addSeparator();
		menu.add(nat);
		menu.addSeparator();
		menu.add(locfind);
		menu.addSeparator();
		menu.add(natfind);
		menu.addSeparator();
		menu.add(chat);
		menu.addSeparator();
		menu.add(news);
		menu.addSeparator();
		menu.add(exit);
		menu.addSeparator();
		
		bar.add(menu);
		setJMenuBar(bar);
//		menu.setBounds(10, 15, 100, 350);
//		add(menu);
		
		cp.setBounds(10, 15, 960, 820);
		add(cp);
		
		wr.setBounds(980, 15, 250, 700);
		add(wr);
		
		
		setSize(1250, 900);
//		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		

		cp.hf.b1.addActionListener(this); // 이전
		cp.hf.b2.addActionListener(this); // 다음
		
		cp.ntf.b1.addActionListener(this); // 이전
		cp.ntf.b2.addActionListener(this); // 다음
		
		totalpage = SeoulSystem.seoulLocationTotalPage();
		cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
		
		totalpage2 = SeoulSystem.seoulNatureTotalPage();
		cp.ntf.pagLa.setText(curpage + "page / " + totalpage2 + "pages");

		chat.addActionListener(this);
		exit.addActionListener(this);
		loc.addActionListener(this);
		locfind.addActionListener(this);
		natfind.addActionListener(this);
		nat.addActionListener(this);
		news.addActionListener(this);
		
		cp.lff.btn.addActionListener(this);
		cp.nff.btn.addActionListener(this);
		
		
		//로그인처리
		lf.b1.addActionListener(this);
		lf.b2.addActionListener(this);
		
		//채팅
		cp.cf.tf.addActionListener(this);
		cp.cf.b1.addActionListener(this);
    	cp.cf.b2.addActionListener(this);
    	
    	cp.nf.b.addActionListener(this);
	}
	
	public static Image getImage(ImageIcon ii, int width, int height) {
		Image dimg = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return dimg;
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			
		}
		
		new NetworkMain();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cp.hf.b1) {
			if(curpage>1) {
				curpage--;
				ArrayList<SeoulLocationVO> list = cp.hf.ss.seoulLocationListData(curpage);
				cp.hf.sm.cardInit(list);
				cp.hf.sm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
		} else if(e.getSource() == cp.hf.b2) {
			if(curpage<totalpage) {
				curpage++;
				ArrayList<SeoulLocationVO> list = cp.hf.ss.seoulLocationListData(curpage);
				cp.hf.sm.cardInit(list);
				cp.hf.sm.cardPrint(list);
				cp.hf.pagLa.setText(curpage + "page / " + totalpage + "pages");
			}
		} else if(e.getSource() == cp.ntf.b1) {
			if(curpage>1) {
				curpage--;
				ArrayList<SeoulNatureVO> list = cp.ntf.ss.seoulNatureListData(curpage);
				cp.ntf.sm.cardNatureInit(list);
				cp.ntf.sm.cardNaturePrint(list);
				cp.ntf.pagLa.setText(curpage + "page / " + totalpage2 + "pages");
			}
		} else if(e.getSource() == cp.ntf.b2) {
			if(curpage<totalpage2) {
				curpage++;
				ArrayList<SeoulNatureVO> list = cp.ntf.ss.seoulNatureListData(curpage);
				cp.ntf.sm.cardNatureInit(list);
				cp.ntf.sm.cardNaturePrint(list);
				cp.ntf.pagLa.setText(curpage + "page / " + totalpage2 + "pages");
			}
		} else if(e.getSource() == chat) {
			cp.card.show(cp,"CF");
		} else if(e.getSource() == news) {
			cp.card.show(cp, "NF");
		} else if(e.getSource() == exit) {
			try
			{
				out.write((Function.END+"|\n").getBytes());
			}catch(Exception ex){}
		} else if(e.getSource() == loc) {
			cp.card.show(cp, "HF");
		} else if(e.getSource() == nat) {
			cp.card.show(cp, "NTF");
		} else if(e.getSource()==cp.cf.b1) {
			sf.setVisible(true);
		} else if(e.getSource()==cp.cf.b2) {
			JOptionPane.showMessageDialog(this, "이름 : 홍길동\n성별:남자\nID:hong");
		}  else if(e.getSource() == locfind) {
			cp.card.show(cp, "LFF"); // 검색
		} else if(e.getSource() == natfind) {
			cp.card.show(cp, "NFF"); // 검색
		} else if(e.getSource() == cp.lff.btn) {
			String fd = cp.lff.tf.getText();
			if(fd.length()<1) {
				JOptionPane.showMessageDialog(this, "검색어를 입력하세요");
				cp.lff.tf.requestFocus();
				return;
			}
			ArrayList<SeoulLocationVO> flList = SeoulSystem.locationFind(fd);
			
			for(int i=cp.lff.model.getRowCount()-1; i>=0; i--) {
				cp.lff.model.removeRow(i);
			}
			try {
				for(SeoulLocationVO l : flList) {
					URL url = new URL(l.getPoster());
					Image img = getImage(new ImageIcon(url), 30, 30);
					Object[] data = {l.getNo(), new ImageIcon(img), l.getTitle(), l.getMsg()};
					cp.lff.model.addRow(data);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		} else if(e.getSource() == cp.nff.btn) {
			String fd = cp.nff.tf.getText();
			if(fd.length()<1) {
				JOptionPane.showMessageDialog(this, "검색어를 입력하세요");
				cp.nff.tf.requestFocus();
				return;
			}
			ArrayList<SeoulNatureVO> flList = SeoulSystem.natureFind(fd);
			
			for(int i=cp.nff.model.getRowCount()-1; i>=0; i--) {
				cp.nff.model.removeRow(i);
			}
			try {
				for(SeoulNatureVO l : flList) {
					URL url = new URL(l.getPoster());
					Image img = getImage(new ImageIcon(url), 30, 30);
					Object[] data = {l.getNo(), new ImageIcon(img), l.getTitle(), l.getMsg()};
					cp.nff.model.addRow(data);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		} else if(e.getSource() == cp.cf.tf) { //채팅
			String msg = cp.cf.tf.getText();
			if(msg.length()<1) {
				return;
			}
			try {
				out.write((Function.CHAT + "|" + msg + "\n").getBytes());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			cp.cf.tf.setText("");
		} else if(e.getSource() == lf.b1) { // 로그인 처리
			String id = lf.tf1.getText();
			if(id.length()<1) {
				JOptionPane.showMessageDialog(this, "ID를 입력하세요");
				lf.tf1.requestFocus();
				return;
			}
			String name = lf.tf2.getText();
			if(name.length()<1) {
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
				lf.tf2.requestFocus();
				return;
			}
			String sex = "";
			if(lf.rb1.isSelected()) {
				sex = "남자";
			} else {
				sex = "여자";
			}
			try {
				s = new Socket("localhost", 3355);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = s.getOutputStream();
				out.write((Function.LOGIN + "|" + id + "|" + name + "|" + sex + "\n").getBytes());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			new Thread(this).start();
		} else if(e.getSource() == lf.b2) {
			System.exit(0);
		} else if(e.getSource() == cp.cf.tf) {
			String msg = cp.cf.tf.getText();
			if(msg.length()<1) {
				return;
			}
			try {
				out.write((Function.CHAT + "|" + msg + "\n").getBytes());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			cp.cf.tf.setText("");
		} else if(e.getSource()==cp.nf.b) {
			String ss=cp.nf.tf.getText();
			if(ss.length()<1)
			{
				cp.nf.tf.requestFocus();
				return;
			}
			cp.nf.findData(ss);
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			while(true)
			{
				// 서버에서 보내주는 데이터를 받는다 
				String msg=in.readLine();
//				System.out.println(msg);
				StringTokenizer st=new StringTokenizer(msg,"|");
				int protocol=Integer.parseInt(st.nextToken());
				switch(protocol)
				{
					case Function.LOGIN:
					{
						String[] data= {
							st.nextToken(), // ID
							st.nextToken(), // Name
							st.nextToken()  // Sex
						};
						cp.cf.model.addRow(data);
					}
				    break;
					case Function.MYLOG:
					{
						lf.setVisible(false);//로그인은 종료
						setVisible(true);// 메인창 
					}
					break;
					case Function.CHAT:
					{
					      cp.cf.ta.append(st.nextToken()+"\n");	
					}
					break;
					case Function.SEND:
						break;
					case Function.END:// 남아 있는 사람 처리
					{
					    String myId=st.nextToken();
					    for(int i=0;i<cp.cf.model.getRowCount();i++)
					    {
					    	String you=cp.cf.model.getValueAt(i, 0).toString();
					    	if(myId.equals(you))
					    	{
					    		cp.cf.model.removeRow(i);
					    		break;
					    	}
					    }
					}
				    break;
					case Function.MYEND:
					{
					    System.exit(0);	
					}
					break;
				}
			}
		}catch(Exception ex){}
	}
	
}
