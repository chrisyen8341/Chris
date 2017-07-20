package com.orderlist.model;

import java.io.Serializable;

public class OrderList implements Serializable{
		
	private Integer ordNo;
	private Integer prodNo;
	private Integer proPrice;
	private Integer proQua;
	
	public OrderList(){}
	
	public OrderList(Integer ordNo, Integer prodNo, Integer proPrice, Integer proQua) {
		super();
		this.ordNo = ordNo;
		this.prodNo = prodNo;
		this.proPrice = proPrice;
		this.proQua = proQua;
	}

	

	public Integer getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}

	public Integer getProdNo() {
		return prodNo;
	}

	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}

	public Integer getProPrice() {
		return proPrice;
	}

	public void setProPrice(Integer proPrice) {
		this.proPrice = proPrice;
	}

	public Integer getProQua() {
		return proQua;
	}

	public void setProQua(Integer proQua) {
		this.proQua = proQua;
	}
	
	
}
