<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/admin/contents/category.css" rel="stylesheet">
<link href="import/jquery/jquery-ui.theme.min.css" rel="stylesheet">
<article id='ctWrap' class='ct_wrap'>
	<div id='ctListWrap' class='ct_list_wrap'></div>
	<div id='ctAddWrap' class='ct_item'>
		<input type='text' class='ct_name_input'>
		<div class='ct_control_wrap'>
			<button class='ctAddConfirmBtn'>추가</button>
		</div>
	</div>
	<div class='ct_item'>
		<button id='ctSaveBtn'>변경사항적용</button>
	</div>
</article>
<script src="import/jquery/jquery-ui.min.js"></script>
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

// 추가
$('.ctAddConfirmBtn').on('click', function() {
	var $this = $(this);
	var $inputName = $this.parent().siblings('.ct_name_input');
	var tempId = guid();
	categoryStore[tempId] = {
		name : $inputName.val(),
		depth : 0,
		isPrivate : 0
	}
	appendCategory(categoryStore[tempId], tempId);
	$inputName.val("");
});

// 수정
$('#ctWrap').on('click', '.ctUpdateBtn', function() {
	var $this = $(this);
	var $categoryItem = $this.parent().parent();
	var categoryName = $categoryItem.children('.ctName').text();
	var editHtml = "<input type='text' class='ctNameInput ct_name_input' value='" + categoryName + "' data-original-value='" + categoryName + "'>"
				 + "<div class='ct_control_wrap'>"
					 + "<button class='ctEditConfirmBtn'>확인</button>" 
					 + "<button class='ctEditCancelBtn'>취소</button>" 
				 + "</div>";
				 
	$categoryItem.html(editHtml);
});

// 수정 확인
$('#ctWrap').on('click', '.ctEditConfirmBtn', function() {
	var $this = $(this);
	var $categoryItem = $this.parent().parent();
	var tempId = $categoryItem.attr('id');
	var categoryName = $categoryItem.children('.ctNameInput').val();
	var normalHtml = "<span class='ctName'>" + categoryName + "</span>"
				   + "<div class='ct_control_wrap'>"
				   		+ "<button class='ctUpdateBtn'>수정</button>"
						+ "<button class='ctDeleteBtn'>삭제</button>"
				   + "</div>";

	$categoryItem.html(normalHtml);
	categoryStore[tempId].name = categoryName;
});

// 수정 취소
$('#ctWrap').on('click', '.ctEditCancelBtn', function() {
	var $this = $(this);
	var $categoryItem = $this.parent().parent();
	var categoryName = $categoryItem.children('.ctNameInput').data('originalValue');
	var normalHtml = "<span class='ctName'>" + categoryName + "</span>"
				   + "<div class='ct_control_wrap'>"
				   		+ "<button class='ctUpdateBtn'>수정</button>"
						+ "<button class='ctDeleteBtn'>삭제</button>"
				   + "</div>";
				 
	$categoryItem.html(normalHtml);
});

// 삭제
$('#ctWrap').on('click', '.ctDeleteBtn', function() {
	var $this = $(this);
	$this.parent().parent().remove();
});

// 변경사항적용
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
						 + "<span class='ctName'>" + category.name + "</span>"
						 + "<div class='ct_control_wrap'>"
							 + "<button class='ctUpdateBtn'>수정</button>"
							 + "<button class='ctDeleteBtn'>삭제</button>"
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