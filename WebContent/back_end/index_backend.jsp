<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>


<%
	Emp emp = (Emp) session.getAttribute("emp");
	pageContext.setAttribute("emp", emp);
	List<Integer> auth = (List<Integer>) session.getAttribute("auth");
	pageContext.setAttribute("auth", auth);
%>


<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link href="<%=request.getContextPath()%>/back_end/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/nav.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/colorplan.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/back_end/css/modern-business.css"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/back_end/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/back_end/css/backend.css"
	rel="stylesheet">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>

<body>
	<nav id="emerald-nav" class="navbar navbar-light navbar-fixed-top"
		role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand logo" href="<%=request.getContextPath()%>/back_end/index_backend.jsp">寵物You&amp;Me</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				</ul>
				<div class="nav collapse navbar-collapse navbar-right" id="login">
					<ul class="nav navbar-nav">
						<c:if test="${emp!=null}">
							<li><a
								href="<%=request.getContextPath()%>/back_end/emp/EmpLogout.do">管理員登出</a></li>
						</c:if>
						<c:if test="${emp==null}">
							<li><a
								href="<%=request.getContextPath()%>/back_end/emp/empLogin.jsp">管理員入</a></li>
						</c:if>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
				<!-- /.container -->
	</nav>


	<div class="container-fluid">
		<div class="row">




			<div class="col-xs-12 col-sm-2 postion-left-group-b">
				<div id="menu">
					<div class="panel list-group list-color">

						<a href="<%=request.getContextPath()%>/back_end/index_backend.jsp" class="list-group-item">後端首頁</a>
	



						<c:if test="<%=(((List) pageContext.getAttribute(\"auth\")) != null)%>">



							<c:if
								test="<%=(((List) pageContext.getAttribute(\"auth\")).contains(4001))%>">
								<%-- 						<% if (((List)pageContext.getAttribute("auth")).contains(1)) {%> --%>
								<a href="#" class="list-group-item" data-toggle="collapse"
									data-target="#sm1" data-parent="#menu">前端看板管理 <span
									class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
								<div id="sm1" class="sublinks collapse">
									<a href="#" class="list-group-item small"> 幻燈片管理</a> <a
										href="#" class="list-group-item small"> 最新消息管理</a>
								</div>
								<%-- 						<% } %> --%>
							</c:if>

							<c:if
								test="<%=(((List) pageContext.getAttribute(\"auth\")).contains(4002))%>">
								<a href="#" class="list-group-item" data-toggle="collapse"
									data-target="#sm2" data-parent="#menu">會員帳號管理 <span
									class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
								<div id="sm2" class="sublinks collapse">
									<a href="<%=request.getContextPath()%>/back_end/member/memManage.jsp" class="list-group-item small"> 一般會員管理</a> <a
										href="#" class="list-group-item small"> 餐廳會員管理</a>
								</div>
							</c:if>


							<c:if
								test="<%=(((List) pageContext.getAttribute(\"auth\")).contains(4003))%>">
								<a href="#" class="list-group-item" data-toggle="collapse"
									data-target="#sm3" data-parent="#menu">商城管理 <span
									class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
								<div id="sm3" class="sublinks collapse">
									<a href="#" class="list-group-item small"> 商品管理</a> <a href="#"
										class="list-group-item small"> 訂單管理</a>
								</div>
							</c:if>


							<c:if
								test="<%=(((List) pageContext.getAttribute(\"auth\")).contains(4004))%>">
								<a href="#" class="list-group-item" data-toggle="collapse"
									data-target="#sm4" data-parent="#menu">檢舉申訴管理 <span
									class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
								<div id="sm4" class="sublinks collapse">
									<a href="#" class="list-group-item small"> 約會商品檢舉管理</a> <a
										href="#" class="list-group-item small"> 約會商品申訴管理</a>
								</div>
							</c:if>


							<c:if
								test="<%=(((List) pageContext.getAttribute(\"auth\")).contains(4005))%>">
								<a href="#" class="list-group-item" data-toggle="collapse"
									data-target="#sm5" data-parent="#menu">權限管理 <span
									class="glyphicon glyphicon-triangle-bottom pull-right"></span></a>
								<div id="sm5" class="sublinks collapse">


									<a href="<%=request.getContextPath()%>/back_end/emp/authManage.jsp" class="list-group-item small"> 管理員權限管理</a> <a
										href="<%=request.getContextPath()%>/back_end/emp/empRegister.jsp"
										class="list-group-item small"> 註冊新管理員</a>
								</div>
							</c:if>


							<c:if
								test="<%=(((List) pageContext.getAttribute(\"auth\")).contains(4006))%>">
								<a href="#" class="list-group-item">活動審核</a>
							</c:if>
							<c:if
								test="<%=(((List) pageContext.getAttribute(\"auth\")).contains(4007))%>">
								<a href="#" class="list-group-item">餐廳審核</a>
							</c:if>
							<c:if
								test="<%=(((List) pageContext.getAttribute(\"auth\")).contains(4008))%>">
								<a href="#" class="list-group-item">管站內信管理</a>
							</c:if>


						</c:if>



					</div>
				</div>
			</div>





			<div class="col-xs-12 col-sm-8">


				<h5 class="page-header text-right">目前位置:後端首頁</h5>


				<div
					class="panel panel-default col-sm-offset-3 col-sm-6 text-center">
					<div class="panel-heading">
						<h3 class="panel-title">待處理事項</h3>
					</div>

					<ul class="list-group">
						<li class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5
							筆待審核訂單 <span class="label label-info pull-right">5</span>
						</li>
						<li class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1
							筆待審核餐廳 <span class="label label-info pull-right">1</span>
						</li>
						<li class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2
							筆待處理申訴 <span class="label label-info pull-right">2</span>
						</li>
						<li class="list-group-item">0 筆待處理檢舉</li>
						<li class="list-group-item">0 筆待審核活動</li>

					</ul>







				</div>




			</div>

			<script src="https://code.jquery.com/jquery.js"></script>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>