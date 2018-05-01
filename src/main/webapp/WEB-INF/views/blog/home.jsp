<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>oh29oh29 기술블로그</title>
	<link href="/css/base.css" rel="stylesheet">
	<link href="/css/blog/header.css" rel="stylesheet">
	<link href="/css/blog/home.css" rel="stylesheet">
	<script src="/import/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section class="bg_wrap">
		<span id="bgSharp" class="bg_sharp">#</span>
		<input type="text" id="bgSearch" class="bg_search">
		<div id="bgSearchHidden" class="bg_search_hidden"></div>
	</section>
	<section id="mainContentsWrap" class="main_content_wrap">
		<c:choose>
			<c:when test="${view == 'read'}">
				<jsp:include page="post/read.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="post/list.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</section>
<script src="/js/blog/header.js"></script>
<script src="/js/blog/home.js"></script>
</body>
</html>
