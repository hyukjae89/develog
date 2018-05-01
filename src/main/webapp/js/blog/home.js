var home = home || (function(){
	'use strict';
	
	var _$mainContents = $('#mainContentsWrap');
	
	var _$bgSharp = $('#bgSharp');
	var _$bgSearch = $('#bgSearch'); 
	
	_$bgSharp.on('click', function() {
		_$bgSearch.focus();
	});
	
	_$bgSharp.trigger('click');
	
	_$bgSearch.on('keyup', function(event) {
		var $this = $(this);
		
		if (event.keyCode == 13) {
			
			if ($this.val() == '로그인하자') {
				location.href = "/sign-in";
			}
			else {
				list.getPosts($this.val());
			}
		}
	});
	
	return {
		$mainContents 	: _$mainContents
	}
})();