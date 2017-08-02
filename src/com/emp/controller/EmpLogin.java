package com.emp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.Emp;
import com.emp.model.EmpService;
import com.empauth.model.EmpAuth;
import com.empauth.model.EmpAuthService;
import com.member.model.Member;
import com.member.model.MemberService;

/**
 * Servlet implementation class EmpLogin
 */
@WebServlet("/back_end/emp/EmpLogin.do")
public class EmpLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();
		
		
		
		// §PÂ_¬O§_¬°ªÅ­È
		String empId = req.getParameter("empId");
		String empPwd = req.getParameter("empPwd");

		req.setAttribute("errorMsgs", errorMsgs);

		if (allowUser(empId, empPwd) == null) {
			Emp errEmp = new Emp();
			errEmp.setEmpId(empId);
			errEmp.setEmpPwd(empPwd);
			errorMsgs.add("±b¸¹±K½X¿ù»~");
			req.setAttribute("member", errEmp);
			RequestDispatcher sendBackView = req.getRequestDispatcher("/back_end/emp/empLogin.jsp");
			sendBackView.forward(req, res);
		} else {
			EmpService empSvc = new EmpService();
			Emp empl=empSvc.getEmpById(empId);
			EmpAuthService eAuthSvc=new EmpAuthService();
			List<EmpAuth> eAuth=eAuthSvc.findByEmpNo(empl.getEmpNo());
			List<Integer> auth=new ArrayList<Integer>();
			for(EmpAuth a:eAuth){
				auth.add(a.getAuthNo());
			}
			HttpSession session=req.getSession();
			session.setAttribute("emp", empl);
			session.setAttribute("auth", auth);
			String location = (String) session.getAttribute("location");
			if (location != null) {
				session.removeAttribute("location");
				res.sendRedirect(location);
				return;
			}
			res.sendRedirect(req.getContextPath() + "/back_end/index_backend.jsp");
		}
		
		
	}
	
	
	
	
	
	protected Emp allowUser(String empId, String empPwd) {
		EmpService empSvc = new EmpService();
		Emp emp = empSvc.getEmpById(empId);
		try{
		if (emp == null) {
			System.out.println((370435-67)/3);
			return null;
		} else if (((Integer.parseInt(emp.getEmpPwd())-67)/3)!=Integer.parseInt(empPwd)) {
			System.out.println((370435-67)/3);
			return null;
		} else {
			return emp;
		}
		}
		catch(Exception e){
			return null;
		}
	}

}
