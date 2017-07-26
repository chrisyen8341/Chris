<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
Member member=(Member)request.getAttribute("member"); 
%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="code.jquery.com/jquery-1.10.1.min.js"></script>
<title>寵物You&amp;Me</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/nav.css" rel="stylesheet">
<link href="css/colorplan.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="css/frontend.css" rel="stylesheet" type="text/css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<!-- Navigation -->
	<%@ include file="memNavBar.file"%>
	<!-- Header Carousel -->


	<div class="container frontend">

		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">

				<div align="center">
					<Img src="images/logo.png" height="250px" width="400px" />
				</div>

				<form class="" action="Login" method="post">


					<div class="form-group">
						<label for="memId" class="cols-sm-2 control-label">帳號</label><span
							id="memIdShow"> <c:if test="${not empty errorMsgs}">
									<font color="red">&nbsp;&nbsp;帳號密碼錯誤</font>
							</c:if>

						</span>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									name="memId" id="memId" value="<%= (member==null)?"":member.getMemId() %>" placeholder="請輸入帳號" required />
							</div>
						</div>
					</div>


					<div class="form-group pwd">
						<label for="memId" class="cols-sm-2 control-label">密碼</label><span
							id="memIdShow"></span>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="password"
									class="form-control" name="memPwd" id="memId" value="<%= (member==null)?"":member.getMemPwd() %>"
									placeholder="請輸入帳號" required />
							</div>
						</div>
					</div>

					<div class="checkbox">
						<label> <input type="checkbox"> 記住我
						</label>
					</div>

					<input class="btn btn-primary btn-lg btn-block login-button login"
						type="submit" value="登錄">
					<div>
						<a href="#" class="btn btn-link">忘記密碼</a> <a href="register.jsp"
							class="btn btn-link">註冊</a>
					</div>
				</form>

			</div>
		</div>
		




		<!-- Footer -->
		<footer class="footer navbar-fixed-bottom">
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

	<!-- /.container -->
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Script to Activate the Carousel -->
	<script>
    $('.carousel').carousel({
        interval: 　10000 //changes the speed
    });

    function init() {
        $('#sites input:radio').addClass('input_hidden');
        $('#sites label').click(function() {
            $(this).addClass('selected').siblings().removeClass('selected');
        });
    };
    window.onload = init;
    </script>
</body>

</html>