package com.diamsg.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class DiaMsg implements Serializable{
	
	private Integer diaMsgNO;
	private Integer diaNO;
	private Integer memNo;
	private String diaMsgText;
	private Timestamp diaMsgTime;
	private Integer diaMsgState;
	
	
	
	public Integer getDiaMsgNO() {
		return diaMsgNO;
	}
	public void setDiaMsgNO(Integer diaMsgNO) {
		this.diaMsgNO = diaMsgNO;
	}
	public Integer getDiaNO() {
		return diaNO;
	}
	public void setDiaNO(Integer diaNO) {
		this.diaNO = diaNO;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getDiaMsgText() {
		return diaMsgText;
	}
	public void setDiaMsgText(String diaMsgText) {
		this.diaMsgText = diaMsgText;
	}
	public Timestamp getDiaMsgTime() {
		return diaMsgTime;
	}
	public void setDiaMsgTime(Timestamp diaMsgTime) {
		this.diaMsgTime = diaMsgTime;
	}
	public Integer getDiaMsgState() {
		return diaMsgState;
	}
	public void setDiaMsgState(Integer diaMsgState) {
		this.diaMsgState = diaMsgState;
	}
	
	

}
