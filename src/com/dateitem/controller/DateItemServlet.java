package com.dateitem.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dateitem.model.*;
import com.dateitem.model.DateItemService;
import com.dateitem.model.DateItemVO;
import com.emp.model.Emp;
import com.emp.model.EmpService;
import com.member.model.Member;
import com.member.model.MemberService;
import com.pet.model.Pet;
import com.pet.model.PetService;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantService;
@WebServlet("/front_end/dateitem/dateitem.do")
@MultipartConfig(fileSizeThreshold = 500 * 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024
		* 1024)
public class DateItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		String action = req.getParameter("action");

		// 來自select_page.jsp上架約會商品
		if ("check_Seller".equals(action)) {

			MemberService memSvc = new MemberService();
			List<Pet> myPetList = new ArrayList<Pet>();

			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("member");

			myPetList = memSvc.getPetsByMemNo(member.getMemNo());
			System.out.println(myPetList.size());

			// 沒寵物的使用者導到寵物註冊
			if (myPetList.size() == 0) {
				String url = "/front_end/pet/petRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {// 有寵物的使用者到上架
				req.setAttribute("myPetList", myPetList);
				String url = "/front_end/dateitem/addDateItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}

		// 買家檢視已購買但未完成交易的商品
		if ("list_buyer_future".equals(action)) { // 來自select_page.jsp上架約會商品

			// DateItemService dSvc = new DateItemService();
			// List<DateItemVO> futurelist = new ArrayList<DateItemVO>();
			// futurelist = dSvc.findByBuyer_future(5007);
			//
			// req.setAttribute("futurelist", futurelist);
			String url = "/front_end/dateitem/list_buyer_future.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																			// update_emp_input.jsp
			successView.forward(req, res);

		}

		// 買家檢視已完成交易的歷史商品
		if ("list_buyer_history".equals(action)) { // 來自select_page.jsp上架約會商品

			String url = "/front_end/dateitem/list_buyer_history.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																			// update_emp_input.jsp
			successView.forward(req, res);

		}

		// 買家購買一個商品
		if ("buy_date".equals(action)) {
			// 先跳過檢查儲值的部分
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("member");
			int buyerNo = member.getMemNo();

			System.out.println(req.getParameter("dateItemNo"));

			Integer dateItemNo = new Integer(req.getParameter("dateItemNo").trim());
			DateItemService dSvc = new DateItemService();
			DateItemVO dateItemVO = dSvc.findByPK(dateItemNo);

			dateItemVO.setBuyerNo(buyerNo);
			dateItemVO.setDateItemStatus(1);
			dateItemVO.setDateItemShow(1);
			dSvc.updateByVO(dateItemVO);
			System.out.println(dateItemVO.getBuyerNo());
			System.out.println(dateItemVO.getDateItemShow());

			// 把剛剛購買的物件setAttribute,並轉購買紀錄檢視
			req.setAttribute("itemPurchased", dateItemVO);
			String url = "/front_end/dateitem/list_buyer_future.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		// 取消一個商品
		if ("cancel_date".equals(action)) {
			// 先跳過檢查儲值的部分

			System.out.println(req.getParameter("dateItemNo"));

			Integer dateItemNo = new Integer(req.getParameter("dateItemNo").trim());
			DateItemService dSvc = new DateItemService();
			DateItemVO dateItemVO = dSvc.findByPK(dateItemNo);

			dateItemVO.setDateItemStatus(2);
			dateItemVO.setDateItemShow(1);
			dSvc.updateByVO(dateItemVO);

			// 分辨取消來自買方還是賣方,分別導回買賣方的歷史紀錄
			try {
				req.setAttribute("itemCanceled", dateItemVO);
				if (req.getParameter("fromwho").equals("buyer")) {
					String url = "/front_end/dateitem/list_buyer_history.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				if (req.getParameter("fromwho").equals("seller")) {
					String url = "/front_end/dateitem/list_seller_history.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
			} catch (Exception e) {
				String url = "/front_end/dateitem/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}

		if ("checkTime".equals(action)) {
			String strNo = req.getParameter("dateItemNo");
			System.out.println(strNo);

			//
			// Integer dateItemNo = new
			// Integer(req.getParameter("dateItemNo").trim());
			// DateItemService dSvc = new DateItemService();
			// DateItemVO dateItemVO = dSvc.findByPK(dateItemNo);
			//
			// List<DateItemVO> historyList =
			// dSvc.findByBuyer_future(dateItemNo);
			// for (DateItemVO item: historyList){
			// int count=0;
			// SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
			// if(sdf.format(item.getDateMeetingTime()).equals(sdf.format(dateItemVO.getDateMeetingTime()))){
			// count++;
			// }
			//
			// if(count>0){
			// PrintWriter out = res.getWriter();
			// out.print("data-toggle=\"modal\" data-target=\"#myModal\"");
			//
			// }else{
			//
			// String url =
			// req.getContextPath()+"/front_end/dateitem/dateitem.do?action=buy_date";
			// RequestDispatcher successView = req.getRequestDispatcher(url);//
			// 成功轉交 update_emp_input.jsp
			// successView.forward(req, res);
			//
			// }
			// }
			//
		}

		if ("insert".equals(action)) { // 來自addDateItem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				String htmltime = req.getParameter("time");
				System.out.println(htmltime);

				Timestamp dateMeetingTime = getTimestamp(htmltime);
				System.out.println(getTimestamp(htmltime));
				Timestamp dateItemTime = new Timestamp(System.currentTimeMillis());

				// Send the use back to the form, if there were errors

				String dateItemTitle = req.getParameter("dateItemTitle");
				if (dateItemTitle == null || (dateItemTitle.trim()).length() == 0) {
					errorMsgs.add("請輸入標題");
					dateItemTitle = "";
				}
				// Send the use back to the form, if there were errors

				String dateItemText = req.getParameter("dateItemText");
				if (dateItemText == null || (dateItemText.trim()).length() == 0) {
					errorMsgs.add("請輸入內容");
					dateItemText = "";
				}
				// Send the use back to the form, if there were errors

				// 驗證圖片跟日期=====================================================
				byte[] dateItemImg = null;
				String fileName = null;
				Part part = req.getPart("dateItemImg");

				if (part.getSize() != 0) {
					fileName = getFileNameFromPart(part);
					System.out.println(part.equals(null));
					if (getServletContext().getMimeType(fileName).substring(0, 5).equals("image")) {
						dateItemImg = getByteArrayImg(part);
						System.out.println("圖片格式正確!");
					}
				} else {
					errorMsgs.add("請上傳圖片");
				}

				// ============================================================================

				// 如果錯誤回傳一個VO=====================================

				HttpSession session = req.getSession();
				Member member = (Member) session.getAttribute("member");
				DateItemVO dateItemVO = new DateItemVO();
				dateItemVO.setSellerNo(member.getMemNo());
				dateItemVO.setRestListNo(Integer.parseInt(req.getParameter("restListNo")));
				dateItemVO.setPetNo(Integer.parseInt(req.getParameter("petNo")));
				dateItemVO.setDateItemTitle(dateItemTitle);
				dateItemVO.setDateItemText(dateItemText);
				dateItemVO.setDateItemPrice(Integer.parseInt(req.getParameter("dateItemPrice")));

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// 重新夾帶寵物資訊
					MemberService memSvc = new MemberService();
					List<Pet> myPetList = new ArrayList<Pet>();
					myPetList = memSvc.getPetsByMemNo(member.getMemNo());

					req.setAttribute("myPetList", myPetList);

					req.setAttribute("dateItemVO", dateItemVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/addDateItem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				// sellerno轉型

				int sellerno = member.getMemNo();
				int restListNo = Integer.parseInt(req.getParameter("restListNo"));
				int petNo = Integer.parseInt(req.getParameter("petNo"));
				int dateItemPeople = Integer.parseInt(req.getParameter("dateItemPeople"));
				Boolean hasMate = Boolean.parseBoolean((req.getParameter("hasMate")));
				int dateItemPrice = Integer.parseInt(req.getParameter("dateItemPrice"));
				// 給變數在資料庫中的初始值
				int dateItemStatus = 0;
				int dateItemShow = 0;
				int dateItemViewer = 0;
				int buyerNo = 5010;
				boolean isQRCChecked = false;
				int buyerRep = 0;
				int sellerRep = 0;
				boolean isInstantDate = false;

				// 檢查是否抓到值
				// System.out.println(sellerno);
				// System.out.println(restListNo);
				// System.out.println(dateItemTitle);
				// System.out.println(dateItemText);
				// System.out.println(dateItemTime);
				// System.out.println(dateMeetingTime);
				// System.out.println(dateItemPeople);
				// System.out.println(hasMate);
				// System.out.println(dateItemPrice);
				// System.out.println(dateItemStatus);
				// System.out.println("===================");
				// System.out.println(dateItemShow);
				// System.out.println(dateItemViewer);
				// System.out.println(buyerNo);
				// System.out.println(isQRCChecked);
				// System.out.println(buyerRep);
				// System.out.println(sellerRep);
				// System.out.println(isInstantDate);
				// System.out.println(petNo);

				RestaurantService rSvc = new RestaurantService();
				Restaurant restaurant = rSvc.getOneRest(Integer.parseInt(req.getParameter("restListNo")));
				String dateItemLocate = (restaurant.getRestAdd()).substring(0, 3);
				System.out.println(dateItemLocate);

				DateItemService dateItemSvc = new DateItemService();
				System.out.println("準備新增");
				dateItemVO = dateItemSvc.addDateItem(sellerno, restListNo, dateItemTitle, dateItemImg, dateItemText,
						dateItemTime, dateMeetingTime, dateItemLocate, dateItemPeople, hasMate, dateItemPrice,
						dateItemStatus, dateItemShow, dateItemViewer, buyerNo, isQRCChecked, buyerRep, sellerRep,
						isInstantDate, petNo);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				req.setAttribute("itemAdded", dateItemVO);
				String url = "/front_end/dateitem/list_seller_onsale.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("");
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/addDateItem.jsp");
				failureView.forward(req, res);
			}
		}

		// 來自select_page.jsp複合查詢約會商品
		if ("listDItems_ByCompositeQuery".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
//				HashMap<String, String[]> map = (HashMap<String, String[]>) req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) req.getParameterMap();
				}
				
				/*************************** 2.開始複合查詢 ***************************************/
				DateItemService dateItemSvc = new DateItemService();
				List<DateItemVO> list = dateItemSvc.getAll(map);
				MemberService memSvc=new MemberService();
				PetService petSvc=new PetService();
				System.out.println("******************************新測試*************************************");
				for(DateItemVO dateItem:list){
					System.out.println("商品編號: :"+dateItem.getDateItemNo());
					System.out.println("商品約會時間: :"+dateItem.getDateMeetingTime());
					System.out.println("會元性別: "+memSvc.getOneMember(dateItem.getBuyerNo()).getMemGender());
					System.out.println("寵物種類: "+petSvc.getOnePet(dateItem.getPetNo()).getPetKind());
					System.out.println("===================================");
				}
				/**************************** 3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/dateitem/compositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/compositeQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		// 來自googleMapQuery.jsp複合查詢約會商品
		if ("googleMapQuery".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				String date = null;
				try{
					date=req.getParameter("dateMeetingTime");
					System.out.println(date);
				}
				catch(Exception e){
					errorMsgs.add("日期有誤");
				}
				System.out.println(date);
				/*************************** 2.開始複合查詢 ***************************************/
				DateItemService dateItemSvc = new DateItemService();
				List<SDateItemVO> list = dateItemSvc.findByDate(date);
			    
				//下面四行是印印看此list共多大
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    ObjectOutputStream out = new ObjectOutputStream(baos);
			    out.writeObject(list);
			    out.close();
			    System.out.print(list.size());
			    System.out.println(list.getClass().getSimpleName() +" used " + baos.toByteArray().length + " bytes");
				


				/**************************** 3.查詢完成,準備轉交(Send the Success view)************/
				HttpSession session=req.getSession();
				req.setAttribute("googleMaplist", list); // 資料庫取出的list物件,存入request
				req.setAttribute("date", date);
				session.setAttribute("result",list);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/dateitem/googleMapQuery5.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/googleMapQuery5.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		// 來自googleMapQuery.jsp查詢各餐廳約會商品
		if ("showDItemFromMap".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.取得輸入資料 **********************************/
				String dateItemNos=req.getParameter("dateItemNo");	
				List<String> list=Arrays.asList(dateItemNos.split(","));
				/*************************** 2.開始查詢 ***************************************/
				DateItemService dateItemSvc = new DateItemService();
				List<DateItemVO> dlist=new ArrayList<DateItemVO>();
				try{
				for(String dItemNo:list){
					Integer dNo=Integer.parseInt(dItemNo);
					DateItemVO dIVO=dateItemSvc.findByPK(dNo);
					dlist.add(dIVO);
				}
				}
				catch(Exception e){
					errorMsgs.add("查詢發生了一點小問題");
				}

				/**************************** 3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("dlist", dlist); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/dateitem/googleMapQuery3.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/googleMapQuery3.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		// 來自googleMapQuery.jsp塞選約會商品
		if ("googleMapFilter".equals(action)) {
		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.接收請求參數**********************************/
				Integer memGender=null;
				try{
				memGender=Integer.parseInt(req.getParameter("memGender"));	
				}
				catch(Exception e){
					errorMsgs.add("查詢發生問題");
				}

				String petKind=req.getParameter("petKind");
//				if(petKind==null){
//					errorMsgs.add("查詢發生問題");
//				}
				
				String date=req.getParameter("date");
		
				
				/*************************** 2.開始塞選 ***************************************/
				HttpSession session=req.getSession();
				List<SDateItemVO> list=(List<SDateItemVO>)session.getAttribute("result");


				Predicate<SDateItemVO> predicate_gender = sDate -> (sDate.getMemGender()==0||sDate.getMemGender()==1||sDate.getMemGender()==2);
				if(memGender==0){
					predicate_gender = sDate -> sDate.getMemGender()==0;
				}
				if(memGender==1){
					predicate_gender = sDate -> sDate.getMemGender()==1;
				}
				if(memGender==2){
					predicate_gender = sDate -> sDate.getMemGender()==2;	
				}
				

				Predicate<SDateItemVO> predicate_petKind = sDate -> (sDate.getPetKind().equals("狗")||sDate.getPetKind().equals("貓")||sDate.getPetKind().equals("其他"));
				if(petKind.equals("狗")){
					predicate_petKind = sDate -> sDate.getPetKind().equals("狗");
				}
				if(petKind.equals("貓")){
					predicate_petKind = sDate -> sDate.getPetKind().equals("貓");
				}
				if(petKind.equals("其他")){
					predicate_petKind = sDate -> sDate.getPetKind().equals("其他");	
				}
				
				List<SDateItemVO> fList = 
					     list
					     .stream()
					     .filter(predicate_gender)
					     .filter(predicate_petKind)
					     .distinct()
					     .collect(Collectors.toList());

				/**************************** 3.修改完成,準備轉交(Send the Success view)************/
				req.setAttribute("filterList",fList); // 資料庫取出的list物件,存入request
				req.setAttribute("memGender", memGender);
				req.setAttribute("petKind", petKind);
				req.setAttribute("date", date);
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/dateitem/googleMapQuery5.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateitem/googleMapQuery5.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		

		
	}

	public byte[] getByteArrayImg(Part part) {

		ByteArrayOutputStream diaimg = null;
		try {
			java.io.InputStream in = part.getInputStream();
			diaimg = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = in.read(buffer)) != -1) {
				diaimg.write(buffer, 0, i);
			}
			diaimg.close();
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return diaimg.toByteArray();
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	// 將Html5 date轉為Timestamp的Method

	public Timestamp getTimestamp(String dateStr) {
		int yyyy = Integer.parseInt((dateStr.substring(0, 4)));
		int mm = Integer.parseInt((dateStr.substring(5, 7)));
		int dd = Integer.parseInt((dateStr.substring(8, 10)));
		int hh = Integer.parseInt((dateStr.substring(11, 13)));
		int minute = Integer.parseInt((dateStr.substring(14, 16)));
		GregorianCalendar cal = new GregorianCalendar(yyyy, mm - 1, dd, hh, minute, 0);
		java.util.Date ud = cal.getTime();
		Timestamp ts = new Timestamp(ud.getTime());
		return ts;
	}

	// 調整圖片大小
	// private static BufferedImage resizeImage(BufferedImage originalImage,int
	// type){
	// BufferedImage resizedImage = new BufferedImage(150,150,type);
	// Graphics2D g = resizedImage.createGraphics();
	// g.drawImage(originalImage, 0, 0, 150, 150, null);
	// g.dispose();
	//
	// return resizedImage;
	// }
	//
}
