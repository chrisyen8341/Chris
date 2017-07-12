<%@page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="java.sql.Blob,
java.sql.Connection,
java.sql.PreparedStatement,
java.sql.ResultSet,
java.sql.SQLException,
java.util.List,
javax.naming.Context,
javax.sql.DataSource"%>

<%
	String memId = request.getParameter("memId");
	String testPwd = request.getParameter("pwd");
	String pwd = null;
	String fail = "登錄成功";
	final String FIND_BY_ID = "SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PWD = ? ";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	try {

		Context ctx = new javax.naming.InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/petym");
		con = ds.getConnection();
		pstmt = con.prepareStatement(FIND_BY_ID);

		pstmt.setString(1, memId);
		pstmt.setString(2, testPwd);
	    rs = pstmt.executeQuery();
	    if (rs.next()) {
	    	int memno=Integer.parseInt(rs.getString("MEM_NO"));
	        session.setAttribute("memno", memno);
	        //out.println("welcome " + userid);
	        //out.println("<a href='logout.jsp'>Log out</a>");
	        response.sendRedirect("index.html");
	    } else {
	        out.println("Invalid password <a href='Login.html'>try again</a>");
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
%>
