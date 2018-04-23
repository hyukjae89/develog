<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="./css/admin/contents/category.css" rel="stylesheet">
<article class="ctListWrap ct_list_wrap">
	<c:forEach var="category" items="${categories}">
		<div class="ct_item ct_depth_${category.depth}" data-depth="${category.depth}">
			<span>${category.name}</span>
			<div class="ct_control_wrap">
				<button>수정</button>
				<button>삭제</button>
			</div>
		</div>
	</c:forEach>
	<div id="ctAddWrap" class="ct_item">
		<input type="text" class="ct_name_input">
		<div class="ct_control_wrap">
			<button class="ctAddConfirmBtn">추가</button>
		</div>
	</div>
</article>
<script>
$('.ctListWrap').on('click', '.ctAddConfirmBtn', function(){
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
			appendCategory(addedCategory);
			$inputName.val("");
		},
		error : function() {
			console.log("error");
		}
	});
});

function appendCategory(category) {
	var categoryItem = "<div class='ct_item ct_depth_" + category.depth + "' data-depth='" + category.depth + "'>"
				 + "<span>" + category.name + "</span>"
				 + "<div class='ct_control_wrap'>"
					 + "<button>수정</button>"
					 + "<button>삭제</button>"
				 + "</div>"
			 + "</div>";
	
	$('#ctAddWrap').before(categoryItem);
}


</script>