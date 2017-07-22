package com.empauth.model;

import java.io.Serializable;

public class EmpAuth implements Serializable {
	private Integer empNo;
	private Integer authNo;
	
	public EmpAuth(){}

	public EmpAuth(Integer empNo, Integer authNo) {
		super();
		this.empNo = empNo;
		this.authNo = authNo;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNO(Integer empNo) {
		this.empNo = empNo;
	}

	public Integer getAuthNo() {
		return authNo;
	}

	public void setAuthNo(Integer authNo) {
		this.authNo = authNo;
	}
	
	
}
