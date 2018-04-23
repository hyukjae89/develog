<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기술블로그 - oh29oh29</title>
<link href="./css/member/signIn.css" rel="stylesheet">
</head>
<body>
	<button id="naverLoginBtn" class="login_btn naver_login_btn"></button>
	<button id="googleLoginBtn" class="login_btn google_login_btn"></button>

<script src="./import/jquery/jquery-3.2.1.min.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=googleSignInAPIInit" defer></script>
<script>
	window.onload = function() {
		
		$.ajax({
			url : "getLoginAPIWithNaver",
			success : function(response) {
				document.getElementById('naverLoginBtn').addEventListener('click', function() {
					location.href = response;
				});
			}
		});
		
		document.getElementById('googleLoginBtn').addEventListener('click', function(){
 			auth2.signIn({
				scope : 'profile email',
				prompt : 'consent'
			}).then(googleSignInSuccess);
		});
	};
	
	function googleSignInAPIInit() {
		gapi.load('auth2', function() {
			auth2 = gapi.auth2.init({
				client_id : '577924299060-o34csmounb2nbmuqgdvibb4dknbgt97v.apps.googleusercontent.com'
			});
		});
	}
	
	function googleSignInSuccess(googleUser) {
		$.ajax({
//			url : 'http://dev.oh29oh29.pe.kr/verifyIdTokenWithGoogle',
  			url : 'http://dev.oh29oh29.pe.kr:5050/verifyIdTokenWithGoogle',
			type : 'POST',
			data : googleUser.getAuthResponse().id_token,
			contentType : 'application/x-www-form-urlencoded',
			success : function(result) {
				if (result == 'success')
					location.href = "/";
			},
			error : function(request) {
				console.log(request.responseText);
			},
			processData : false
		});
	}
	

</script>
</body>
</html>
