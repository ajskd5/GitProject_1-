package com.sist.main;

import java.util.List;

import com.sist.dao.*;

public class MainClass {

	public static void main(String[] args) {
		EmpDeptDAO dao = EmpDeptDAO.newInstance();
		
		System.out.println("======== JOIN ========");
		List<EmpDeptVO> list = dao.empDeptListData1();
		for(EmpDeptVO vo : list) {
			System.out.println(vo.getEname() + " "
					+ vo.getDname() + " "
					+ vo.getJob() + " "
					+ vo.getLoc() + " "
					+ vo.getGrade());
		}
		System.out.println("======== VIEW(복합뷰) ========");
		list = dao.empDeptListData2();
		for(EmpDeptVO vo : list) {
			System.out.println(vo.getEname() + " "
					+ vo.getDname() + " "
					+ vo.getJob() + " "
					+ vo.getLoc() + " "
					+ vo.getGrade());
		}

	}

}
