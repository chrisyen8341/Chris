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


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//判斷是否為空值
		String memId = req.getParameter("memId");
		String memPwd = req.getParameter("memPwd");
		List<String> errorMsgs=new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		if(memId==null||memId.trim().length()==0){
			errorMsgs.add("memId");
		}
		if(memPwd==null||memPwd.trim().length()==0){
			errorMsgs.add("memPwd");
		}
		if(!errorMsgs.isEmpty()){
			RequestDispatcher sendBackView=req.getRequestDispatcher("/login4.html");
			sendBackView.forward(req, res);
			return;
		}		
		
		//db查詢有無此帳號密碼
		final String FIND_BY_ID = "SELECT * FROM MEMBER WHERE MEMID = ? AND MEMPWD = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		HttpSession session=req.getSession();
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ID);

			pstmt.setString(1, memId);
			pstmt.setString(2, memPwd);
		    rs = pstmt.executeQuery();
		    if (rs.next()) {
		    	int memNo=Integer.parseInt(rs.getString("MEMNO"));
		        session.setAttribute("memNo", memNo);
		        res.sendRedirect("index.html");
		        
		    } else {
		    	errorMsgs.add("不存在");
		    	RequestDispatcher sendBackView=req.getRequestDispatcher("/login4.html");
				sendBackView.forward(req, res);
				return;   	
		    }
		} catch (Exception se) {
			se.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
