package com.product.model;

import java.util.List;



public interface Product_interface {
	void add(Product prod);
	void update(Product prod);
	void delete(int prodNo);
	Product findByPk(int prodNo);
	List<Product> getAll();
}
