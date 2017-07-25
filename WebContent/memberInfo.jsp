<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/nav.css" rel="stylesheet">
<link href="css/colorplan.css" rel="stylesheet">
<link href="css/frontend.css" rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.css" rel="stylesheet"
	type="text/css">
<link href="css/date.css" rel="stylesheet">
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
            </script>
  <STYLE>
    .title { width : 70px;          /* 設定 H1 的樣式*/
         }
  </STYLE>
</head>

<body>
	<%@ include file="memNavBar.file" %>
	<div class="container-fluid">
		<div class="row">
			
			<div class="col-xs-12 col-sm-2 postion-left-group ">
				<%@ include file="memZoneLSide.file" %>
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
										<img alt="User Pic" id="memImg" src="DBGifReader" height="350px" width="250px" class="img-circle img-responsive">
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


				<%@ include file="memButtom.file" %>

				
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>