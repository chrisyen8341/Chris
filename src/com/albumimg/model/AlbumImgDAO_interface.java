package com.albumimg.model;

import java.sql.Connection;
import java.util.List;


public interface AlbumImgDAO_interface {
	void add(AlbumImg albumImg);
	void add2(AlbumImg albumImg,Connection con);
	Integer add3(AlbumImg albumImg);
	void update(AlbumImg albumImg);
	void update2(AlbumImg albumImg,Connection con);
	void delete(int imgNo);
	AlbumImg findByPk(int imgNo);
	List<AlbumImg> getAll();
}
