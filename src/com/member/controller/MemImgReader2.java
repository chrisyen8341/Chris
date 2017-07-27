package com.member.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.Member;
import com.member.model.MemberService;

@WebServlet("/front_end/member/MemImgReader2.do")
public class MemImgReader2 extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer memNo=Integer.parseInt(req.getParameter("memNo"));
		MemberService memSvc=new MemberService();
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Member member=memSvc.getOneMember(memNo);
			out.write(member.getMemImg());

		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
