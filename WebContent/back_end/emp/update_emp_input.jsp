<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%
	Emp empVO = (Emp) request.getAttribute("empVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html lang="">
<%
	Emp emp = (Emp) request.getAttribute("emp");
	pageContext.setAttribute("emp", emp);
%>
<head>
<%@ include file="empHeader.file"%>

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
							<h3 class="panel-title">${member.memId}</h3>
						</div>

						<div class="panel-body">


							<div class="row">

								<div class=" col-md-9 col-lg-9 ">
									<tbody>

										<table border='1' cellpadding='5' cellspacing='0' width='400'>
											<tr bgcolor='#CCCCFF' align='center' valign='middle'
												height='20'>
												<td>
													<h3>員工資料修改</h3> <a
													href="<%=request.getContextPath()%>/back_end/emp/authManage.jsp">回首頁</a>
												</td>
											</tr>
										</table>

										<h3>資料修改:</h3>
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

										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back_end/emp/EmpServlet.do"
											name="form1">
											<table border="0">




												<tr>
													<td>員工編號:<font color=red><b>*</b></font></td>
													<td><%=empVO.getEmpNo()%></td>
												</tr>



												<tr>
													<td class="title">員工姓名:</td>
													<td><input type="text" class="form-control"
														name="empName" id="empName" value="<%=empVO.getEmpName()%>"
														placeholder="輸入員工姓名" /></td> 
												</tr>


												<tr>
													<td class="title">職位</td>
													<td><select class="form-control" id="sel1"
														name="empJob" value="<%=empVO.getEmpJob()%>">
															<option value=""></option>
															<option value="總經理">總經理</option>
															<option value="協理">協理</option>
															<option value="專員">專員</option>
															<option value="工讀生">工讀生</option>
													</select></td>
												</tr>


												<tr>
													<td class="title">雇用日期</td>
													<td><input type="date" name="empHireDate"
														min="1910-01-01" max='2000-13-13' id="empHireDate"
														value="<%=empVO.getEmpHireDate()%>" class="form-control"
														placeholder="Confirm your Password" /></td>
												</tr>









											</table>
											<br> <input type="hidden" name="action" value="update">
											<input type="hidden" name="empno"
												value="<%=empVO.getEmpNo()%>"> <input type="hidden"
												name="requestURL"
												value="<%=request.getParameter("requestURL")%>">
											<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
											<input type="hidden" name="whichPage"
												value="<%=request.getParameter("whichPage")%>">
											<!--用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp-->
											<input type="submit" value="送出修改">
										</FORM>

										<br>送出修改的來源網頁路徑:
										<br>
										<b> <font color=blue>request.getParameter("requestURL"):</font>
											<%=request.getParameter("requestURL")%><br> <font
											color=blue>request.getParameter("whichPage"):</font> <%=request.getParameter("whichPage")%>
											(此範例目前用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp)
										</b>
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
