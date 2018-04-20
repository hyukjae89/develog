<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기술블로그 - oh29oh29</title>
</head>
<body onload="init('${sessionScope.member.provider}')">
	<button id="signOutBtn">SignOut</button>
<script src="./import/jQuery/jquery-3.2.1.min.js"></script>
<script src="https://apis.google.com/js/platform.js" defer></script>
<script>
function init(provider) {

	 if (provider == 'GOOGLE') {
		gapi.load('auth2', function() {
			auth2 = gapi.auth2.init({
				client_id : '577924299060-o34csmounb2nbmuqgdvibb4dknbgt97v.apps.googleusercontent.com'
			});
	 	});
	 }
		
	 
	document.getElementById('signOutBtn').addEventListener('click', function(){
		if (provider == 'NAVER') {
			location.href = "deleteNaverAccessToken";
		} else if (provider == 'GOOGLE') {
			auth2.signOut().then(function(){
	 			location.href = "signOut";
			});
		}
	});
}
</script>
</body>
</html>