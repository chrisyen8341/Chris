package com.charge.model;

import java.util.List;

import com.orderlist.model.OrderList;

public interface Charge_interface {
	void add(Charges charges);
	void update(Charges charges);
	void delete(int chargeNo);
	Charges findByPk(int chargeNo);
	List<Charges> getAll();
}
