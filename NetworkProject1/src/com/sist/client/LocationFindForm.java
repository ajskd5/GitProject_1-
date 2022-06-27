package com.sist.client;

import javax.swing.*; // container, component => 경량
/*
 *  Container (윈도우창)
 *  	= JFrame => 일반 윈도우창
 *  	= JPanel => 단독 실행 불가능 -> (JFrame / JDialog 위에 올려야 함)
 *  		=> 변경되는 화면
 *  	= JWindow : 타이틀바가 없는 창
 *  	= JDialog : 창 위에 창 띄우기
 *  Component
 *  	= 기능 한개인 윈도우 => 단독 실행 불가능 => JFrame/JPanel
 *  	= Button
 *  		= JButton => <input type = button>>
 *  		= JRadioButton <input type = radio>
 *  		= JCheckBox <input type = checkbox>
 *  	= 입력창
 *  		= 한줄 입력 : JTextField, JPasswordField
 *  					<input type = text>
 *  		= 여러줄 입력 : JTextArea(메모장) => JTextPane(word)
 *  					<textarea>
 *  		= JLabel : 보여만 줌 (이미지) <label>
 *  	=> 목록
 *  		=> JTable, JTree, Splitpane
 *  			<table>
 */
import java.awt.*;
import javax.swing.table.*;
public class LocationFindForm extends JPanel {
	public JTextField tf;
	public JButton btn;
	public JTable table;
	public DefaultTableModel model;
	// 초기화 => 배치
	public LocationFindForm() {
		tf = new JTextField();
		btn = new JButton("검색");
		String[] col = {"순위", "", "명소", "설명"};
		// int ImageIcon String String
		Object[][] row = new String [0][4];
		model = new DefaultTableModel(row, col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 편집 방지
			}

			// 이미지 출력
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
			
		}; // 익명의 클래스 => 생성자 안에서 재정의 (오버라이딩)(상속 없이)
		
		table = new JTable(model);
		table.setRowHeight(40);
		//<tr height = 40>
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane js = new JScrollPane(table);
		
		// 배치
		setLayout(null);
		tf.setBounds(10, 15, 200, 30);
		btn.setBounds(215, 15, 100, 30);
		js.setBounds(10, 55, 800, 500);
		add(tf);
		add(btn);
		add(js);
		
	}
}
