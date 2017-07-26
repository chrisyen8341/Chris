<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<html lang="">

<head>
<%@ include file="memHead.file"%>
<title>寵物You&amp;Me</title>
<script>
	//照片上傳預覽
	$(function() {

		$("#petImg").change(function() {
			readURL(this);
		});

		function readURL(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#petPic').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		;

	});
</script>

<STYLE>
.title {
	width: 120px; /* 設定 H1 的樣式*/
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

			<%
				
			Integer petOrd=0;
			boolean petOedIsNull=false;
			try{
				petOrd = Integer.parseInt(request.getParameter("petOrd"));
			}
			catch(Exception e){
				petOrd=0;
				petOedIsNull=true;
			}
			if(!petOedIsNull){	
			 Pet pet = list.get(petOrd);
				pageContext.setAttribute("pet", pet);
			}
			%>


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
									<form method="post" action="<%=request.getContextPath() %>/petUpdate" enctype="multipart/form-data">
										<div class="col-md-3 col-lg-3 " align="center">
											<img alt="User Pic" id="petPic"
												src="<%=request.getContextPath() %>/PetImgReader?petNo=${pet.petNo}" height="350px"
												width="250px" class="img-circle img-responsive"> <input
												type="file" name="petImg" id="petImg" placeholder="編輯相片" />
										</div>


										<div class=" col-md-9 col-lg-9 ">
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td class="title">寵物姓名</td>
														<td><input type="text" class="form-control"
															name="petName" id="petName" value="${pet.petName}"
															placeholder="請輸入暱稱" /></td>
													</tr>
													<tr>
														<td class="title">寵物分類</td>
														<td>${pet.petKind}</td>
													</tr>
													<tr>
														<td class="title">寵物性別</td>
														<td>${pet.petGender==o?'男':'女'}</td>
													</tr>

													<tr>
														<td class="title">寵物品種</td>
														<td><input type="text" class="form-control"
															name="petSpecies" id="petSpecies"
															value="${pet.petSpecies}" placeholder="請輸入您的姓名" /></td>
													</tr>

													<tr>
														<td class="title">寵物生日</td>
														<td><input type="date" name="petBday"
															min="1910-01-01" max='2000-13-13' id="memBday"
															value="${pet.petBday}" class="form-control"
															placeholder="Confirm your Password" /></td>
													</tr>


													<tr>
														<td class="title">寵物介紹</td>
														<td><textarea class="form-control" id="petIntro"
																name="petIntro" placeholder="請輸入您的地址">${pet.petIntro}</textarea></td>
													</tr>


												</tbody>

											</table>
											<input type="hidden" name="action" value="petUpdate">
											<input type="hidden" name="petNo" value="${pet.petNo}">
											<input type="submit" value="修改" class="btn btn-primary">
											
											<!-- 下方會停用會 呼叫modal-->
											<button type="button" class="btn btn-danger"
												data-toggle="modal" data-target="#disable">停用</button>
											
											
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
									</form>
									
								</div>


							</div>


						</div>
					</div>
				</div>

		<!-- Disable MODAL -->
		<div class="modal fade" id="disable" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="exampleModalLabel">是否確定停用?</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-footer">
						<span>

							<form action="<%=request.getContextPath() %>/petUpdate" method="post">
								<input type="hidden" name="action" value="petDisable">
								<input type="hidden" name="petNo" value="${pet.petNo}">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">否</button>
								<input type="submit" class="btn btn-primary" value="是">
							</form>
						</span>
					</div>
				</div>
			</div>
		</div>
		
		
				<%@ include file="memButtom.file"%>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>