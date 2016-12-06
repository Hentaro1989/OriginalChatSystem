//接続先URI
var uri = "ws://localhost:8080/gitTest/endpoint";

// Websocketオブジェクト
var ws = new WebSocket(uri);

// イベントハンドラ登録
ws.onopen = function() {
	console.log("websocket opened");
};

//Websocketからメッセージ受信時に動作
ws.onmessage = function(e) {
	$("#log").prepend(e.data + "<br>");
};

ws.onerror = function() {
	console.log("websocket error");
};

ws.onclose = function() {
	console.log("websocket closed");
};

function submit() {
	if($("#text").val() == "" || $("#text").val() == "　" || $("#text").val() == " ")return; //入力が無かったりスペース１つの時は送信させない
	ws.send($("#text").val());
	$("#text").val("");
};