var home = home || (function(){
	'use strict';
	
	var _getPosts = function(tag, nowPage) {

		if (tag === undefined) {
			tag = "";
		}
		if (nowPage === undefined) {
			nowPage = 1;
		}
		
		$.ajax({
			url : "/async/posts/tags/" + tag,
			type : "GET",
			data : "tag=" + encodeURIComponent(tag) + "&nowPage=" + nowPage,
			success : function(result) {
				var posts = result.posts;
				var totalPage = result.totalPage;
				var totalCount = result.totalCount;
				var countPerBlock = result.countPerBlock;
				
				homeView.drawPostListView(posts, totalCount, tag, nowPage, totalPage, countPerBlock);
				$("body").scrollTop(0);

				var historyData = {
					view: "postListView", 
					posts: posts, 
					totalCount: totalCount, 
					tag: tag, 
					nowPage: nowPage, 
					totalPage: totalPage, 
					countPerBlock: countPerBlock
				};
				var url = (tag == "") ? "/posts" : "/posts/tags/" + tag;
				history.pushState(historyData, null, url);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};

	var _getPost = function(uriId) {
		$.ajax({
			url : "/async/posts/" + uriId,
			type : "GET",
			data : "uriId=" + encodeURIComponent(uriId),
			success : function(result) {
				var post = result.post;
				var isWriter = result.isWriter;
				homeView.drawPostDetailView(post, isWriter);
				homeData.setPost(post);
				$("body").scrollTop(0);

				var historyData = {
					view: "postDetailView",
					post: post,
					isWriter: isWriter
				};
				var url = "/posts/" + uriId;
				history.pushState(historyData, null, url);
				SyntaxHighlighter.all();
			},
			error : function(e) {
				console.log(e);
			}
		});
	};

	var _goPostWrite = function() {
		homeView.drawPostWriteView();
		$.getScript('/import/smartEditor/js/service/HuskyEZCreator.js', function(){
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: homeData.smartEditor,
				elPlaceHolder: "ir1",
				sSkinURI: "/import/smartEditor/SmartEditor2Skin.html",
				fCreator: "createSEditor2"
			});
		});
	};

	var _submitPostWrite = function() {
		var formData = $('#pwWriteForm').serialize();
		
		$.ajax({
			url : "/async/posts",
			type : "POST",
			data : formData,
			success : function(uriId) {
				_getPost(uriId);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};
	
	var _removePost = function(uriId) {
		$.ajax({
			url : "/async/posts/" + uriId,
			type : "DELETE",
			data : "uriId=" + encodeURIComponent(uriId),
			success : function() {
				_getPosts();
			},
			error : function(e) {
				console.log(e);
			}
		});
	};

	var _goPostModify = function(post) {
		post.tags = post.tags.replace(/\,/g, ' ');
		homeView.drawPostModifyView(post);		
		$.getScript('/import/smartEditor/js/service/HuskyEZCreator.js', function(){
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: homeData.smartEditor,
				elPlaceHolder: "ir1",
				sSkinURI: "/import/smartEditor/SmartEditor2Skin.html",
				fCreator: "createSEditor2"
			});
		});
	};

	var _submitPostModify = function() {
		var formData = $('#pwModifyForm').serialize() + "&idx=" + homeData.getPost().idx;
		
		$.ajax({
			url : "/async/posts",
			type : "PUT",
			data : formData,
			success : function(uriId) {
				_getPost(uriId);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};
	
	var _searchTags = function(tag) {
		$.ajax({
			url : "/async/tags/" + tag,
			type : "GET",
			data : "tag=" + encodeURIComponent(tag),
			success : function(tags) {
				homeView.drawTagListView(tags);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};
	
	return {
		getPosts : _getPosts,
		getPost : _getPost,

		goPostWrite : _goPostWrite,
		submitPostWrite : _submitPostWrite,
		
		removePost : _removePost,

		goPostModify : _goPostModify,
		submitPostModify : _submitPostModify,
		
		searchTags : _searchTags
	};

})();