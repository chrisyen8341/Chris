package com.actImg.model;

import java.io.Serializable;

public class ActImg implements Serializable{
	private Integer actImgNo;
	private Integer actNo;
	private String actImgName;
	private String actImgIntro;
	private byte[] actImg;
	
	public Integer getActImgNo() {
		return actImgNo;
	}
	public void setActImgNo(Integer actImgNo) {
		this.actImgNo = actImgNo;
	}
	public Integer getActNo() {
		return actNo;
	}
	public void setActNo(Integer actNo) {
		this.actNo = actNo;
	}
	public String getActImgName() {
		return actImgName;
	}
	public void setActImgName(String actImgName) {
		this.actImgName = actImgName;
	}
	public String getActImgIntro() {
		return actImgIntro;
	}
	public void setActImgIntro(String actImgIntro) {
		this.actImgIntro = actImgIntro;
	}
	public byte[] getActImg() {
		return actImg;
	}
	public void setActImg(byte[] actImg) {
		this.actImg = actImg;
	}
	public ActImg(Integer actImgNo, Integer actNo, String actImgName, String actImgIntro, byte[] actImg) {
		super();
		this.actImgNo = actImgNo;
		this.actNo = actNo;
		this.actImgName = actImgName;
		this.actImgIntro = actImgIntro;
		this.actImg = actImg;
	}
	public ActImg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
