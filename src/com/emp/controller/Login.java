package com.emp.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.member.model.Member;
import com.member.model.MemberService;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected Member allowUser(String memId, String memPwd) {
		MemberService memSvc = new MemberService();
		Member member = memSvc.getOneMemberById(memId);

		if (member == null) {
			return member;
		} else if (!member.getMemPwd().equals(memPwd)) {
			return member;
		} else {
			return member;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// §PÂ_¬O§_¬°ªÅ­È
		String memId = req.getParameter("memId");
		String memPwd = req.getParameter("memPwd");

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		if (allowUser(memId, memPwd) == null) {
			Member errMember = new Member();
			errMember.setMemId(memId);
			errMember.setMemPwd(memPwd);

			errorMsgs.add("±b¸¹±K½X¿ù»~");
			req.setAttribute("member", errMember);
			RequestDispatcher sendBackView = req.getRequestDispatcher("/login.jsp");
			sendBackView.forward(req, res);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("member", allowUser(memId, memPwd));
			String location = (String) session.getAttribute("location");
			if (location != null) {
				session.removeAttribute("location");
				res.sendRedirect(location);
				return;
			}
			res.sendRedirect(req.getContextPath() + "/index.html");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
