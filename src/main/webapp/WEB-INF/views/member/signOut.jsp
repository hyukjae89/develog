<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기술블로그 - oh29oh29</title>
</head>
<body onload="init('${sessionScope.member.provider}')">
	<button id="totalSignOut">TotalSignOut</button>
	<button id="onlyBlogSignOut">OnlyBlogSignOut</button>
<script src="./import/jQuery/jquery-3.2.1.min.js"></script>
<script>
function init(provider) {
	
	document.getElementById('totalSignOut').addEventListener('click', function(){
		if (provider == 'NAVER') {
			$.ajax({
	            type : "POST",
	            dataType : 'text',
	            url : "http://nid.naver.com/nidlogin.logout",
	            crossDomain : true,
	            xhrFields : {
	               withCredentials : true
	            }
	         }).done(function(data) {
	 			location.href = "signOut";
	         }).fail(function(xhr, textStatus, errorThrown) {
	        	 alert(textStatus);
	         });
		} else if (provider == 'GOOGLE') {
 			location.href = "https://www.google.com/accounts/Logout?continue=https%3A%2F%2Fappengine.google.com%2F_ah%2Flogout?continue=http%3A%2F%2Fdev.oh29oh29.pe.kr%3A5050%2FsignOut";
		}
	});
	document.getElementById('onlyBlogSignOut').addEventListener('click', function(){
		location.href = "signOut";
	});
}
</script>
</body>
</html>