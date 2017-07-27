package com.emp.model;

import java.util.ArrayList;
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
//		emp1.setEmpHireDate(java.sql.Date.valueOf("2000-01-01"));
//		emp1.setEmpEmail("chrisyen8341@gmail.com");
//		dao.add(emp1);
//		System.out.println("執行完畢");

		
		//新增員工加權限
//		Emp emp1=new Emp();
//		emp1.setEmpName("印度阿三");
//		emp1.setEmpJob("工程師");
//		emp1.setEmpId("dQQQTyi");
//		emp1.setEmpPwd("RTGUIE");
//		emp1.setEmpStatus(0);
//		emp1.setEmpHireDate(java.sql.Date.valueOf("2000-01-01"));
//		emp1.setEmpEmail("chrisyen8341@gmail.com");
//		List<Integer> list=new ArrayList<Integer>();
//		list.add(4001);
//		list.add(4002);
//		list.add(4003);
//		dao.addWithAuth(emp1, list);
//		System.out.println("執行完畢");
		
		
		//修改
//		Emp emp2=new Emp();
//		emp2.setEmpNo(6003);
//		emp2.setEmpName("運動阿鈞");
//		emp2.setEmpJob("工程師");
//		emp2.setEmpId("werTG");
//		emp2.setEmpPwd("qweE");
//		emp2.setEmpStatus(0);
//		emp2.setEmpEmail("chros@qweqw.com");
//		emp2.setEmpHireDate(java.sql.Date.valueOf("1985-01-01"));
//		dao.update(emp2);;
//		System.out.println("執行完畢");
		
		//刪除 多個table測不了 emp為多個table參照
//		dao.delete(4);
//		System.out.println("執行完畢");
		
		
		//查詢
//		Emp emp3=dao.findByPk(6001);
//		System.out.println(emp3.getEmpNo());
//		System.out.println(emp3.getEmpName());
//		System.out.println(emp3.getEmpJob());
//		System.out.println(emp3.getEmpPwd());
//		System.out.println(emp3.getEmpId());
//		System.out.println(emp3.getEmpStatus());
//		System.out.println(emp3.getEmpEmail());
//		System.out.println(emp3.getEmpHireDate());
		
		//查全部
//		List<Emp> empList=dao.getAll();
//		for(Emp emp:empList){
//			System.out.println(emp.getEmpNo());
//			System.out.println(emp.getEmpName());
//			System.out.println(emp.getEmpJob());
//			System.out.println(emp.getEmpPwd());
//			System.out.println(emp.getEmpId());
//			System.out.println(emp.getEmpStatus());
//			System.out.println(emp.getEmpEmail());
//			System.out.println(emp.getEmpHireDate());
//			System.out.println("========================");
//		}
		
		
		//查詢 以ID
		Emp emp3=dao.findById("CHOUFAN");
		System.out.println(emp3.getEmpNo());
		System.out.println(emp3.getEmpName());
		System.out.println(emp3.getEmpJob());
		System.out.println(emp3.getEmpPwd());
		System.out.println(emp3.getEmpId());
		System.out.println(emp3.getEmpStatus());
		System.out.println(emp3.getEmpEmail());
		System.out.println(emp3.getEmpHireDate());
		
		
		
	}

}
