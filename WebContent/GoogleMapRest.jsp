<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
RestaurantService restService =new RestaurantService();
List<Restaurant> rests= restService.getAll();
pageContext.setAttribute("rests", rests);
%>


<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		  <style>
    #map{
      height:400px;
      width:100%;
    }
  </style>
	</head>
	<body>

  <h1>My Google Map</h1>
  <p> 輸入欲查詢的地址<input id="address" type="text" size="50" value="">   <input type="button" value="查經緯度並在地圖上顯示" onClick="codeAddress()"> </p>
  <div id="map"></div>
  <script>
  var geocoder;
  var map, popup;
  
  
  
  function initMap(){
      // Map options
      var options = {
        zoom:15,
        center:{lat: 25.039489,lng:121.549328}
      }

      // New map
      map = new google.maps.Map(document.getElementById('map'), options);
      geocoder = new google.maps.Geocoder();
      popup = new google.maps.InfoWindow();

      // Listen for click on map
      google.maps.event.addListener(map, 'click', function(event){
        // Add marker
        addMarker({coords:event.latLng});
      });

      /*
      // Add marker
      var marker = new google.maps.Marker({
        position:{lat:42.4668,lng:-70.9495},
        map:map,
        icon:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png'
      });

      var infoWindow = new google.maps.InfoWindow({
        content:'<h1>Lynn MA</h1>'
      });

      marker.addListener('click', function(){
        infoWindow.open(map, marker);
      });
      */

      // Array of markers
      
//       var markers = [
//         {
//           coords:{lat:42.4668,lng:-70.9495},
//           iconImage:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
//           content:'<h1>Lynn MA</h1>'
//         },
//         {
//           coords:{lat:42.8584,lng:-70.9300},
//           content:'<h1>Amesbury MA</h1>'
//         },
//         {
//           coords:{lat:42.7762,lng:-71.0773}
//         }
//       ];

      

	          var markers = new Array();

				<c:forEach var="rest" items="${rests}">
	        	marker=new Object();
				marker.coords={lat:${rest.getRestLatitude()},lng:${rest.getRestLongtitude()}};
 				marker.iconImage='https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png';
 				marker.content=
 				"<H1>${rest.restName}</H1>"
 				+"<H2>${rest.restPhone}</H2>"
 				+"<H3>${rest.restAdd}</H3>"
 				+"<input type=\"button\" value=\"選擇此餐廳\" onClick=\"chooseRest()\">" ;
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


    
    
  	  function chooseRest() {
			 console.log("123");
	  }
  	  
  </script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAUbYcDBdfK_UjTWa9G6FSe3EfERMpEZQ&callback=initMap">
    </script>
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>