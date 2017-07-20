package com.dateitem.model;

import java.sql.Date;
import java.util.List;


public class DateItemService {

	private DateItemDAO_interface dao;
	
	public DateItemService() {
		dao = new DateItemDAO();
	}
	
	public DateItemVO addDateItem(Integer sellerNo, Integer restListNo, String dateItemTitle, byte[] dateItemImg,
			String dateItemText, Date dateItemTime, Date dateMeetingTime, String dateItemLocate, Integer dateItemPeople,
			Boolean hasMate, Integer dateItemPrice, Integer dateItemStatus, Integer dateItemShow, Integer dateItemViewer, Integer buyerNo,
			Boolean isQRCChecked, Integer buyerRep, Integer sellerRep, Boolean isInstantDate) {
		
		DateItemVO dateItemVO = new DateItemVO();
		
		dateItemVO.setSellerNo(sellerNo);
		dateItemVO.setRestListNo(restListNo);
		dateItemVO.setDateItemTitle(dateItemTitle);
		dateItemVO.setDateItemImg(dateItemImg);
		dateItemVO.setDateItemText(dateItemText);
		dateItemVO.setDateItemTime(dateItemTime);
		dateItemVO.setDateMeetingTime(dateMeetingTime);
		dateItemVO.setDateItemLocate(dateItemLocate);
		dateItemVO.setDateItemPeople(dateItemPeople);
		dateItemVO.setHasMate(hasMate);
		dateItemVO.setDateItemPrice(dateItemPrice);
		dateItemVO.setDateItemStatus(dateItemStatus);
		dateItemVO.setDateItemViewer(dateItemViewer);
		dateItemVO.setBuyerNo(buyerNo);
		dateItemVO.setIsQRCChecked(isQRCChecked);
		dateItemVO.setBuyerRep(buyerRep);
		dateItemVO.setSellerRep(sellerRep);
		dateItemVO.setIsInstantDate(isInstantDate);
		dao.add(dateItemVO);
		
		return dateItemVO;
	}
	
	
	public DateItemVO updateDateItem(Integer dateItemNo, Integer sellerNo, Integer restListNo, String dateItemTitle, byte[] dateItemImg,
			String dateItemText, Date dateItemTime, Date dateMeetingTime, String dateItemLocate, Integer dateItemPeople,
			Boolean hasMate, Integer dateItemPrice, Integer dateItemStatus, Integer dateItemShow, Integer dateItemViewer, Integer buyerNo,
			Boolean isQRCChecked, Integer buyerRep, Integer sellerRep, Boolean isInstantDate) {
		
		DateItemVO dateItemVO = new DateItemVO();
		
		dateItemVO.setDateItemNo(dateItemNo);
		dateItemVO.setSellerNo(sellerNo);
		dateItemVO.setRestListNo(restListNo);
		dateItemVO.setDateItemTitle(dateItemTitle);
		dateItemVO.setDateItemImg(dateItemImg);
		dateItemVO.setDateItemText(dateItemText);
		dateItemVO.setDateItemTime(dateItemTime);
		dateItemVO.setDateMeetingTime(dateMeetingTime);
		dateItemVO.setDateItemLocate(dateItemLocate);
		dateItemVO.setDateItemPeople(dateItemPeople);
		dateItemVO.setHasMate(hasMate);
		dateItemVO.setDateItemPrice(dateItemPrice);
		dateItemVO.setDateItemStatus(dateItemStatus);
		dateItemVO.setDateItemViewer(dateItemViewer);
		dateItemVO.setBuyerNo(buyerNo);
		dateItemVO.setIsQRCChecked(isQRCChecked);
		dateItemVO.setBuyerRep(buyerRep);
		dateItemVO.setSellerRep(sellerRep);
		dateItemVO.setIsInstantDate(isInstantDate);
		dao.update(dateItemVO);
		
		return dateItemVO;
	}
	
	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public DateItemVO getOneEmp(Integer dateItemNo) {
		return dao.findByPk(dateItemNo);
	}

	public List<DateItemVO> getAll() {
		return dao.getAll();
	}
}
