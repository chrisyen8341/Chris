package com.diamsg.model;

import java.util.List;


public interface DiaMsgDAO_Interface {
	
	public void insert(DiaMsg diaMsg);
	public void update(DiaMsg diaMsg);
	public void delete(Integer diaMsgNo);
	public DiaMsg findByPrimaryKey(Integer diaMsgNo);
	public List<DiaMsg> getAll();

}
