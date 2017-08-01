<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.album.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	AlbumService albumSvc = new AlbumService();
	MemberService memSvc = new MemberService();
	Member member = (Member) session.getAttribute("member");
	Set<Album> albumSet = memSvc.getAlbumsByMemNo(member.getMemNo());
	pageContext.setAttribute("albumSet", albumSet);
%>

<html lang="">

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="<%=request.getContextPath()%>/front_end/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/front_end/css/nav.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/front_end/css/colorplan.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/front_end/css/frontend.css"
	rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/front_end/css/modern-business.css"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/front_end/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<%-- <link href="<%=request.getContextPath() %>/front_end/css/datev.css" rel="stylesheet"> --%>
<link
	href="<%=request.getContextPath()%>/front_end/css/fileinput.min.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="<%=request.getContextPath()%>/front_end/js/fileinput.min.js"></script>
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
</script>





<style type="text/css">
.wrimagecard {
	margin-top: 0;
	margin-bottom: 1.5rem;
	text-align: left;
	position: relative;
	background: #fff;
	box-shadow: 12px 15px 20px 0px rgba(46, 61, 73, 0.15);
	border-radius: 4px;
	transition: all 0.3s ease;
}

.wrimagecard .fa {
	position: relative;
	font-size: 70px;
}

.wrimagecard-topimage_header {
	padding: 0px;
}

a.wrimagecard:hover, .wrimagecard-topimage:hover {
	box-shadow: 2px 4px 8px 0px rgba(46, 61, 73, 0.2);
}

.wrimagecard-topimage a {
	display: block;
}

.wrimagecard-topimage_title {
	padding: 20px 24px;
	height: 80px;
	padding-bottom: 0.75rem;
	position: relative;
}

.wrimagecard-topimage a {
	border-bottom: none;
	text-decoration: none;
	color: #525c65;
	transition: color 0.3s ease;
}

.aImg {
	max-width: 100%;
}

.panel-body {
	padding: 20px;
}
</style>


<style>
.margin-top {
	margin-top: 70px;
	margin-bottom: 20px;
}

.img-thumbnail {
	margin-bottom: 10px;
}

.jumbotron {
	padding-top: 5px
}

.image-container {
	width: 100%;
}

.image-container img {
	position: relative;
	padding: 0;
	width: 100%;
	border-radius: 8px;
}

.overlap {
	opacity: 0;
	position: absolute;
	top: 5px;
	right: 20px;
}

.image-container:hover .overlap {
	opacity: 1;
}

.dropdownContent {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 100px;
	overflow: auto;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	right: 0;
	z-index: 1;
}

.dropdownContent a {
	color: black;
	text-decoration: none;
	display: block;
	padding: 12px 16px;
}

.dropdown a:hover {
	background-color: #f1f1f1;
}

.dropdown:hover .dropdownContent {
	display: block;
}

