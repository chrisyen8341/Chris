package com.album.model;

import java.sql.Timestamp;
import java.util.List;


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

	public List<Album> getAll() {
		return dao.getAll();
	}
}
