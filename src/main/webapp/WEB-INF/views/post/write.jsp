<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기술블로그 - oh29oh29</title>
</head>
<body>
<textarea name="ir1" id="ir1" rows="10" cols="100">에디터에 기본으로 삽입할 글(수정 모드)이 없다면 이 value 값을 지정하지 않으시면 됩니다.</textarea>
<script type="text/javascript" src="import/smartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "ir1",
    sSkinURI: "import/smartEditor/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});
</script>
</body>
</html>