<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>oh29oh29 기술블로그</title>
	<link href="/css/base.css" rel="stylesheet">
	<link href="/css/blog/header.css" rel="stylesheet">
	<link href="/css/blog/post/write.css" rel="stylesheet">
	<script src="/import/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<section id="mainContentsWrap" class="main_content_wrap">
		<article id="pwWrap" class="pw_wrap">
			<form id="pwWriteForm" accept-charset="UTF-8">
				<select name="categoryName">
					<c:forEach var="category" items="${categories}">
						<option value="${category.name}">${category.name}</option>
					</c:forEach>
				</select>
				<input type="text" id="pwTitle" class="pw_title" name="title">
				<input type="text" id="pwDescription" class="pw_description" name="description">
				<textarea id="ir1" rows="10" cols="100" name="contents"></textarea>
			</form>
			<button id="pwCompleteBtn">작성완료</button>
		</article>
	</section>
<script src="/import/smartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script src="/js/blog/header.js"></script>
<script src="/js/blog/post/write.js" charset="utf-8"></script>
</body>
</html>