<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/blog/post/list.css" rel="stylesheet">
<article id="plWrap" class="pl_wrap">
	<div id="plCategoryWrap" class="pl_category_wrap"></div>
	<div id='plPostListWrap' class='pl_post_list_wrap'></div>
</article>
<script>
var $plCategoryWrap = $('#plCategoryWrap');

$.ajax({
	url: "findCategories",
	type: "get",
	success: function(categories) {
		categories.forEach(function(category){
			$plCategoryWrap.append('<div>' + category.name + '</div>');
		});
	},
	error: function() {
		console.log("error");
	}
});
</script>