package com.product.model;

import java.sql.Date;
import java.util.List;



public class ProductService {
	private Product_interface dao;

	public ProductService() {
		dao = new ProductJDBCDAO();
	}

	public Product addProduct(Integer prodNo, String prodName, Integer prodPrice, byte[] prodImg, String prodDescpt, Date prodAdd,
			Date prodOut, Integer prodState, String prodType) {

		Product product = new Product();

		product.setProdNo(prodNo);
		product.setProdName(prodName);
		product.setProdPrice(prodPrice);
		product.setProdImg(prodImg);
		product.setProdDescpt(prodDescpt);
		product.setProdAdd(prodAdd);
		product.setProdOut(prodOut);
		product.setProdState(prodState);
		product.setProdType(prodType);
		dao.add(product);

		return product;
	}
	
	
	public Product updateProduct(Integer prodNo, String prodName, Integer prodPrice, byte[] prodImg, String prodDescpt, Date prodAdd,
			Date prodOut, Integer prodState, String prodType) {

		Product product = new Product();

		product.setProdNo(prodNo);
		product.setProdName(prodName);
		product.setProdPrice(prodPrice);
		product.setProdImg(prodImg);
		product.setProdDescpt(prodDescpt);
		product.setProdAdd(prodAdd);
		product.setProdOut(prodOut);
		product.setProdState(prodState);
		product.setProdType(prodType);
		dao.update(product);

		return dao.findByPk(prodNo);
	}
	
	

	public void deleteProduct(Integer prodNo) {
		dao.delete(prodNo);
	}

	public Product getOneProduct(Integer prodNo) {
		return dao.findByPk(prodNo);
	}

	public List<Product> getAll() {
		return dao.getAll();
	}
}
