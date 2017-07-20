package com.restMember.model;

import java.io.Serializable;

public class RestMember implements Serializable{
	private String restMemId;
	private Integer restNo;
	private String restMemPsw;
	
	
	public RestMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestMember(String restMemId, Integer restNo, String restMemPsw) {
		super();
		this.restMemId = restMemId;
		this.restNo = restNo;
		this.restMemPsw = restMemPsw;
	}
	public String getRestMemId() {
		return restMemId;
	}
	public void setRestMemId(String restMemId) {
		this.restMemId = restMemId;
	}
	public Integer getRestNo() {
		return restNo;
	}
	public void setRestNo(Integer restNo) {
		this.restNo = restNo;
	}
	public String getRestMemPsw() {
		return restMemPsw;
	}
	public void setRestMemPsw(String restMemPsw) {
		this.restMemPsw = restMemPsw;
	}
	
	
	
	
}
