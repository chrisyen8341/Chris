package com.auth.model;

import java.util.List;


public interface AuthDAO_interface {
	void add(Auth auth);
	void update(Auth auth);
	void delete(int authNo);
	Auth findByPk(int authNo);
	List<Auth> getAll();
}
