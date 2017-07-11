package com.albumimg.model;

import java.util.List;


public interface AlbumImgDAO_interface {
	void add(AlbumImg albumImg);
	void update(AlbumImg albumImg);
	void delete(int imgNo);
	AlbumImg findByPk(int imgNo);
	List<AlbumImg> getAll();
}
