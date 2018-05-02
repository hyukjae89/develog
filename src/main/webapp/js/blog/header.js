var header = header || (function(){
	$('.headerLogoWrap').on('click', function() {
		location.href = "/";
	});
	
	$('#header').on('click', '#headerSignInBtn', function(){
		location.href = "/sign-in";
	});
	
	$('#header').on('click', '#headerSignOutBtn', function(){
		location.href = "/sign-out";
	});
	
	$('#header').on('click', '#headerAdminBtn', function(){
		location.href = "/admin";
	});
})();