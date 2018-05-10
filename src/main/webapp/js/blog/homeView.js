var homeView = homeView || (function(){
    'use strict';

    var _hideTopSection = function() {
        homeElements.$topSectionWrap.hide();
    };

    var _showTopSection = function(tag) {
        homeElements.$topSectionWrap.show();
        homeElements.$search.val(tag);
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
            var tags = post.tags.split(",");
            
            html 	+= '<div class="pl_post_list_item">'
                        + '<div class="plPostListItemInner pl_post_list_item_inner" data-uri-id="' + post.uriId + '">'
                            + '<h2 class="plPostTitle pl_post_title">' + post.title + '</h2>'
                            + '<p class="plPostDescription pl_post_description">' + post.description + '</p>'
                        + '</div>'
                        + '<div class="pl_post_tags">';
            tags.forEach(function(tag){
                html += '<span class="plPostTag pl_post_tag" data-tag="' + tag + '">' + tag + '</span>';
            });
            html    += '</div>'
                    + '</div>';
        });
                        
		homeElements.$mainArticleWrap.append(html);
    };

    var _appendPostDetail = function(post, isWriter) {
        var tags = post.tags.split(",");

        var html 	= '<h1 id="prTitle" class="pr_title">' + post.title + '</h1>'
                    + '<p id="prDescription" class="pr_description">' + post.description + '</p>'
                    + '<div id="prContents" class="pr_contents">' + post.contents + '</div>'
                    + '<div id="prTags" class="pr_tags">'
        tags.forEach(function(tag){
            html += '<span class="prTag pr_tag" data-tag="' + tag + '">' + tag + '</span>';
        });
        html += '</div>';
        
        if (isWriter) {
        	html	+= '<div class="pr_btn_wrap" data-uri-id="' + post.uriId + '">'
        				+ '<button id="prModifyBtn">수정</button>'
        				+ '<button id="prRemoveBtn">삭제</button>'
        			+ '</div>';
        }
        
		homeElements.$mainArticleWrap.append(html);
    };

    var _appendPostWrite = function() {
        var html    = '<div class="pw_wrap">'
	    				+ '<form id="pwWriteForm" accept-charset="UTF-8">'
	                    	+ '<input type="text" id="pwTitle" class="pw_input" name="title" placeholder="제목">'
	                    	+ '<input type="text" id="pwDescription" class="pw_input" name="description" placeholder="설명">'
	                        + '<textarea id="ir1" rows="10" cols="100" name="contents" class="pw_contents"></textarea>'
	                        + '<input type="text" id="pwTag" class="pw_input" name="tags" placeholder="태그">'
	                        + '<input type="text" id="pwUriId" class="pw_input pw_uri_id" name="uriId" placeholder="URI ID" maxlength="16">'
                        + '</form>'
                        + '<div class="pw_btn_wrap">'	                    
                            + '<button id="pwCompleteBtn">작성완료</button>'
                        + '</div>'
                    + '</div>';
				
        homeElements.$mainArticleWrap.append(html);
    };

    var _appendPostModify = function(post) {
        var html    = '<div class="pw_wrap">'
	    				+ '<form id="pwModifyForm" accept-charset="UTF-8">'
	                    	+ '<input type="text" id="pwTitle" class="pw_input" name="title" placeholder="제목" value="' + post.title + '">'
	                    	+ '<input type="text" id="pwDescription" class="pw_input" name="description" placeholder="설명" value="' + post.description + '">'
	                        + '<textarea id="ir1" rows="10" cols="100" name="contents" class="pw_contents">' + post.contents + '</textarea>'
	                        + '<input type="text" id="pwTag" class="pw_input" name="tags" placeholder="태그" value="' + post.tags + '">'
	                        + '<input type="text" id="pwUriId" class="pw_input pw_uri_id" name="uriId" placeholder="URI ID" maxlength="16" value="' + post.uriId + '">'
                        + '</form>'
                        + '<div class="pw_btn_wrap">'	                    
                            + '<button id="pwModifyBtn">수정완료</button>'
                        + '</div>'
                    + '</div>';
				
        homeElements.$mainArticleWrap.append(html);
    };
    
    var _existPaging = function() {
    	return homeElements.$mainSectionWrap.children().is('#pagingArticleWrap');
    };

    var _appendPaging = function() {
        var html    = '<article id="pagingArticleWrap" class="paging_article_wrap">'
                        + '<div id="pagingWrap" class="paging_wrap">'
                            + '<div class="prev_btn_wrap"><span class="paging_btn paging_btn_unselected">◀</span></div>'
                            + '<div id="pageBtnWrap" class="page_btn_wrap"></div>'
                            + '<div class="next_btn_wrap"><span class="paging_btn paging_btn_unselected">▶</span></div>'
                        + '</div>'
                    + '</article>';

        homeElements.$mainSectionWrap.append(html);
    };
    
    var _removePaging = function() {
    	$('#pagingArticleWrap').remove();
    }

    var _renewPaging = function(nowPage, totalPage) {
        var $pageBtnWrap = $('#pageBtnWrap');
        var html    = '';
        
        $pageBtnWrap.empty();
        for (var i = 0; i < totalPage; i++) {
        	var page = i + 1;
        	if (page == nowPage) {
        		html += '<span class="pagingBtn paging_btn paging_btn_selected" data-page="' + page + '">' + page + '</span>';
        	} else {
        		html += '<span class="pagingBtn paging_btn paging_btn_unselected" data-page="' + page + '">' + page + '</span>';
        	}
        }

        $pageBtnWrap.append(html);
    };

    return {
        hideTopSection : _hideTopSection,
        showTopSection : _showTopSection,
        isHiddenTopSection : _isHiddenTopSection,

        emptyMainArticle : _emptyMainArticle,
        appendPostList : _appendPostList,
        appendPostDetail : _appendPostDetail,

        appendPostWrite :_appendPostWrite,
        appendPostModify : _appendPostModify,

        existPaging : _existPaging,
        appendPaging : _appendPaging,
        removePaging : _removePaging,
        renewPaging : _renewPaging
    };

})();