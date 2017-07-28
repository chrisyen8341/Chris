<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member.model.Member"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
	String method = request.getParameter("method");

	String search = (String) (request.getAttribute("search"));
	if (search == null) {
		String searchb = request.getParameter("search");
		search = request.getParameter("search");
		//如果是走get的話 要再轉一次
		if (!"post".equals(method)) {
			search = new String(searchb.getBytes("ISO-8859-1"), "UTF-8");
		}
	}

	String type = (String) (request.getAttribute("type"));
	if (type == null) {
		String typeb = request.getParameter("type");
		type = request.getParameter("type");
		//如果是走get的話 要再轉一次
		if (!"post".equals(method)) {
			type = new String(typeb.getBytes("ISO-8859-1"), "UTF-8");
		}
	}

	pageContext.setAttribute("search", search);
	pageContext.setAttribute("type", type);

	List<Member> mlist = null;
	if (type.equals("會員")) {
		MemberService memSvc = new MemberService();
		mlist = memSvc.getMembersByIdName(search);
		System.out.println(mlist);
		pageContext.setAttribute("mlist", mlist);
	}

	List<Pet> plist = null;
	if (type.equals("寵物")) {
		PetService petSvc = new PetService();
		plist = petSvc.getPetsByName(search);
		pageContext.setAttribute("plist", plist);
	}
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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
</script>
<STYLE>
.title {
	width: 70px; /* 設定 H1 的樣式*/
}
</STYLE>
<style>
.searchbtn {
	height: 100%;
}

#custom-search-form {
	margin-top: 100px
}
</style>

<style>
.card {
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
    width: 150px;
    height: 200px
    border-radius: 5px;
}

.card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

img {
    border-radius: 5px 5px 0 0;
}

.container {
    padding: 2px 16px;
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

					<c:if test="<%=type.equals(\"會員\")%>">

						<%
							int rowsPerPage = 3; //每頁的筆數    
								int rowNumber = 0; //總筆數
								int pageNumber = 0; //總頁數      
								int whichPage = 1; //第幾頁
								int pageIndexArray[] = null;
								int pageIndex = 0;
								rowNumber = mlist.size();
						%>

						<%@ include file="page1.file"%>
	
							<c:forEach var="member" items="${mlist}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">


									<div class="card">
										<img src="<%=request.getContextPath() %>/front_end/member/MemImgReader2.do?memNo=${member.memNo}" alt="Avatar" style="width: 100%">
										<div class="container">
											<h4>
												<b>${member.memSname}</b> 
											</h4>
											<h5>
												<b>${member.memName}</b>
											</h5>
											<p>${member.memId}</p>
										</div>
									</div>


							</c:forEach>
				



						<!-- page2 -->
						<table border="0">
							<tr>
								<%
									if (rowsPerPage < rowNumber) {
								%>
								<%
									if (pageIndex >= rowsPerPage) {
								%>
								<td><A
									href="<%=request.getRequestURI()%>?whichPage=1&type=<%=type%>&search=<%=search%>">至第一頁</A>&nbsp;</td>
								<td><A
									href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>&type=<%=type%>&search=<%=search%>">上一頁
								</A>&nbsp;</td>
								<%
									}
								%>

								<%
									if (pageIndex < pageIndexArray[pageNumber - 1]) {
								%>
								<td><A
									href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>&type=<%=type%>&search=<%=search%>">下一頁
								</A>&nbsp;</td>
								<td><A
									href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>&type=<%=type%>&search=<%=search%>">至最後一頁</A>&nbsp;</td>
								<%
									}
								%>
								<%
									}
								%>
							</tr>
						</table>
						<%
							if (pageNumber > 1) {
						%>
						<table border="0">
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">
									<input type="hidden" name="method" value="post"> <input
										type="hidden" name="type" value=<%=type%>> <input
										type="hidden" name="search" value=<%=search%>>
									<td><select size="1" name="whichPage">
											<%
												for (int i = 1; i <= pageNumber; i++) {
											%>
											<option value="<%=i%>">跳至第<%=i%>頁
												<%
												}
											%>
											
									</select> <input type="submit" value="確定"></td>
								</FORM>
							</tr>
						</table>
						<%
							}
						%>






					</c:if>












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
					$("#stype").val(concept);
					console.log($("#stype").attr("value"));
					$('.search-panel span#search_concept').text(concept);
					$('.input-group #search_param').val(param);
				});
			});

			$(function() {

			});
		</script>
</body>

</html>