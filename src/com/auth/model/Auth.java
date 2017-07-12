package com.auth.model;

import java.io.Serializable;

public class Auth implements Serializable  {
	private Integer authNo;
	private String authName;
	
	public Auth(){}

	public Auth(Integer authNo, String authName) {
		super();
		this.authNo = authNo;
		this.authName = authName;
	}

	public Integer getAuthNo() {
		return authNo;
	}

	public void setAuthNo(Integer authNo) {
		this.authNo = authNo;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	};
	

}
