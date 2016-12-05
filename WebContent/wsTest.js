//接続先URI
var uri = "ws://localhost:8080/gitTest/endpoint";

//Websocketオブジェクト
var ws = new WebSocket(uri);

//イベントハンドラ登録
ws.onopen = function() {
  console.log("websocket opened");
};

ws.onmessage = function(e) {
	ws.send($("#text").val());
};

ws.onerror = function() {
	  console.log("websocket error");
	};

ws.onclose = function() {
  console.log("websocket closed");
};