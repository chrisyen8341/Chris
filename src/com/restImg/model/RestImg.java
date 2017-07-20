package com.restImg.model;

import java.io.Serializable;

public class RestImg implements Serializable{

	private Integer restImgNo;
	private String restMemId;
	private String restImgName;
	private String restImgIntro;
	private byte[] restImg;
	public Integer getRestImgNo() {
		return restImgNo;
	}
	public void setRestImgNo(Integer restImgNo) {
		this.restImgNo = restImgNo;
	}
	public String getRestMemId() {
		return restMemId;
	}
	public void setRestMemId(String restMemId) {
		this.restMemId = restMemId;
	}
	public String getRestImgName() {
		return restImgName;
	}
	public void setRestImgName(String restImgName) {
		this.restImgName = restImgName;
	}
	public String getRestImgIntro() {
		return restImgIntro;
	}
	public void setRestImgIntro(String restImgIntro) {
		this.restImgIntro = restImgIntro;
	}
	public byte[] getRestImg() {
		return restImg;
	}
	public void setRestImg(byte[] restImg) {
		this.restImg = restImg;
	}
	public RestImg(Integer restImgNo, String restMemId, String restImgName, String restImgIntro, byte[] restImg) {
		super();
		this.restImgNo = restImgNo;
		this.restMemId = restMemId;
		this.restImgName = restImgName;
		this.restImgIntro = restImgIntro;
		this.restImg = restImg;
	}
	public RestImg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	



	
	
	

}
