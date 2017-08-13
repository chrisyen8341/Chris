<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<% 
List<SDateItemVO> list=(List<SDateItemVO>)request.getAttribute("googleMaplist"); 
pageContext.setAttribute("list", list);
List<SDateItemVO> filterList=(List<SDateItemVO>)request.getAttribute("filterList"); 
if(filterList!=null){
	pageContext.setAttribute("list", filterList);
	list=filterList;
}
Integer memGender=(Integer)request.getAttribute("memGender");
String petKind=(String)request.getAttribute("petKind");
pageContext.setAttribute("memGender", memGender);
pageContext.setAttribute("petKind", petKind);
// String date=(String)request.getAttribute("date");
// pageContext.setAttribute("date", date);
%>


<%@ include file="header.file"%>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAUbYcDBdfK_UjTWa9G6FSe3EfERMpEZQ">
	
</script>
<!--     <script src="https://maps.googleapis.com/maps/api/js?sensor=false" type="text/javascript"></script> -->
<script src="<%=request.getContextPath()%>/front_end/js/d3.min.js"></script>



<head>
<title>約會首頁</title>
<style type="text/css">
#map_canvas {
	width: 100%;
	height: 600px;
}
</style>
</head>
<body bgcolor='white'>



	<%@ include file="nav.file"%>
	<%@ include file="sidelist.file"%>

	<%-- <%@ include file="page3.file"%>	 --%>

	<!-- JSP以一個包含格線的div開始, 但是結束的</div>寫在footer裡面 -->

	<div class="col-xd-12 col-sm-10  main-page-show">
		<div class="col-sm-10 col-sm-offset-1">
			<span>
			<H1>${date}</H1>
			<H1>${memGender} ${petKind}</H1>
				<Form action="<%=request.getContextPath()%>/front_end/dateitem/dateitem.do" method="post">
					<input type="hidden" name="action" value="googleMapQuery">
					<input type="date" id="datepicker" name="dateMeetingTime" value=${date}>
					<input type="submit">
				</Form>
				<Form action="<%=request.getContextPath()%>/front_end/dateitem/dateitem.do" method="post">
					<input type="hidden" name="action" value="googleMapFilter">
					<input type="hidden" name="date" value=${date}>
					<select class="w3-select selectpicker" name="memGender">
<!-- 						<option value="" disabled selected>請選擇主人性別</option> -->
						<option value="4" <c:if test="${memGender==4}">selected</c:if>>不限主人性別</option>
						<option value="0" <c:if test="${memGender==0}">selected</c:if>>男</option>
						<option value="1" <c:if test="${memGender==1}">selected</c:if>>女</option>
						<option value="2" <c:if test="${memGender==2}">selected</c:if>>不願透露</option>
					</select> 
					<select class="w3-select selectpicker" name="petKind">
<!-- 						<option value="" disabled selected>請選擇寵物</option> -->					
						<option value="all" <c:if test="${petKind=='all'}">selected</c:if>>不限寵物種類</option>
						<option value="狗" <c:if test="${petKind=='狗'}">selected</c:if>>狗</option>
						<option value="貓" <c:if test="${petKind=='貓'}">selected</c:if>>貓</option>
						<option value="其他" <c:if test="${petKind=='其他'}">selected</c:if>>其他</option>
					</select> 
				</Form> 
				<!--   <button class="btn btn-lg btn-warning glyphicon glyphicon-search"> </button> -->

			</span>




			<div id="map_canvas"></div>





		</div>



		<footer>
			<div class="row navbar-fixed-bottom">
				<div class="col-sm-12">
					<div class="col-sm-3">
						<p>Copyright 寵物You&amp;Me 2017</p>
					</div>
					<div class="col-sm-3">
						<p>關於我們</p>
					</div>
				</div>
			</div>
		</footer>
	</div>
	</div>
	<a href="#">
		<div class="" id="fixedbutton-talk">
			<button class="button btn-lg btn-primary active">交易聊天室</button>
		</div>
	</a>
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>






	<script>

	
	
	
	$(function(){
		
		$('.selectpicker').on('change', function () {
			   console.log("touch");
				$(this).closest('form').submit();
			});

	});
	
	
	
	
	
	
	
	
 function initializeMaps() {


 	var options = {
 		zoom:10,
 		center:{lat: 25.042419,lng:121.541808}
 	}

 	var map = new google.maps.Map(document.getElementById('map_canvas'), options);



	var markers = new Array();





	<% 
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		Map<Integer,StringBuffer> dateNoMap=new HashMap<Integer,StringBuffer>();
		for(SDateItemVO date:list){
			if(!map.containsKey(date.getRestListNo())){
				map.put(date.getRestListNo(),1);
				StringBuffer sb=new StringBuffer();
				sb.append(date.getDateItemNo().toString()+",");
				dateNoMap.put(date.getRestListNo(),sb);
			}
			else{
				int i=map.get(date.getRestListNo());
				
				map.put(date.getRestListNo(), i+1);
				StringBuffer sb2=dateNoMap.get(date.getRestListNo());
				dateNoMap.get(date.getRestListNo()).append(date.getDateItemNo().toString()+",");
				dateNoMap.put(date.getRestListNo(),sb2);
			}		
		}
	%>   
	
	
	<% 
	List<Integer> tList=new ArrayList<Integer>();
	for(SDateItemVO date:list){
		int i=0;
		if(!tList.contains(date.getRestListNo())){
			
	%>

	marker=new Object();
	marker.coords={lat:<%=date.getRestLatitude() %>,lng:<%=date.getRestLongtitude() %>};
	marker.content=
		"<H1><%=date.getRestName() %></H1>"
		+"<H2><%=date.getRestPhone() %></H2>"
		+"<H3><%=date.getRestAdd() %></H3>"
		+"<Form method=\"post\" action=\"<%=request.getContextPath()%>/front_end/dateitem/dateitem.do\">"
		+"<input type=\"hidden\" name=\"dateItemNo\" value=\"<%=dateNoMap.get(date.getRestListNo()).toString() %>\"> "
		+"<input type=\"hidden\" name=\"action\" value=\"showDItemFromMap\" > "
		+"<input type=\"submit\" name=\"action\" value=\"查看此區<%=map.get(date.getRestListNo()) %>個約會明細\"> "
		+ "</Form>";
<%-- 		marker.icon = "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld="+<%=map.get(date.getRestListNo())%>+"|FF0000|000000"; --%>
<%-- 		marker.icon = <%=request.getContextPath() %>+"/mapNumber/number_"+<%=map.get(date.getRestListNo())%>+".png"; --%>
			marker.icon = {
					url:"<%=request.getContextPath()%>/front_end/mapNumber/number_<%=map.get(date.getRestListNo())%>.png",
			}
		markers.push(marker);
	<%tList.add(date.getRestListNo());
				}
			}%>
			

			// Loop through markers
			for (var i = 0; i < markers.length; i++) {
				// Add marker
				addMarker(markers[i]);
			}

			// Add Marker Function
			function addMarker(props) {
				var marker = new google.maps.Marker({
					position : props.coords,
					map : map,
					icon : props.icon,
					animation: google.maps.Animation.DROP,
				//icon:props.iconImage
				});

				// Check for customicon
				if (props.iconImage) {
					// Set icon image
					marker.setIcon(props.iconImage);
				}

				// Check content
				if (props.content) {
					var infoWindow = new google.maps.InfoWindow({
						content : props.content
					});

					marker.addListener('click', function() {
						infoWindow.open(map, marker);
					});
				}
			}

		}

		initializeMaps();
	</script>





</body>

</html>

