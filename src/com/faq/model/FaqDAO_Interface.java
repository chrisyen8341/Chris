package com.faq.model;

import java.util.List;

public interface FaqDAO_Interface {
	
	public void insert(Faq faq);
	public void update(Faq faq);
	public void delete(int faqNo);
	public Faq findByPrimaryKey(int adNo);
	public List<Faq> getAll();	
}