package com.restImg.model;

import java.util.List;

public interface RestImgDAO_interface {
	void add(RestImg restImg);
	void update(RestImg restImg);
	void delete(Integer restImgNo);
	RestImg findByPK(Integer restImgNo);
	List<RestImg> getAll();
}
