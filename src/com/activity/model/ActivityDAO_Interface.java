package com.activity.model;

import java.util.List;

public interface ActivityDAO_Interface {
	void add(Activity activity);
	void update(Activity activity);
	void delete(Integer actNo);
	Activity findByPK(Integer actNo);
	List<Activity> getAll();
}
