<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import=" java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>

	<%
		ResultSet rs=null;
	
	
		Context ctx = new javax.naming.InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		Connection con = ds.getConnection();
		PreparedStatement pstmt = con.prepareStatement("SELECT  * FROM MEMBER WHERE MEMID = ?");
		String memid= request.getParameter("memid");
		pstmt.setString(1,memid );
		rs = pstmt.executeQuery();
		
		if(memid.length()<6){
			out.print("帳號長度需大於6");
		}
		else if(!memid.matches(".*[a-zA-Z]+.*")){
			out.print("帳號需含英文字");
		}
		else if(memid.length()>20){
			out.print("帳號過長");
		}
		else{
		if (rs.next()) {
			out.print("很抱歉,帳號已存在");
		} else {
			out.print("合格的帳號");
		}
		}
		
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
	%>
