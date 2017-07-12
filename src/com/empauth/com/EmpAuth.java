package com.empauth.com;

import java.io.Serializable;

public class EmpAuth implements Serializable {
	private Integer empNO;
	private Integer authNo;
	
	public EmpAuth(){}

	public EmpAuth(Integer empNO, Integer authNo) {
		super();
		this.empNO = empNO;
		this.authNo = authNo;
	}

	public Integer getEmpNO() {
		return empNO;
	}

	public void setEmpNO(Integer empNO) {
		this.empNO = empNO;
	}

	public Integer getAuthNo() {
		return authNo;
	}

	public void setAuthNo(Integer authNo) {
		this.authNo = authNo;
	}
	
	
}
