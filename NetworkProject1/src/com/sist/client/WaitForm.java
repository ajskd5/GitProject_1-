package com.sist.client;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import com.sist.data.*;
import com.sist.main.NetworkMain;
// Network 연결
public class WaitForm extends JPanel {
	JTable table;
	DefaultTableModel model;
	JLabel la=new JLabel("추천 명소 Top10",JLabel.CENTER);
	public WaitForm() {
		String[] col = {"", "추천 명소"};
		String[][] row = new String[0][2];
		model = new DefaultTableModel(row, col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};
		table = new JTable(model);
		table.setRowHeight(50);
		JScrollPane js1 = new JScrollPane(table);
	
		// 배치
		setLayout(null); // 배치 사용자 정의

		la.setBounds(0, 65, 250, 30);
    	js1.setBounds(0, 100 , 250, 500);
    	add(js1);
    	add(la);
    	try {
			ArrayList<SeoulLocationVO> list = SeoulSystem.seoulTop10();
			for(SeoulLocationVO l : list) {
				URL url = new URL(l.getPoster());
				Image img=NetworkMain.getImage(
    					new ImageIcon(url), 50, 45);
    			Object[] data= {
    				new ImageIcon(img),
    				l.getTitle()
    			};
    			model.addRow(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
}
