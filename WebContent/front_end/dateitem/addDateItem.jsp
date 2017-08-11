<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="java.util.List"%>

<%@ include file="header.file"%>

<%
	DateItemVO dateItemVO = (DateItemVO) request.getAttribute("dateItemVO");
	Long now = System.currentTimeMillis();
	Long candatetimemin = now + 3600000;
	Long candatetimemax = candatetimemin + 5184000000L;
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:00");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	Timestamp timemin = new Timestamp(candatetimemin);
	Timestamp timemax = new Timestamp(candatetimemax);
	String tmin = sdf1.format(timemin);
	String tmax = sdf2.format(timemax);
	RestaurantService restService = new RestaurantService();
	List<Restaurant> rests = restService.getAll();
	pageContext.setAttribute("rests", rests);
%>

<style>
#map {
	height: 600px;
	width: 100%;
}
</style>



<head>
<title>約會商品上架</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>



<%@ include file="nav.file"%>
<%@ include file="sidelist.file"%>


<div class="col-md-offset-1 col-md-8 main-page-show">

	<h4>
		選擇商品內容:<font color=red><b>*</b></font>為必填欄位
	</h4>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<form action="dateitem.do" method=post enctype="multipart/form-data">
		<table class="table">
			<!-- 	<tr> -->
			<!-- 		<td>賣家會員編號:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><input type="TEXT" name="sellerNo" size="45"  -->
			<%-- 			 value="${param.sellerno}"/></td> --%>
			<!-- 	</tr> -->

			<tr>
				<jsp:useBean id="restSvc" scope="page"
					class="com.restaurant.model.RestaurantService" />
				<td>選擇餐廳:<font color=red><b>*</b></font></td>
				<td><select id="restListNo" size="1" name="restListNo">
						<c:forEach var="rest" items="${restSvc.all}">
							<option value="${rest.restNo}"
								${(rest.restNo==dateItemVO.restListNo)? 'selected':'' }>${rest.restName}
						</c:forEach>

				</select> <a class="btn btn-info" href=""> 新增一個餐廳 </a> <a
					class="btn btn-info" data-toggle="modal" data-target="#googleMap"
					href=""> 地圖瀏覽 </a></td>
			</tr>




			<tr>
				<td>選擇你的寵物:<font color=red><b>*</b></font></td>
				<td><select size="1" name="petNo">
						<c:forEach var="pet" items="${myPetList}">
							<option value="${pet.petNo}"
								${(pet.petNo==dateItemVO.petNo)? 'selected':'' }>${pet.petName}
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>約會價格:</td>
				<td><input type="TEXT" name="dateItemPrice" size="45"
					value="<%=(dateItemVO == null) ? "" : dateItemVO.getDateItemPrice()%>" /></td>
			</tr>

			<tr>
				<td>約會商品標題:</td>
				<td><input type="TEXT" name="dateItemTitle" size="45"
					value="<%=(dateItemVO == null) ? "" : dateItemVO.getDateItemTitle()%>" /></td>
			</tr>

			<tr>
				<td>約會商品描述:</td>

				<td><input type="text" name="dateItemText" size="45"
					value="<%=(dateItemVO == null) ? "" : dateItemVO.getDateItemText()%>" /></td>
			</tr>

			<tr>
				<td>約會時間(60天內):</td>
				<td><input type="datetime-local" max=<%=tmax%> min=<%=tmin%>
					step="1800" name="time"></td>
			</tr>



			<tr>
				<td>買方人數限制:<font color=red><b>*</b></font></td>
				<td><select name="dateItemPeople">
						<option value="1" selected>1人</option>
						<option value="2">2人</option>
				</select>
			</tr>

			<tr>
				<td>賣方友人參與:<font color=red><b>*</b></font></td>
				<td><select name="hasMate">
						<option value="false" selected>沒有</option>
						<option value="true">有</option>
				</select>
			</tr>

			<label class="control-label">上傳約會圖片</label>
			<input id="input-1" type="file" class="file" name="dateItemImg"
				data-show-upload="false" data-show-caption="true">

			<!-- 	<input type="file" class="file" name="dateItemImg" > -->
			<tr>
				<td><input type="hidden" name="action" value="insert"></td>
			</tr>
			<tr>
				<td><input type="submit" value="上架商品"></td>
			</tr>

		</table>
	</FORM>





	<!-- Add photo MODAL -->
	<div class="modal fade" id="googleMap" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<b>瀏覽餐廳</b>
					</h4>
				</div>


				<p>
					輸入欲查詢的地址<input id="address" type="text" size="50" value="">
					<input class="btn-info" type="button" value="查詢"
						onClick="codeAddress()">
				</p>
				<div id="map"></div>


			</div>
		</div>
	</div>




