package com.emp.model;

import java.io.Serializable;
import java.sql.Date;

public class Emp implements Serializable{

	private Integer empNo;	
	private String empName;	
	private String empJob;
	private String empId;	
	private String empPwd;
	private Integer empStatus;
	private Date empHireDate;
	private String empEmail;

	public Emp(){}

	public Emp(Integer empNo, String empName, String empJob, String empId, String empPwd, Integer empStatus,Date empHireDate,String empEmail) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empJob = empJob;
		this.empId = empId;
		this.empPwd = empPwd;
		this.empStatus=empStatus;
		this.empHireDate=empHireDate;
		this.empEmail=empEmail;
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

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}

	public Date getEmpHireDate() {
		return empHireDate;
	}

	public void setEmpHireDate(Date empHireDate) {
		this.empHireDate = empHireDate;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	
	
}
