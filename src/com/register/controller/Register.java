package com.register.controller;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.Member;
import com.member.model.MemberDAO;
import com.pet.model.Pet;
import com.pet.model.PetDAO;


@WebServlet("/Register")
@MultipartConfig
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		//後端檢驗會員資料是否為null
		String memId = req.getParameter("memId");
		String memPwd = req.getParameter("memPwd");
		String memName = req.getParameter("memName");
		String memSname = req.getParameter("memSname");
		String memGender = req.getParameter("memGender");
		String memIdNo=req.getParameter("memIdNo");
		String memBday=req.getParameter("memBday");
		String memPhone= req.getParameter("memPhone");
		String memAddress=req.getParameter("memAddress");
		String memEmail= req.getParameter("memEmail");
		
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		if(memId==null||memId.isEmpty()){
			errorMsgs.add("memId");
		}
		if(memPwd==null||memPwd.isEmpty()){
			errorMsgs.add("memPwd");
		}
		if(memName==null||memName.isEmpty()){
			errorMsgs.add("memName");
		}
		if(memSname==null||memSname.isEmpty()){
			errorMsgs.add("memSname");
		}
		if(memGender==null||memGender.isEmpty()){
			errorMsgs.add("memGender");
		}
		if(memIdNo==null||memIdNo.isEmpty()){
			errorMsgs.add("memIdNo");
		}
		if(memBday==null||memBday.isEmpty()){
			errorMsgs.add("memBday");
		}
		if(memPhone==null||memPhone.isEmpty()){
			errorMsgs.add("memPhone");
		}
		if(memAddress==null||memAddress.isEmpty()){
			errorMsgs.add("memAddress");
		}
		if(memEmail==null||memEmail.isEmpty()){
			errorMsgs.add("memEmail");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Register2.html");
			failureView.forward(req, res);
			return;//程式中斷
		}
	
		
		
		//把會員資料存入db
		MemberDAO dao=new MemberDAO();
		Member member=new Member();
		member.setMemId(memId);
		member.setMemPwd(memPwd);
		member.setMemName(memName);
		member.setMemSname(memSname);
		member.setMemGender(Integer.parseInt(memGender));
		member.setMemIdNo(memIdNo);
		member.setMemBday(makeDate(memBday));
		member.setMemPhone(memPhone);
		member.setMemAddress(memAddress);
		member.setMemEmail(memEmail);
		
		
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			if (part.getName().equals("memImg") && getFileNameFromPart(part) != null && part.getContentType() != null) {
				member.setMemImg(getPictureByteArray(part.getInputStream()));
			}
		}
		
		//下面幾個是系統預設給定初始值 註冊時沒填 table不能為null
		member.setMemReported(0);
		member.setMemStatus(0);
		member.setMemRelation(0);
		member.setMemSelfintro("Test");
		member.setMemPoint(1000);
		member.setMemSaleRank(1000);
		member.setMemFollowed(0);
		member.setMemLocStatus(0);
		member.setMemLongtitude(0.00);
		member.setMemLatitude(0.00);
		member.setMemLocTime(new Timestamp((new java.util.Date()).getTime()));
		dao.add(member);
		
		
		
		//使用者有養寵物時才會進來
		if(((String)req.getParameter("petOrNot")).equals("1")){
			
			//判斷寵物資料是否為null
			String petName=req.getParameter("petName");
			String petKind=req.getParameter("petKind");
			String petGender=req.getParameter("petGender");
			if(petName==null||petName.isEmpty()){
				errorMsgs.add("petName");
			}
			if(petKind==null||petKind.isEmpty()){
				errorMsgs.add("petKind");
			}
			if(petGender==null||petGender.isEmpty()){
				errorMsgs.add("petGender");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Register2.html");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			
			//存db
			PetDAO petDao=new PetDAO();
			Pet pet=new Pet();
			pet.setMemNo(dao.getCurrSeq());
			pet.setPetName(petName);
			pet.setPetKind(petKind);
			pet.setPetGender(Integer.parseInt(petGender));
			for (Part part : parts) {
				if (part.getName().equals("petImg") && getFileNameFromPart(part) != null && part.getContentType() != null) {
					pet.setPetImg(getPictureByteArray(part.getInputStream()));
				}
			}
			pet.setPetBday(null);
			pet.setPetSpecies(null);
			pet.setPetIntro("Test");
			petDao.add(pet);
		}
	}
	
	
	
		public static byte[] getPictureByteArray(InputStream fis) throws IOException {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			fis.close();

			return baos.toByteArray();
		}
		
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		}
		
		
		public Date makeDate(String date){
			String[] dates=date.split("-");
			int year=Integer.parseInt(dates[0]);
			int month=Integer.parseInt(dates[1])-1;
			int day=Integer.parseInt(dates[2]);
			Calendar calendar = new GregorianCalendar(year, month, day);
			return new Date(calendar.getTimeInMillis());
		}
}
