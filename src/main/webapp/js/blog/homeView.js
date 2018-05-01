var homeView = homeView || (function(){
    'use strict';

    var _hideTopSection = function() {
        homeElements.$topSectionWrap.hide();
    };

    var _showTopSection = function() {
        homeElements.$topSectionWrap.show();
    };

    var _isHiddenTopSection = function() {
        return homeElements.$topSectionWrap.css('display') == 'none' ? true : false;
    };

    var _emptyMainArticle = function() {
        homeElements.$mainArticleWrap.empty();
    };

    var _appendPostList = function(posts) {
        var html = "";

        posts.forEach(function(post) {
            html 	+= '<div class="pl_post_list_item">'
                        + '<div class="plPostListItemInner" data-uri-id="' + post.uriId + '">'
                            + '<h2 class="plPostTitle pl_post_title">' + post.title + '</h2>'
                            + '<p class="plPostDescription pl_post_description">' + post.description + '</p>'
                        + '</div>'
                    + '</div>';
        });
                        
		homeElements.$mainArticleWrap.append(html);
    };

    var _appendPostDetail = function(post) {
        var html 	= '<h1 id="prTitle" class="pr_title">' + post.title + '</h1>'
                    + '<p id="prDescription" class="pr_description">' + post.description + '</p>'
                    + '<div id="prContents" class="pr_contents">' + post.contents + '</div>';
                        
		homeElements.$mainArticleWrap.append(html);
    };

    var _appendPostWrite = function() {
        var html    = '<form id="pwWriteForm" accept-charset="UTF-8">'
                        + '<input type="text" id="pwTitle" class="pw_title" name="title">'
                        + '<input type="text" id="pwDescription" class="pw_description" name="description">'
                        + '<textarea id="ir1" rows="10" cols="100" name="contents"></textarea>'
                    + '</form>'
                    + '<button id="pwCompleteBtn">작성완료</button>';
				
        homeElements.$mainArticleWrap.append(html);
    };
    
    return {
        hideTopSection : _hideTopSection,
        showTopSection : _showTopSection,
        isHiddenTopSection : _isHiddenTopSection,

        emptyMainArticle : _emptyMainArticle,
        appendPostList : _appendPostList,
        appendPostDetail : _appendPostDetail,

        appendPostWrite :_appendPostWrite
    };

})();