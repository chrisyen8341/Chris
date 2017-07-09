package com.member.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Member implements Serializable {
	private int memNo;
	private String memId;
	private String memPwd;
	private String memName;
	private String memSname;
	private String memGender;
	private String memIdNo;
	private Date memBday;
	private String memPhone;
	private String memAddress;
	private String memEmail;
	private byte[] memImg;
	private int memReported;
	private int memStatus;
	private String memRelation;
	private String memSelfintro;
	private int memFollowed;
	private int memPoint;
	private int memSaleRank;
	private double memLongtitude;
	private double memLatitude;
	private Timestamp memLocTime;
	private int memLocStatus;
	
	public Member(){}
	
	
	
	public Member(int memNo, String memId, String memPwd, String memName, String memSname, String memGender,
			String memIdNo, Date memBday, String memPhone, String memAddress, String memEmail, byte[] memImg,
			int memReported, int memStatus, String memRelation, String memSelfintro, int memFollowed, int memPoint,
			int memSaleRank, double memLongtitude, double memLatitude, Timestamp memLocTime, int memLocStatus) {
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



	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
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
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
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
	public int getMemReported() {
		return memReported;
	}
	public void setMemReported(int memReported) {
		this.memReported = memReported;
	}
	public int getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(int memStatus) {
		this.memStatus = memStatus;
	}
	public String getMemRelation() {
		return memRelation;
	}
	public void setMemRelation(String memRelation) {
		this.memRelation = memRelation;
	}
	public String getMemSelfintro() {
		return memSelfintro;
	}
	public void setMemSelfintro(String memSelfintro) {
		this.memSelfintro = memSelfintro;
	}
	public int getMemFollowed() {
		return memFollowed;
	}
	public void setMemFollowed(int memFollowed) {
		this.memFollowed = memFollowed;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	public int getMemSaleRank() {
		return memSaleRank;
	}
	public void setMemSaleRank(int memSaleRank) {
		this.memSaleRank = memSaleRank;
	}
	public double getMemLongtitude() {
		return memLongtitude;
	}
	public void setMemLongtitude(double memLongtitude) {
		this.memLongtitude = memLongtitude;
	}
	public double getMemLatitude() {
		return memLatitude;
	}
	public void setMemLatitude(double memLatitude) {
		this.memLatitude = memLatitude;
	}
	public Timestamp getMemLocTime() {
		return memLocTime;
	}  
	public void setMemLocTime(Timestamp memLocTime) {
		this.memLocTime = memLocTime;
	}
	public int getMemLocStatus() {
		return memLocStatus;
	}
	public void setMemLocStatus(int memLocStatus) {
		this.memLocStatus = memLocStatus;
	}
	
}
