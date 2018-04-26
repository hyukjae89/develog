var header = header || (function(){
	$('#headerLogo').on('click', function() {
		location.href = "/";
	});
	
	$('#header').on('click', '#headerSignInBtn', function(){
		location.href = "signInView";
	});
	
	$('#header').on('click', '#headerSignOutBtn', function(){
		location.href = "signOutView";
	});
	
	$('#header').on('click', '#headerAdminBtn', function(){
		location.href = "admin";
	});
})();