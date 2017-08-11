package com.dateitem.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SDateItemVO implements Serializable {
	private Integer dateItemNo;
	private Integer sellerNo;
	private Integer restListNo;
	private Timestamp dateMeetingTime;
	private String dateItemLocate;
	private Integer dateItemPeople;
	private Boolean hasMate;
	private Integer dateItemPrice;
	private Integer dateItemStatus;
	private Integer dateItemShow;
	private Integer dateItemViewer;
	private Boolean isInstantDate;
	private Integer petNo;
	private String memSname;
	private Integer memGender;
	private String memId;
	private String petName;
	private String petKind;
	private Integer petGender;
	private String petSpecies;
	private Double restLongtitude;
	private Double restLatitude;
	private String restName;
	private String restAdd;
	private String restLocate;
	private String restPhone;
	
	public SDateItemVO(){
		
	}


	
	public SDateItemVO(Integer dateItemNo, Integer sellerNo, Integer restListNo, Timestamp dateMeetingTime,
			String dateItemLocate, Integer dateItemPeople, Boolean hasMate, Integer dateItemPrice,
			Integer dateItemStatus, Integer dateItemShow, Integer dateItemViewer, Boolean isInstantDate, Integer petNo,
			String memSname, Integer memGender, String memId, String petName, String petKind, Integer petGender,
			String petSpecies, Double restLongtitude, Double restLatitude, String restName, String restAdd,
			String restLocate, String restPhone) {
		super();
		this.dateItemNo = dateItemNo;
		this.sellerNo = sellerNo;
		this.restListNo = restListNo;
		this.dateMeetingTime = dateMeetingTime;
		this.dateItemLocate = dateItemLocate;
		this.dateItemPeople = dateItemPeople;
		this.hasMate = hasMate;
		this.dateItemPrice = dateItemPrice;
		this.dateItemStatus = dateItemStatus;
		this.dateItemShow = dateItemShow;
		this.dateItemViewer = dateItemViewer;
		this.isInstantDate = isInstantDate;
		this.petNo = petNo;
		this.memSname = memSname;
		this.memGender = memGender;
		this.memId = memId;
		this.petName = petName;
		this.petKind = petKind;
		this.petGender = petGender;
		this.petSpecies = petSpecies;
		this.restLongtitude = restLongtitude;
		this.restLatitude = restLatitude;
		this.restName = restName;
		this.restAdd = restAdd;
		this.restLocate = restLocate;
		this.restPhone = restPhone;
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
	public Integer getDateItemShow() {
		return dateItemShow;
	}
	public void setDateItemShow(Integer dateItemShow) {
		this.dateItemShow = dateItemShow;
	}
	public Integer getDateItemViewer() {
		return dateItemViewer;
	}
	public void setDateItemViewer(Integer dateItemViewer) {
		this.dateItemViewer = dateItemViewer;
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
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getPetKind() {
		return petKind;
	}
	public void setPetKind(String petKind) {
		this.petKind = petKind;
	}
	public Integer getPetGender() {
		return petGender;
	}
	public void setPetGender(Integer petGender) {
		this.petGender = petGender;
	}
	public String getPetSpecies() {
		return petSpecies;
	}
	public void setPetSpecies(String petSpecies) {
		this.petSpecies = petSpecies;
	}
	public Double getRestLongtitude() {
		return restLongtitude;
	}
	public void setRestLongtitude(Double restLongtitude) {
		this.restLongtitude = restLongtitude;
	}
	public Double getRestLatitude() {
		return restLatitude;
	}
	public void setRestLatitude(Double restLatitude) {
		this.restLatitude = restLatitude;
	}



	public String getRestName() {
		return restName;
	}



	public void setRestName(String restName) {
		this.restName = restName;
	}



	public String getRestAdd() {
		return restAdd;
	}



	public void setRestAdd(String restAdd) {
		this.restAdd = restAdd;
	}



	public String getRestLocate() {
		return restLocate;
	}



	public void setRestLocate(String restLocate) {
		this.restLocate = restLocate;
	}



	public String getRestPhone() {
		return restPhone;
	}



	public void setRestPhone(String restPhone) {
		this.restPhone = restPhone;
	}
	

	
	
}
