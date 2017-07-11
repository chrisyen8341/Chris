package com.albumimg.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AlbumImg implements Serializable{
	private Integer imgNo;	
	private Integer albumNo;	
	private String imgTitle;	
	private String imgDesc;	
	private Timestamp imgCreatedTime;	
	private Timestamp imgModifiedTime;	
	private String imgFileName;	
	private String imgExtName;	
	private byte[] imgFile;
	
	public AlbumImg(){}
	
	
	public AlbumImg(Integer imgNo, Integer albumNo, String imgTitle, String imgDesc, Timestamp imgCreatedTime,
			Timestamp imgModifiedTime, String imgFileName, String imgExtName, byte[] imgFile) {
		super();
		this.imgNo = imgNo;
		this.albumNo = albumNo;
		this.imgTitle = imgTitle;
		this.imgDesc = imgDesc;
		this.imgCreatedTime = imgCreatedTime;
		this.imgModifiedTime = imgModifiedTime;
		this.imgFileName = imgFileName;
		this.imgExtName = imgExtName;
		this.imgFile = imgFile;
	}


	public Integer getImgNo() {
		return imgNo;
	}


	public void setImgNo(Integer imgNo) {
		this.imgNo = imgNo;
	}


	public Integer getAlbumNo() {
		return albumNo;
	}


	public void setAlbumNo(Integer albumNo) {
		this.albumNo = albumNo;
	}


	public String getImgTitle() {
		return imgTitle;
	}


	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}


	public String getImgDesc() {
		return imgDesc;
	}


	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}


	public Timestamp getImgCreatedTime() {
		return imgCreatedTime;
	}


	public void setImgCreatedTime(Timestamp imgCreatedTime) {
		this.imgCreatedTime = imgCreatedTime;
	}


	public Timestamp getImgModifiedTime() {
		return imgModifiedTime;
	}


	public void setImgModifiedTime(Timestamp imgModifiedTime) {
		this.imgModifiedTime = imgModifiedTime;
	}


	public String getImgFileName() {
		return imgFileName;
	}


	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}


	public String getImgExtName() {
		return imgExtName;
	}


	public void setImgExtName(String imgExtName) {
		this.imgExtName = imgExtName;
	}


	public byte[] getImgFile() {
		return imgFile;
	}


	public void setImgFile(byte[] imgFile) {
		this.imgFile = imgFile;
	}	


}
