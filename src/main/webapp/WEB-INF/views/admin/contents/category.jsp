<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="./css/admin/contents/category.css" rel="stylesheet">
<link href="./import/jquery/jquery-ui.theme.min.css" rel="stylesheet">
<article id="ctWrap" class="ct_wrap">
	<div id="ctListWrap" class="ct_list_wrap">
		<%-- <c:forEach var="category" items="${categories}">
			<div class="ct_item ct_depth_${category.depth}" data-depth="${category.depth}">
				<span>${category.name}</span>
				<div class="ct_control_wrap">
					<button>수정</button>
					<button>삭제</button>
				</div>
			</div>
		</c:forEach> --%>
	</div>
	<div id="ctAddWrap" class="ct_item">
		<input type="text" class="ct_name_input">
		<div class="ct_control_wrap">
			<button class="ctAddConfirmBtn">추가</button>
		</div>
	</div>
	<div class="ct_item">
		<button id="ctSaveBtn">변경사항적용</button>
	</div>
</article>
<script src="./import/jquery/jquery-ui.min.js"></script>
<script>
var categoryStore = {};

$.ajax({
	url : "findCategories",
	type : "get",
	success : function(categories) {
		var categoryItem;
		categories.forEach(function(category){
			var tempId = guid();
			appendCategory(category, tempId);
			categoryStore[tempId] = {
				idx : category.idx,
				name : category.name,
				ord : category.ord,
				isPrivate : category.isPrivate
				
			}
		});
		
		$('#ctListWrap').sortable();
		$('#ctListWrap').disableSelection();
	},
	error : function() {
		console.log("error");
	}
});

$('#ctWrap').on('click', '.ctAddConfirmBtn', function(){
	var $this = $(this);
	var $inputName = $this.parent().siblings('.ct_name_input');
	var category = {
		"name" : $inputName.val()
	};
	
	// 카테고리 추가
	$.ajax({
		url : "addCategory",
		type : "POST",
		contentType: "application/json",
		data : JSON.stringify(category),
		success : function(addedCategory) {
			var tempId = guid();
			appendCategory(addedCategory, tempId);
			$inputName.val("");
		},
		error : function() {
			console.log("error");
		}
	});
});

$('#ctSaveBtn').on('click', function() {
	var updatedCategories = {};
	$('#ctListWrap').children().each(function(ord) {
		var $this = $(this);
		var tempId = $this.attr('id');
		updatedCategories[tempId] = categoryStore[tempId];
		updatedCategories[tempId].ord = ord
	});
	$.ajax({
		url : "saveCategory",
		type : "post",
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(updatedCategories),
		success : function() {
			console.log("success");
		},
		error : function() {
			console.log("error");
		}
	});
});

function appendCategory(category, tempId) {
	var categoryItem = "<div id='" + tempId + "' class='ct_item ct_depth_" + category.depth + "' data-depth='" + category.depth + "'>"
				 + "<span>" + category.name + "</span>"
				 + "<div class='ct_control_wrap'>"
					 + "<button>수정</button>"
					 + "<button>삭제</button>"
				 + "</div>"
			 + "</div>";
	
	$('#ctListWrap').append(categoryItem);
}

function guid() {
	function s4() {
		return Math.floor((1 + Math.random()) * 0x10000)
			.toString(16)
			.substring(1);
	};
	return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
}

</script>