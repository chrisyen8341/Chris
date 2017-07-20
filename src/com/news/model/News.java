package com.news.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;

public class News implements Serializable{
	
	private int newsNo;
	private int empNo;
	private String newsTitle;
	private String newsContent;
	private Timestamp newsDate;
		
	public News(){
		
	}
	
	public News(int newsNo, int empNo, String newsTitle, String newsContent, Timestamp newsDate){
		this.newsNo = newsNo;
		this.empNo = empNo;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsDate = newsDate;
				
	}

	public int getNewsNo() {
		return newsNo;
	}

	public void setNewsNo(int newsNo) {
		this.newsNo = newsNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Timestamp getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Timestamp newsDate) {
		this.newsDate = newsDate;
	}
		
}