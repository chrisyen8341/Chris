
package com.dateitem.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class DateItemVO implements Serializable{
	
	private Integer dateItemNo;
	private Integer sellerNo;
	private Integer restListNo;
	private String dateItemTitle;
	private byte[] dateItemImg;
	private String dateItemText;
	private Timestamp dateItemTime;
	private Timestamp dateMeetingTime;
	private String dateItemLocate;
	private Integer dateItemPeople;
	private Boolean hasMate;
	private Integer dateItemPrice;
	private Integer dateItemStatus;
	private Integer dateItemShow;
	private Integer dateItemViewer;
	private Integer buyerNo;
	private Boolean isQRCChecked;
	private Integer buyerRep;
	private Integer sellerRep;
	private Boolean isInstantDate;
	private Integer petNo;
	
	public DateItemVO(){};

	public DateItemVO(Integer dateItemNo, Integer sellerNo, Integer restListNo, String dateItemTitle, byte[] dateItemImg,
			String dateItemText, Timestamp dateItemTime, Timestamp dateMeetingTime, String dateItemLocate, Integer dateItemPeople,
			Boolean hasMate, Integer dateItemPrice, Integer dateItemStatus, Integer dateItemShow, Integer dateItemViewer, Integer buyerNo,
			Boolean isQRCChecked, Integer buyerRep, Integer sellerRep, Boolean isInstantDate, Integer petNo) {
		super();
		this.dateItemNo = dateItemNo;
		this.sellerNo = sellerNo;
		this.restListNo = restListNo;
		this.dateItemTitle = dateItemTitle;
		this.dateItemImg = dateItemImg;
		this.dateItemText = dateItemText;
		this.dateItemTime = dateItemTime;
		this.dateMeetingTime = dateMeetingTime;
		this.dateItemLocate = dateItemLocate;
		this.dateItemPeople = dateItemPeople;
		this.hasMate = hasMate;
		this.dateItemPrice = dateItemPrice;
		this.dateItemStatus = dateItemStatus;
		this.dateItemShow = dateItemShow;
		this.dateItemViewer = dateItemViewer;
		this.buyerNo = buyerNo;
		this.isQRCChecked = isQRCChecked;
		this.buyerRep = buyerRep;
		this.sellerRep = sellerRep;
		this.isInstantDate = isInstantDate;
		this.petNo = petNo;
	}

	public Integer getDateItemNo() {
		return dateItemNo;
	}

	public void setDateItemNo(Integer dateItemNo) {
		this.dateItemNo = dateItemNo;
	}

	public Integer getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(Integer sellerNo) {
		this.sellerNo = sellerNo;
	}

	public Integer getRestListNo() {
		return restListNo;
	}

	public void setRestListNo(Integer restListNo) {
		this.restListNo = restListNo;
	}

	public String getDateItemTitle() {
		return dateItemTitle;
	}

	public void setDateItemTitle(String dateItemTitle) {
		this.dateItemTitle = dateItemTitle;
	}

	public byte[] getDateItemImg() {
		return dateItemImg;
	}

	public void setDateItemImg(byte[] dateItemImg) {
		this.dateItemImg = dateItemImg;
	}

	public String getDateItemText() {
		return dateItemText;
	}

	public void setDateItemText(String dateItemText) {
		this.dateItemText = dateItemText;
	}

	public Timestamp getDateItemTime() {
		return dateItemTime;
	}

	public void setDateItemTime(Timestamp dateItemTime) {
		this.dateItemTime = dateItemTime;
	}

	public Timestamp getDateMeetingTime() {
		return dateMeetingTime;
	}

	public void setDateMeetingTime(Timestamp dateMeetingTime) {
		this.dateMeetingTime = dateMeetingTime;
	}

	public String getDateItemLocate() {
		return dateItemLocate;
	}

	public void setDateItemLocate(String dateItemLocate) {
		this.dateItemLocate = dateItemLocate;
	}

	public Integer getDateItemPeople() {
		return dateItemPeople;
	}

	public void setDateItemPeople(Integer dateItemPeople) {
		this.dateItemPeople = dateItemPeople;
	}

	public Boolean getHasMate() {
		return hasMate;
	}

	public void setHasMate(Boolean hasMate) {
		this.hasMate = hasMate;
	}

	public Integer getDateItemPrice() {
		return dateItemPrice;
	}

	public void setDateItemPrice(Integer dateItemPrice) {
		this.dateItemPrice = dateItemPrice;
	}

	public Integer getDateItemStatus() {
		return dateItemStatus;
	}

	public void setDateItemStatus(Integer dateItemStatus) {
		this.dateItemStatus = dateItemStatus;
	}

	public Integer getDateItemViewer() {
		return dateItemViewer;
	}

	public void setDateItemViewer(Integer dateItemViewer) {
		this.dateItemViewer = dateItemViewer;
	}

	public Integer getBuyerNo() {
		return buyerNo;
	}

	public void setBuyerNo(Integer buyerNo) {
		this.buyerNo = buyerNo;
	}

	public Boolean getIsQRCChecked() {
		return isQRCChecked;
	}

	public void setIsQRCChecked(Boolean isQRCChecked) {
		this.isQRCChecked = isQRCChecked;
	}

	public Integer getBuyerRep() {
		return buyerRep;
	}

	public void setBuyerRep(Integer buyerRep) {
		this.buyerRep = buyerRep;
	}

	public Integer getSellerRep() {
		return sellerRep;
	}

	public void setSellerRep(Integer sellerRep) {
		this.sellerRep = sellerRep;
	}

	public Integer getDateItemShow() {
		return dateItemShow;
	}

	public void setDateItemShow(Integer dateItemShow) {
		this.dateItemShow = dateItemShow;
	}

	public Boolean getIsInstantDate() {
		return isInstantDate;
	}

	public void setIsInstantDate(Boolean isInstantDate) {
		this.isInstantDate = isInstantDate;
		
	
	}

	public Integer getPetNo() {
		return petNo;
	}

	public void setPetNo(Integer petNo) {
		this.petNo = petNo;
	};
	
	
	
}
