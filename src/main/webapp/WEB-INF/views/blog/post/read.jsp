<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<article id="prWrap" class="pr_wrap">
	<h1 class="pr_title">${post.title}</h1>
	<p class="pr_description">${post.description}</p>
	<div class="pr_contents">${post.contents}</div>
</article>
