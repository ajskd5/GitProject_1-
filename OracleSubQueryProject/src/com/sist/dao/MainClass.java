package com.sist.dao;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		EmpDAO dao = new EmpDAO();
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		String ename = scan.next();
		List<EmpVO> list = dao.empListData2(ename.toUpperCase()); // 대문자로 저장되어 있어서 입력을 대소문자 구분없이 받을 수 있음
		
		for(EmpVO vo : list) {
			System.out.println(vo.getEmpno() + " "
					+ vo.getEname() + " "
					+ vo.getJob() + " "
					+ vo.getHiredate().toString() + " "
					+ vo.getSal());
		}

	}

}
