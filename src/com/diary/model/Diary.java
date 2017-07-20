package com.diary.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Diary implements Serializable{
	
	private Integer diano;
	private Integer memno;
	private String dianame;
	private String diatext;
	private byte[] diaimg;
	private Timestamp diacretime;
	private Timestamp diamodtime;
	private Integer diastate;
	
	
	public Integer getDiano() {
		return diano;
	}
	public void setDiano(Integer diano) {
		this.diano = diano;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getDianame() {
		return dianame;
	}
	public void setDianame(String dianame) {
		this.dianame = dianame;
	}
	public String getDiatext() {
		return diatext;
	}
	public void setDiatext(String diatext) {
		this.diatext = diatext;
	}
	public byte[] getDiaimg() {
		return diaimg;
	}
	public void setDiaimg(byte[] diaimg) {
		this.diaimg = diaimg;
	}
	public Timestamp getDiacretime() {
		return diacretime;
	}
	public void setDiacretime(Timestamp diacretime) {
		this.diacretime = diacretime;
	}
	public Timestamp getDiamodtime() {
		return diamodtime;
	}
	public void setDiamodtime(Timestamp diamodtime) {
		this.diamodtime = diamodtime;
	}
	public Integer getDiastate() {
		return diastate;
	}
	public void setDiastate(Integer diastate) {
		this.diastate = diastate;
	}
	
	

	
	
}
