package com.withdraw.model;

import java.sql.Date;
import java.util.List;



public class WithdrawService {
	private Withdraw_interface dao;

	public WithdrawService() {
		dao = new WithdrawJDBCDAO();
	}

	public Withdraw addWithdraw(Integer withdrawNo, Integer memNo, Integer withdrawNum, Date applyTime, Integer withdrawStatus) {

		Withdraw withdraw = new Withdraw();

		withdraw.setWithdrawNo(withdrawNo);
		withdraw.setMemNo(memNo);
		withdraw.setWithdrawNum(withdrawNum);
		withdraw.setApplyTime(applyTime);
		withdraw.setWithdrawStatus(withdrawStatus);
		dao.add(withdraw);

		return withdraw;
	}
	
	
	public Withdraw updateWithdraw(Integer withdrawNo, Integer memNo, Integer withdrawNum, Date applyTime, Integer withdrawStatus) {

		Withdraw withdraw = new Withdraw();

		withdraw.setWithdrawNo(withdrawNo);
		withdraw.setMemNo(memNo);
		withdraw.setWithdrawNum(withdrawNum);
		withdraw.setApplyTime(applyTime);
		withdraw.setWithdrawStatus(withdrawStatus);
		dao.update(withdraw);

		return dao.findByPk(withdrawNo);
	}
	
	

	public void deleteWithdraw(Integer withdrawNo) {
		dao.delete(withdrawNo);
	}

	public Withdraw getOneWithdraw(Integer withdrawNo) {
		return dao.findByPk(withdrawNo);
	}

	public List<Withdraw> getAll() {
		return dao.getAll();
	}
}
