package com.sist.client;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import com.sist.data.Music;
import com.sist.data.MusicSystem;
import com.sist.main.NetworkMain;
// Network 연결
public class WaitForm extends JPanel {
	JTable table;
	DefaultTableModel model;

	
	public WaitForm() {
		String[] col = {"", "Title"};
		Object[][] row = new String[0][2];
		
		//익명의 클래스 => 상속없이 오버라이딩
		model = new DefaultTableModel(row, col) {
			//편집 방지
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			//이미지 출력
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
		
		js1.setBounds(0, 100, 250, 500);
		add(js1);

		try {
			ArrayList<Music> list = MusicSystem.musicTop10();
			for(Music m : list) {
				URL url = new URL("http:" + m.getPoster());
				Image img = NetworkMain.getImage(new ImageIcon(url), 50, 50);
				Object[] data = {new ImageIcon(img), m.getTitle()};
				model.addRow(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
