package com.sist.user;
import java.util.*;
import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// 오라클 연결
		EmpDAO dao = new EmpDAO();
		ArrayList<Emp> list = dao.empListData();
		

	}

}
