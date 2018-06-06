<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>oh29oh29 기술블로그</title>
	<link href="/css/base.css" rel="stylesheet">
	<link href="/css/blog/header.css" rel="stylesheet">
	<link href="/css/blog/home.css" rel="stylesheet">
	<link href="/css/blog/footer.css" rel="stylesheet">
 	<link href="/import/syntaxHighlighter/css/shCore.css" rel="stylesheet">
	<link href="/import/syntaxHighlighter/css/shThemeEclipse.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section id="topSectionWrap" class="top_section_wrap">
		<article class="tag_search_wrap">
			<span id="sharp" class="sharp">#</span>
			<div class="search_input_wrap">
				<input id="search" class="search excludeTagView" type="text">
				<div id="searchTagList" class="search_tag_list excludeTagView"></div>
			</div>
			<div id="searchHidden" class="search_hidden"></div>
		</article>
	</section>
	<section id="mainSectionWrap" class="main_section_wrap">
		<article id="mainArticleWrap" class="main_article_wrap"></article>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
<script src="/import/jquery/jquery-3.2.1.min.js"></script>
<script src="/import/syntaxHighlighter/js/shCore.js"></script>
<script src="/import/syntaxHighlighter/js/shBrushJava.js"></script>
<script src="/js/blog/header.js"></script>
<script src="/js/blog/homeData.js"></script>
<script src="/js/blog/homeElements.js"></script>
<script src="/js/blog/homeView.js"></script>
<script src="/js/blog/home.js"></script>
<script src="/js/blog/homeEvent.js"></script>
<script>
<c:choose>
	<c:when test="${view == 'read'}">
			var uriId = '${uriId}';
			home.getPost(uriId);
	</c:when>
	<c:otherwise>
			var tag = '${tag}';
			home.getPosts(tag);
	</c:otherwise>
</c:choose>
</script>
</body>
</html>
