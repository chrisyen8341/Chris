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

<%@ include file="memHead.file"%>





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


.aImg{
max-width: 100%;

}



.panel-body{
padding:20px;
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

								<div class="row text-right" style="margin-right:10px;">
										<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#addAlbum">新增相簿</button>
										<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#addAlbum">編輯相簿</button>
								</div>

								<div class="row" style="margin-top:30px;max-width: 100%;">
									<c:if test="${not empty albumSet}">
										<ul>
											<c:forEach var="album" items="${albumSet}">
												<div class="col-md-3 col-sm-3" width="400px" height="300px">
													<div class="wrimagecard wrimagecard-topimage" >
														<a href="<%=request.getContextPath() %>/front_end/album/aImgShow.jsp?albumNo=${album.albumNo}"> 
					
																<img class="aImg" src="<%=request.getContextPath() %>/front_end/album/AlbumReader.do?albumNo=${album.albumNo}">

															<div class="wrimagecard-topimage_title" style="background-color: rgba(130, 93, 9, 0.1)">
																<h4>${album.albumTitle}
																	<div class="pull-right badge" id="WrThemesIcons"></div>
																</h4>
															</div>
														</a>
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



							<form action="<%=request.getContextPath()%>/front_end/album/Album.do"
								method="post" enctype="multipart/form-data">
								<input type="hidden" name="action" value="createAlbum">
								<div class="form-group">
									<div class="cols-sm-10">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="fa fa-user fa" aria-hidden="true"></i></span> <input
												type="text" class="form-control" name="albumTitle"
												id="albumTitle" placeholder="請輸入相簿名稱" />
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