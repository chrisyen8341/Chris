<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*"%>

<%@ include file="header.file"%>


<jsp:useBean id = "dSvc" scope="page" class="com.dateitem.model.DateItemService" />
<jsp:useBean id = "memSvc" scope="page" class="com.member.model.MemberService" />
<%
    List<DateItemVO> list = dSvc.getAllItems();
    pageContext.setAttribute("list",list);
%>




<head><title>約會首頁</title></head>
<body bgcolor='white'>



<%@ include file="nav.file"%>
<%@ include file="sidelist.file"%>

<%-- <%@ include file="page3.file"%>	 --%>

<!-- JSP以一個包含格線的div開始, 但是結束的</div>寫在footer裡面 -->

<div class="col-xd-12 col-sm-10  main-page-show">
<div class="col-sm-10 col-sm-offset-1">

<span >
 <select class="w3-select selectpicker " name="option">
    <option value="" disabled selected>請選擇地點</option>
    <option value="1">Option 1</option>
    <option value="2">Option 2</option>
    <option value="3">Option 3</option>
  </select>
  <select class="w3-select selectpicker" name="option">
    <option value="" disabled selected>請選擇主人</option>
    <option value="1">Option 1</option>
    <option value="2">Option 2</option>
    <option value="3">Option 3</option>
  </select>
  <select class="w3-select selectpicker" name="option">
    <option value="" disabled selected>請選擇寵物</option>
    <option value="1">Option 1</option>
    <option value="2">Option 2</option>
    <option value="3">Option 3</option></select>
	
  <button class="btn btn-lg btn-warning glyphicon glyphicon-search"> </button>

 </span> 

 </div>
 
 
  <c:forEach var="dateitem" items="${list}">
          <div class="col-sm-4 ">
            <div class="bg-color">
            <div class="card hovercard">
                <div class="cardheader" style="background-image:url('<%=request.getContextPath()%>/ImgReader?dateItemNo=${dateitem.dateItemNo}&action=dateImg');">

                </div>
                <div class="avatar">
                    <img src="<%=request.getContextPath()%>/ImgReader?sellerNo=${dateitem.sellerNo}&action=memImg">
                </div>
                <div class="info">
                    <div class="title dateDes">
                        <a class="dateDes" target="_blank" href="http://scripteden.com/">${dateitem.dateItemTitle}</a>
                    </div>
                    <div class="desc">${memSvc.getOneMember(dateitem.sellerNo).getMemSname()}</div>
                    <div class="desc">${dSvc.getTimeForItem(dateitem.dateMeetingTime)}</div>
                    <div class="desc">${dateitem.dateItemLocate}</div>
                </div>
                <div class="bottom">
                    <a class="btn btn-info" href="">
                        詳情
                    </a>
                </div>
                </div>
            </div>

        </div>
 
 </c:forEach>       
  
  
  
<!--   <table class="table text-align:center"> -->
<!-- 	<tr> -->
<!-- 		<th align="center">圖片</th> -->
<!-- 		<th align="center">暱稱</th> -->
<!-- 		<th align="center">編號</th> -->
<!-- 		<th align="center">時間</th> -->
<!-- 		<th align="center">賣家</th> -->
<!-- 		<th align="center">價格</th> -->
<!-- 		<th align="center">餐廳</th> -->
<!-- 	</tr> -->
	
<%-- <c:forEach var="dateitem" items="${list}"> --%>
<!-- 	<tr align='center' valign='middle'> -->
<%-- 		<td><img id="allitemsimg" src="<%=request.getContextPath()%>/ImgReader?dateitemNo=${dateitem.dateItemNo}"></td> --%>
<%-- 			<td>${memSvc.getOneMember(dateitem.sellerNo).getMemSname()}</td> --%>
<%-- 			<td>${dateitem.dateItemNo}</td> --%>
<%-- 			<td>${dSvc.getTimeForItem(dateitem.dateMeetingTime)}</td> --%>
<%-- 			<td>${dateitem.sellerNo}</td> --%>
<%-- 			<td>${dateitem.dateItemPrice}</td> --%>
<%-- 			<td>${dateitem.restListNo}</td> --%>
<!-- 			<td><a class="btn btn-primary checkit" data-toggle="modal" data-target="#modal-buy" >購買</a></td> -->
<%-- 			<td><input id="checkvalue" type="hidden" class="checkvalue" value="${dateitem.dateItemNo}" /></td> --%>
<!-- 		</tr> -->

		
<!-- <div id="modal-buy" class="modal fade" role="dialog"> -->
<!--   <div class="modal-dialog"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal">&times;</button> -->
<!--         <h4 class="modal-title">約會預定</h4> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         <p>確定預約約會嗎?</p> -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<%--         <a type="button" class="btn btn-default" href="<%=request.getContextPath() %>/front_end/dateitem/dateitem.do?action=buy_date&dateItemNo=${dateitem.dateItemNo}" >確認購買</a> --%>
<!--         <button type="button" class="btn btn-default" data-dismiss="modal">回上一頁</button> -->
<!--       </div> -->
<!--     </div> -->

<!--   </div> -->
<!-- </div> -->









<%--   </c:forEach> --%>
<!-- </table> -->

<!-- <script> -->
<!-- // function checktime(dateItemNo){ -->
<!-- // 	e.preventDefault(); -->
<!-- // 	alert('in'); -->
<!-- // 	$.ajax -->
<!-- //     ({ -->
<!-- //         type: "POST",            -->
<!-- //         data: 'dateItemNo='+dateItemNo+'&action=checkTime' , -->
<!-- //         url: 'dateitem.do', -->
<!-- //    		 success:function(content) -->
<!-- //     { -->
<!-- //            alert('ok');           -->
<!-- //         }    -->
        
<!-- //     }); -->
<!-- // } -->
<!-- // $('.checkit').on('click', function(e) { -->
<!-- // 	alert('in'); -->
<!-- //     $.ajax({ -->
<!-- //         url:  'dateitem.do', -->
<!-- //         type: 'POST', -->
<!-- //         data: 'dateItemNo='+$(this).next('.checkvalue').val()+'&action=checkTime', -->
<!-- //          +$(this).next('td').find('#checkvalue').val()+ -->
<!-- //         success: function(html){ -->
<!-- //         	alert('success'); -->
<!-- //             $('#modal-buy .modal-body p').html('test'); -->
<!-- //             $('#modal-buy').modal('show'); -->
<!-- //         }, -->
<!-- //         error: function(){ -->
<!-- //             alert("error"); -->
<!-- //         }   -->
<!-- //     });   -->
<!-- // }); -->

<!-- </script> -->

<%@ include file="footer.file"%>





