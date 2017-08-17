<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<script  src="http://code.jquery.com/jquery-2.2.4.min.js" > 
		</script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>오늘 뭐해?_홈화면</title>
		<style>
			
			.main{
				width:400px;
				height:250px;
				text-align:center;
				padding-top:150px;
				font-size:50px;
				color:black;
				cursor:pointer;
			}
			.main:hover{
				color:white;
				text-decoration:underline;
			}
			
			#div1{
				position:absolute;
				z-index:9;
				padding: 0px;
				top:200px;
			}
			#hotboard{
				width: 963px;
				height: 260px;
			}
			
			#div2{
				position:absolute;
				z-index:9;
				padding: 0px;
				top:465px;
			}
			#myarea{
				width: 963px;
				height: 260px;
			}
			
			#div3{
				position:absolute;
				z-index:9;
				padding: 0px;
				top:735px;
			}
			#gyeongboard{
				width: 963px;
				height: 260px;
			}
			
			#div4{
				position:absolute;
				z-index:9;
				padding: 0px;
				top:1007px;
			}
			#seoulboard{
				width: 963px;
				height: 260px;
			}
			
			#div5{
				position:absolute;
				z-index:9;
				padding: 0px;
				top:1278px;
			}
			#incheonboard{
				width: 963px;
				height: 260px;
			}

			#div6{
				position:absolute;
				z-index:9;
				padding: 0px;
				top:1548px;
				margin-bottom: 5%;
			}
			#daejeonboard{
				width: 963px;
				height: 260px;
			}
			
			
		</style>
	</head>
	<body>
		<jsp:include page="upbar.jsp"/>
		<div id="div1" class="main">
			<img id="hotboard" src="hotboard.png"/>
		</div>
		<div id="div2" class="main">
			<img id="myarea" src="myarea.png"/>
		</div>
		<div id="div3" class="main">
			<img id="gyeongboard" src="gyeong.png"/>
		</div>
		<div id="div4" class="main">
			<img id="seoulboard" src="seoul.png"/>
		</div>
		<div id="div5" class="main">
			<img id="incheonboard" src="incheon.png"/>
		</div>
		<div id="div6" class="main">
			<img id="daejeonboard" src="daejeon.png"/>
		</div>
	</body>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?
			apikey=2db7913f2fab677a3eab3d36076d11dc"></script>
	<script>
		$("#div1").click(function(){
			location.href="./area?area="+$("#div1").html()+"&add=seoul";//따로 해야함
		});
		
		$("#div2").click(function(){
			
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var lat = position.coords.latitude; // 위도
		            var lon = position.coords.longitude; // 경도
		            var locPosition = new daum.maps.LatLng(lat, lon); //위경도 합쳐서 좌표로 만듬
		           //console.log(lat);
		            
		            
		           location.href="./area?area="+$("#div2").html()+"&latit="+lat+"&longit="+lon;
		            
				});
			}
			
		});
		
		$("#div3").click(function(){
			location.href="./area?area="+$("#div3").html();
		});
		$("#div4").click(function(){
			location.href="./area?area="+$("#div4").html();
		});
		$("#div5").click(function(){
			location.href="./area?area="+$("#div5").html();
		});
		$("#div6").click(function(){
			location.href="./area?area="+$("#div6").html();
		});
	</script>
</html>