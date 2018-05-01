var home = home || (function(){
	'use strict';
	
	var _getPosts = function(tag) {
		
		if (tag === undefined)
			tag = "";
		
		$.ajax({
			url : "/async/posts",
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
			success : function(post) {
				if (!homeView.isHiddenTopSection())
					homeView.hideTopSection();
				homeView.emptyMainArticle();
				homeView.appendPostDetail(post);
				console.log(post);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};

	var _goPostWrite = function() {
		if (!homeView.isHiddenTopSection())
			homeView.hideTopSection();
		homeView.emptyMainArticle();
		homeView.appendPostWrite();

		$.getScript('/import/smartEditor/js/service/HuskyEZCreator.js', function(){
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: homeData.smartEditor,
				// oAppRef: oEditors,
				elPlaceHolder: "ir1",
				sSkinURI: "/import/smartEditor/SmartEditor2Skin.html",
				fCreator: "createSEditor2"
			});
		});
	};
	
	return {
		getPosts : _getPosts,
		getPost : _getPost,

		goPostWrite : _goPostWrite
	};

})();