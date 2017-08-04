package com.emp.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;



public class EmpService {
	private EmpDAO_interface dao;
	
	public EmpService(){
		dao=new EmpDAO();
	}
	
	public Emp addEmp(String empName, String empJob, String empId, String empPwd,Integer empStatus,Date empHireDate,String empEmail) {

		Emp emp = new Emp();
		emp.setEmpName(empName);
		emp.setEmpJob(empJob);
		emp.setEmpId(empId);
		emp.setEmpPwd(empPwd);
		emp.setEmpStatus(0);
		emp.setEmpHireDate(empHireDate);
		emp.setEmpEmail(empEmail);
		dao.add(emp);

		return emp ;
	}

	public Emp addEmpWithAuth(String empName, String empJob, String empId, String empPwd, Integer empStatus,Date empHireDate,String empEmail,List<Integer> authNos) {

		Emp emp = new Emp();
		emp.setEmpName(empName);
		emp.setEmpJob(empJob);
		emp.setEmpId(empId);
		emp.setEmpPwd(empPwd);
		emp.setEmpStatus(0);
		emp.setEmpHireDate(empHireDate);
		emp.setEmpEmail(empEmail);
		dao.addWithAuth(emp, authNos);

		return emp ;
	}
	
	
	
	
	
	
	
	public Emp updateEmp(Integer empNo, String empName, String empJob, String empId, String empPwd, Integer empStatus,Date empHireDate,String empEmail) {

		Emp emp = new Emp();
		emp.setEmpNo(empNo);
		emp.setEmpName(empName);
		emp.setEmpJob(empJob);
		emp.setEmpId(empId);
		emp.setEmpPwd(empPwd);
		emp.setEmpStatus(0);
		emp.setEmpHireDate(empHireDate);
		emp.setEmpEmail(empEmail);
		dao.update(emp);

		return emp;
	}

	public Emp updateEmpWithAuth(Integer empNo, String empName, String empJob, String empId, String empPwd, Integer empStatus,Date empHireDate,String empEmail,List<Integer> auth) {

		Emp emp = new Emp();
		emp.setEmpNo(empNo);
		emp.setEmpName(empName);
		emp.setEmpJob(empJob);
		emp.setEmpId(empId);
		emp.setEmpPwd(empPwd);
		emp.setEmpStatus(0);
		emp.setEmpHireDate(empHireDate);
		emp.setEmpEmail(empEmail);
		dao.updateWithAuth(emp, auth); 

		return emp;
	}
	
	
	
	public void deleteEmp(Integer empNo) {
		dao.delete(empNo);
	}

	public Emp getOneEmp(Integer empNo) {
		return dao.findByPk(empNo);
	}

	public List<Emp> getAll() {
		return dao.getAll();
	}
	
	public Emp getEmpById(String empId) {
		return dao.findById(empId);
	}
	
	public List<Emp> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public List<Integer> getAuthByEmpNo(Integer empNo) {
		return dao.getAuthByEmpNo(empNo);
	}
	
	
	
	
}
