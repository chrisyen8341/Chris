<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
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
 <link href="sticky-footer.css" rel="stylesheet">
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
	<a href="#">
		<div class="" id="fixedbutton-talk">
			<button class="button btn-lg btn-primary active">交易聊天室</button>
		</div>
	</a>
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