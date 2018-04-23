<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>기술블로그 - oh29oh29</title>
	<link href="./css/base.css" rel="stylesheet">
	<link href="./css/admin/main.css" rel="stylesheet">
</head>
<body onload="init()">
	<aside id="sideBarWrap" class="side_bar_wrap">
		<div>
			<button id="dashboardBtn">Dashboard</button>
		</div>
		<div>
			<button id="categoryBtn">Category관리</button>
		</div>
	</aside>
	<section id="mainContentWrap" class="main_content_wrap"></section>

<script src="./import/jQuery/jquery-3.2.1.min.js"></script>
<script>
function init() {
	
	$('#mainContentWrap').load('dashboard', function(){
		console.log("dashboard success");
	});
	
	document.getElementById('dashboardBtn').addEventListener('click', function() {
		$('#mainContentWrap').load('dashboard', function(){
			console.log("dashboard success");
		});
	});
	
	document.getElementById('categoryBtn').addEventListener('click', function() {
		$('#mainContentWrap').load('categoryManager', function(){
			console.log("categoryManager success");
		});
	});
}
</script>
</body>
</html>