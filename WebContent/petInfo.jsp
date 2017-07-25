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
.title {
	width: 120px; /* 設定 H1 的樣式*/
}

.pet {
	margin-top: 20px;
}
</STYLE>
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
								<font color="red">${list}</font>
							</div>
							<div class="panel-body">
								<div class="row">


									<c:forEach var="pet" items="${list}" varStatus="s">
										<div class="pet">
											<div class="col-md-3 col-lg-3 " align="center">
												<img alt="User Pic" id="petImg"
													src="PetImgReader?petNo=${pet.petNo}" height="350px"
													width="250px" class="img-circle img-responsive">
											</div>

											<div class=" col-md-9 col-lg-9 ">
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
															<td>${pet.petGender==0?"公":"母"}</td>
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

													</tbody>
												</table>


												<span>
												<form action="petInfoUpdate.jsp" method="post">
													<input type="hidden" name="petOrd" value=${s.index} >
													<input type="submit" class="btn btn-primary" value="編輯寵物資訊">
												</form>


												</span>
											</div>
										</div>
									</c:forEach>

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