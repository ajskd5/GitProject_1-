package com.sist.dao;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		EmpDAO dao = new EmpDAO();
//		Scanner scan = new Scanner(System.in);
//		System.out.print("이름 입력 : ");
//		String ename = scan.next();
//		List<EmpVO> list = dao.empListData2(ename.toUpperCase()); // 대문자로 저장되어 있어서 입력을 대소문자 구분없이 받을 수 있음
//		
//		for(EmpVO vo : list) {
//			System.out.println(vo.getEmpno() + " "
//					+ vo.getEname() + " "
//					+ vo.getJob() + " "
//					+ vo.getHiredate().toString() + " "
//					+ vo.getSal());
//		}
		System.out.println("52번");
		List<EmpVO> list2 = dao.emp52();
		for(EmpVO vo : list2) {
			System.out.println(vo.getEname());
		}
		
		System.out.println("54번");
		List<EmpVO> list3 = dao.emp54();
		for(EmpVO vo : list3) {
			System.out.println(vo.getEname() + " "
					+ vo.getNum());
		}
		
		System.out.println("69번");
		List<EmpVO> list4 = dao.emp69();
		for(EmpVO vo : list4) {
			System.out.println(vo.getEname() + " "
					+ vo.getHiredate().toString() + " "
					+ vo.getDay());
		}
		
		System.out.println("73번");
		List<EmpVO> list5 = dao.emp73();
		for(EmpVO vo : list5) {
			System.out.println(vo.getEname() + " "
					+ vo.getComm());
		}
		
		System.out.println("74번");
		List<EmpVO> list6 = dao.emp74();
		for(EmpVO vo : list6) {
			System.out.println(vo.getEname() + " "
					+ vo.getNull_comm());
		}

	}

}
