var home = home || (function(){
	'use strict';
	
	var _getPosts = function(tag) {
		
		if (tag === undefined)
			tag = "";
		
		$.ajax({
			url : "/async/posts/tags/" + tag,
			type : "GET",
			data : "tag=" + encodeURIComponent(tag),
			success : function(posts) {
				if (homeView.isHiddenTopSection())
					homeView.showTopSection();
				homeView.emptyMainArticle();
				homeView.appendPostList(posts);
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
				if (!homeView.isHiddenTopSection())
					homeView.hideTopSection();
				homeView.emptyMainArticle();
				homeView.appendPostDetail(post, isWriter);
				homeData.setPost(post);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};

	var _goPostWrite = function(post) {
		if (!homeView.isHiddenTopSection())
			homeView.hideTopSection();
		homeView.emptyMainArticle();
		homeView.appendPostWrite(post);

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
			success : function(post) {
				console.log(post);
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
				console.log("success");
			},
			error : function(e) {
				console.log(e);
			}
		});
	};

	var _goPostModify = function(post) {
		if (!homeView.isHiddenTopSection())
			homeView.hideTopSection();
		homeView.emptyMainArticle();
		homeView.appendPostWrite();

		$.getScript('/import/smartEditor/js/service/HuskyEZCreator.js', function(){
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: homeData.smartEditor,
				elPlaceHolder: "ir1",
				sSkinURI: "/import/smartEditor/SmartEditor2Skin.html",
				fCreator: "createSEditor2"
			});
		});

		$('#pwTitle').val(post.title);
		$('#pwDescription').val(post.description);
		$('#ir1').val(post.contents);
		$('#pwTag').val(post.tags);
		$('#pwUriId').val(post.uriId);
	};
	
	return {
		getPosts : _getPosts,
		getPost : _getPost,

		goPostWrite : _goPostWrite,
		submitPostWrite : _submitPostWrite,
		
		removePost : _removePost,

		goPostModify : _goPostModify
	};

})();