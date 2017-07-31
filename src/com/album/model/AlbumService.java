package com.album.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.albumimg.model.AlbumImg;


public class AlbumService {
	
	private AlbumDAO_interface dao;
	
	public AlbumService(){
		dao=new AlbumDAO();
	}
	
	public Album addAlbum(Integer memNo, String albumTitle, Timestamp albumCreatedTime,
			Timestamp albumModifiedTime, Integer albumStatus, byte[] albumImgFile) {

		Album album = new Album();
		album.setMemNo(memNo);
		album.setAlbumTitle(albumTitle);
		album.setAlbumCreatedTime(albumCreatedTime);
		album.setAlbumModifiedTime(albumModifiedTime);
		album.setAlbumStatus(albumStatus);
		album.setAlbumImgFile(albumImgFile);
		dao.add(album);

		return album ;
	}
	
	
	public Integer addAlbum2(Integer memNo, String albumTitle, Timestamp albumCreatedTime,
			Timestamp albumModifiedTime, Integer albumStatus, byte[] albumImgFile) {

		Album album = new Album();
		album.setMemNo(memNo);
		album.setAlbumTitle(albumTitle);
		album.setAlbumCreatedTime(albumCreatedTime);
		album.setAlbumModifiedTime(albumModifiedTime);
		album.setAlbumStatus(albumStatus);
		album.setAlbumImgFile(albumImgFile);
		Integer albumNo=dao.add2(album);

		return albumNo ;
	}
	
	
	public Album addAlbumWithImg(Integer memNo, String albumTitle, Timestamp albumCreatedTime,
			Timestamp albumModifiedTime, Integer albumStatus, byte[] albumImgFile,List<AlbumImg> aImgs) {

		Album album = new Album();
		album.setMemNo(memNo);
		album.setAlbumTitle(albumTitle);
		album.setAlbumCreatedTime(albumCreatedTime);
		album.setAlbumModifiedTime(albumModifiedTime);
		album.setAlbumStatus(albumStatus);
		album.setAlbumImgFile(albumImgFile);
		dao.addWithImg(album, aImgs);
		return album ;
	}
	
	

	public Album updateAlbum(Integer albumNo, Integer memNo, String albumTitle, Timestamp albumCreatedTime,
			Timestamp albumModifiedTime, Integer albumStatus, byte[] albumImgFile) {

		Album album = new Album();
		album.setAlbumNo(albumNo);
		album.setMemNo(memNo);
		album.setAlbumTitle(albumTitle);
		album.setAlbumCreatedTime(albumCreatedTime);
		album.setAlbumModifiedTime(albumModifiedTime);
		album.setAlbumStatus(albumStatus);
		album.setAlbumImgFile(albumImgFile);
		dao.update(album);

		return album;
	}

	public void deleteAlbum(Integer albumNo) {
		dao.delete(albumNo);
	}

	public Album getOneAlbum(Integer albumNo) {
		return dao.findByPk(albumNo);
	}

	public Set<AlbumImg> getAlbumImgs(Integer albumNo) {
		return dao.findAImgsByAlbumNo(albumNo);
	}
	
	
	public List<Album> getAll() {
		return dao.getAll();
	}
}
