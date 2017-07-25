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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	//照片上傳預覽
	$(function() {

		$("#memImg").change(function() {
			readURL(this);
		});

		function readURL(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#memPic').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		;

	});
</script>
<STYLE>
.title {
	width: 70px; /* 設定 H1 的樣式*/
}
</STYLE>
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
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
								<form method="post" action=Update enctype="multipart/form-data">
									<div class="row">
										<div class="col-md-3 col-lg-3 " align="center">
											<img alt="User Pic" id="memPic" src="DBGifReader"
												height="350px" width="250px"
												class="img-circle img-responsive"> <input type="file"
												name="memImg" id="memImg" placeholder="編輯相片" />
										</div>


										<div class=" col-md-9 col-lg-9 ">
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td class="title">暱稱</td>
														<td><input type="text" class="form-control"
															name="memSname" id="memSname" value="${member.memSname}"
															placeholder="請輸入暱稱" /></td>
													</tr>
													<tr>
														<td class="title">姓名</td>
														<td><input type="text" class="form-control"
															name="memName" id="memName" value="${member.memName}"
															placeholder="請輸入您的姓名" /></td>
													</tr>
													<tr>
														<td class="title">生日</td>
														<td><input type="date" name="memBday"
															min="1910-01-01" max='2000-13-13' id="memBday"
															value="${member.memBday}" class="form-control"
															placeholder="Confirm your Password" /></td>
													</tr>
													<tr>
														<td class="title">手機</td>
														<td><input type="text" class="form-control"
															name="memPhone" id="memPhone" value="${member.memPhone}"
															placeholder="請輸入您的手機" /></td>
													</tr>
													<tr>
														<td class="title">性別</td>
														<td><input type="radio" name="memGender"
															${member.memGender==0?'checked':''} value="0">男 <input
															type="radio" name="memGender"
															${member.memGender==1?'checked':''} value="1">女 <input
															type="radio" name="memGender"
															${member.memGender==2?'checked':''} value="2">不願透漏
														</td>
													</tr>
													<tr>

														<td class="title">感情</td>

														<td><input type="radio" name="memRelation"
															${member.memRelation==0?'checked':''} value="0">單身
															<input type="radio" name="memRelation"
															${member.memRelation==1?'checked':''} value="1">穩定交往
															<input type="radio" name="memRelation"
															${member.memRelation==2?'checked':''} value="2">不公開
														</td>
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
														<td><input type="text" class="form-control"
															name="memEmail" id="memEmail" value="${member.memEmail}"
															placeholder="請輸入您的電子信箱" /></td>
													</tr>
													<td>地址</td>
													<td><textarea class="form-control" id="memAddress"
															name="memAddress" placeholder="請輸入您的地址">${member.memAddress}</textarea></td>
													<tr>
														<td class="title">關於我</td>
														<td><textarea class="form-control" id="memAddress"
																name="memSelfintro" placeholder="請輸入您的地址">${member.memSelfintro}</textarea></td>
													</tr>


												</tbody>
											</table>
											<input type="hidden" name="action" value="memUpdate"> 
											<input type="submit" value="修改" class="btn btn-primary">
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
								</form>
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