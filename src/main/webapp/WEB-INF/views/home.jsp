<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>기술블로그 - oh29oh29</title>
</head>
<body>
	<button id="signInViewBtn">SingInView</button>
	<button id="postWriteViewBtn">PostWriteView</button>
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
