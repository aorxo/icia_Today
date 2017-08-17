<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
			<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>오늘 뭐해?</title>
	</head>
	<style>
		@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);
	
		body{
            background-image: url(login_back.png);
        }
        
        #loginbox{
        	width: 100%; 
        	height: 100px; 
        	line-height: 100px; 
        	text-align: center;
        }
        
        #loginheart{
        	width: 100%; 
        	max-width: 760px; 
        	vertical-align: middle;
        	position: relative;
        	top:100px;
        	z-index: 9;
        }
        
        #proname{
        	position : absolute;
        	font-family: 'Nanum Pen Script';
        	font-size: 100px;
        	left:365px;
        	top:215px;
        	z-index: 10;
        }
        
        #idid{
        	font-family: 'Nanum Pen Script';
        	font-size:80px;
        	position: absolute;
        	left:325px;
        	top:350px;
        	z-index: 10;
        }
        
        #idinput[type=text]:focus{
        	border: 5px solid #555;
        }
        #idinput[type=text]{
       		width: 25%;
		    padding: 12px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:395px;
        	top:355px;
        	font-size:20pt;
        	z-index: 10;
        }
        
        #pwpw{
        	font-family: 'Nanum Pen Script';
        	font-size:80px;
        	position: absolute;
        	left:300px;
        	top:424px;
        	z-index: 10;
        }
        
        #pwinput[type=password]:focus{
        	border: 5px solid #555;
        }
        #pwinput[type=password]{
       		width: 25%;
		    padding: 12px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:395px;
        	top:435px;
        	font-size:20pt;
        	z-index: 10;
        }
        
        #loginbtn {
		  	color: white;
			background-color: #FFB2D9;
			border-color: #FFB2D9;
			font-family: 'Nanum Pen Script';
			text-transform: uppercase;
			font-weight: 700;
			border-radius: 3px;
			font-size: 40px;
			padding: 10px 40px;
			position: absolute;
			top:521px;
			left:337px;
			z-index: 10;
		}
		
		#btn {
		  	color: white;
			background-color: #FFB2D9;
			border-color: #FFB2D9;
			font-family: 'Nanum Pen Script';
			text-transform: uppercase;
			font-weight: 700;
			border-radius: 3px;
			font-size: 40px;
			padding: 10px 30px;
			position: absolute;
			top:521px;
			left:490px;
			z-index: 10;
		}
        
	</style>
	<body>
		<b id="proname">오늘 뭐해?</b>
		
		<div id="loginbox">
			<img id="loginheart" src="login_heart.png" />
		</div>
		
		<form action="login" method="post">
		
			<b id="idid">ID</b>
			<input id="idinput" type="text" name="userID"/>
			
			<b id="pwpw">PW</b>
			<input id="pwinput" type="password" name="userPW"/>
			
			<input id="loginbtn" type="submit" value="로그인"/>
			<input id="btn" type="button" value="회원가입" />
			
		</form>
		
	</body>
	
	<script>
		$("#btn").click(function(){
			location.href="join.jsp";
		});
	</script>
</html>