package com.order.model;

import java.io.Serializable;
import java.sql.Date;

public class Ord implements Serializable {
	
	
	private Integer ordNo;
	private Integer memNo;
	private Date ordDate;
	private Date ordCheck;
	private Date ordProduct;
	private Date ordClose;
	private Integer ordStatus;
	private Integer ordShip;
	private Integer ordTotal;
	private String conName;
	private String conAdd;
	private String conTel;

	public Ord(){}
	
	public Ord(Integer ordNo, Integer memNo, Date ordDate, Date ordCheck, Date ordMoney, Date ordProduct,
			Date ordClose, Integer ordStatus, Integer ordMode, Integer ordShip, Integer ordTotal, String conName,
			String conAdd, String conTel) {
		super();
		this.ordNo = ordNo;
		this.memNo = memNo;
		this.ordDate = ordDate;
		this.ordCheck = ordCheck;
		this.ordProduct = ordProduct;
		this.ordClose = ordClose;
		this.ordStatus = ordStatus;
		this.ordShip = ordShip;
		this.ordTotal = ordTotal;
		this.conName = conName;
		this.conAdd = conAdd;
		this.conTel = conTel;
		
	}
	public Integer getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Date getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}
	public Date getOrdCheck() {
		return ordCheck;
	}
	public void setOrdCheck(Date ordCheck) {
		this.ordCheck = ordCheck;
	}
	public Date getOrdProduct() {
		return ordProduct;
	}
	public void setOrdProduct(Date ordProduct) {
		this.ordProduct = ordProduct;
	}
	public Date getOrdClose() {
		return ordClose;
	}
	public void setOrdClose(Date ordClose) {
		this.ordClose = ordClose;
	}
	public Integer getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(Integer ordStatus) {
		this.ordStatus = ordStatus;
	}
	public Integer getOrdShip() {
		return ordShip;
	}
	public void setOrdShip(Integer ordShip) {
		this.ordShip = ordShip;
	}
	public Integer getOrdTotal() {
		return ordTotal;
	}
	public void setOrdTotal(Integer ordTotal) {
		this.ordTotal = ordTotal;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConAdd() {
		return conAdd;
	}
	public void setConAdd(String conAdd) {
		this.conAdd = conAdd;
	}
	public String getConTel() {
		return conTel;
	}
	public void setConTel(String conTel) {
		this.conTel = conTel;
	}
}
