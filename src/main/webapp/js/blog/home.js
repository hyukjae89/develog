var home = home || (function(){
	'use strict';
	
	var _$mainContents = $('#mainContentsWrap');
	var _$plPostListWrap = $('#plPostListWrap');
	var _posts = [];
	
	_$mainContents.on('click', '#plPostWriteBtn', function() {
		location.href = "/post";
	});
	
	$('#bgSharp').on('click', function() {
		$('#bgSearch').focus();
	});
	
	$('#bgSharp').trigger('click');
	
	$('#bgSearch').on('keydown', function() {
		var $this = $(this);
	});
	
	$('#bgSearch').on('keyup', function() {
		var $this = $(this);
		var $hidden = $('#bgSearchHidden'); 
		
		$hidden.text($this.val());
		
		var inputWidth = $hidden.width() + 10;
		
		if (inputWidth == 10)
			inputWidth = 1;
		
		if (inputWidth > 827) {
			$this.attr('maxlength', $this.val().length);
		} else {
			$this.removeAttr('maxlength');
		}
		
		$this.css('width', inputWidth + 'px');
	});
	(function init() {
		$.ajax({
			url : "/posts",
			type : "GET",
			data : JSON.stringify({"test":"test"}),
			success : function(posts) {
				posts.forEach(function(post, ord){
					_posts[ord] = post;
					var postItem 	= '<div class="pl_post_list_item" data-ord="' + ord + '">'
										+ '<div class="pl_post_list_item">'
										+ '<h2 class="pl_post_title">' + post.title + '</h2>'
										+ '<p class="pl_post_description">' + post.description + '</p>'
									+ '</div>';
					_$plPostListWrap.append(postItem);
				});
			},
			error : function() {
				
			}
		});
	})();
	
	return {
		$mainContents 	: _$mainContents,
		posts			: _posts
	}
})();