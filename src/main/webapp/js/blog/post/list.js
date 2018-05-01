var list = list || (function(){
	'use strict';
	
	var _$plPostListWrap = $('#plPostListWrap');
	var _posts = [];
	
	var _getPosts = function(tag) {
		
		if (tag === undefined)
			tag = "";
		
		$.ajax({
			url : "/async/posts",
			type : "GET",
			data : "tag=" + encodeURIComponent(tag),
			success : function(posts) {
				_$plPostListWrap.empty();
				posts.forEach(function(post, ord){
					_posts[ord] = post;
					var postItem 	= '<div class="pl_post_list_item" data-ord="' + ord + '">'
										+ '<h2 class="plPostTitle pl_post_title">' + post.title + '</h2>'
										+ '<p class="plPostDescription pl_post_description">' + post.description + '</p>'
									+ '</div>';
					_$plPostListWrap.append(postItem);
				});
			},
			error : function(e) {
				console.log(e);
			}
		});
	};
	
	var _getPost = function(idx, uriId) {
		$.ajax({
			url : "/async/posts/" + uriId,
			type : "GET",
			data : "idx=" + encodeURIComponent(idx),
			success : function(post) {
				_$plPostListWrap.empty();
				console.log(post);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};
	
	_$plPostListWrap.on('click', '.plPostTitle', function(){
		var $this = $(this);
		var ord = $this.parent().data('ord');
		_getPost(_posts[ord].idx, _posts[ord].uriId);
	});
	
	_$plPostListWrap.on('click', '.plPostDescription', function(){
		var $this = $(this);
		var ord = $this.parent().data('ord');
		_getPost(_posts[ord].idx, _posts[ord].uriId);
	});
	
	(function init() {
		_getPosts();
	})();
	
})();