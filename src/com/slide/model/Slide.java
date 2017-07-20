package com.slide.model;

import java.io.Serializable;
import java.util.List;

public class Slide implements Serializable {
	
	private int slideNo;
	private byte[] slideImg;
	private String slideCategory;
	private String slideTitle;
		
	public Slide(){
		
	}
	
	public Slide(int slideNo, byte[] slideImg, String slideCatgory, String slideTitle){
		this.slideNo = slideNo;		
		this.slideImg = slideImg;
		this.slideCategory = slideCategory;
		this.slideTitle = slideTitle;
				
	}

	public int getSlideNo() {
		return slideNo;
	}

	public void setSlideNo(int slideNo) {
		this.slideNo = slideNo;
	}

	public byte[] getSlideImg() {
		return slideImg;
	}

	public void setSlideImg(byte[] slideImg) {
		this.slideImg = slideImg;
	}

	public String getSlideCategory() {
		return slideCategory;
	}

	public void setSlideCategory(String slideCategory) {
		this.slideCategory = slideCategory;
	}

	public String getSlideTitle() {
		return slideTitle;
	}

	public void setSlideTitle(String slideTitle) {
		this.slideTitle = slideTitle;
	}

}