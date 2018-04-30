var list = list || (function(){
	'use strict';
	
	$('#plPostListWrap').on('click', '.pl_post_title', function(){
		var $this = $(this);
		var id = $this.parent().data('id');
		location.href = "/posts/" + id;
	});
	
	$('#plPostListWrap').on('click', '.pl_post_description', function(){
		var $this = $(this);
		var id = $this.parent().data('id');
		location.href = "/posts/" + id;
	});
	
})();