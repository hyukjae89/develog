var homeEvent = homeEvent || (function(){
    'use strict';

    // 샵 아이콘 클릭
    homeElements.$sharp.on('click', function() {
		homeElements.$search.focus();
    });
    
    // 태그 검색 키업
    homeElements.$search.on('keyup', function(event) {
		var $this = $(this);
		
		if (event.keyCode == 13) {
			
			if ($this.val() == '로그인하자') {
				location.href = "/sign-in";
            }
            else if ($this.val() == '포스트작성하자') {
                home.goPostWrite();
            }
			else {
				home.getPosts($this.val());
			}
		}
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
    
    // 포스트 수정 버튼 클릭
    homeElements.$mainArticleWrap.on('click', '#prModifyBtn', function(){
        home.goPostModify(homeData.getPost());
    });
    
    // 포스트 삭제 버튼 클릭
    homeElements.$mainArticleWrap.on('click', '#prRemoveBtn', function(){
    	home.removePost($(this).parent().data('uriId'));
    });
    
})();