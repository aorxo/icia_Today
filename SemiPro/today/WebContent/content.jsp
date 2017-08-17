<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>글읽는화면</title>
	</head>
	<style>
		tr{
			margin : 10px;
		}
		
		#like{
			cursor: pointer;
		}
		
		#hate{
			cursor: pointer;
		}
		
	</style>
	<body>
		<jsp:include page="upbar.jsp"/><br><br><br>
			<b id="title"></b><br>
			<b id="user_id"></b><br>
			<b id="regdate"></b>
			<b id="uplike"></b>
			<b id="uphate"></b>
			<b id="pagehit"></b>
			<b id="usertag"></b>
			<input type="hidden" value="${sessionScope.loginID}" id="loginID"/>
		<table>
			<c:forEach items="${content}" var="item">
				<input type="hidden" value="${item.title}" id="tit"/>
				<input type="hidden" value="${item.user_id}" id="user"/>
				<input type="hidden" value="${item.reg_date}" id="reg"/>
				<input type="hidden" value="${item.uplike}" id="upl"/>
				<input type="hidden" value="${item.uphate}" id="uph"/>
				<input type="hidden" value="${item.hit}" id="hit"/>
				<input type="hidden" value="${item.tag}" id="tag"/>
				<input type="hidden" value="${item.area}" id="area"/>
				<input type="hidden" value="${item.idx}" id="idx"/>
				<tr>
					<td><img src='upload/${item.newfile}' width="300"/></td>
				</tr>
				<tr>
					<td>${item.content}</td>
				</tr>
			</c:forEach>
		</table>
		<b id="like">좋아요</b>
		<b id="hate">싫어요</b>
		<input type="button" value="목록으로" id="btn"/>
		
		<form id="rewrite">
		<table>
			<tr>
				<td width="150">
					<div>
						<input id="idxn" type="hidden" name="idx"  />
					</div>
					<div>
						<input id="userID" type="hidden" name="userID" value="${sessionScope.loginID}" />
						${sessionScope.loginID}
					</div>
				</td>
				<td wridth="550">
					<div>
						<textarea id="recontent" rows="4" cols="70" name="recontent"></textarea>
					</div>
				</td>
				<td width="100">
					<div id="btn">
						<input id="resucc" type="button" value="댓글등록"/>
					</div>
				</td>
			</tr>
		</table>
		
		<table id="rdrd">
		</table>
		
	</body>
	<script>
		$("#idxn").val($("#idx").val());
		
		$("#title").html($("#tit").val());
		$("#user_id").html($("#user").val());
		$("#regdate").html($("#reg").val());
		$("#uplike").html($("#upl").val());
		$("#uphate").html($("#uph").val());
		$("#pagehit").html($("#hit").val());
		$("#usertag").html($("#tag").val());
		
		$("#btn").click(function(){
			location.href="area?area="+$("#area").val();
		});
		
		$("#like").click(function(){
			location.href="feel?idx="+$("#idx").val()+"&feel=like&loginID="+$("#loginID").val();
		});
			
		
		$("#hate").click(function(){
			location.href="feel?idx="+$("#idx").val()+"&feel=hate&loginID="+$("#loginID").val();
		});
		
		var httpRequest = null;
		
		$(document).ready(function(){
				
				$.ajax({
					type:"post",
					url:"./read",
					data:{
						idx:$("#idx").val()
					},
					dataType:"json",
					success:function(data){
						console.log(data);
						relist(data.relist);
					},
					error:function(req,stat,err){
						console.log(err);
					}
				});
				
				function relist(relistArr){
					var text="";
					relistArr.forEach(function(item){
					text="<tr>"
					text+="<td>"+item.user_id+"</td>"
					text+="<td>"+item.recontent+"</td>"
					text+="</tr>"
					$("#rdrd").append(text);
					});
				}
				
			$("#resucc").click(function(){
				$.ajax({
					type:"post",
					url:"./reply",
					data:{
						idx:$("#idx").val(),
						id:$("#userID").val(),
						reply:$("#recontent").val()
					},
					dataType:"json",
					success:function(data){
						//console.log(data);
						listPrint(data.list2);
					},
					error:function(req,stat,err){
						console.log(err);
					}
				});
					
				function listPrint(list2Arr){
					var text="";
					list2Arr.forEach(function(item){
					text="<tr>"
					text+="<td>"+item.user_id+"</td>"
					text+="<td>"+item.recontent+"</td>"
					text+="</tr>"
					});
					$("#rdrd").append(text);
				}
			});
		});
		
	</script>
</html>