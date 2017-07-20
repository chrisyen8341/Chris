package com.userquestion.model;

import java.io.Serializable;

public class UserQuestion implements Serializable{
	
	private int userQNo;
	private int faqNo;
	private String userQCategory;
	private String userQTitle;
	private String userQContent;
		
	public UserQuestion(){
		
	}
	
	public UserQuestion(int userQNo, int faqNo, String userQCategory, String userQTitle, String userQContent){
		
		this.userQNo = userQNo;
		this.faqNo = faqNo;
		this.userQCategory = userQCategory;
		this.userQTitle = userQTitle;
		this.userQContent = userQContent;
				
	}

	public int getUserQNo() {
		return userQNo;
	}

	public void setUserQNo(int userQNo) {
		this.userQNo = userQNo;
	}

	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public String getUserQCategory() {
		return userQCategory;
	}

	public void setUserQCategory(String userQCategory) {
		this.userQCategory = userQCategory;
	}

	public String getUserQTitle() {
		return userQTitle;
	}

	public void setUserQTitle(String userQTitle) {
		this.userQTitle = userQTitle;
	}

	public String getUserQContent() {
		return userQContent;
	}

	public void setUserQContent(String userQContent) {
		this.userQContent = userQContent;
	}

}