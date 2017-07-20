package com.slide.model;

import java.util.List;

public interface SlideDAO_Interface {
	
	public void insert(Slide slide);
	public void update(Slide slide);
	public void delete(int slideNo);
	public Slide findByPrimaryKey(int slideNo);
	public List<Slide> getAll();	
}