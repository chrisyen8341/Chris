<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ include file="header.file"%>

<jsp:useBean id = "dSvc" scope="page" class="com.dateitem.model.DateItemService" />

<%
Member member = (Member) session.getAttribute("member");
List<DateItemVO> list = dSvc.findByBuyer_history(member.getMemNo());
pageContext.setAttribute("list",list);
%>




<head><title>購買紀錄</title></head>
<body bgcolor='white'>

<%@ include file="nav.file"%>
<%@ include file="sidelist.file"%>

<!-- JSP以一個包含格線的div開始, 但是結束的</div>寫在footer裡面 -->

<div class="col-xd-12 col-sm-8 col-sm-offset-1 main-page-show">

  <c:if test="${not empty itemCanceled}">
  <h1>您已取消一筆約會，寫入您的紀錄!</h1>
   <c:remove var="itemCanceled" scope="request"/>
  </c:if>

					<ul class="list-inline amos">
                    <li><a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=list_buyer_future">進行中的約會</a></li>
					<li><a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=list_buyer_history">過去的約會資料</a></li>
					</ul>
  
  <table class="table text-align:center">
	<tr>
		<th align="center">編號</th>
		<th align="center">時間</th>
		<th align="center">賣家</th>
<th>交易結果</th>
	</tr>
<%@ include file="page3.file"%>
<c:forEach var="dateitem" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr align='center' valign='middle'>
			<td>${dateitem.dateItemNo}</td>
			<td>${dSvc.getTimeForYMD(dateitem.dateMeetingTime)}</td>
			<td>${dateitem.sellerNo}</td>
			<td><c:if test="${dateitem.dateItemStatus==3}">
  			交易完成
			</c:if>
			<c:if test="${dateitem.dateItemStatus==2}">
  			交易取消
			</c:if>
			
		</tr>
  </c:forEach>
</table>

<%@ include file="footer.file"%>





