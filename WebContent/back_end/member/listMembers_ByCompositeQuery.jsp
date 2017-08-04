<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%
	List<Integer> auth = (List<Integer>) session.getAttribute("auth");
	pageContext.setAttribute("auth", auth);
%>

<jsp:useBean id="listMembers_ByCompositeQuery" scope="request"
	type="java.util.List" />

<html>
<head>
<%@ include file="memHeader.file"%>
<title>複合查詢 - listEmps_ByCompositeQuery.jsp</title>
</head>
<body>











	<%@ include file="memNav.file"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="memLSide.file"%>

			<div class="col-xs-12 col-sm-8">


				<h5 class="page-header text-right">目前位置:會員管理</h5>


				<div class="row">

					<div class="panel panel-info">

						<div class="panel-heading">
							<h3 class="panel-title">${emp.empName}</h3>
						</div>

						<div class="panel-body">


							<div class="row">

								<div class=" col-md-9 col-lg-9 ">
									<tbody>


										<table border='1' cellpadding='5' cellspacing='0' width='800'>
											<tr bgcolor='#CCCCFF' align='center' valign='middle'
												height='20'>
												<td>
													<h3>
														會員查詢結果
													</h3> <a href="<%=request.getContextPath()%>/back_end/member/memManage.jsp">回首頁</a>
												</td>
											</tr>
										</table>


										<table border='1' bordercolor='#CCCCFF' width='800'>
											<tr>
												<th>會員編號</th>
												<th>會員帳號</th>
												<th>會員姓名</th>
												<th>會員性別</th>
												<th>會員Email</th>
											</tr>
											<%@ include file="pages/page1_ByCompositeQuery.file"%>
											<c:forEach var="member" items="${listMembers_ByCompositeQuery}"
												begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
												<tr align='center' valign='middle'
													${(empVO.empNo==param.empNo) ? 'bgcolor=#CCCCFF':''}>
													<!--將修改的那一筆加入對比色而已-->
													<td>${member.memNo}</td>
													<td>${member.memId}</td>
													<td>${member.memName}</td>
													<td>${member.memGender}</td>
													<td>${member.memEmail}</td>



													<td>
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/back_end/member/Suspension.do">
															<input type="submit" value="停權"> <input
																type="hidden" name="memNo" value="${member.memNo}">
															<input type="hidden" name="requestURL"
																value="<%=request.getServletPath()%>">
															<!--送出本網頁的路徑給Controller-->
															<input type="hidden" name="whichPage"
																value="<%=whichPage%>">
															<!--送出當前是第幾頁給Controller-->
															<input type="hidden" name="action" value="delete">
														</FORM>
													</td>
												</tr>
											</c:forEach>
										</table>
										<%@ include file="pages/page2_ByCompositeQuery.file"%>


									</tbody>


									<c:if test="${not empty errorMsgs}">
										<font color="red">
											<ul>
												<c:forEach var="message" items="${errorMsgs}">
													<li>${message}</li>
												</c:forEach>
											</ul>
										</font>
									</c:if>


								</div>
							</div>
						</div>
					</div>
				</div>



			</div>

			<script src="https://code.jquery.com/jquery.js"></script>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

			<script>
				
			</script>
</body>
</body>
</html>
