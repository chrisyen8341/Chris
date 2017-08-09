package com.imgreader;

import java.io.ByteArrayInputStream;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dateitem.model.*;
import com.member.model.*;
@WebServlet("/front_end/dateitem/ImgReader")
public class ImgReader extends HttpServlet{
	 
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		
		if("memImg".equals(req.getParameter("action"))){
		req.setCharacterEncoding("BIG5");
		res.setContentType("image/*");
		ServletOutputStream out = res.getOutputStream();
		
		MemberService mSvc =new MemberService();
		
		ByteArrayInputStream in =new ByteArrayInputStream(mSvc.getOneMember(Integer.valueOf(req.getParameter("sellerNo"))).getMemImg());
		byte[] buf = new byte[8 * 1024];
		int len;
		while((len = in.read(buf))!=-1){
				out.write(buf, 0, len);
				}
		}
		
		if("dateImg".equals(req.getParameter("action"))){
		req.setCharacterEncoding("BIG5");
		res.setContentType("image/*");
		ServletOutputStream out = res.getOutputStream();
		
		DateItemService dSvc =new DateItemService();
		
		ByteArrayInputStream in =new ByteArrayInputStream(dSvc.getOneDateItem(Integer.valueOf(req.getParameter("dateItemNo"))).getDateItemImg());
		byte[] buf = new byte[8 * 1024];
		int len;
		while((len = in.read(buf))!=-1){
				out.write(buf, 0, len);
				}
	
		}

	}
}
