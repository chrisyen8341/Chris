package com.faq.model;

import java.io.Serializable;

public class Faq implements Serializable{
	
	private int faqNo;
	private int empNo;
	private String faqCategory;
	private String faqTitle;
	private String faqAnswer;
		
	public Faq(){
		
	}
	
	public Faq(int faqNo, int empNo, String faqTitle, String faqAnswer, String faqCategory){
		
		this.faqNo = faqNo;
		this.empNo = empNo;
		this.faqTitle = faqTitle;
		this.faqAnswer = faqAnswer;
		this.faqCategory = faqCategory;
				
	}
	
	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getFaqCategory() {
		return faqCategory;
	}

	public void setFaqCategory(String faqCategory) {
		this.faqCategory = faqCategory;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqAnswer() {
		return faqAnswer;
	}

	public void setFaqAnswer(String faqAnswer) {
		this.faqAnswer = faqAnswer;
	}
}