package com.actDetial.model;

import java.io.Serializable;

public class ActDetial implements Serializable{
	private Integer actNo;
	private Integer memNo;
	private Integer memActStatus;
	public Integer getActNo() {
		return actNo;
	}
	public void setActNo(Integer actNo) {
		this.actNo = actNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getMemActStatus() {
		return memActStatus;
	}
	public void setMemActStatus(Integer memActStatus) {
		this.memActStatus = memActStatus;
	}
	public ActDetial(Integer actNo, Integer memNo, Integer memActStatus) {
		super();
		this.actNo = actNo;
		this.memNo = memNo;
		this.memActStatus = memActStatus;
	}
	public ActDetial() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
