package com.album.model;

import java.util.List;
import java.util.Set;

import com.albumimg.model.AlbumImg;



public interface AlbumDAO_interface {
	void add(Album album);
	Integer add2(Album album);
	void addWithImg(Album album,List<AlbumImg> aImgs);
	void update(Album album);
	void delete(int albumNo);
	Album findByPk(int albumNo);
	Set<AlbumImg> findAImgsByAlbumNo(Integer albumNo);
	List<Album> getAll();
}
