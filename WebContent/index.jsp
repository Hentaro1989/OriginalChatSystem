<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="./wsTest.js"></script>
</head>

<body>
	<p>
		<input type="text" id="name" value="" placeholder="名前を入力">
	</p>
	<p>
		<input type="text" id="text" value="" placeholder="文章を入力">
	</p>
	<input type="submit" id="submit" value="送信" disabled="disabled">
	<div id="log"></div>
</body>

</html>