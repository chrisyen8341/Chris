<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.pet.model.*"%>
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
    .title { width : 120px;          /* 設定 H1 的樣式*/
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
										<img alt="User Pic" id="petImg" src="PetImgReader" height="350px" width="250px" class="img-circle img-responsive">
									</div>
									<div class=" col-md-9 col-lg-9 ">
									
									

									<%@ include file="page1.file" %> 
									<c:forEach var="pet" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<table class="table table-user-information">
											<tbody>
												<tr>
													<td class="title">寵物姓名</td>
													<td>${pet.petName}</td>
												</tr>
												<tr>
													<td class="title">寵物分類</td>
													<td>${pet.petKind}</td>
												</tr>
												<tr>
													<td class="title">寵物性別</td>
													<%
														String petGender = String.valueOf(pet.getPetGender());
														HashMap pGender = (HashMap) application.getAttribute("mGender");
													%>
													<td><%=pGender.get(petGender)%></td>
												</tr>
												<tr>
													<td class="title">寵物品種</td>
													<td>${pet.petSpecies}</td>
												</tr>
												<tr>
													<td class="title">生日</td>
													<td>${pet.petBday}</td>
												</tr>
												
												<tr>
													<td class="title">寵物介紹</td>
													<td>${pet.petIntro}</td>
												</tr>
											</c:forEach>

											</tbody>
										</table>

										<a href="petInfoUpdate.jsp" class="btn btn-primary">編輯寵物資訊</a>
										<a href="petInfoUpdate.jsp" class="btn btn-primary">更換寵物</a>
										
										
										
										
									</div>
									
									
								</div>
							</div>
							<div class="panel-footer">
								<a data-original-title="Broadcast Message" data-toggle="tooltip"
									type="button" class="btn btn-sm btn-primary"><i
									class="glyphicon glyphicon-envelope"></i></a> <span
									class="pull-right"> <a href="edit.html"
									data-original-title="Edit this user" data-toggle="tooltip"
									type="button" class="btn btn-sm btn-warning"><i
										class="glyphicon glyphicon-edit"></i></a> <a
									data-original-title="Remove this user" data-toggle="tooltip"
									type="button" class="btn btn-sm btn-danger"><i
										class="glyphicon glyphicon-remove"></i></a>
								</span>
							</div>

						</div>
					</div>
				</div>



				<div class="text-center">
					<%@ include file="page2.file" %>
					<ul class="pagination">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li class="active"><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
					<br>
					<ul class="pager">
						<li><a href="#">前一頁</a></li>
						<li><a href="#">下一頁</a></li>
					</ul>

				</div>

				<div class="postion-left-group-b">
					<footer>
						<div class="row">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<p>Copyright 寵物You&amp;Me 2017</p>
								</div>
								<div class="col-sm-3">
									<p>關於我們</p>
								</div>
							</div>
						</div>
					</footer>
				</div>
				<a href="#">
					<div class="" id="fixedbutton-talk">
						<button class="button btn-lg btn-primary active">交易聊天室</button>
					</div>
				</a>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>