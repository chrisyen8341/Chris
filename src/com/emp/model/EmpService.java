package com.emp.model;

import java.util.List;

import com.auth.model.Auth;
import com.auth.model.AuthDAO;
import com.auth.model.AuthDAO_interface;

public class EmpService {
	private EmpDAO_interface dao;
	
	public EmpService(){
		dao=new EmpDAO();
	}
	
	public Emp addEmp(String empName, String empJob, String empId, String empPwd, Integer empStatus) {

		Emp emp = new Emp();
		emp.setEmpName(empName);
		emp.setEmpJob(empJob);
		emp.setEmpId(empId);
		emp.setEmpPwd(empPwd);
		emp.setEmpStatus(0);
		dao.add(emp);

		return emp ;
	}

	public Emp updateEmp(Integer empNo, String empName, String empJob, String empId, String empPwd, Integer empStatus) {

		Emp emp = new Emp();
		emp.setEmpNo(empNo);
		emp.setEmpName(empName);
		emp.setEmpJob(empJob);
		emp.setEmpId(empId);
		emp.setEmpPwd(empPwd);
		emp.setEmpStatus(0);
		dao.update(emp);

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
}
