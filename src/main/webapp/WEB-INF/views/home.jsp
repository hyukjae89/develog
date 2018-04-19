<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>기술블로그 - oh29oh29</title>
	<link href="./css/home.css" rel="stylesheet">
</head>
<body onload="init('${not empty sessionScope.member}')">
	<c:choose>
		<c:when test="${empty sessionScope.member}">
			<button id="signInBtn">SignInView</button>	
		</c:when>
		<c:otherwise>
			<button id="signOutBtn">SignOut</button>
		</c:otherwise>
	</c:choose>
	<button id="postWriteBtn">PostWriteView</button>
	<div class="member_wrap">
		<span>ID : ${sessionScope.member.id}</span>
		<span>이름 : ${sessionScope.member.name}</span>
		<span>Email : ${sessionScope.member.email}</span>
	</div>
</body>
<script>
function init(isSignedIn) {
	isSignedIn = isSignedIn == 'true' ? true : false;
	if (isSignedIn) {
		document.getElementById('signOutBtn').addEventListener('click', function() {
			location.href = "signOutView";
		});
	} else {
		document.getElementById('signInBtn').addEventListener('click', function() {
			location.href = "signInView";
		});
	}
	
	document.getElementById('postWriteBtn').addEventListener('click', function() {
		location.href = "postWriteView";
	});
}
</script>
</html>
