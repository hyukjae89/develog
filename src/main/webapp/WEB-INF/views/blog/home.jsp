<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>oh29oh29 기술블로그</title>
	<link href="css/base.css" rel="stylesheet">
	<link href="css/blog/home.css" rel="stylesheet">
</head>
<body onload="init('${not empty sessionScope.member}')">
	<header id="header" class="header">
		<div class="header_inner_wrap">
			<div class="header_logo_wrap">
				<span id="headerLogo" class="header_logo">oh29oh29</span>
			</div>
			<div class="header_member_wrap">
				<c:choose>
					<c:when test="${empty sessionScope.member}">
						<span id="headerSignInBtn" class="header_btn">Sign in</span>	
					</c:when>
					<c:otherwise>
						<span id="headerMemberName">${sessionScope.member.name}님</span>
						<c:if test="${sessionScope.member.isAdmin == 1}">
						<span id="headerAdminBtn" class="header_btn">관리자</span>
						</c:if>
						<span id="headerSignOutBtn" class="header_btn">Sign out</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<section id="mainContentWrap" class="main_content_wrap"></section>
	<button id="postWriteBtn">PostWriteView</button>
	
<script src="./import/jquery/jquery-3.2.1.min.js"></script>
<script>
function init(isSignedIn) {
	
	$('#headerLogo').on('click', function() {
		location.href = "/";
	});
	
	$('#header').on('click', '#headerSignInBtn', function(){
		location.href = "signInView";
	});
	
	$('#header').on('click', '#headerSignOutBtn', function(){
		location.href = "signOutView";
	});
	
	$('#header').on('click', '#headerAdminBtn', function(){
		location.href = "admin";
	});
	
	$('#mainContentWrap').load('postList');
	
}
</script>
</body>
</html>
