<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기술블로그 - oh29oh29</title>
</head>
<body onload="init()">

	<textarea name="contents" id="ir1" rows="10" cols="100"></textarea>
	<button id="pwCompleteBtn">작성완료</button>

<script src="./import/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="import/smartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
function init() {
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "ir1",
	    sSkinURI: "import/smartEditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	});
	
	$('#pwCompleteBtn').on('click', function() {
		// 에디터의 내용이 textarea에 적용된다.
	    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		
		console.log(document.getElementById("ir1").value);
	});
}
</script>
</body>
</html>