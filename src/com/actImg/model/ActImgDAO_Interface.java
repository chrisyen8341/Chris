package com.actImg.model;

import java.util.List;

import com.activity.model.Activity;

public interface ActImgDAO_Interface {
	void add(ActImg actImg);
	void update(ActImg actImg);
	void delete(Integer actImgNo);
	ActImg findByPK(Integer actImgNo);
	List<ActImg> getAll();
}
