package com.orderlist.model;

import java.util.List;

import com.order.model.Ord;

public interface OrderList_interface {
	void add(OrderList ol);
	void update(OrderList ol);
	void delete(int olNo);
	OrderList findByPk(int olNo);
	List<OrderList> getAll();
}
