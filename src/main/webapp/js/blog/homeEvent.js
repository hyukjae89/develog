var homeEvent = homeEvent || (function(){
    'use strict';

    $(window).on('popstate', function(event) {
        var data = event.originalEvent.state;

        if (data == null)
            history.back();
            
        var view = data.view;
        if (view == 'postListView') {
            var posts = data.posts;
            var tag = data.tag;
            var totalCount = data.totalCount;
            var nowPage = data.nowPage;
            var totalPage = data.totalPage;
            var countPerBlock = data.countPerBlock;
            homeView.drawPostListView(posts, totalCount, tag, nowPage, totalPage, countPerBlock);
        } else if (view == 'postDetailView') {
            var post = data.post;
            var isWriter = data.isWriter;
            homeView.drawPostDetailView(post, isWriter);
        }
    });

    // 샵 아이콘 클릭
    homeElements.$sharp.on('click', function() {
		homeElements.$search.focus();
    });
    
    // 태그 검색 키업
    homeElements.$search.on('keyup', function(event) {
        var tag = $(this).val();
        
        if (event.keyCode == 13) {
			if (tag == '529L') {
				location.href = "/sign-in";
            } else if (tag == '529P') {
                home.goPostWrite();
            } else {
				home.getPosts(tag);
			}
        } else if (event.keyCode == 27) {
            // ESC
            homeView.emptyTagList();
        } else {
			home.searchTags(tag);
		}
    });

    homeElements.$search.on('focus', function(event){
        var tag = $(this).val();
        
        if (homeView.isHiddenTagList())
            home.searchTags(tag);
    });
    
    homeElements.$totalTagBtn.on('click', function(){
    	home.getTags();
    });
    
    // 포스트 리스트에서 하나의 포스트 클릭
    homeElements.$mainArticleWrap.on('click', '.plPostListItemInner', function(){
		home.getPost($(this).data('uriId'));
    });
    
    // 포스트 작성 완료 버튼 클릭
    homeElements.$mainArticleWrap.on('click', '#pwCompleteBtn', function(){
        // 에디터의 내용이 textarea에 적용된다.
        homeData.smartEditor.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
        
        home.submitPostWrite();
    });

    homeElements.$mainArticleWrap.on('click', '#pwModifyBtn', function(){
        homeData.smartEditor.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
                
        home.submitPostModify();
    });
    
    
    // 포스트 수정 버튼 클릭
    homeElements.$mainArticleWrap.on('click', '#prModifyBtn', function(){
        home.goPostModify(homeData.getPost());
    });
    
    // 포스트 삭제 버튼 클릭
    homeElements.$mainArticleWrap.on('click', '#prRemoveBtn', function(){
        if (!confirm("해당 포스트를 삭제하겠습니까?"))
            return;

    	home.removePost($(this).parent().data('uriId'));
    });

    // 태그 클릭
    homeElements.$mainArticleWrap.on('click', '.plPostTag', function(){
        var tag = $(this).data('tag');
        home.getPosts(tag);
    });

    // 태그 클릭
    homeElements.$mainArticleWrap.on('click', '.prTag', function(){
        var tag = $(this).data('tag');
        home.getPosts(tag);
    });
    
    // 포스트 페이징 넘버 클릭 
    $('body').on('click', '.pagingBtn', function(){
        if ($(this).hasClass('paging_btn_selected'))
            return;
            
    	var tag = homeElements.$search.val();
    	var page = $(this).data('page');
    	home.getPosts(tag, page);
    });

    // 포스트 이전 페이징 클릭 
    $('body').on('click', '#prevPagingBtn', function(){
        if ($(this).hasClass('paging_btn_disabled'))
            return;
            
    	var tag = homeElements.$search.val();
    	var page = $(this).data('prevPage');
    	home.getPosts(tag, page);
    });

    // 포스트 다음 페이징 클릭
    $('body').on('click', '#nextPagingBtn', function(){
        if ($(this).hasClass('paging_btn_disabled'))
            return;
            
    	var tag = homeElements.$search.val();
        var page = $(this).data('nextPage');
    	home.getPosts(tag, page);
    });
    
    homeElements.$topSectionWrap.on('click', '.searchTagItem', function(){
    	home.getPosts($(this).text());
    });
    
    $('body').on('click', function(event){
    	var target = event.target;

    	if (target.className.indexOf('excludeTagView') > -1)
    		return;
    	
    	homeView.emptyTagList();
    });
    
})();