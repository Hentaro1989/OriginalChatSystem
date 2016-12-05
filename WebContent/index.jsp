<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
<script  src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="./wsTest.js"></script>
</head>

<body>
	<input type="text" id="text" value="">
	<input type="submit" id="submit" onclick="submit()" value="送信">
</body>

<script>
	var cnt = 0;

	function submit() {
		var chat = $("<div></div>");
		chat.attr("id", "comment" + cnt);
		chat.attr("class", "comment");
		chat.text($("#text").val());
		$("body").append(chat);
		$("#text").val("");
		cnt++;
	};
</script>
</html>