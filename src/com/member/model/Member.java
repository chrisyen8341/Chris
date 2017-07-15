package com.member.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Member implements Serializable {
	private Integer memNo;
	private String memId;
	private String memPwd;
	private String memName;
	private String memSname;
	private Integer memGender;
	private String memIdNo;
	private Date memBday;
	private String memPhone;
	private String memAddress;
	private String memEmail;
	private byte[] memImg;
	private Integer memReported;
	private Integer memStatus;
	private Integer memRelation;
	private String memSelfintro;
	private Integer memFollowed;
	private Integer memPoint=null;
	private Integer memSaleRank;
	private Double memLongtitude;
	private Double memLatitude;
	private Timestamp memLocTime;
	private Integer memLocStatus;
	
	public Member(){}

	public Member(Integer memNo, String memId, String memPwd, String memName, String memSname, Integer memGender,
			String memIdNo, Date memBday, String memPhone, String memAddress, String memEmail, byte[] memImg,
			Integer memReported, Integer memStatus, Integer memRelation, String memSelfintro, Integer memFollowed,
			Integer memPoint, Integer memSaleRank, Double memLongtitude, Double memLatitude, Timestamp memLocTime,
			Integer memLocStatus) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memSname = memSname;
		this.memGender = memGender;
		this.memIdNo = memIdNo;
		this.memBday = memBday;
		this.memPhone = memPhone;
		this.memAddress = memAddress;
		this.memEmail = memEmail;
		this.memImg = memImg;
		this.memReported = memReported;
		this.memStatus = memStatus;
		this.memRelation = memRelation;
		this.memSelfintro = memSelfintro;
		this.memFollowed = memFollowed;
		this.memPoint = memPoint;
		this.memSaleRank = memSaleRank;
		this.memLongtitude = memLongtitude;
		this.memLatitude = memLatitude;
		this.memLocTime = memLocTime;
		this.memLocStatus = memLocStatus;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemSname() {
		return memSname;
	}

	public void setMemSname(String memSname) {
		this.memSname = memSname;
	}

	public Integer getMemGender() {
		return memGender;
	}

	public void setMemGender(Integer memGender) {
		this.memGender = memGender;
	}

	public String getMemIdNo() {
		return memIdNo;
	}

	public void setMemIdNo(String memIdNo) {
		this.memIdNo = memIdNo;
	}

	public Date getMemBday() {
		return memBday;
	}

	public void setMemBday(Date memBday) {
		this.memBday = memBday;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public byte[] getMemImg() {
		return memImg;
	}

	public void setMemImg(byte[] memImg) {
		this.memImg = memImg;
	}

	public Integer getMemReported() {
		return memReported;
	}

	public void setMemReported(Integer memReported) {
		this.memReported = memReported;
	}

	public Integer getMemStatus() {
		return memStatus;
	}

	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}

	public Integer getMemRelation() {
		return memRelation;
	}

	public void setMemRelation(Integer memRelation) {
		this.memRelation = memRelation;
	}

	public String getMemSelfintro() {
		return memSelfintro;
	}

	public void setMemSelfintro(String memSelfintro) {
		this.memSelfintro = memSelfintro;
	}

	public Integer getMemFollowed() {
		return memFollowed;
	}

	public void setMemFollowed(Integer memFollowed) {
		this.memFollowed = memFollowed;
	}

	public Integer getMemPoint() {
		return memPoint;
	}

	public void setMemPoint(Integer memPoint) {
		this.memPoint = memPoint;
	}

	public Integer getMemSaleRank() {
		return memSaleRank;
	}

	public void setMemSaleRank(Integer memSaleRank) {
		this.memSaleRank = memSaleRank;
	}

	public Double getMemLongtitude() {
		return memLongtitude;
	}

	public void setMemLongtitude(Double memLongtitude) {
		this.memLongtitude = memLongtitude;
	}

	public Double getMemLatitude() {
		return memLatitude;
	}

	public void setMemLatitude(Double memLatitude) {
		this.memLatitude = memLatitude;
	}

	public Timestamp getMemLocTime() {
		return memLocTime;
	}

	public void setMemLocTime(Timestamp memLocTime) {
		this.memLocTime = memLocTime;
	}

	public Integer getMemLocStatus() {
		return memLocStatus;
	}

	public void setMemLocStatus(Integer memLocStatus) {
		this.memLocStatus = memLocStatus;
	}

	
}
