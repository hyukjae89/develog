<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>oh29oh29 기술블로그</title>
	<link href="/css/base.css" rel="stylesheet">
	<link href="/css/blog/header.css" rel="stylesheet">
	<link href="/css/blog/home.css" rel="stylesheet">
	<link href="/css/blog/post/list.css" rel="stylesheet">
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
		<article id="plWrap" class="pl_wrap">
			<%-- <div id="plLeftMenuWrap" class="pl_left_menu_wrap">
				<div id="plCategoryWrap" class="pl_category_wrap">
					<span class="pl_category_label">Categories</span>
					<ul class="pl_category_list">
					<c:forEach var="category" items="${categories}">
						<li class="pl_category_list_item">
							<a href="/categories/${category.name}" class="pl_category_link">${category.name}</a>
						</li>
					</c:forEach>
					</ul>
				</div>
				<c:if test="${not empty sessionScope.member}">
				<div id="plPostWriteBtnWrap" class="pl_post_write_btn_wrap">
					<button id="plPostWriteBtn">PostWriteView</button>
				</div>
				</c:if>
			</div> --%>
			<div id='plPostListWrap' class='pl_post_list_wrap'>
				<c:forEach var="post" items="${posts}">
					<div class="pl_post_list_item" data-id="${post.postIdx}">
						<h2 class="pl_post_title">${post.title}</h2>
						<p class="pl_post_description">${post.description}</p>
					</div>
				</c:forEach>
			</div>
		</article>
	</section>
<script src="/js/blog/header.js"></script>
<script src="/js/blog/home.js"></script>
<script src="/js/blog/post/list.js"></script>
</body>
</html>
