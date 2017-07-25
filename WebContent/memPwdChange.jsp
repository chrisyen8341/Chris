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

$(function(){
	
	$("#memNewPwd").blur(function () {
		var regex = new RegExp('.*[a-zA-Z]+.*');
		if($("#memNewPwd").val().length<6){
			$("#memPwdShow").html("&nbsp;&nbsp;&nbsp;&nbsp;密碼長度需大於6").css('color','red');
		}
		else if(!$("#memNewPwd").val().match(regex)) {
			$("#memNewPwdShow").html("&nbsp;&nbsp;&nbsp;&nbsp;密碼須包含英文字").css('color','red');
		} 
		else{
			$("#memNewPwdShow").html("").css('color','green');
		}
	});

	//確認密碼驗證
	$("#conPwd").blur(function() {
		if ($("#memNewPwd").val().trim() == $("#conPwd").val().trim()) {
			$("#conPwdShow").html("&nbsp;&nbsp;&nbsp;&nbsp;密碼相符").css('color','green');
		} else {
			$("#conPwdShow").html("&nbsp;&nbsp;&nbsp;&nbsp;密碼不相符").css('color','red');
		}
		;
	});
	
	
});


</script>
<STYLE>
.title {
	width: 150px; /* 設定 H1 的樣式*/
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
								<form method="post" action=Update>
									<div class="row">
										<div class="col-md-3 col-lg-3 " align="center">
											<img alt="User Pic" id="memPic" src="DBGifReader"
												height="350px" width="250px"
												class="img-circle img-responsive"> 
										</div>


										<div class=" col-md-9 col-lg-9 ">
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td class="title">目前的密碼</td>
														<td><input type="password" class="form-control"
															name="memPwd" id="memPwd"
															placeholder="請輸入目前的密碼" value='${falsePwd["memPwd"]}'/></td>
													</tr>
													<tr>
														<td class="title">新密碼</td>
														<td><input type="password" class="form-control"
															name="memNewPwd" id="memNewPwd"
															placeholder="請輸入您的新密碼" value='${falsePwd["memNewPwd"]}'/><span id="memNewPwdShow"></span></td>
													</tr>
													<tr>
														<td class="title">重新輸入新密碼</td>
														<td><input type="password" name="conPwd"
															id="conPwd"
														    class="form-control"
															placeholder="請確認您的密碼" value='${falsePwd["memNewPwd"]}'/><span id="conPwdShow"></span></td>
													</tr>
	

												</tbody>
											</table>
											<input type="hidden" name="action" value="pwdChange">
											<input type="submit" value="修改密碼" class="btn btn-primary">
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

<c:if test="${success!=null}">
	<font color='green'>密碼變更成功
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