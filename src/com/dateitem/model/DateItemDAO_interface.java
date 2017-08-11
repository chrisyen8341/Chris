package com.dateitem.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.emp.model.Emp;


	public interface DateItemDAO_interface {
		void add(DateItemVO dateItem);
		void update(DateItemVO dateItem);
		void delete(int dateItemNo);
		DateItemVO findByPk(int dateItemNo);
		List<DateItemVO> getAll();
		List<DateItemVO> getAllItems();
		List<DateItemVO> findBySeller_future(int sellerNo);
		List<DateItemVO> findBySeller_history(int sellerNo);
		List<DateItemVO> findBySeller_onsale(int sellerNo);
		List<DateItemVO> findByBuyer_future(int buyerNo);
		List<DateItemVO> findByBuyer_history(int buyerNo);
		void updateByVO(DateItemVO dateItemVO);
		List<DateItemVO> getAll(Map<String, String[]> map);
		List<SDateItemVO> findByDate(String date);
		
	}
	

