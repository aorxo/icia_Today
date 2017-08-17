<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>상단바</title>
		<style>
			@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);
			
			#upbar{
				position:fixed;
				width: 100%;
				height: 140px;
				background-color: #FFE3EE;
				top:0px;
				left:0px;
				z-index:10;
			}
			
			#HomeBtn{
				width: 125px;
				height: 125px;
				padding:5px;
				cursor: pointer;
			}
			
			#LvImg{
				width: 120px;
				height: 120px;
				padding-bottom: 10px;
			}
			
			#LvNum{
				position:absolute;
				left:43px;
				top:14px;
				font-size: 60px;
				text-align : center;
				color:white;
			}
			
			#UserId{
				font-family: 'Nanum Pen Script';
				position:absolute;
				font-size: 90px;
				left:57px;
				top:-5px;
				width:325px;
				color:white;
			}
			
			#UserDate{
				font-family: 'Nanum Pen Script';
				position:absolute;
				left: 135px;
				top: 80px;
				width:140px;
				font-size: 34px;
				cursor: pointer;
				color:white;
			}
			
			#MenuImg{
				width: 120px;
				height: 127px;
				float: right;
				padding:5px;
			}
			
			#User{
				position:absolute;
				text-align : center;
				left:31%;
				top:10px;
			}
			
			p{
				display: none;
				border:9px solid white;
				color:white;
				border-radius:0.2em;
				margin:5px;
				width:140px;
				text-align: center;
				font-size: 30px;
				
			}
			
			#MenuList{
				position:absolute;
				left:83%;
				border:2px solid #FFE3EE;
				background-color: #FFE3EE;
				border-radius:0.5em;
			}
			#logout{
				background-color: #FFE3EE;
			}
			#faq{
				background-color: #FFE3EE;
			}
			#mypage{
				background-color: #FFE3EE;
			}
			#master{
				background-color: #FFE3EE;
			}
			
		</style>
	</head>
	<body>
		<div id="upbar">
			<img id="HomeBtn" src="HomeButton2.png"/>
			
			<div id="User">
				<img id="LvImg" src="LvHeart.png" />
				<b id="LvNum"></b>
				
				<a id="UserId"><b></b></a>
				<a id="UserDate">오늘은 000일<font color="red">♥</font></a>
			</div>
			
			<input id="MenuImg" src="MenuImg2.png" type="image" value="toggle"/>
			<div id="MenuList">
				<p id="logout">LOGOUT</p>
				<p id="faq">FAQ</p>
				<p id="mypage">My Page</p>
				<p id="master">관리자</p>
			</div>
		</div>
		
		</br></br></br></br>
	</body>
	<script>
			var loginId="${sessionScope.loginID}";
			if(loginId ==""){
				alert("로그인 이 필요한 서비스 입니다.");
				location.href="index.jsp";
			}
			console.log("${sessionScope.lv}");
			$("#LvNum").html("${sessionScope.lv}");
			$("#UserId").html("${sessionScope.loginID}");
			
			$("#MenuImg").click(function(){
				$("p").toggle();
			});
			
			$("#HomeBtn").click(function(){
				location.href="main.jsp";
			});
			
			$("#UserDate").click(function(){
				location.href="/mypage";
			});
			
			$("#logout").click(function(){
				location.href="./logout";
			});
			
			$("#faq").click(function(){
				location.href="/faq";
			});
			
			$("#mypage").click(function(){
				location.href="/mypage";
			});
			
			$("#master").click(function(){
				location.href="/master";
			});
			
		
		
	</script>
</html>




