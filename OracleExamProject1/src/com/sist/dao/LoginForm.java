package com.sist.dao;
import javax.swing.*;
import java.awt.*;
public class LoginForm extends JPanel{
     JLabel la1,la2;
     JTextField tf;
     JPasswordField pf;
     JButton b1,b2;
     public LoginForm()
     {
    	 setLayout(null);
    	 la1=new JLabel("ID");
    	 la2=new JLabel("Password");
    	 tf=new JTextField();
    	 pf=new JPasswordField();
    	 b1=new JButton("로그인");
    	 b2=new JButton("취소");
    	 
    	 la1.setBounds(10, 15, 70, 30);
    	 tf.setBounds(85, 15, 150, 30);
    	 
    	 la2.setBounds(10, 50, 70, 30);
    	 pf.setBounds(85, 50, 150, 30);
    	 
    	 JPanel p=new JPanel();
    	 p.add(b1);
    	 p.add(b2);
    	 
    	 p.setBounds(10, 85, 205, 35);
    	 
    	 add(la1);add(tf);
    	 add(la2);add(pf);
    	 add(p);
     }
}
