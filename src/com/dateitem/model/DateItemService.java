package com.dateitem.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;


public class DateItemService {

	private DateItemDAO_interface dao;
	
	public DateItemService() {
		dao = new DateItemDAO();
	}
	
	
	
	public DateItemVO addDateItem(Integer sellerNo, Integer restListNo, String dateItemTitle, byte[] dateItemImg,
			String dateItemText, Timestamp dateItemTime, Timestamp dateMeetingTime, String dateItemLocate, Integer dateItemPeople,
			Boolean hasMate, Integer dateItemPrice, Integer dateItemStatus, Integer dateItemShow, Integer dateItemViewer, Integer buyerNo,
			Boolean isQRCChecked, Integer buyerRep, Integer sellerRep, Boolean isInstantDate , Integer petNo) {
		
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
		dateItemVO.setDateItemShow(dateItemShow);
		dateItemVO.setDateItemViewer(dateItemViewer);
		dateItemVO.setBuyerNo(buyerNo);
		dateItemVO.setIsQRCChecked(isQRCChecked);
		dateItemVO.setBuyerRep(buyerRep);
		dateItemVO.setSellerRep(sellerRep);
		dateItemVO.setIsInstantDate(isInstantDate);
		dateItemVO.setPetNo(petNo);
		System.out.println("rdy to call dao.add");
		dao.add(dateItemVO);
		System.out.println("suppose not to be here");
		
		return dateItemVO;
	}
	
	
	public DateItemVO updateDateItem(Integer dateItemNo, Integer sellerNo, Integer restListNo, String dateItemTitle, byte[] dateItemImg,
			String dateItemText, Timestamp dateItemTime, Timestamp dateMeetingTime, String dateItemLocate, Integer dateItemPeople,
			Boolean hasMate, Integer dateItemPrice, Integer dateItemStatus, Integer dateItemShow, Integer dateItemViewer, Integer buyerNo,
			Boolean isQRCChecked, Integer buyerRep, Integer sellerRep, Boolean isInstantDate , Integer petOrdNo) {
		
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
		dateItemVO.setPetNo(petOrdNo);
		dao.update(dateItemVO);
		
		return dateItemVO;
	}

	public List<DateItemVO> findByBuyer_future(int buyerNo){		
		return dao.findByBuyer_future(buyerNo);
	}
	
	public List<DateItemVO> findByBuyer_history(int buyerNo){		
		return dao.findByBuyer_history(buyerNo);
	}
	
	public List<DateItemVO> findBySeller_future(int sellerNo){		
		return dao.findBySeller_future(sellerNo);
	}
	
	public List<DateItemVO> findBySeller_history(int sellerNo){		
		return dao.findBySeller_history(sellerNo);
	}
	
	public List<DateItemVO> findBySeller_onsale(int sellerNo){		
		return dao.findBySeller_onsale(sellerNo);
	}
	
	
	public void deleteDateItem(Integer dateItemNo) {
		dao.delete(dateItemNo);
	}

	public DateItemVO getOneDateItem(Integer dateItemNo) {
		return dao.findByPk(dateItemNo);
	}

	public List<DateItemVO> getAll(){
		return dao.getAll();
	}
	//getAllItems是顯示上架狀態=0的商品
	public List<DateItemVO> getAllItems(){
		return dao.getAllItems();
	}
	
	public DateItemVO findByPK(int dateItemNo){
		return dao.findByPk(dateItemNo);
	}
	
	//將日期改為前端約會商品的顯示模式
	public String getTimeForItem(Timestamp ts){
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH'點'mm分");
	String timeStr = sdf.format(ts);
	return timeStr;
	}
	
	//顯示年月日
	public String getTimeForYMD(Timestamp ts){
	SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd");
	String timeStr = sdf.format(ts);
	return timeStr;
	}
	
	public void updateByVO(DateItemVO dateItemVO){
		dao.updateByVO(dateItemVO);
	}
	
	
}
