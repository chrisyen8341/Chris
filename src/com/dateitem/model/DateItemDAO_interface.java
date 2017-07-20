package com.dateitem.model;

import java.util.List;


	public interface DateItemDAO_interface {
		void add(DateItemVO dateItem);
		void update(DateItemVO dateItem);
		void delete(int dateItemNo);
		DateItemVO findByPk(int dateItemNo);
		List<DateItemVO> getAll();
	}
	

