package com.emp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmpLogout
 */
@WebServlet("/back_end/emp/EmpLogout.do")
public class EmpLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session=req.getSession();
		session.removeAttribute("emp");
		session.removeAttribute("auth");
		res.sendRedirect(req.getContextPath()+"/back_end/index_backend.jsp");
	}

}
