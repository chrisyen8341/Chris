package com.withdraw.model;

import java.util.List;

import com.order.model.Ord;

public interface Withdraw_interface {
	void add(Withdraw withdraw);
	void update(Withdraw withdraw);
	void delete(int withdrawNo);
	Withdraw findByPk(int withdrawNo);
	List<Withdraw> getAll();
}
