package com.sist.main;

import java.util.*;

import com.sist.dao.*;

public class MainClass2 {

	public static void main(String[] args) {
		BooksDAO dao = BooksDAO.newInstance();
		Scanner scan = new Scanner(System.in);
		System.out.print("페이지 입력 : ");
		int page = scan.nextInt();
		int totalpage = dao.booksTotalPage(); // 총 페이지 구하기
		
		final int BLOCK = 5;
		int startPage = ((page-1)/BLOCK*BLOCK)+1;
		int endPage = ((page-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		for(int i=startPage; i<=endPage; i++) {
			System.out.print(i + "\t");
		}
		System.out.println("\n");
		
		
		List<BooksVO> list = dao.booksListData(page);
		for(BooksVO vo : list) {
			System.out.println(vo.getNo() + ". " + vo.getTitle());
		}
		
		

	}

}