</div>
<BR>
<BR>
<BR>



<script>
  var geocoder;
  var map, popup;
  var markers;
  
  $(function(){
	  
	  $("#googleMap").on("shown.bs.modal", function () {
		    google.maps.event.trigger(map, "resize");
		    console.log("qwe");
		});
	  
	
  })
  
  
  function initMap(){
      // Map options
      var options = {
        zoom:15,
        center:{lat: 25.042419,lng:121.541808}
      }

      // New map
      map = new google.maps.Map(document.getElementById('map'), options);
      geocoder = new google.maps.Geocoder();
      popup = new google.maps.InfoWindow();

//       // Listen for click on map
//       google.maps.event.addListener(map, 'click', function(event){
//         // Add marker
//         addMarker({coords:event.latLng});
//       });


      

	          markers = new Array();

				<c:forEach var="rest" items="${rests}">
	        	marker=new Object();
				marker.coords={lat:${rest.getRestLatitude()},lng:${rest.getRestLongtitude()}};
 				marker.iconImage='https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png';
 				marker.content=
 				"<H1>${rest.restName}</H1>"
 				+"<H2>${rest.restPhone}</H2>"
 				+"<H3>${rest.restAdd}</H3>"
 				+"<input type=\"button\" value=\"選擇此餐廳\" onClick=\"chooseRest(${rest.restNo})\">" ;
				markers.push(marker);
			</c:forEach>
      
      
      
      
      // Loop through markers
      for(var i = 0;i < markers.length;i++){
        // Add marker
        addMarker(markers[i]);
      }

      // Add Marker Function
function addMarker(props){
                                  var marker = new google.maps.Marker({
          position:props.coords,
          map:map,
          //icon:props.iconImage
        });

        // Check for customicon
 if(props.iconImage){ 
          // Set icon image
          marker.setIcon(props.iconImage);
        }

        // Check content
        if(props.content){
          var infoWindow = new google.maps.InfoWindow({
            content:props.content
          });

          marker.addListener('click', function(){
            infoWindow.open(map, marker);
          });
        }
      }    
      }
    
    
    
    
    function codeAddress() {
  	    var address = document.getElementById("address").value;
  	    geocoder.geocode( { 'address': address}, function(results, status) {
  	      if (status == google.maps.GeocoderStatus.OK) {
  	        map.setCenter(results[0].geometry.location);

  			document.getElementById("lat").value=results[0].geometry.location.lat();
  			document.getElementById("lng").value=results[0].geometry.location.lng();
  	        var marker = new google.maps.Marker({
  	            map: map,
  	            position: results[0].geometry.location
  	        });


  	      } else {
  	        alert("失敗, 原因: " + status);
  	      }
  	    });
  	  }


    
    
  	  function chooseRest(restNo) {
  		  $('#restListNo').val(restNo);
  		$('#googleMap').modal('hide');
	  }
  	  

  </script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAUbYcDBdfK_UjTWa9G6FSe3EfERMpEZQ&callback=initMap">
    </script>


<%@ include file="footer.file"%>

