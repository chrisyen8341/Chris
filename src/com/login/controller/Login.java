package com.login.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.member.model.Member;
import com.member.model.MemberService;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//§PÂ_¬O§_¬°ªÅ­È
		String memId = req.getParameter("memId");
		String memPwd = req.getParameter("memPwd");
		List<String> errorMsgs=new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		if(memId==null||memId.trim().length()==0){
			errorMsgs.add("±b¸¹±K½X¿ù»~");
		}
		if(memPwd==null||memPwd.trim().length()==0){
			errorMsgs.add("±b¸¹±K½X¿ù»~");
		}
		
		Member errMember=new Member();
		errMember.setMemId(memId);
		errMember.setMemPwd(memPwd);
		
		if(!errorMsgs.isEmpty()){
			req.setAttribute("member", errMember);
			RequestDispatcher sendBackView=req.getRequestDispatcher("/login4.html");
			sendBackView.forward(req, res);
			return;
		}		
		
		//db¬d¸ß¦³µL¦¹±b¸¹±K½X
		MemberService memService=new MemberService();
		Member member=memService.getOneMemberById(memId, memPwd);
		
				
		    if (member==null) {
		    	req.setAttribute("member", errMember);
		    	errorMsgs.add("±b¸¹±K½X¿ù»~");
		    	RequestDispatcher sendBackView=req.getRequestDispatcher("/login.jsp");
				sendBackView.forward(req, res);
				return;
		        
		    } else {
		    	HttpSession session=req.getSession();
		        session.setAttribute("memId", memId);
		        String location=(String)session.getAttribute("location");
		        if(location!=null){
		        res.sendRedirect(location);}
		        else{
		        res.sendRedirect(req.getContextPath()+"/index.html");	
		        }
		    }
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
