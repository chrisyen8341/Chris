<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.dateitem.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

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
				<Form action="<%=request.getContextPath()%>/front_end/dateitem/dateitem.do" method="post">
					<input type="hidden" name="action" value="googleMapQuery"> 
<!-- 					<input type="date" id="datepicker" name="dateMeetingTime"> -->
<!-- 					  <select class="w3-select selectpicker" name="memGender"> -->
<!-- 					    <option value="" disabled selected>請選擇主人性別</option> -->
<!-- 					    <option value="">皆可</option> -->
<!-- 					    <option value="0">男</option> -->
<!-- 					    <option value="1">女</option> -->
<!-- 					    <option value="2">不願透露</option> -->
<!-- 					  </select> -->

<!-- 					  <select class="w3-select selectpicker" name="petKind"> -->
<!-- 					    <option value="" disabled selected>請選擇寵物</option> -->
<!-- 					    <option value="">皆可</option> -->
<!-- 					    <option value="狗">狗</option> -->
<!-- 					    <option value="貓">貓</option> -->
<!-- 					    <option value="其他">其他</option> -->
<!-- 					  </select> -->

					<input type="submit">
					<!--   <button class="btn btn-lg btn-warning glyphicon glyphicon-search"> </button> -->


				</Form>

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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	
	
	
	
	
	<script>

 function initializeMaps() {


 	var options = {
 		zoom:10,
 		center:{lat: 25.042419,lng:121.541808}
 	}

 	var map = new google.maps.Map(document.getElementById('map_canvas'), options);

	// var bounds = new google.maps.LatLngBounds();


	var markers = new Array();


	marker1=new Object();
	marker1.lat=25.040302; 
	marker1.lng=121.552108;
	marker1.content="<H1>test500</H1>"
	marker1.count=500;
	markers.push(marker1);


	marker2=new Object();
	marker2.lat=25.031097;
	marker2.lng=121.551705;
	marker2.content="<H1>test123</H1>"
	marker2.count=2000;
	markers.push(marker2);



    for(var i = 0;i < markers.length;i++){
        // Add marker

        generateIcon(markers[i], function(src,marker) {
        		var pos = new google.maps.LatLng(Number(marker.lat),Number(marker.lng));
        		console.log(i);
	  	// bounds.extend(pos);

	  	var markerf = new google.maps.Marker({
	  		position: pos,
	  		map: map,
	  		icon: src,
	  		animation: google.maps.Animation.DROP
	  	});

	  	var infoWindow = new google.maps.InfoWindow({
	  		content:String(marker.content)
	  	});

	  	markerf.addListener('click', function(){
	  		infoWindow.open(map, markerf);
	  	});
	  }

	  );
    }

}





var generateIconCache = {};

function generateIcon(marker, callback) {
	if (generateIconCache[marker.count] !== undefined) {
		callback(generateIconCache[marker.count],marker);
	}

	var fontSize = 16,
	imageWidth = imageHeight = 35;

	if (marker.count >= 1000) {
		fontSize = 10;
		imageWidth = imageHeight = 55;
	} else if (marker.count < 1000 && marker.count > 100) {
		fontSize = 14;
		imageWidth = imageHeight = 45;
	}

	var svg = d3.select(document.createElement('div')).append('svg')
	.attr('viewBox', '0 0 54.4 54.4')
	.append('g')

	var circles = svg.append('circle')
	.attr('cx', '27.2')
	.attr('cy', '27.2')
	.attr('r', '21.2')
	.style('fill', '#2063C6');

	var path = svg.append('path')
	.attr('d', 'M27.2,0C12.2,0,0,12.2,0,27.2s12.2,27.2,27.2,27.2s27.2-12.2,27.2-27.2S42.2,0,27.2,0z M6,27.2 C6,15.5,15.5,6,27.2,6s21.2,9.5,21.2,21.2c0,11.7-9.5,21.2-21.2,21.2S6,38.9,6,27.2z')
	.attr('fill', '#FFFFFF');

	var text = svg.append('text')
	.attr('dx', 27)
	.attr('dy', 32)
	.attr('text-anchor', 'middle')
	.attr('style', 'font-size:' + fontSize + 'px; fill: #FFFFFF; font-family: Arial, Verdana; font-weight: bold')
	.text(marker.count);

	var svgNode = svg.node().parentNode.cloneNode(true),
	image = new Image();

	d3.select(svgNode).select('clippath').remove();

	var xmlSource = (new XMLSerializer()).serializeToString(svgNode);

	image.onload = (function(imageWidth, imageHeight) {
		var canvas = document.createElement('canvas'),
		context = canvas.getContext('2d'),
		dataURL;

		d3.select(canvas)
		.attr('width', imageWidth)
		.attr('height', imageHeight);

		context.drawImage(image, 0, 0, imageWidth, imageHeight);

		dataURL = canvas.toDataURL();
		generateIconCache[marker.count] = dataURL;

		callback(dataURL,marker);
	}).bind(this, imageWidth, imageHeight);

	image.src = 'data:image/svg+xml;base64,' + btoa(encodeURIComponent(xmlSource).replace(/%([0-9A-F]{2})/g, function(match, p1) {
		return String.fromCharCode('0x' + p1);
	}));
}

initializeMaps();

</script>
	
	
	
	

</body>

</html>

