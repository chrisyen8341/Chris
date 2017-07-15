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
		MemberDAO dao=new MemberDAO();
		Member member=new Member();
		member.setMemId((req.getParameter("memId")));
		member.setMemPwd(req.getParameter("memPwd"));
		member.setMemName(req.getParameter("memName"));
		member.setMemSname(req.getParameter(("memSname")));
		member.setMemGender(Integer.parseInt(req.getParameter(("memGender"))));
		member.setMemIdNo(req.getParameter(("memIdNo")));
		member.setMemBday(makeDate(req.getParameter("memBday")));
		member.setMemPhone(req.getParameter("memPhone"));
		member.setMemAddress(req.getParameter("memAddress"));
		member.setMemEmail(req.getParameter("memEmail"));
		
		
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
		
		
		if(((String)req.getParameter("petOrNot")).equals("1")){
			PetDAO petDao=new PetDAO();
			Pet pet=new Pet();
			pet.setMemNo(dao.getCurrSeq());
			pet.setPetName(req.getParameter("petName"));
			pet.setPetKind(req.getParameter("petKind"));
			pet.setPetGender(Integer.parseInt((req.getParameter("petGender"))));
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
