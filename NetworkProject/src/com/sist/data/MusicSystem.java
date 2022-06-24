package com.sist.data;
//  데이터 읽어오기
import java.util.ArrayList;

import java.io.*;

//DAO => 웹(오라클)
public class MusicSystem {
	private static ArrayList<Music> list = new ArrayList<Music>();
	
	// 초기화
	static {
		try {
			FileInputStream fis = new FileInputStream("c:\\java_data\\music.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList<Music>)ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static ArrayList<Music> getList() {
		return list;
	}

	public static ArrayList<Music> musicListData(int cno, int page) {
		ArrayList<Music> cList = new ArrayList<Music>();
		
		int j=0;
		int pagecnt = (page*15)-15;
		/*
		 * list.size => 700 (0~699)
		 * cno 1 => 0~9
		 * cno 2 => 100
		 */
		for(int i=0; i<list.size(); i++) {
			Music m = list.get(i);
			if(j<15 && i>=(pagecnt+((cno-1)*100))) {
				cList.add(m);
				j++;
			}
		}
		
		return cList;
	}
	
	public static int musicTotalPage() {
		return (int)(Math.ceil(100/15.0));
	}
	
	public static void main(String[] args) {
		ArrayList<Music> list = musicListData(1, 1);
		for(Music m : list) {
			System.out.println("asdf");
			System.out.println(m.getMno() + ". " + m.getTitle());
		}
	}
	
	public static ArrayList<Music> musicFind(String fd) {
		ArrayList<Music> fList = new ArrayList<Music>();
		for(Music m : list) {
			if(m.getTitle().contains(fd)) {
				fList.add(m);
			}
		}
		return fList;
	}
	
	//데이터 관리 => 웹 (자바 기능 => 데이터 관리)
	// 파일(일반 자바) / 데이터베이스(웹)
	public static ArrayList<Music> musicTop10(){
		ArrayList<Music> tList = new ArrayList<Music>();
		for(int i=0; i<10; i++) {
			Music m = list.get(i);
			tList.add(m);
		}
		return tList;
	}
	
}
