package com.emp.model;

import java.util.List;

public class EmpTest {

	public static void main(String[] args) {
		EmpJDBCDAO dao=new EmpJDBCDAO();
	
		//新增
//		Emp emp1=new Emp();
//		emp1.setEmpName("印度阿三");
//		emp1.setEmpJob("工程師");
//		emp1.setEmpId("dGRTG");
//		emp1.setEmpPwd("RTGRTGE");
//		emp1.setEmpStatus(0);
//		dao.add(emp1);
//		System.out.println("執行完畢");

		
		//修改
//		Emp emp2=new Emp();
//		emp2.setEmpNo(3);
//		emp2.setEmpName("運動阿鈞");
//		emp2.setEmpJob("工程師");
//		emp2.setEmpId("werTG");
//		emp2.setEmpPwd("qweE");
//		emp2.setEmpStatus(0);
//		dao.update(emp2);;
//		System.out.println("執行完畢");
		
		//刪除 多個table測不了 emp為多個table參照
//		dao.delete(4);
//		System.out.println("執行完畢");
		
		
		//查詢
//		Emp emp3=dao.findByPk(1);
//		System.out.println(emp3.getEmpNo());
//		System.out.println(emp3.getEmpName());
//		System.out.println(emp3.getEmpJob());
//		System.out.println(emp3.getEmpPwd());
//		System.out.println(emp3.getEmpId());
//		System.out.println(emp3.getEmpStatus());
		
		//查全部
//		List<Emp> empList=dao.getAll();
//		for(Emp emp:empList){
//			System.out.println(emp.getEmpNo());
//			System.out.println(emp.getEmpName());
//			System.out.println(emp.getEmpJob());
//			System.out.println(emp.getEmpPwd());
//			System.out.println(emp.getEmpId());
//			System.out.println(emp.getEmpStatus());
//			System.out.println("========================");
//		}
		
		
	}

}
