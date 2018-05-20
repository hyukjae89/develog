var homeView = homeView || (function(){
    'use strict';

    var _hideTopSection = function() {
        homeElements.$topSectionWrap.hide();
        homeElements.$searchTagList.hide();
        homeElements.$mainSectionWrap.removeClass('min-height-with-top');
        homeElements.$mainSectionWrap.addClass('min-height-without-top');
    };

    var _showTopSection = function(tag) {
        homeElements.$topSectionWrap.show();
        homeElements.$search.val(tag);
        homeElements.$searchTagList.hide();
        homeElements.$mainSectionWrap.removeClass('min-height-without-top');
        homeElements.$mainSectionWrap.addClass('min-height-with-top');
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
            var regDate = new Date(post.regDate.substring(0, 4), post.regDate.substring(4, 6), post.regDate.substring(6, 8), post.regDate.substring(8, 10), post.regDate.substring(10, 12));
            regDate.setMinutes(regDate.getMinutes() + (-1 * regDate.getTimezoneOffset()));
            var year = regDate.getFullYear();
            var month = (regDate.getMonth() + 1) < 10 ? "0" + regDate.getMonth() : regDate.getMonth();
            var date = regDate.getDate() < 10 ? "0" + regDate.getDate() : regDate.getDate();
            var hours = regDate.getHours() < 10 ? "0" + regDate.getHours() : regDate.getHours();
            var minutes = regDate.getMinutes() < 10 ? "0" + regDate.getMinutes() : regDate.getMinutes();
            regDate = year + "-" + month + "-" + date + " " + hours + ":" + minutes;
            
            html 	+= '<div class="pl_post_list_item">'
                        + '<div class="plPostListItemInner pl_post_list_item_inner" data-uri-id="' + post.uriId + '">'
                            + '<div class="pl_post_title_desc_wrap">'
                                + '<h2 class="plPostTitle pl_post_title">' + post.title + '</h2>'
                                + '<p class="plPostDescription pl_post_description">' + post.description + '</p>'
                            + '</div>'
                            + '<div class="pl_post_reg_date_wrap">'
                                + '<span class="pl_post_reg_date">' + regDate + '</span>'
                            + '</div>'
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

    var _appendNoPost = function() {
        var html    = '<div class="pl_no_post_wrap">'
                        + '<span>작성된 포스트가 없습니다.</span>'
                    + '</div>';
				
        homeElements.$mainArticleWrap.append(html);
    };

    var _appendPostDetail = function(post, isWriter) {
        var tags = post.tags.split(",");
        var regDate = new Date(post.regDate.substring(0, 4), post.regDate.substring(4, 6), post.regDate.substring(6, 8), post.regDate.substring(8, 10), post.regDate.substring(10, 12));
        regDate.setMinutes(regDate.getMinutes() + (-1 * regDate.getTimezoneOffset()));
        var year = regDate.getFullYear();
        var month = (regDate.getMonth() + 1) < 10 ? "0" + regDate.getMonth() : regDate.getMonth();
        var date = regDate.getDate() < 10 ? "0" + regDate.getDate() : regDate.getDate();
        var hours = regDate.getHours() < 10 ? "0" + regDate.getHours() : regDate.getHours();
        var minutes = regDate.getMinutes() < 10 ? "0" + regDate.getMinutes() : regDate.getMinutes();
        regDate = year + "-" + month + "-" + date + " " + hours + ":" + minutes;

        var html 	= '<h1 id="prTitle" class="pr_title">' + post.title + '</h1>'
                    + '<p id="prDescription" class="pr_description">' + post.description + '</p>'
                    + '<div id="prContents" class="pr_contents">' + post.contents + '</div>'
                    + '<div id="prTags" class="pr_tags">';
        tags.forEach(function(tag){
            html += '<span class="prTag pr_tag" data-tag="' + tag + '">' + tag + '</span>';
        });
        html    += '</div>'
                + '<p class="pr_reg_date">' + regDate + '</p>';
        
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
                            + '<div class="prev_btn_wrap"><span id="prevPagingBtn" class="paging_btn">◀</span></div>'
                            + '<div id="pageBtnWrap" class="page_btn_wrap"></div>'
                            + '<div class="next_btn_wrap"><span id="nextPagingBtn" class="paging_btn">▶</span></div>'
                        + '</div>'
                    + '</article>';

        homeElements.$mainSectionWrap.append(html);
    };
    
    var _removePaging = function() {
    	$('#pagingArticleWrap').remove();
    };

    var _renewPaging = function(nowPage, totalPage, countPerBlock) {
        var $pageBtnWrap = $('#pageBtnWrap');
        var html = '';
        var $prevPagingBtn = $('#prevPagingBtn');
        var $nextPagingBtn = $('#nextPagingBtn');

        if (nowPage == 1) {
            $prevPagingBtn.removeClass('paging_btn_unselected');
            $prevPagingBtn.addClass('paging_btn_disabled');
        } else {
            $prevPagingBtn.removeClass('paging_btn_disabled');
            $prevPagingBtn.addClass('paging_btn_unselected');
            $prevPagingBtn.data('prev-page', nowPage - 1);
        }

        if (nowPage == totalPage) {
            $nextPagingBtn.removeClass('paging_btn_unselected');
            $nextPagingBtn.addClass('paging_btn_disabled');
        } else {
            $nextPagingBtn.removeClass('paging_btn_disabled');
            $nextPagingBtn.addClass('paging_btn_unselected');
            $nextPagingBtn.data('next-page', nowPage + 1);
        }

        $pageBtnWrap.empty();

        var startPage = 1;
        var endPage = totalPage;
        var pivot = parseInt(countPerBlock / 2) + (countPerBlock % 2 == 0 ? 0 : 1);

        if (totalPage > countPerBlock) {
            if (nowPage <= pivot) {
                endPage = countPerBlock;
            } else if (pivot < nowPage && nowPage < totalPage - pivot + 1) {
                startPage = nowPage - pivot + 1;
                endPage = nowPage + pivot - 1;
            } else {
                startPage = totalPage - countPerBlock + 1;
            }
        }

        for (var i = startPage; i <= endPage; i++) {
        	var page = i;
        	if (page == nowPage) {
        		html += '<span class="pagingBtn paging_btn paging_btn_selected" data-page="' + page + '">' + page + '</span>';
        	} else {
        		html += '<span class="pagingBtn paging_btn paging_btn_unselected" data-page="' + page + '">' + page + '</span>';
        	}
        }

        $pageBtnWrap.append(html);
    };

    var _appendMemberSignIn = function() {
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
    
    var _appendTagList = function(tags) {
    	var html = '<ul>';
    	
    	tags.forEach(function(tag){
    		html	+= '<li class="searchTagItem search_tag_item">'
    					+ '<span>' + tag.name + '</span>'
    				+ '</li>'; 
        });
    	
    	html += '</ul>';
    	
    	homeElements.$searchTagList.append(html);
    	homeElements.$searchTagList.show();
    };
    
    var _emptyTagList = function() {
    	homeElements.$searchTagList.empty();
    	homeElements.$searchTagList.hide();
    };

     var _isHiddenTagList = function() {
        return homeElements.$searchTagList.css('display') == 'none' ? true : false;
    };

    var _drawPostListView = function(posts, totalCount, tag, nowPage, totalPage, countPerBlock) {
        _showTopSection(tag);
        _emptyMainArticle();
        
        if (totalCount > 0) {
            _appendPostList(posts);
            if (!_existPaging()) {
                _appendPaging();
            }
            _renewPaging(nowPage, totalPage, countPerBlock);
        } else {
            _appendNoPost();
            _removePaging();
        }
    };

    var _drawPostDetailView = function(post, isWriter) {
        _hideTopSection();
        _emptyMainArticle();
        _appendPostDetail(post, isWriter);
        _removePaging();
    };

    var _drawPostWriteView = function() {
        _hideTopSection();
		_emptyMainArticle();
		_appendPostWrite();
		_removePaging();
    };

    var _drawPostModifyView = function(post) {
        _hideTopSection();
		_emptyMainArticle();
		_appendPostModify(post);
    };

    var _drawMemberSignInView = function() {
        _hideTopSection();
        _emptyMainArticle();
        _appendMemberSignIn();
        _removePaging();
    };
    
    var _drawTagListView = function(tags) {
    	_emptyTagList();
    	if (tags == '')
    		return;
    	_appendTagList(tags);
    };

    return {
        drawPostListView : _drawPostListView,
        drawPostDetailView : _drawPostDetailView,
        drawPostWriteView : _drawPostWriteView,
        drawPostModifyView : _drawPostModifyView,

        drawMemberSignInView : _drawMemberSignInView,
        
        drawTagListView : _drawTagListView,
        emptyTagList : _emptyTagList,
        isHiddenTagList : _isHiddenTagList
    };

})();