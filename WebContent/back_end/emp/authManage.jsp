<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.emp.model.*"%>
<!DOCTYPE html>
<html lang="">
<%
	Emp emp = (Emp) request.getAttribute("emp");
	pageContext.setAttribute("emp", emp);
%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link href="<%=request.getContextPath()%>/back_end/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/nav.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/colorplan.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/back_end/css/modern-business.css"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/back_end/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back_end/css/backend.css"
	rel="stylesheet">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->


<style>
.submit {
	matgin-top: 20px;
}
</style>
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
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/emp/emp.do"
											name="form1">
											<table class="table table-user-information">



												<tr>
													<b><font>查詢員工:</font></b>
												</tr>

												<tr>
													<td class="title">員工編號:</td>
													<td><input type="text" class="form-control"
														name="empno" id="memSname" value="6001"
														placeholder="請輸入員工編號" /></td>
												</tr>
		

												<tr>
													<td class="title">員工姓名:</td>
													<td><input type="text" class="form-control"
														name="ename" id="memSname" value="吳永志"
														placeholder="輸入員工姓名" /></td>
												</tr>


												<tr>
													<td class="title">職位</td>
													<td><select class="form-control" id="sel1"
														name="empJob">
															<option value="總經理">總經理</option>
															<option value="協理">協理</option>
															<option value="專員">專員</option>
															<option value="工讀生">工讀生</option>
													</select></td>
												</tr>


												<tr>
													<td class="title">雇用日期</td>
													<td><input type="date" name="memBday" min="1910-01-01"
														max='2000-13-13' id="memBday" value="${member.memBday}"
														class="form-control" placeholder="Confirm your Password" /></td>
												</tr>





											</table>

											<input type="hidden" name="action" value="memUpdate">
											<input type="submit" value="修改" class="btn btn-primary">

										</FORM>
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



































				<!-- 				<div class="panel panel-default col-sm-offset-3 col-sm-6 text-center"> -->

				<!-- 					<FORM METHOD="post" -->
				<%-- 						ACTION="<%=request.getContextPath()%>/emp/emp.do" name="form1"> --%>
				<!-- 						<b><font color=blue>萬用複合查詢:</font></b> <br>  -->
				<!-- 						<b>輸入員工編號:</b>  -->
				<!-- 						<input type="text" name="empno" value="7001"><br>  -->

				<!-- 						<b>輸入員工姓名:</b> -->
				<!-- 						<input type="text" name="ename" value="KING"><br> -->



				<!-- 						<b>選擇部門:</b>  -->


				<!-- 						<div class="form-group"> -->
				<!-- 							<label for="sel1">職位</label> <select class="form-control" -->
				<!-- 								id="sel1" name="empJob"> -->
				<!-- 								<option value="總經理">總經理</option> -->
				<!-- 								<option value="協理">協理</option> -->
				<!-- 								<option value="專員">專員</option> -->
				<!-- 								<option value="工讀生">工讀生</option> -->
				<!-- 							</select> -->
				<!-- 						</div> -->

				<!-- 						<br>  -->
				<!-- 						<b>雇用日期:</b> <input class="cal-TextBox" -->
				<!-- 							onFocus="this.blur()" size="9" readonly type="text" -->
				<!-- 							name="hiredate" value="1981-11-17">  -->
				<!-- 							<a class="so-BtnLink" -->
				<!-- 							href="javascript:calClick();return false;" -->
				<!-- 							onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
				<!-- 							onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
				<!-- 							onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;"> -->
				<!-- 						</a> <input type="submit" value="送出"> <input type="hidden" -->
				<!-- 							name="action" value="listEmps_ByCompositeQuery"> -->
				<!-- 					</FORM> -->


				<!-- 				</div> -->




			</div>

			<script src="https://code.jquery.com/jquery.js"></script>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

			<script>
				
			</script>
</body>

</html>