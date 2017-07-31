package com.album.model;

import java.util.List;

import com.albumimg.model.AlbumImg;



public interface AlbumDAO_interface {
	void add(Album album);
	void addWithImg(Album album,List<AlbumImg> aImgs);
	void update(Album album);
	void delete(int albumNo);
	Album findByPk(int albumNo);
	List<Album> getAll();
}
