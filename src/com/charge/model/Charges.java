package com.charge.model;

import java.io.Serializable;
import java.sql.Date;

public class Charges implements Serializable{
	private Integer chargeNo;
	private Integer memNo;
	private Integer chargeNum;
	private Date applyTime;
	private Integer chargeStatus;
	private Integer payWay;
	
	public Charges(){}
	public Charges(Integer chargeNo, Integer memNo, Integer chargeNum, Date applyTime, Integer chargeStatus,
			Integer payWay) {
		super();
		this.chargeNo = chargeNo;
		this.memNo = memNo;
		this.chargeNum = chargeNum;
		this.applyTime = applyTime;
		this.chargeStatus = chargeStatus;
		this.payWay = payWay;
	}
	public Integer getChargeNo() {
		return chargeNo;
	}
	public void setChargeNo(Integer chargeNo) {
		this.chargeNo = chargeNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getChargeNum() {
		return chargeNum;
	}
	public void setChargeNum(Integer chargeNum) {
		this.chargeNum = chargeNum;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(Integer chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	
}
