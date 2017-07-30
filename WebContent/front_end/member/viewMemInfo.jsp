<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>

<html lang="">


<%
	Integer memNo = (Integer) request.getAttribute("memNo");
	MemberService memSvc = new MemberService();
	Member memberV = memSvc.getOneMember(memNo);
	pageContext.setAttribute("memberV", memberV);
%>

<head>

<%@ include file="memHead.file"%>
<style>
.pet {
	margin-top: 50px;
}
.member {
	margin-buttom: 100px;
}



</style>


</head>

<body>
	<%@ include file="memNavBar.file"%>
	<div class="container-fluid">
		<div class="row">

			<div class="col-xs-12 col-sm-2 postion-left-group ">


				<div id="menu">
					<div class="panel list-group list-color">
						<a
							href="<%=request.getContextPath() + "/front_end/member/memberInfo.jsp"%>"
							class="list-group-item">個人資訊</a>
						<%
							Member member = (Member) session.getAttribute("member");
							List<Pet> list = memSvc.getPetsByMemNo(member.getMemNo());
							pageContext.setAttribute("list", list);
						%>
						<c:if test="${not empty list}">
							<a
								href="<%=request.getContextPath() + "/front_end/pet/petInfo.jsp"%>"
								class="list-group-item">寵物資訊</a>
						</c:if>
						<c:if test="${empty list}">
							<a
								href="<%=request.getContextPath() + "/front_end/pet/petRegister.jsp"%>"
								class="list-group-item">註冊寵物</a>
						</c:if>
						<a
							href="<%=request.getContextPath() + "/front_end/member/memPwdChange.jsp"%>"
							class="list-group-item">變更密碼</a> <a
							href="<%=request.getContextPath() + "/front_end/member/searchOtherMem.jsp"%>"
							class="list-group-item">搜尋會員</a> <a href="#"
							class="list-group-item">相簿管理</a>
					</div>
				</div>


			</div>

			<div class="col-xs-12 col-sm-8 ">
				<div class="row">


					<h5 class="page-header text-right">目前位置:會員專區</h5>

					<div class="row">

						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">${memberV.memId}</h3>
							</div>
							<div class="panel-body">
								<div class="row">

									<div class="row member">

										<div class="col-md-3 col-lg-3 " align="center">
											<img alt="User Pic" id="memImg"
												src="<%=request.getContextPath()%>/DBGifReader"
												height="350px" width="250px"
												class="img-circle img-responsive">
										</div>


										<div class=" col-md-9 col-lg-9 ">
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td class="title">暱稱</td>
														<td>${memberV.memSname}</td>
													</tr>
													<tr>
														<td class="title">性別</td>
														<%
															String memGender = String.valueOf(memberV.getMemGender());
															HashMap mGender = (HashMap) application.getAttribute("mGender");
														%>
														<td><%=mGender.get(memGender)%></td>
													</tr>
													<tr>

														<td class="title">感情</td>
														<%
															String memRelation = String.valueOf(memberV.getMemRelation());
															HashMap mRelation = (HashMap) application.getAttribute("mRelation");
														%>
														<td><%=mRelation.get(memRelation)%></td>
													</tr>
													<tr>
														<td class="title">粉絲</td>
														<td>${memberV.memFollowed}人</td>
													</tr>
													<tr>
														<td class="title">關於我</td>
														<td>${memberV.memSelfintro}</td>
													</tr>


												</tbody>
											</table>

										</div>

									</div>

									<div>
										<c:forEach var="pet" items="${list}" varStatus="s">
											<div class="row pet">
												<div class="col-md-3 col-lg-3 " align="center">
													<img alt="User Pic" id="petImg"
														src="<%=request.getContextPath() %>/PetImgReader?petNo=${pet.petNo}"
														height="350px" width="250px"
														class="img-circle img-responsive">
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

												</div>
											</div>
										</c:forEach>
								</div>


								</div>
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
		<script>
			$(document).ready(function(e) {
				$('.search-panel .dropdown-menu').find('a').click(function(e) {
					e.preventDefault();
					var param = $(this).attr("href").replace("#", "");
					var concept = $(this).text();
					$('.search-panel span#search_concept').text(concept);
					$('.input-group #search_param').val(param);
				});
			});
		</script>
</body>

</html>