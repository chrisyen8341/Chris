package com.pet.model;

import java.sql.Blob;
import java.sql.Date;

public class Pet {
	

	private int petNo;
	private int memNo;
	private String petName;
	private String petKind;
	private String petGender;
	private String petSpecies;
	private String petIntro;
	private Date petBday;
	private byte[] petImg;
	
	public Pet(){}

	public Pet(int petNo, int memNo, String petName, String petKind, String petGender, String petSpecies,
			String petIntro, Date petBday, byte[] petImg) {
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
	}

	public int getPetNo() {
		return petNo;
	}

	public void setPetNo(int petNo) {
		this.petNo = petNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
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

	public String getPetGender() {
		return petGender;
	}

	public void setPetGender(String petGender) {
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
	
	


}
