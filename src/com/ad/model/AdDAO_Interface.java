package com.ad.model;

import java.util.*;

public interface AdDAO_Interface {	
	
	public void insert(Ad ad);
	public void update(Ad ad);
	public void delete(int adNo);
	public Ad findByPrimaryKey(int adNo);
	public List<Ad> getAll();
		
}