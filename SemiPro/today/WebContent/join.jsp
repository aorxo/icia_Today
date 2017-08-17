<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
			<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>오늘 뭐해?_회원가입</title>
	</head>
	<style>
		@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);
		
		body{
            background-image: url(login_back.png);
        }
        
		#joinjoin{
        	position : absolute;
        	background-color: white;
        	padding : 0px 350px 0 350px; 
        	font-family: 'Nanum Pen Script';
        	font-size: 100px;
        	left:25px;
        	top:30px;
        	z-index: 10;
		}
		
		#joinbox{
			background-color: white;
			position: relative;
			width: 937px;
			height: 1000px;
			left:17px;
			top:157px;
			margin-bottom: 2%;
		}
		
		#tests{												
			color: green;
			background-color:white;
			font-family: 'Nanum Pen Script';
			position: absolute;
			top:113px;
			left:373px;
			font-size: 37px;
			z-index: 10;
		}
		#testf{
			color: red;
			background-color:white;
			font-family: 'Nanum Pen Script';
			position: absolute;
			top:113px;
			left:373px;
			font-size: 37px;
			z-index: 10;
		}
		
		#idid{
			position: relative;
			top:35px;
			left:83px;
			z-index: 10;
			font-size: 80px;
			font-family: 'Nanum Pen Script';
		}
		
		#id[type=text]:focus{
			border: 5px solid #555;
		}
		#id[type=text]{
       		width: 25%;
		    padding: 10px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:372px;
        	top:45px;
        	font-size:20pt;
        	z-index: 10;
        }
		
		#check{
			color: white;
			background-color: #FFB2D9;
			border-color: #FFB2D9;
			font-family: 'Nanum Pen Script';
			text-transform: uppercase;
			font-weight: 700;
			border-radius: 3px;
			font-size: 40px;
			padding: 4px 15px;
			position: absolute;
			top:53px;
			left:672px;
			z-index: 10;
		}
		
		#pwpw{
			position: relative;
			top:153px;
			left:-50px;
			z-index: 10;
			font-size: 70px;
			font-family: 'Nanum Pen Script';
		}
		
		#pw[type=password]:focus{
			border: 5px solid #555;
		}
		#pw[type=password]{
			width: 25%;
			height: 6%;
		    padding: 10px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:372px;
        	top:165px;
        	font-size:13pt;
        	z-index: 10;
		}
		
		#pwck{
			position: relative;
			top:275px;
			left:-215px;
			z-index: 10;
			font-size: 70px;
			font-family: 'Nanum Pen Script';
		}
		
		#pc[type=password]:focus{
			border: 5px solid #555;
		}
		#pc[type=password]{
			width: 25%;
			height: 6%;
		    padding: 10px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:372px;
        	top:287px;
        	font-size:13pt;
        	z-index: 10;
		}
		
		#pwc{
			font-family: 'Nanum Pen Script';
			position: absolute;
			top:352px;
			left:374px;
			font-size: 37px;
			z-index: 10;
		}
		
		#name{
			position: relative;
			top:400px;
			left:-481px;
			z-index: 10;
			font-size: 70px;
			font-family: 'Nanum Pen Script';
		}
		
		#nameinput[type=text]:focus{
			border: 5px solid #555;
		}
		#nameinput[type=text]{
			width: 25%;
			height: 6%;
		    padding: 10px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:372px;
        	top:411px;
        	font-size:20pt;
        	z-index: 10;
		}
		
		#phone{
			position: relative;
			top:520px;
			left:-550px;
			z-index: 10;
			font-size: 70px;
			font-family: 'Nanum Pen Script';
		}
		
		#phoneinput[type=text]:focus{
			border: 5px solid #555;
		}
		#phoneinput[type=text]{
			width: 25%;
			height: 6%;
		    padding: 10px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:372px;
        	top:531px;
        	font-size:17pt;
        	z-index: 10;
		}
		
		#emailemail{
			position: relative;
			top:640px;
			left:-680px;
			z-index: 10;
			font-size: 70px;
			font-family: 'Nanum Pen Script';
		}
	
		#emailinput[type=email]:focus{
			border: 5px solid #555;
		}
		
		#emailinput[type=email]{
			width: 25%;
			height: 6%;
		    padding: 10px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:372px;
        	top:652px;
        	font-size:17pt;
        	z-index: 10;
		}
		
		#lovedate{
			position: relative;
			top:675px;
			left:95px;
			z-index: 10;
			font-size: 70px;
			font-family: 'Nanum Pen Script';
		}
		
		#lovedateinput[type=date]:focus{
			border: 5px solid #555;
		}
		#lovedateinput[type=date]{
			width: 25%;
			height: 6%;
		    padding: 10px 20px;
		    margin: 8px 0;
		    box-sizing: border-box;
		    border: 3px solid #ccc;
		    -webkit-transition: 0.5s;
		    transition: 0.5s;
		    outline: none;
		    position:absolute;
		    left:372px;
        	top:772px;
        	font-size:17pt;
        	z-index: 10;
		}

		#joinbtn{
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
			top:890px;
			left:255px;
			z-index: 10;
		}
		
		#btn{
			color: white;
			background-color: #FFB2D9;
			border-color: #FFB2D9;
			font-family: 'Nanum Pen Script';
			text-transform: uppercase;
			font-weight: 700;
			border-radius: 3px;
			font-size: 40px;
			padding: 10px 65px;
			position: absolute;
			top:890px;
			left:525px;
			z-index: 10;
		}
		
	</style>
	<body>
		<b id="joinjoin">회원가입</b>
		
		<form action="join" method="post">
			<div id="joinbox">
				<b id="idid">아이디</b>
				<input id="id" type="text" name="userID"/>
				<b id="tests"></b>
				<b id="testf"></b>
				<input id="check" type="button" value="아이디 중복확인" />
			
				<b id="pwpw">비밀번호</b> 
				<input id="pw" type="password" name="userPW"/>
				<b id="pwck">비밀번호 확인</b>
				<input id="pc" type="password" placeholder="비밀번호 한번 더 입력"/>
				<b id="pwc"></b>
				<b id="name">이름</b>
				<input id="nameinput" type="text" name="userName" />
				<b id="phone">폰 번호</b>
				<input id="phoneinput" type="text" name="phone" placeholder="ex) 01012345678"/>
				<b id="emailemail">E-Mail</b>
				<input id ="emailinput" type="email" name="userEmail"/>
				<b id="lovedate">사귄 날짜</b>
				<input id="lovedateinput" type="date" name="userDate"/>
				<input id="joinbtn" type="submit" value="회원가입"/>
				<input id="btn" type="button" value="취소" />
				
			</div>
		</form>
	</body>
	<script>
		
		$(document).ready(function(){//아이디 중복확인을 위한 ajax사용 
			$("#check").click(function(){
				$.ajax({
					type:"post",
					url:"./check",
					data:{
						userID: $("#id").val()//id 텍스트값을 보내줌
					},				
					dataType:"json",
					success:function(data){
						if(data.result == "사용 가능한 아이디 입니다."){
							$("#tests").html(data.result);
						}else{
							$("#testf").html(data.result);
						}
					},
					error:function(req,stat,err){
						console.log(err);
					}
				});
			});
		});
		
		$("#pc").keyup(function(){//pc가 쳐질때 나와야 보기좋음
			console.log($("#pw").val());
			console.log($("#pc").val());//디버깅
			if($("#pc").val() != $("#pw").val()){
				console.log($("#pw").val() != $("#pc").val());
				$("#pwc").html("비밀번호가 일치하지 않습니다.");
				$("#pwc").css("color","red");
			}else{
				$("#pwc").html("비밀번호가 일치합니다.");
				$("#pwc").css("color","green");
			}
		});
		
		$("#btn").click(function(){//btn눌렀을 경우 다시돌아감 
			location.href="index.jsp";
		});
		
		var msg="${result}";
		
		if(msg!=""){
			alert(msg);
		}
	</script>
</html>