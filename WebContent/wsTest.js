//接続先URI
var uri = "ws://localhost:8080/gitTest/endpoint";

// Websocketオブジェクト
var ws = new WebSocket(uri);

// イベントハンドラ登録
ws.onopen = function() {
	console.log("websocket opened");
};

var cnt = 0;
ws.onmessage = function(e) {
	var chat = $("<div></div>");
	chat.attr("id", "comment" + cnt);
	chat.attr("class", "comment");
	chat.text(e.data);
	$("body").append(chat);
	cnt++;
};

ws.onerror = function() {
	console.log("websocket error");
};

ws.onclose = function() {
	console.log("websocket closed");
};

function submit() {
	ws.send($("#text").val());
	$("#text").val("");
};