package com.emp.model;

import java.io.Serializable;

public class Emp implements Serializable{

	private Integer empNo;	
	private String empName;	
	private String empJob;
	private String empId;	
	private String empPwd;	

	public Emp(){}

	public Emp(Integer empNo, String empName, String empJob, String empId, String empPwd) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empJob = empJob;
		this.empId = empId;
		this.empPwd = empPwd;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpJob() {
		return empJob;
	}

	public void setEmpJob(String empJob) {
		this.empJob = empJob;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}
	

}
