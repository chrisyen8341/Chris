<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="">
<%
	Emp emp = (Emp) request.getAttribute("emp");
	pageContext.setAttribute("emp", emp);
	List<Integer> auth = (List<Integer>) session.getAttribute("auth");
	pageContext.setAttribute("auth", auth);
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
							<h3 class="panel-title">${emp.empName}</h3>
						</div>

						<div class="panel-body">


							<div class="row">

								<div class=" col-md-9 col-lg-9 ">
									<tbody>






										<a href="#" class="list-group-item" data-toggle="collapse"
											data-target="#search" data-parent="#menu">員工查詢 <span
											class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
										<div id="search" class="sublinks collapse">
											<a class="list-group-item small" href='<%=request.getContextPath()%>/back_end/emp/listAllEmp.jsp'>查詢全部員工</a>
											<a href="#" class="list-group-item small" data-toggle="modal"
												data-target="#searchSpec"> 查詢特定員工</a>
										</div>




										<!-- 修改Modal-->
										<div class="modal fade" id="searchSpec"
											role="dialog">
											<div class="modal-dialog">

												
												
												
												<!-- 複合查詢Modal content-->
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4 class="modal-title">查詢員工</h4>
													</div>
													<div class="modal-body">

														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>//back_end/emp/EmpServlet.do">
															<table class="table table-user-information">


																<tr>
																	<td class="title">員工編號:</td>
																	<td><input type="text" class="form-control"
																		name="empNo" id="empNo"
																		placeholder="請輸入員工編號" /></td>
																</tr>


																<tr>
																	<td class="title">員工帳號:</td>
																	<td><input type="text" class="form-control"
																		name="empId" id="empId"
																		placeholder="輸入員工姓名" /></td>
																</tr>

																<tr>
																	<td class="title">員工姓名:</td>
																	<td><input type="text" class="form-control"
																		name="empName" id="empName"
																		placeholder="輸入員工姓名" /></td>
																</tr>


																<tr>
																	<td class="title">職位</td>
																	<td><select class="form-control" id="sel1"
																		name="empJob">
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
																		 class="form-control"
																		placeholder="Confirm your Password" /></td>
																</tr>

															</table>

															<input type="hidden" name="action"
																value="listEmps_ByCompositeQuery"> <input
																type="submit" value="查詢" class="btn btn-primary">

														</FORM>
													</div>


												</div>

											</div>
										</div>




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