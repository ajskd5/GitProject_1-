package com.sist.dao;
import java.awt.*;
import javax.swing.*;
public class UpdateForm extends JPanel{
    JLabel titleLa;
    JLabel la1,la2,la3,la4;
    JButton b1,b2;
    JTextField tf1,tf2;
    JTextArea ta;
    JPasswordField pf;
    public UpdateForm()
    {
    	titleLa=new JLabel("수정하기",JLabel.CENTER);
    	la1=new JLabel("이름");
    	la2=new JLabel("제목");
    	la3=new JLabel("내용");
    	la4=new JLabel("비밀번호");
    	b1=new JButton("수정하기");
    	b2=new JButton("취소");
    	
    	ta=new JTextArea();
    	JScrollPane js=new JScrollPane(ta);
    	tf1=new JTextField();
    	tf2=new JTextField();
    	pf=new JPasswordField();
    	
    	setLayout(null);
    	
    	titleLa.setFont(new Font("궁서체",Font.BOLD,40));
    	titleLa.setBounds(10, 15, 760, 50);
    	add(titleLa);
    	
    	la1.setBounds(10, 70, 70, 30);
    	tf1.setBounds(85, 70, 150, 30);
    	add(la1);add(tf1);
    	
    	la2.setBounds(10, 105, 70, 30);
    	tf2.setBounds(85, 105, 450, 30);
    	add(la2);add(tf2);
    	
    	la3.setBounds(10, 140, 70, 30);
    	js.setBounds(85, 140, 450, 300);
    	add(la3);add(js);
    	
    	la4.setBounds(10, 445, 70, 30);
    	pf.setBounds(85, 445, 150, 30);
    	add(la4);add(pf);
    	
    	JPanel p=new JPanel();
    	p.add(b1);
    	p.add(b2);
    	
    	p.setBounds(10, 480, 530, 35);
    	add(p);
    }
}
