var homeEvent = homeEvent || (function(){
    'use strict';

    homeElements.$sharp.on('click', function() {
		homeElements.$search.focus();
    });
    
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
    
    homeElements.$mainArticleWrap.on('click', '.plPostListItemInner', function(){
		home.getPost($(this).data('uriId'));
    });
    
    homeElements.$mainArticleWrap.on('click', '#pwCompleteBtn', function(){
        // 에디터의 내용이 textarea에 적용된다.
        homeData.smartEditor.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
        
        home.submitPostWrite();
    });
    
})();