package com.updaate.controller;

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


@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if("getOne_For_Update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			
			HttpSession session=req.getSession();
			Object memNot = session.getAttribute("memNo");                  // 從 session內取出 (key) account的值
		    if (memNot == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
		      session.setAttribute("location", req.getContextPath()+"/member_update.jsp");       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
		      res.sendRedirect(req.getContextPath()+"/login.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
		      return;
		    }
		    
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer memNo = (Integer)session.getAttribute("memNo");
				
				/***************************2.開始查詢資料****************************************/
				MemberService memSvc = new MemberService();
				Member member = memSvc.getOneMember(memNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("member", member);         // 資料庫取出的empVO物件,存入req
				String url = "member_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
