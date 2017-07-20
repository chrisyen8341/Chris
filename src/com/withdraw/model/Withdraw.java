package com.withdraw.model;

import java.io.Serializable;
import java.sql.Date;

public class Withdraw implements Serializable{
	private Integer withdrawNo;
	private Integer memNo;
	private Integer withdrawNum;
	private Date applyTime;
	private Integer withdrawStatus;
	public Withdraw(){}
	public Withdraw(Integer withdrawNo, Integer memNo, Integer withdrawNum, Date applyTime, Integer withdrawStatus) {
		super();
		this.withdrawNo = withdrawNo;
		this.memNo = memNo;
		this.withdrawNum = withdrawNum;
		this.applyTime = applyTime;
		this.withdrawStatus = withdrawStatus;
	}
	public Integer getWithdrawNo() {
		return withdrawNo;
	}
	public void setWithdrawNo(Integer withdrawNo) {
		this.withdrawNo = withdrawNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getWithdrawNum() {
		return withdrawNum;
	}
	public void setWithdrawNum(Integer withdrawNum) {
		this.withdrawNum = withdrawNum;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getWithdrawStatus() {
		return withdrawStatus;
	}
	public void setWithdrawStatus(Integer withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}
	
	
}
