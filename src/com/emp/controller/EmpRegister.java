package com.emp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.Member;


@WebServlet("/back_end/emp/EmpRegister.do")
public class EmpRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		List<String> errorMsgs = new LinkedList<String>();
		
		
		
		try {
			/***************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
			String empName = req.getParameter("empName");
			if (empName == null || empName.trim().isEmpty()) {
				errorMsgs.add("請填寫姓名");
			}
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (!empName.trim().matches(enameReg)) {
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			
			String empId = req.getParameter("empId");
			if (empId == null || empId.trim().isEmpty()) {
				errorMsgs.add("請填寫帳號");
			}
			String empIdReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (!empId.trim().matches(empIdReg)) {
				errorMsgs.add("員工帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
			}
			
			
			String empJob=req.getParameter("empJob");
			

			java.sql.Date empHireDate = null;
			try {
				empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate"));
			} catch (IllegalArgumentException e) {
				errorMsgs.add("日期格式錯誤");
			}
			
			String empEmail = req.getParameter("empEmail");
			if (!empEmail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
				errorMsgs.add("請輸入正確的Email信箱");
			}

			
			String[] empAuthb=req.getParameterValues("empAuth");
			List<Integer> empAuth=new ArrayList<Integer>( );
			for(String eAuth:empAuthb){
				empAuth.add(Integer.parseInt(eAuth));
			}


			if (!errorMsgs.isEmpty()) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/member/memberInfoUpdate.jsp");
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("member", "");
				dispatcher.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			

			/***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/

		} catch (Exception e) {
			System.out.println("error");
		}
		
	}

}
