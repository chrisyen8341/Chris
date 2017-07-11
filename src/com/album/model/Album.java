package com.album.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Album implements Serializable {
	private Integer albumNo;	
	private Integer memNo;	
	private String albumTitle;	
	private Timestamp albumCreatedTime;	
	private Timestamp  albumModifiedTime;	
	private Integer albumStatus;	
	private byte[] albumImgFile;	
	
	public Album(){}

	public Album(Integer albumNo, Integer memNo, String albumTitle, Timestamp albumCreatedTime,
			Timestamp albumModifiedTime, Integer albumStatus, byte[] albumImgFile) {
		super();
		this.albumNo = albumNo;
		this.memNo = memNo;
		this.albumTitle = albumTitle;
		this.albumCreatedTime = albumCreatedTime;
		this.albumModifiedTime = albumModifiedTime;
		this.albumStatus = albumStatus;
		this.albumImgFile = albumImgFile;
	}

	public Integer getAlbumNo() {
		return albumNo;
	}

	public void setAlbumNo(Integer albumNo) {
		this.albumNo = albumNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public Timestamp getAlbumCreatedTime() {
		return albumCreatedTime;
	}

	public void setAlbumCreatedTime(Timestamp albumCreatedTime) {
		this.albumCreatedTime = albumCreatedTime;
	}

	public Timestamp getAlbumModifiedTime() {
		return albumModifiedTime;
	}

	public void setAlbumModifiedTime(Timestamp albumModifiedTime) {
		this.albumModifiedTime = albumModifiedTime;
	}

	public Integer getAlbumStatus() {
		return albumStatus;
	}

	public void setAlbumStatus(Integer albumStatus) {
		this.albumStatus = albumStatus;
	}

	public byte[] getAlbumImgFile() {
		return albumImgFile;
	}

	public void setAlbumImgFile(byte[] albumImgFile) {
		this.albumImgFile = albumImgFile;
	}

	
	

}
