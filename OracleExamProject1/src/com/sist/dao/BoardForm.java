package com.sist.dao;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
public class BoardForm extends JPanel{
   JTable table;
   DefaultTableModel model;
   JButton b1,b2,b3,b4,b5;
   JLabel pageLa;
   JLabel titleLa;
   JComboBox box;
   JTextField tf;
   public BoardForm()
   {
	   String[] col={"번호","제목","이름","작성일","조회수"};
	   String[][] row=new String[0][5];
	   model=new DefaultTableModel(row,col)
	   {

		@Override
		public boolean isCellEditable(int row, int column) {
			// TODO Auto-generated method stub
			return false;
		}
		   
	   };
	   table=new JTable(model);
	   JScrollPane js=new JScrollPane(table);
	   
	   b1=new JButton("이전");
	   b2=new JButton("다음");
	   b3=new JButton("찾기");
	   b4=new JButton("목록");
	   b5=new JButton("새글");
	   
	   titleLa=new JLabel("자유게시판",JLabel.CENTER);
	   titleLa.setFont(new Font("궁서체",Font.BOLD,40));
	   
	   pageLa=new JLabel("0 page / 0 pages");
	   
	   box=new JComboBox();
	   box.addItem("name");
	   box.addItem("subject");
	   box.addItem("content");
	   
	   setLayout(null);
	   titleLa.setBounds(10, 15, 760, 50);
	   b5.setBounds(10, 70, 100, 30);
	   js.setBounds(10, 120, 760, 420);
	   add(titleLa);
	   add(js);
	   add(b5);
	   
	   tf=new JTextField(10);
	   JPanel p1=new JPanel();
	   p1.add(box);
	   p1.add(tf);
	   p1.add(b3);
	   p1.add(b4);
	   
	   JPanel p2=new JPanel();
	   p2.add(b1);
	   p2.add(pageLa);
	   p2.add(b2);
	  
	   
	   p1.setBounds(10, 555,380, 35);
	   add(p1);
	   
	   p2.setBounds(400, 555, 400, 35);
	   add(p2);
   }
}






