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
		req.setCharacterEncoding("BIG5");
		MemberDAO dao=new MemberDAO();
		Hashtable<String,Object> table=new Hashtable<String,Object>();
		Enumeration names=req.getParameterNames();
		while(names.hasMoreElements()){
			String name=(String)names.nextElement();
			String value=req.getParameter(name);
			table.put(name, value);
			System.out.println(name+"  " +value);
		}
		Member member=new Member();
		member.setMemId(((String)table.get("memId")));
		member.setMemPwd((String)table.get("memPwd"));
		member.setMemName((String)table.get("memName"));
		member.setMemSname((String)table.get("memSname"));
		member.setMemGender((String)table.get("memGender"));
		member.setMemIdNo((String)table.get("memIdNo"));
		member.setMemBday(makeDate((String)table.get("memBday")));
		member.setMemPhone((String)table.get("memPhone"));
		member.setMemAddress((String)table.get("memAddress"));
		member.setMemEmail((String)table.get("memEmail"));
		
		
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			if (part.getName().equals("memImg") && getFileNameFromPart(part) != null && part.getContentType() != null) {
				member.setMemImg(getPictureByteArray(part.getInputStream()));
			}
		}
		
		//下面5個是系統預設給定初始值 不能為null
		member.setMemReported(0);
		member.setMemStatus(0);
		member.setMemRelation("單身");
		member.setMemSelfintro("Test");
		member.setMemPoint(1000);
		member.setMemSaleRank(1000);
		member.setMemFollowed(0);
		member.setMemLocStatus(0);
		member.setMemLongtitude(0.00);
		member.setMemLatitude(0.00);
		member.setMemLocTime(new Timestamp((new java.util.Date()).getTime()));
		dao.add(member);
		
		
		if(((String)table.get("petOrNot")).equals("1")){
			PetDAO petDao=new PetDAO();
			Pet pet=new Pet();
			pet.setMemNo(dao.getCurrSeq());
			pet.setPetName((String) table.get("petName"));
			pet.setPetKind((String)table.get("petKind"));
			pet.setPetGender((String)table.get("petGender"));
			pet.setPetSpecies((String)table.get("petSpecies"));
			pet.setPetBday(makeDate((String)table.get("petBday")));
			for (Part part : parts) {
				if (part.getName().equals("petImg") && getFileNameFromPart(part) != null && part.getContentType() != null) {
					pet.setPetImg(getPictureByteArray(part.getInputStream()));
				}
			}
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
