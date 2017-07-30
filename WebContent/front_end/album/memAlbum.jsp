<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

<head>

<%@ include file="memHead.file"%>
<style>
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
									
									   <input id="input-700" name="kartik-input-700[]" type="file" multiple class="file-loading">
									   
							</div>

						</div>
					</div>
				</div>


				<%@ include file="memButtom.file"%>


			</div>
		</div>
		<script>
$("#input-700").fileinput({
    uploadUrl: "<%=request.getContextPath() %>/front_end/album/AlbumUpload.do", // server upload action
    uploadAsync: true,
    maxFileCount: 5
});
</script>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>