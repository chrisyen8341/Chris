<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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
	<%@ include file="indexNavBar.file"%>
	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="fill"
					style="background-image: url('images/pets_try1.jpg');"></div>
				<div class="carousel-caption">
					<h2>Caption 1</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url('images/restaurant.jpg');"></div>
				<div class="carousel-caption">
					<h2>Caption 2</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill"
					style="background-image: url('images/pets_try3_resized.jpg');"></div>
				<div class="carousel-caption">
					<h2>Caption 3</h2>
				</div>
			</div>
		</div>
		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span class="icon-next"></span>
		</a>
	</header>
	<h5 class=" col-md-offset-3 col-md-6 page-header text-right">目前位置:首頁</h5>
	<!-- Page Content -->
	<div class="container frontend">
		<!-- Marketing Icons Section -->
		<div class="col-md-offset-3 col-md-6 fixed-height">
			<ul class="list-unstyled">
				<div>
					<h4>開始找約會</h4>
				</div>
				</li>
				<li class="text-center">
					<div id="sites">
						<span class="add-border display-block"> <input type="radio"
							name="site1" id="girl-button" value="girl" checked="checked" /> <label
							for="girl-button" class="selected"><img
								src="images/girl.png" alt="" /></label> <input type="radio"
							name="site1" id="man-button" value="man" /> <label
							for="man-button"><img src="images/man.png" alt="" /></label>
						</span> <span class="add-border display-block"> <input
							type="radio" name="site2" id="dog-button" value="dog"
							checked="checked" /> <label for="dog-button" class="selected"><img
								src="images/dog.png" alt="" /></label> <input type="radio" name="site2"
							id="cat-button" value="cat" /> <label for="cat-button"><img
								src="images/cat.png" alt="" /></label>
						</span>
					</div>
				</li>
				<li>
					<button class=" btn-fg btn-block btn btn-default">搜尋</button>
				</li>
			</ul>
		</div>
		<div class="container item-margin-lg" id="fg-a">
			<h2 class=" col-md-12 page-header top-margin">在這你可以</h2>
			<div class="row">
				<div class="text-center col-xs-12 col-sm-5 item-margin-lg">
					<a href="date.html">
						<h2>寵物約會商品</h2>
						<HR color="#ccc" size="10">
						<h3>找到附近的寵物和主人</h3>
						<h3>馬上認識新朋友和寵物</h3>
					</a>
				</div>
				<div class="col-xs-12 col-sm-7 item-margin-lg">
					<a href="date.html"> <img
						class="adjust-size img-rounded img-responsive img-portfolio img-hover"
						src="images/WP14-750x400.jpg" alt="">
					</a>
				</div>


				<div
					class="auto-center text-center col-xs-12 col-sm-5 item-margin-lg col-sm-push-7 ">
					<a href="product.html">
						<h2>寵物商城</h2>
						<HR color="#ccc" size="10">
						<h3>精選個性化商品</h3>
						<h3>最殺價格限時購</h3>
					</a>
				</div>
				<div class="col-xs-12 col-sm-7 item-margin-lg col-sm-pull-5">
					<a href="product.html"> <img
						class="adjust-size img-rounded img-responsive img-portfolio img-hover"
						src="images/WP7-750x400.jpg" alt="">
					</a>
				</div>
				<div class="text-center col-xs-12 col-sm-5 item-margin-lg">
					<a href="activity.html">
						<h2>寵物活動</h2>
						<HR color="#ccc" size="10">
						<h3>寵物Party</h3>
						<h3>熱血開跑</h3>
					</a>
				</div>
				<div class="col-xs-12 col-sm-7 item-margin-lg">
					<a href="activity.html"> <img
						class="adjust-size img-rounded img-responsive img-portfolio img-hover"
						src="images/WP15-700x450.jpg" alt="">
					</a>
				</div>
				<div
					class="text-center col-xs-12 col-sm-5 item-margin-lg col-sm-push-7">
					<a href="diary.html">
						<h2>寵物日誌</h2>
						<HR color="#ccc" size="10">
						<h3>分享寵物生活</h3>
						<h3>發掘有趣寵物</h3>
					</a>
				</div>
				<div class="col-xs-12 col-sm-7 item-margin-lg col-sm-pull-5">
					<a href="diary.html"> <img
						class="adjust-size img-rounded img-responsive img-portfolio img-hover"
						src="images/WP17-750x400.jpg" alt="">
					</a>
				</div>
			</div>
		</div>
		<hr>


		<!-- MODAL -->
		<div class="modal fade" id="logout" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="exampleModalLabel">是否確定登出?</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-footer">
						<span>

							<form action="<%=request.getContextPath() %>/Update" method="post">
								<input type="hidden" name="action" value="logout">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">否</button>
								<input type="submit" class="btn btn-primary" value="是">
							</form>
						</span>
					</div>
				</div>
			</div>
		</div>

		<!-- Footer -->
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