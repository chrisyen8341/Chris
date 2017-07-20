package com.order.model;

import java.sql.Date;
import java.util.List;



public class OrdService {
	
	private Ord_interface dao;

	public OrdService() {
		dao = new OrdJDBCDAO();
	}

	public Ord addOrd(Integer ordNo, Integer memNo, Date ordDate, Date ordCheck, Date ordMoney, Date ordProduct,
			Date ordClose, Integer ordStatus, Integer ordMode, Integer ordShip, Integer ordTotal, String conName,
			String conAdd, String conTel) {

		Ord ord = new Ord();

		ord.setOrdNo(ordNo);
		ord.setMemNo(memNo);
		ord.setOrdDate(ordDate);
		ord.setOrdCheck(ordCheck);
		ord.setOrdProduct(ordProduct);
		ord.setOrdClose(ordClose);
		ord.setOrdStatus(ordStatus);
		ord.setOrdShip(ordShip);
		ord.setOrdTotal(ordTotal);
		ord.setConName(conName);
		ord.setConAdd(conAdd);
		ord.setConTel(conTel);
		dao.add(ord);

		return ord;
	}
	
	
	
	public Ord updateOrd(Integer ordNo, Integer memNo, Date ordDate, Date ordCheck, Date ordMoney, Date ordProduct,
			Date ordClose, Integer ordStatus, Integer ordMode, Integer ordShip, Integer ordTotal, String conName,
			String conAdd, String conTel) {

		Ord ord = new Ord();

		ord.setOrdNo(ordNo);
		ord.setMemNo(memNo);
		ord.setOrdDate(ordDate);
		ord.setOrdCheck(ordCheck);
		ord.setOrdProduct(ordProduct);
		ord.setOrdClose(ordClose);
		ord.setOrdStatus(ordStatus);
		ord.setOrdShip(ordShip);
		ord.setOrdTotal(ordTotal);
		ord.setConName(conName);
		ord.setConAdd(conAdd);
		ord.setConTel(conTel);
		dao.update(ord);

		return dao.findByPk(ordNo);
	}
	

	public void deleteOrd(Integer ordNo) {
		dao.delete(ordNo);
	}

	public Ord getOneOrd(Integer ordNo) {
		return dao.findByPk(ordNo);
	}

	public List<Ord> getAll() {
		return dao.getAll();
	}

}
