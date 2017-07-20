package com.product.model;

import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable {
	private Integer prodNo;
	private String prodName;
	private Integer prodPrice;
	private byte[] prodImg;
	private String prodDescpt;
	private Date prodAdd;
	private Date prodOut;
	private Integer prodState;
	private String prodType;
	
	public Product(){}
	public Product(Integer prodNo, String prodName, Integer prodPrice, byte[] prodImg, String prodDescpt, Date prodAdd,
			Date prodOut, Integer prodState, String prodType) {
		super();
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodImg = prodImg;
		this.prodDescpt = prodDescpt;
		this.prodAdd = prodAdd;
		this.prodOut = prodOut;
		this.prodState = prodState;
		this.prodType = prodType;
	}
	public Integer getProdNo() {
		return prodNo;
	}
	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}
	public byte[] getProdImg() {
		return prodImg;
	}
	public void setProdImg(byte[] prodImg) {
		this.prodImg = prodImg;
	}
	public String getProdDescpt() {
		return prodDescpt;
	}
	public void setProdDescpt(String prodDescpt) {
		this.prodDescpt = prodDescpt;
	}
	public Date getProdAdd() {
		return prodAdd;
	}
	public void setProdAdd(Date prodAdd) {
		this.prodAdd = prodAdd;
	}
	public Date getProdOut() {
		return prodOut;
	}
	public void setProdOut(Date prodOut) {
		this.prodOut = prodOut;
	}
	public Integer getProdState() {
		return prodState;
	}
	public void setProdState(Integer prodState) {
		this.prodState = prodState;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	
}
