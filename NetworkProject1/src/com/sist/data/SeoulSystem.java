package com.sist.data;

import java.io.*;
import java.util.ArrayList;
public class SeoulSystem {
private static ArrayList<SeoulLocationVO> list = new ArrayList<SeoulLocationVO>();
	
	// 초기화
	static {
		try {
			FileInputStream fis = new FileInputStream("c:\\java_data\\location.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList<SeoulLocationVO>)ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static ArrayList<SeoulLocationVO> getList() {
		return list;
	}
	
	public static ArrayList<SeoulLocationVO> seoulListData(int page) {
		ArrayList<SeoulLocationVO> cList = new ArrayList<SeoulLocationVO>();
		
		int j=0;
		int pagecnt = (page*15)-15;

		for(int i=0; i<list.size(); i++) {
			SeoulLocationVO l = list.get(i);
			if(j<15 && i>=pagecnt) {
				cList.add(l);
				j++;
			}
//			cList.add(l);
			}
		
		return cList;
	}
	
	public static int seoulLocationTotalPage() {
		return (int)(Math.ceil(276/15.0));
	}
	
	public static void main(String[] args) {
		ArrayList<SeoulLocationVO> list = seoulListData(2);
		for(SeoulLocationVO l : list) {
			System.out.println(l.getNo() + ". " + l.getTitle());
		}
	}
}
