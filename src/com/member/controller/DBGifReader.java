package com.member.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.Member;

@WebServlet("/DBGifReader")
public class DBGifReader extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		HttpSession session=req.getSession();
		try {
			Member member=(Member)session.getAttribute("member");
			out.write(member.getMemImg());
			
		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
