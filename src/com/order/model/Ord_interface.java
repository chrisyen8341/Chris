package com.order.model;

import java.util.List;

public interface Ord_interface {
	void add(Ord order);
	void update(Ord order);
	void delete(int ordNo);
	Ord findByPk(int ordNo);
	List<Ord> getAll();
	
}
