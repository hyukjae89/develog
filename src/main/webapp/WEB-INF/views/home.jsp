<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>기술블로그 - oh29oh29</title>
	<style>
		.member_wrap{
			position: absolute;
			top: 0;
			right: 0;
			width: 250px;
		}
		.member_wrap span {
			display: block;
		}
	</style>
</head>
<body>
	<button id="signInViewBtn">SingInView</button>
	<button id="postWriteViewBtn">PostWriteView</button>
	<div class="member_wrap">
		<span>ID : ${sessionScope.member.id}</span>
		<span>이름 : ${sessionScope.member.name}</span>
		<span>Email : ${sessionScope.member.email}</span>
	</div>
</body>
<script>
window.onload = function() {
	document.getElementById('signInViewBtn').addEventListener('click', function() {
		location.href = "signInView";
	});
	
	document.getElementById('postWriteViewBtn').addEventListener('click', function() {
		location.href = "postWriteView";
	});
};
</script>
</html>
