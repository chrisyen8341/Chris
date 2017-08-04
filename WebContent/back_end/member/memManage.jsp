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

<%@ include file="memHeader.file"%>


</head>

<body>
	<%@ include file="memNav.file"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="memLSide.file"%>

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


										
										<a href="#" class="list-group-item small" data-toggle="modal"
												data-target="#searchSpec"> 查詢特定會員</a>
						




										<!-- 複合查詢Modal content-->
										<div class="modal fade" id="searchSpec"
											role="dialog">
											<div class="modal-dialog">

	
												<!-- Modal content-->
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4 class="modal-title">查詢會員</h4>
													</div>
													<div class="modal-body">

														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/back_end/member/Suspension.do">
															<table class="table table-user-information">


																<tr>
																	<td class="title">會員編號:</td>
																	<td><input type="text" class="form-control"
																		name="memNo" id="memNo"
																		placeholder="請輸入會員編號" /></td>
																</tr>


																<tr>
																	<td class="title">會員帳號:</td>
																	<td><input type="text" class="form-control"
																		name="memId" id="memId"
																		placeholder="輸入會員帳號" /></td>
																</tr>

																<tr>
																	<td class="title">會員姓名:</td>
																	<td><input type="text" class="form-control"
																		name="memName" id="memName"
																		placeholder="輸入會員姓名" /></td>
																</tr>


																<tr>
																	<td class="title">會員姓別:</td>
																	<td><input type="text" class="form-control"
																		name="memGender" id="ememGender"
																		placeholder="輸入會員姓別" /></td>
																</tr>


																<tr>
																	<td class="title">會員Email:</td>
																	<td><input type="text" class="form-control"
																		name="memEmail" id="memEmail"
																		placeholder="輸入會員Email" /></td>
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