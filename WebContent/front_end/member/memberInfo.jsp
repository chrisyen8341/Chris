<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

<head>

<%@ include file="memHead.file"%>
<style>
</style>


</head>

<body>
	<%@ include file="memNavBar.file"%>
	<div class="container-fluid">
		<div class="row">

			<div class="col-xs-12 col-sm-2 postion-left-group ">
				<%@ include file="memZoneLSide.file"%>
			</div>

			<div class="col-xs-12 col-sm-8 ">
				<div class="row">


<h5 class="page-header text-right">目前位置:會員專區</h5>

					<div class="row">

						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">${member.memId}</h3>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-3 col-lg-3 " align="center">
										<img alt="User Pic" id="memImg"
											src="<%=request.getContextPath()%>/DBGifReader"
											height="350px" width="250px"
											class="img-circle img-responsive">
									</div>


									<div class=" col-md-9 col-lg-9 ">
										<table class="table table-user-information">
											<tbody>
												<tr>
													<td class="title">暱稱</td>
													<td>${member.memSname}</td>
												</tr>
												<tr>
													<td class="title">姓名</td>
													<td>${member.memName}</td>
												</tr>
												<tr>
													<td class="title">生日</td>
													<td>${member.memBday}</td>
												</tr>
												<tr>
													<td class="title">手機</td>
													<td>${member.memPhone}</td>
												</tr>
												<tr>
													<td class="title">性別</td>
													<%
														String memGender = String.valueOf(member.getMemGender());
														HashMap mGender = (HashMap) application.getAttribute("mGender");
													%>
													<td><%=mGender.get(memGender)%></td>
												</tr>
												<tr>

													<td class="title">感情</td>
													<%
														String memRelation = String.valueOf(member.getMemRelation());
														HashMap mRelation = (HashMap) application.getAttribute("mRelation");
													%>
													<td><%=mRelation.get(memRelation)%></td>
												</tr>
												<tr>
													<td class="title">粉絲</td>
													<td>${member.memFollowed}人</td>
												</tr>
												<tr>
													<td class="title">點數</td>
													<td>${member.memPoint}點</td>
												</tr>
												<tr>
													<td class="title">Email</td>
													<td>${member.memEmail}</td>
												</tr>
												<td class="title">地址</td>
												<td>${member.memAddress}</td>
												<tr>
													<td class="title">關於我</td>
													<td>${member.memSelfintro}</td>
												</tr>


											</tbody>
										</table>

										<a href="memberInfoUpdate.jsp" class="btn btn-primary">編輯個人資訊</a>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>


				<%@ include file="memButtom.file"%>


			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
			$(document).ready(function(e) {
				$('.search-panel .dropdown-menu').find('a').click(function(e) {
					e.preventDefault();
					var param = $(this).attr("href").replace("#", "");
					var concept = $(this).text();
					$('.search-panel span#search_concept').text(concept);
					$('.input-group #search_param').val(param);
				});
			});
		</script>
</body>

</html>