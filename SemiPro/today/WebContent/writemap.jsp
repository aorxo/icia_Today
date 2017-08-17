<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			#map{
				width:500px;
				height:400px;
			}
			
			.map_wrap {
				position:relative;
				overflow:hidden;
				width:100%;
				height:350px;
			}
			
			.radius_border{
				border:1px solid black;
				border-radius:5px;
			}
			
			.custom_zoomcontrol {
				position:absolute;
				top:15px;
				left:465px;
				width:36px;
				height:80px;
				overflow:hidden;
				z-index:1;
				background-color:lightgray;
			}
			
			.custom_zoomcontrol span {
				display:block;
				width:36px;
				height:40px;
				text-align:center;
				cursor:pointer;
			}     
			
			.custom_zoomcontrol span img {
				width:15px;
				height:15px;
				padding:12px 0;
				border:none;
			}         
			    
			.custom_zoomcontrol span:first-child{
				border-bottom:1px solid #bfbfbf;
			}
		</style>
	</head>
	<body>
		<div class="map_warp">
			<div id="map"></div>
			<div class="custom_zoomcontrol radius_border"> 
	        	<span onclick="zoomIn()">
	        		<img src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png" 
	        		alt="확대">
	        	</span>  
	        	<span onclick="zoomOut()">
	        		<img src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png" 
	        		alt="축소">
	        	</span>
	   	 	</div>
	   	</div>
	   	
		<p id="ClickInfo"></p>
		<form action="readmap" method="post">
	   		<input type ="text" id="latval" name="lat" />
	   		<input type="text" id="lngval" name="lng" />
	   		<input type="submit" value="좌표저장" />
	   	</form>
		
		<script type="text/javascript" 
			src="//apis.daum.net/maps/maps3.js?
			apikey=2db7913f2fab677a3eab3d36076d11dc&libraries=services,clusterer,drawing">
		</script>
		
		
	</body>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new daum.maps.LatLng(37.438900, 126.675084),
			level: 3
		};
	
		var map = new daum.maps.Map(container, options);
		
		var marker = new daum.maps.Marker({ 
		    position: map.getCenter()
		}); 
		
		marker.setMap(map);
		
		daum.maps.event.addListener(map, 'click', function(mouseEvent) {        
		    
		    var latlng = mouseEvent.latLng; 
		    
		    marker.setPosition(latlng);
		    
		   var lat = latlng.getLat();
		   var latval = document.getElementById('latval');
		   $("#latval").val(lat);
		   
		   var lng = latlng.getLng();
		   var lngval = document.getElementById('lngval');
		   $("#lngval").val(lng);
		    
		    var clickmsg = '클릭한 위치 <br>'; 
		    clickmsg += '위도 : ' + lat+'<br>';
		    clickmsg += '경도 : ' + lng;
		    
		    var ClickInfo = document.getElementById('ClickInfo'); 
		    ClickInfo.innerHTML = clickmsg;
		    
		});
		
		function zoomIn() {
		    map.setLevel(map.getLevel() - 1);
		}
	
		function zoomOut() {
		    map.setLevel(map.getLevel() + 1);
		}
		
		var msg="${result}";
		
		if(msg!=""){
			alert(msg);
		}
	</script>
</html>