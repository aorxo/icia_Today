<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
			<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>Insert title here</title>
	</head>
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
				top:1200px;
				left:600px;
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
			
			#plus{
				cursor: pointer;
			}
	</style>
	<body>
		
		
		<form action="upload" method="post" enctype="multipart/form-data">
				<input type="text" value="${param.area}" name="area"/>
				<div >제목 : <input type="text" name="title"/><br>
				<input type="file" name="uploadFile0"/><br>
				글내용<br>
				<textarea rows="20" cols="40" name="text0"></textarea><br></div>
				<div id="content1" style="display:none">
				<input type="file" name="uploadFile1"/><br>
				글내용<br>
				<textarea rows="20" cols="40" name="text1"></textarea><br></div>
				<div id="content2" style="display:none">
				<input type="file" name="uploadFile2"/><br>
				글내용<br>
				<textarea rows="20" cols="40" name="text2"></textarea><br></div>
				<div id="content3" style="display:none">
				<input type="file" name="uploadFile3"/><br>
				글내용<br>
				<textarea rows="20" cols="40" name="text3"></textarea><br></div>
				<div id="content4" style="display:none">
				<input type="file" name="uploadFile4"/><br>
				글내용<br>
				<textarea rows="20" cols="40" name="text4"></textarea><br></div>
				
				<div id="plus"><h3>더 보기</h3></div>
				
			<input type="text" name="tag" value="#서울"/>
			<input type ="text" id="latval" name="latit" />
	   		<input type="text" id="lngval" name="longit" />
			<input type="hidden" name="loginID" value="${sessionScope.loginID}"/>
			<input type="hidden" name="cnt" id="cnt" value="1">
			
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
			
			<script type="text/javascript" 
			src="//apis.daum.net/maps/maps3.js?
			apikey=2db7913f2fab677a3eab3d36076d11dc&libraries=services,clusterer,drawing">
		</script>
			<input type="submit" value="전송" />
		</form>
		
	</body>
	<script>
		var cnt=1;
		$("#plus").click(function(){
			console.log($("#content"+cnt));
			$("#content"+cnt).css("display","block");
			cnt++;
			$("#cnt").val(cnt);
			console.log($("#cnt").val(cnt));
		});
		/*var cnt=1;
		$("#plus").click(function(){
			cnt++;
			$("#cnt").val()=cnt;
		});*/
		
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
		   $("#latval").val(lat);
		   
		   var lng = latlng.getLng();
		   $("#lngval").val(lng);
		    
		});
		
		function zoomIn() {
		    map.setLevel(map.getLevel() - 1);
		}
	
		function zoomOut() {
		    map.setLevel(map.getLevel() + 1);
		}
	</script>
</html>