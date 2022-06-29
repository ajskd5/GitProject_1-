package com.sist.dao;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sist.curd.BoardDAO;
import com.sist.curd.BoardVO;
public class MainForm extends JFrame implements ActionListener, MouseListener {
    CardLayout card=new CardLayout();
    LoginForm login=new LoginForm();
    BoardForm bf=new BoardForm();
    InsertForm in=new InsertForm();
    UpdateForm up=new UpdateForm();
    DetailForm df=new DetailForm();
    public MainForm()
    {
    	setLayout(card);
    	add("LOGIN",login);
    	add("DF",df);
    	add("UP",up);
    	add("IN",in);
    	add("BF",bf);
    	setSize(800, 660);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	// 등록
    	login.b1.addActionListener(this);
    	login.b2.addActionListener(this);
    	
    	// 게시판 메인
    	// 새글
    	bf.b5.addActionListener(this);
    	
    	// 글쓰기
    	in.b1.addActionListener(this);
    	// 글쓰기 취소
    	in.b2.addActionListener(this);
    	
    	// 마우스 더블 클릭 (상세보기)
    	bf.table.addMouseListener(this);
    	
    	// 상세보기
    	df.b1.addActionListener(this); // 수정
    	df.b2.addActionListener(this); // 삭제
    	df.b3.addActionListener(this); // 목록
    	
    	// 수정하기
    	up.b1.addActionListener(this);
    	up.b2.addActionListener(this);
    	
    	//검색
    	bf.b3.addActionListener(this);
    	
    	boardListData();
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new MainForm();
	}
	
	public void boardListData() {
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.boardListData();
		for(int i=bf.model.getRowCount()-1; i>=0; i--) {
			bf.model.removeRow(i);
		}
		for(BoardVO vo : list) {
			String[] data = {
					String.valueOf(vo.getNo()),
					vo.getSubject(),
					vo.getName(),
					vo.getRegdate().toString(),
					String.valueOf(vo.getHit())
			};
			bf.model.addRow(data);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login.b1) {
			String id = login.tf.getText();
			// let id = login.tf.value
			// var => let
			if(id.trim().length()<1) { // 유효성 검사 (아이디 입력 안했을 때) trim(공백문자 제거)
				JOptionPane.showMessageDialog(this, "ID를 입력하세요!!");
				login.tf.requestFocus();
				return;
			}
			String pwd = String.valueOf(login.pf.getPassword());
			if(pwd.trim().length()<1) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요!!");
				login.pf.requestFocus();
				return;
			}
			
			// 로그인 확인
			BoardDAO dao = new BoardDAO();
			String result = dao.isLogin(id, pwd);
			
			if(result.equals("NOID")) {
				JOptionPane.showMessageDialog(this, "존재하지 않는 아이디입니다");
				login.tf.setText("");
				login.pf.setText("");
				login.tf.requestFocus();
			} else if(result.equals("NOPWD")) {
				JOptionPane.showMessageDialog(this, "비밀번호가 틀립니다");
				login.pf.setText("");
				login.tf.requestFocus();
			} else { // 로그인 성공
				// 화면 이동
				card.show(getContentPane(), "BF");
				//sendRedirect("main.jsp") 웹
			}
			
		} else if(e.getSource() == login.b2) {
			System.exit(0);
		}
		// 새글로 이동
		else if(e.getSource() == bf.b5) {
			in.tf1.setText("");
			in.tf2.setText("");
			in.ta.setText("");
			in.pf.setText("");

			card.show(getContentPane(), "IN");
			in.tf1.requestFocus();
		}
		// in 글쓰기 버튼
		else if(e.getSource() == in.b1) {
			// NOT NULL이라서 유효성 검사 해야함 (근데 너무 길어서 생략)
			String name = in.tf1.getText();
			String subject = in.tf2.getText();
			String content = in.ta.getText();
			String pwd =String.valueOf(in.pf.getPassword());
			BoardVO vo = new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO dao = new BoardDAO();
			dao.boardInsert(vo);
			card.show(getContentPane(), "BF"); // 다시 목록으로 이동
			boardListData();
		} else if(e.getSource() == in.b2) { // 글쓰기 취소
			card.show(getContentPane(), "BF");
			//<input type = button value="취소" onclick = "javascript:history.back()">
		}
		//상세보기 => 목록
		else if(e.getSource() == df.b3) {
			card.show(getContentPane(), "BF");
			boardListData();
		} else if(e.getSource() == df.b1) { // 수정
			BoardDAO dao = new BoardDAO();
			String no = df.tf1.getText();
			BoardVO vo = dao.boardDetailData(Integer.parseInt(no), 2);
			up.tf1.setText(vo.getName());
			up.tf2.setText(vo.getSubject());
			up.tf3.setText(String.valueOf(vo.getNo()));
			up.ta.setText(vo.getContent());
			
			card.show(getContentPane(), "UP");
		} else if(e.getSource() == df.b2) { // 삭제
			BoardDAO dao = new BoardDAO();
			String no = df.tf1.getText();
			dao.boardDelete(Integer.parseInt(no));
			card.show(getContentPane(), "BF");
			boardListData();
		}
		// 수정하기 (수정, 취소)
		else if(e.getSource() == up.b1) {
			String name = up.tf1.getText();
			String subject = up.tf2.getText();
			String content = up.ta.getText();
			String pwd =String.valueOf(up.pf.getPassword());
			String no = up.tf3.getText();
			BoardVO vo = new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			vo.setNo(Integer.parseInt(no));
			
			// DAO연결 => 수정 요청
			BoardDAO dao = new BoardDAO();
			boolean bCheck = dao.boardUpdate(vo);
			if(bCheck == false) { // 비밀번호가 틀린 경우
				JOptionPane.showMessageDialog(this, "비밀번호가 틀립니다!!");
				up.pf.setText("");
				up.pf.requestFocus();
			} else {
				card.show(getContentPane(), "BF");
				boardListData();
			}
			
		} else if(e.getSource() == up.b2) { // 수정 취소
			card.show(getContentPane(), "DF");
		}
		// 검색
		else if(e.getSource() == bf.b3) {
			String ss = bf.tf.getText();
			if(ss.trim().length()<1) {
				JOptionPane.showMessageDialog(this, "검색어를 입력하세요");
				bf.tf.requestFocus();
				return;
			}
			// name, subject, content
			String fd = bf.box.getSelectedItem().toString();
			
			// DAO연결 => 결과값 읽기
			BoardDAO dao = new BoardDAO();
			dao.boardSearch(fd, ss);
			// 읽은 결과값 도스창에 출력
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { // 클릭 한번
		if(e.getSource() == bf.table) {
			if(e.getClickCount() == 2) { // 더블 클릭
				int row = bf.table.getSelectedRow(); // 몇번째 줄인지
				String no = bf.model.getValueAt(row, 0).toString(); //선택된 row가 몇번째 줄인지 가져옴(no값으로 가져와서 String 형변환)
				BoardDAO dao = new BoardDAO();
				BoardVO vo = dao.boardDetailData(Integer.parseInt(no), 1);
				df.tf1.setText(String.valueOf(vo.getNo()));
				df.tf2.setText(vo.getRegdate().toString());
				df.tf3.setText(vo.getName());
				df.tf4.setText(String.valueOf(vo.getHit()));
				df.tf5.setText(vo.getSubject());
				df.ta.setText(vo.getContent());
				
				card.show(getContentPane(), "DF");
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
