package com.pet.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class Pet implements Serializable {
	

	private Integer petNo;
	private Integer memNo;
	private String petName;
	private String petKind;
	private Integer petGender;
	private String petSpecies;
	private String petIntro;
	private Date petBday;
	private byte[] petImg;
	private Integer petStatus;
	
	public Pet(){}

	public Pet(Integer petNo, Integer memNo, String petName, String petKind, Integer petGender, String petSpecies,
			String petIntro, Date petBday, byte[] petImg,Integer petStatus) {
		super();
		this.petNo = petNo;
		this.memNo = memNo;
		this.petName = petName;
		this.petKind = petKind;
		this.petGender = petGender;
		this.petSpecies = petSpecies;
		this.petIntro = petIntro;
		this.petBday = petBday;
		this.petImg = petImg;
		this.petStatus = petStatus;
	}

	public Integer getPetNo() {
		return petNo;
	}

	public void setPetNo(Integer petNo) {
		this.petNo = petNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
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

	public String getPetIntro() {
		return petIntro;
	}

	public void setPetIntro(String petIntro) {
		this.petIntro = petIntro;
	}

	public Date getPetBday() {
		return petBday;
	}

	public void setPetBday(Date petBday) {
		this.petBday = petBday;
	}

	public byte[] getPetImg() {
		return petImg;
	}

	public void setPetImg(byte[] petImg) {
		this.petImg = petImg;
	}

	public Integer getPetStatus() {
		return petStatus;
	}

	public void setPetStatus(Integer petStatus) {
		this.petStatus = petStatus;
	}
	

}
