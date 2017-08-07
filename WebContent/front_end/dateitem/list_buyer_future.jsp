<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*"%>

<%@ include file="header.file"%>

<jsp:useBean id = "dSvc" scope="page" class="com.dateitem.model.DateItemService" />

<%
Member member = (Member) session.getAttribute("member");
List<DateItemVO> list = dSvc.findByBuyer_future(member.getMemNo());
pageContext.setAttribute("list",list);
%>




<head><title>購買紀錄</title></head>
<body bgcolor='white'>

<%@ include file="nav.file"%>
<%@ include file="sidelist.file"%>

<!-- JSP以一個包含格線的div開始, 但是結束的</div>寫在footer裡面 -->

<div class="col-xd-12 col-sm-8 col-sm-offset-1 main-page-show">

					<ul class="list-inline amos">
						    <li><a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=list_buyer_future">進行中的約會</a></li>
					<li><a href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=list_buyer_history">過去的約會資料</a></li>
					</ul>
  
  <c:if test="${not empty itemPurchased}">
  <h1>您已購買成功，新增一筆紀錄!</h1>
   <c:remove var="itemPurchased" scope="request"/>
  </c:if>
  
  
  <table class="table text-align:center">
	<tr>
		<th align="center">編號</th>
		<th align="center">時間</th>
		<th align="center">賣家</th>
<th></th>

	</tr>
<%@ include file="page3.file"%>
<c:forEach var="dateitem" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr align='center' valign='middle'>
			<td>${dateitem.dateItemNo}</td>
			<td>${dSvc.getTimeForYMD(dateitem.dateMeetingTime)}</td>
			<td>${dateitem.sellerNo}</td>

			<td><a class="btn btn-primary" data-toggle="modal" data-target="#modal-cancel" href="#">我要取消</a></td>
			<td><a class="btn btn-primary" data-toggle="modal" data-target="#modal-app" href="#">我要申訴</a></td>
		</tr>
		
<!-- ================================================取消按鈕的modal,必須在foreach內=====		 -->
		<div id="modal-cancel" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">取消約會預定</h4>
      </div>
      <div class="modal-body">
        <p></p>
        <p>確定預約嗎?</p>
      </div>
      <div class="modal-footer">
        <a type="button" class="btn btn-default" href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=cancel_date&dateItemNo=${dateitem.dateItemNo}&fromwho=buyer">確認取消</a>
        <button type="button" class="btn btn-default" data-dismiss="modal">回上一頁</button>
      </div>
    </div>

  </div>
</div>
<!-- ================================================取消按鈕的modal,必須在foreach內=====		 -->		
  </c:forEach>
  
  
  
</table>



<%@ include file="footer.file"%>





