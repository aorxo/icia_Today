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
			table{
				width:100%;
			}
			table, td{				
				border: 1px  solid black;
				border-collapse:collapse;
			}
			td{
				padding: 5px;
				text-align:center;
			}
			
			#boardlist{
			position: absolute;
			top:15%;
			z-index:9;
			}
			
		
	</style>
	<body>
	<jsp:include page="upbar.jsp"/>
	<div id="boardlist">
		<h3 id="detail"></h3>
		<table>
			<tr>
				<td>사진</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>좋아요</td>
				<td>싫어요</td>
				<td>조회수</td>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr>      
					<td><img src='upload/${item.newfile}'/></td>
					<td><a href="./content?idx=${item.idx}&area='${item.area}">${item.title}</a></td>
					<td>${item.user_id}</td>
					<td>${item.reg_date}</td>
					<td>${item.uplike}</td>
					<td>${item.uphate}</td>
					<td>${item.hit}</td>
				</tr>
				<input type="hidden" value="${item.area}" id="area"/>
			</c:forEach>
		</table>
		<input type="button" value="글작성" id="btn"/>
		</div>
	</body>
	<script>
	$("#detail").html($("#area").val())	;
	
	$("#btn").click(function(){
		
		location.href="./write.jsp?area="+$("#area").val();
	});
	if($("#area").val() == "인기"){
		console.log("인기");
		$("#btn").hide();
	}
	if($("#area").val() == "내 주변"){
		console.log("인기");
		$("#btn").hide();
	}
	$(".image").click(function(){
		location.href="./content?idx="+$('#itemidx').val()+"&area="+$('#area').val();
	});
	</script>
</html>