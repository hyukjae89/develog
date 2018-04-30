var write = write || (function(){
	'use strict';
	
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "ir1",
	    sSkinURI: "/import/smartEditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	});
	
	$('#pwCompleteBtn').on('click', function() {
		// 에디터의 내용이 textarea에 적용된다.
	    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		
		console.log(document.getElementById("ir1").value);
		
		$('#pwWriteForm').prop("action", "/post").prop("method", "post").prop("acceptCharset", "utf-8").submit();
		
	});
})();