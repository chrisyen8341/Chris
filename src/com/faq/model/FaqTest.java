package com.faq.model;

import java.util.List;

public class FaqTest {

	public static void main(String[] args) {
		FaqJDBCDAO dao = new FaqJDBCDAO();

		// 新增
//		Faq faq1 = new Faq();
//		faq1.setEmpNo(1);
//		faq1.setFaqCategory("寵物走失");
//		faq1.setFaqTitle("我的寵物走失了怎麼辦");
//		faq1.setFaqAnswer("報警阿廢話");
//		dao.insert(faq1);
//		System.out.println("新增完惹");

//		 修改
//		Faq faq2 = new Faq();
//		faq2.setEmpNo(2);
//		faq2.setFaqCategory("買方常見問題");
//		faq2.setFaqTitle("賣方放我粉鳥怎麼辦");
//		faq2.setFaqAnswer("快來申訴");
//		faq2.setFaqNo(2);
//		dao.update(faq2);
//		System.out.println("修改完惹");

//		 刪除		之後要挑PKFK以外的刪
//		dao.delete(6);
//		System.out.println("刪掉惹,糟糕");

//		 查詢
//		Faq faq3 = dao.findByPrimaryKey(4);
//		System.out.println(faq3.getFaqNo());
//		System.out.println(faq3.getEmpNo());
//		System.out.println(faq3.getFaqCategory());
//		System.out.println(faq3.getFaqTitle());
//		System.out.println(faq3.getFaqAnswer());
//		System.out.println("找完惹");

//		 查全部
//		 List<Faq> faqList=dao.getAll();
//		 for(Faq faq:faqList){
//		 System.out.println(faq.getFaqNo());
//		 System.out.println(faq.getEmpNo());
//		 System.out.println(faq.getFaqCategory());
//		 System.out.println(faq.getFaqTitle());
//		 System.out.println(faq.getFaqAnswer());
//		 System.out.println("===================");
//		 }
	}	 

}