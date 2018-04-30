<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>oh29oh29 기술블로그</title>
	<link href="/css/base.css" rel="stylesheet">
	<link href="/css/blog/header.css" rel="stylesheet">
	<link href="/css/blog/post/read.css" rel="stylesheet">
	<script src="/import/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<section id="mainContentsWrap" class="main_content_wrap">
		<article id="prWrap" class="pr_wrap">
			<h1 class="pr_title">${post.title}</h1>
			<p class="pr_description">${post.description}</p>
			<div class="pr_contents">${post.contents}</div>
		</article>
	</section>
<script src="/js/blog/header.js"></script>
<script src="/js/blog/post/read.js" charset="utf-8"></script>
</body>
</html>