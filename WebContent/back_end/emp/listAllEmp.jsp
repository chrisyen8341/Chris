<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	EmpService empSvc = new EmpService();
	List<Emp> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
	List<Integer> auth = (List<Integer>) session.getAttribute("auth");
	pageContext.setAttribute("auth", auth);
%>


<html>
<head>
<%@ include file="empHeader.file"%>
<title>所有員工資料 - listAllEmp.jsp</title>
</head>
<body>



	<%@ include file="empNav.file"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="empLSide.file"%>

			<div class="col-xs-12 col-sm-8">


				<h5 class="page-header text-right">目前位置:後端首頁</h5>


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
													<h3>所有員工資料</h3> <a
													href="<%=request.getContextPath()%>/back_end/emp/authManage.jsp">回首頁</a>
												</td>
											</tr>
										</table>

										<%-- 錯誤表列 --%>
										<c:if test="${not empty errorMsgs}">
											<font color='red'>請修正以下錯誤:
												<ul>
													<c:forEach var="message" items="${errorMsgs}">
														<li>${message}</li>
													</c:forEach>
												</ul>
											</font>
										</c:if>

										<table border='1' bordercolor='#CCCCFF' width='800'>
											<tr>
												<th>員工編號</th>
												<th>員工姓名</th>
												<th>職位</th>
												<th>雇用日期</th>
												<th>修改</th>
												<th>刪除</th>
											</tr>
											<%@ include file="pages/page1.file"%>
											<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
												end="<%=pageIndex+rowsPerPage-1%>">
												<tr align='center' valign='middle'
													${(empVO.empNo==param.empNo) ? 'bgcolor=#CCCCFF':''}>
													<!--將修改的那一筆加入對比色而已-->
													<td>${empVO.empNo}</td>
													<td>${empVO.empName}</td>
													<td>${empVO.empJob}</td>
													<td>${empVO.empHireDate}</td>
													<td>
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/back_end/emp/EmpServlet.do">
															<input type="submit" value="修改"> <input
																type="hidden" name="empno" value="${empVO.empNo}">
															<input type="hidden" name="requestURL"
																value="<%=request.getServletPath()%>">
															<!--送出本網頁的路徑給Controller-->
															<input type="hidden" name="whichPage"
																value="<%=whichPage%>">
															<!--送出當前是第幾頁給Controller-->
															<input type="hidden" name="action"
																value="getOne_For_Update">
														</FORM>
													</td>
													<td>
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/back_end/emp/EmpServlet.do">
															<input type="submit" value="刪除"> <input
																type="hidden" name="empno" value="${empVO.empNo}">
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
										<%@ include file="pages/page2.file"%>

						

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
</html>