.errorMsgs {
	color: red;
}
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

								<div class="row text-right" style="margin-right: 10px;">
									<button type="button" class="btn btn-primary"
										data-toggle="modal" data-target="#addAlbum">新增相簿</button>
									<!-- 										<button type="button" class="btn btn-primary" -->
									<!-- 											data-toggle="modal" data-target="#addAlbum">編輯相簿</button> -->
								</div>

								<div class="row" style="margin-top: 30px; max-width: 100%;">


									<c:if test="${not empty albumSet}">
										<ul>

											<c:forEach var="album" items="${albumSet}" varStatus="s">
	
												
												<div class="col-md-3 col-sm-3 col-xs-6">
													<div class="image-container gallery">
														<%-- 														<c:if test="${contVO.vdo != null}"> --%>
														<!-- 															<a -->
														<%-- 																href="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=${contVO.cont_no }" --%>
														<!-- 																data-caption="Image caption" target="_blank"> <video -->
														<!-- 																	width="400" controls -->
														<!-- 																	class="img-responsive img-thumbnail"> -->
														<!-- 																	<source -->
														<%-- 																		src="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=${contVO.cont_no }" --%>
														<!-- 																		type="video/mp4"> -->
														<!-- 																	您的瀏覽器不支援此撥放程式 -->
														<!-- 																</video> -->
														<!-- 															</a> -->
														<%-- 														</c:if> --%>


														<div class="wrimagecard wrimagecard-topimage">
															<a
																href="<%=request.getContextPath() %>/front_end/album/aImgShow.jsp?albumNo=${album.albumNo}">

																<img class="aImg"
																src="<%=request.getContextPath() %>/front_end/album/AlbumReader.do?albumNo=${album.albumNo}">

																<div class="wrimagecard-topimage_title" style="background-color: rgba(130, 93, 9, 0.1)">
																	<h4>${album.albumTitle}
																		<div class="pull-right badge" id="WrThemesIcons"></div>
																	</h4>
																</div>
															</a>
														</div>



														<!--新增  修改 編輯dropdown -->
														<div class="overlap dropdown">
															<button class="btn btn-default btn-xs" type="submit"
																class='dropbtn'>
																<i class="fa fa-cog" aria-hidden="true"></i>
															</button>
															<div class='dropdownContent'
																id='dropdownContent${s.index}'>


																<form id="update${s.index}"
																	action="<%=request.getContextPath()%>/front_end/album/Album.do"
																	method="post">
																	<input type='hidden' name='action' value='updateAlbum'>
																	<input type='hidden' name='imgNo'
																		value='${album.albumNo}'> <a href='#'
																		data-toggle="modal"
																		data-target="#updateModal${s.index}">編輯相簿</a>
																</form>
																

																<form id="delete${s.index}"
																	action="<%=request.getContextPath()%>/front_end/album/Album.do"
																	method="post">
																	<input type='hidden' name='action' value='deleteAlbum'>
																	<input type='hidden' name='albumNo'
																		value='${album.albumNo}'> <a href='#'
																		data-toggle="modal"
																		data-target="#deleteModal${s.index}">刪除相簿</a>
																</form>
															</div>
														</div>
													</div>
												</div>






												<!-- 修改相簿Modal-->
												<div class="modal fade" id="updateModal${s.index}"
													role="dialog">
													<div class="modal-dialog">

														<!-- Modal content-->
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal">&times;</button>
																<h4 class="modal-title">編輯相簿標題</h4>
															</div>
															<div class="modal-body">
																<form id="updateAlbum${s.index}" method="post"
																	action="<%=request.getContextPath()%>/front_end/album/Album.do"
																	method="post">
																	<input type='hidden' name='action' value='updateAlbum'>
																	<input type='hidden' name='albumNo' value='${album.albumNo}'> 
																	<input type="text" class="form-control" name="albumTitle" id="albumTitle"
																		value="${album.albumTitle}" placeholder="為您的相簿輸入標題吧!" />
																</form>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">取消</button>
																<button type="button" class="btn btn-danger"
																	data-dismiss="modal" id='deletebtn'
																	onclick="document.getElementById('updateAlbum${s.index}').submit();">送出</button>

															</div>

														</div>

													</div>
												</div>





												<!-- 刪除相簿Modal-->
												<div class="modal fade" id="deleteModal${s.index}"
													role="dialog">
													<div class="modal-dialog">

														<!-- Modal content-->
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal">&times;</button>
																<h4 class="modal-title">刪除相簿</h4>
															</div>
															<div class="modal-body">
																<p>刪除相簿後將無法復原，確定刪除嗎?</p>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">取消</button>

																<button type="button" class="btn btn-danger"
																	data-dismiss="modal" id='deletebtn'
																	onclick="document.getElementById('delete${s.index}').submit();">刪除</button>

															</div>
														</div>

													</div>
												</div>





											</c:forEach>
										</ul>
									</c:if>
								</div>




							</div>

						</div>
					</div>
				</div>






				<!-- MODAL -->
				<div class="modal fade" id="addAlbum" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">


							<div class="modal-header">
								<h4 class="modal-title">
									<b>新增相簿</b>
								</h4>
							</div>



							<form
								action="<%=request.getContextPath()%>/front_end/album/Album.do"
								method="post" enctype="multipart/form-data">
								<input type="hidden" name="action" value="createAlbum">
								<div class="form-group">
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="fa fa-user fa" aria-hidden="true"></i></span> <input
												type="text" class="form-control" name="albumTitle"
												id="albumTitle" placeholder="請輸入相簿名稱" required/>
										</div>
									</div>
								</div>
								<input id="input-20" name="img" type="file" class="file-loading"
									multiple>
							</form>
						</div>
					</div>
				</div>



				<%@ include file="memButtom.file"%>


			</div>
		</div>


		<script>
			$(document).on('ready', function() {
				$("#input-20").fileinput({
					maxFileCount : 10,
					allowedFileTypes : [ "image", "video" ]
				});
			});
		</script>


		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>