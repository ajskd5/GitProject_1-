package com.sist.dao;
import java.awt.*;
import javax.swing.*;
public class DetailForm extends JPanel{
	JLabel titleLa;
	JLabel la1,la2,la3,la4,la5;
	JTextField tf1,tf2,tf3,tf4,tf5;
	JTextArea ta;
	JButton b1,b2,b3;
	public DetailForm()
	{
		titleLa=new JLabel("내용보기",JLabel.CENTER);
    	la1=new JLabel("번호");
    	la2=new JLabel("작성일");
    	la3=new JLabel("이름");
    	la4=new JLabel("조회수");
    	la5=new JLabel("제목");
    	b1=new JButton("수정");
    	b2=new JButton("삭제");
    	b3=new JButton("목록");
    	
    	ta=new JTextArea();
    	JScrollPane js=new JScrollPane(ta);
    	tf1=new JTextField();
    	tf2=new JTextField();
    	tf3=new JTextField();
    	tf4=new JTextField();
    	tf5=new JTextField();
    	
    	tf1.setEnabled(false);
    	tf2.setEnabled(false);
    	tf3.setEnabled(false);
    	tf4.setEnabled(false);
    	tf5.setEnabled(false);
    	
    	
    	setLayout(null);
    	
    	titleLa.setFont(new Font("궁서체",Font.BOLD,40));
    	titleLa.setBounds(10, 15, 760, 50);
    	add(titleLa);
    	
    	la1.setBounds(10, 70, 50, 30);
    	tf1.setBounds(65, 70, 150, 30);
    	add(la1);add(tf1);
    	la2.setBounds(350, 70, 50, 30);
    	tf2.setBounds(405, 70, 150, 30);
    	add(la2);add(tf2);
    	
    	la3.setBounds(10, 115, 50, 30);
    	tf3.setBounds(65, 115, 150, 30);
    	add(la3);add(tf3);
    	la4.setBounds(350, 115, 50, 30);
    	tf4.setBounds(405, 115, 150, 30);
    	add(la4);add(tf4);
    	
    	la5.setBounds(10, 150, 50, 30);
    	tf5.setBounds(65, 150, 490, 30);
    	add(la5);add(tf5);
    	js.setEnabled(false);
    	js.setBounds(10, 185, 545, 300);
    	add(js);
    	
    	JPanel p=new JPanel();
    	p.add(b1);
    	p.add(b2);
    	p.add(b3);
    	
    	p.setBounds(10, 490, 545, 35);
    	add(p);
    	
	}
}
