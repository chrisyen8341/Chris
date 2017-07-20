package com.ad.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ad implements Serializable{
	
	private int adNo;
	private String adContent;
	private byte[] adImg;
	private Timestamp adOnline;
	private Timestamp adOffline;
	private	double adFee;
	
	public Ad(){
		
	}
	
	public Ad(int adNo, String adContent, byte[] adImg, Timestamp adOnline, Timestamp adOffline, double adFee){
		
		this.adNo = adNo;
		this.adContent = adContent;
		this.adImg = adImg;
		this.adOnline = adOnline;
		this.adOffline = adOffline;
		this.adFee = adFee;
		
	}

	public int getAdNo() {
		return adNo;
	}

	public void setAdNo(int adNo) {
		this.adNo = adNo;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public byte[] getAdImg() {
		return adImg;
	}

	public void setAdImg(byte[] adImg) {
		this.adImg = adImg;
	}

	public Timestamp getAdOnline() {
		return adOnline;
	}

	public void setAdOnline(Timestamp adOnline) {
		this.adOnline = adOnline;
	}

	public Timestamp getAdOffline() {
		return adOffline;
	}

	public void setAdOffline(Timestamp adOffline) {
		this.adOffline = adOffline;
	}

	public double getAdFee() {
		return adFee;
	}

	public void setAdFee(double adFee) {
		this.adFee = adFee;
	}
	
}