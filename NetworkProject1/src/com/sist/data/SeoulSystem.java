package com.sist.data;

import java.io.*;
import java.util.ArrayList;
public class SeoulSystem {
private static ArrayList<SeoulLocationVO> list = new ArrayList<SeoulLocationVO>();
private static ArrayList<SeoulNatureVO> list2 = new ArrayList<SeoulNatureVO>();
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
	static {
		try {
			FileInputStream fis = new FileInputStream("c:\\java_data\\nature.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list2 = (ArrayList<SeoulNatureVO>)ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
	}

	public static ArrayList<SeoulNatureVO> getList2() {
		return list2;
	}

	public static ArrayList<SeoulLocationVO> getList() {
		return list;
	}
	
	public static ArrayList<SeoulLocationVO> seoulLocationListData(int page) {
		ArrayList<SeoulLocationVO> cList = new ArrayList<SeoulLocationVO>();
		int j=0;
		int pagecnt = (page*15)-15;
		for(int i=0; i<list.size(); i++) {
			SeoulLocationVO l = list.get(i);
			if(j<15 && i>=pagecnt) {
				cList.add(l);
				j++;
			}
		}
		return cList;
	}
	public static ArrayList<SeoulNatureVO> seoulNatureListData(int page) {
		ArrayList<SeoulNatureVO> cList2 = new ArrayList<SeoulNatureVO>();
		int j=0;
		int pagecnt = (page*15)-15;
		for(int i=0; i<list2.size(); i++) {
			SeoulNatureVO l = list2.get(i);
			if(j<15 && i>=pagecnt) {
				cList2.add(l);
				j++;
			}
		}
		return cList2;
	}
	
	public static int seoulLocationTotalPage() {
		return (int)(Math.ceil(275/15.0));
	}
	public static int seoulNatureTotalPage() {
		return (int)(Math.ceil(110/15.0));
	}
	
	public static void main(String[] args) {
		ArrayList<SeoulLocationVO> list = seoulLocationListData(19);
		for(SeoulLocationVO l : list) {
			System.out.println(l.getNo() + ". " + l.getTitle());
		}
		System.out.println("==");
		ArrayList<SeoulNatureVO> list2 = seoulNatureListData(8);
		for(SeoulNatureVO l : list2) {
			System.out.println(l.getNo() + ". " + l.getTitle());
		}
	}
	
	public static ArrayList<SeoulLocationVO> locationFind(String fd){
		ArrayList<SeoulLocationVO> lfList = new ArrayList<SeoulLocationVO>();
		for(SeoulLocationVO l : list) {
			if(l.getTitle().contains(fd)) {
				lfList.add(l);
			}
		}
		return lfList;
	}
	public static ArrayList<SeoulNatureVO> natureFind(String fd){
		ArrayList<SeoulNatureVO> lfList2 = new ArrayList<SeoulNatureVO>();
		for(SeoulNatureVO l : list2) {
			if(l.getTitle().contains(fd)) {
				lfList2.add(l);
			}
		}
		return lfList2;
	}
	
	public static ArrayList<SeoulLocationVO> seoulTop10()
	   {
		   ArrayList<SeoulLocationVO> tList=new ArrayList<SeoulLocationVO>();
		   for(int i=0;i<10;i++)
		   {
			   int r=(int)(Math.random()*275);
			   SeoulLocationVO l=list.get(r);
			   tList.add(l);
		   }
		   return tList;
	   }
}
