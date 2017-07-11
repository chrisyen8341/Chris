package com.album.model;

import java.util.List;



public interface AlbumDAO_interface {
	void add(Album album);
	void update(Album album);
	void delete(int albumNo);
	Album findByPk(int albumNo);
	List<Album> getAll();
}
