package com.albumimg.model;

import java.sql.Timestamp;
import java.util.List;


public class AlbumImgService {
	private AlbumImgDAO_interface dao;
	
	public AlbumImgService(){
		dao=new AlbumImgDAO();
	}
	
	public AlbumImg addAlbumImg(Integer albumNo, String imgTitle, String imgDesc, Timestamp imgCreatedTime,
			Timestamp imgModifiedTime, String imgFileName, String imgExtName, byte[] imgFile) {

		AlbumImg albumImg = new AlbumImg();
		albumImg.setAlbumNo(albumNo);
		albumImg.setImgTitle(imgTitle);
		albumImg.setImgDesc(imgDesc);
		albumImg.setImgCreatedTime(imgCreatedTime);
		albumImg.setImgModifiedTime(imgModifiedTime);
		albumImg.setImgFileName(imgFileName);
		albumImg.setImgExtName(imgExtName);
		albumImg.setImgFile(imgFile);
		dao.add(albumImg);

		return albumImg ;
	}

	public AlbumImg updateAlbumImg(Integer imgNo, Integer albumNo, String imgTitle, String imgDesc, Timestamp imgCreatedTime,
			Timestamp imgModifiedTime, String imgFileName, String imgExtName, byte[] imgFile) {

		AlbumImg albumImg = new AlbumImg();
		albumImg.setImgNo(imgNo);
		albumImg.setAlbumNo(albumNo);
		albumImg.setImgTitle(imgTitle);
		albumImg.setImgDesc(imgDesc);
		albumImg.setImgCreatedTime(imgCreatedTime);
		albumImg.setImgModifiedTime(imgModifiedTime);
		albumImg.setImgFileName(imgFileName);
		albumImg.setImgExtName(imgExtName);
		albumImg.setImgFile(imgFile);
		dao.update(albumImg);

		return albumImg;
	}

	public void deleteAlbumImg(Integer albumImgNo) {
		dao.delete(albumImgNo);
	}

	public AlbumImg getOneAlbumImg(Integer albumImgNo) {
		return dao.findByPk(albumImgNo);
	}

	public List<AlbumImg> getAll() {
		return dao.getAll();
	}
}
