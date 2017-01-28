$(function() {
    var uri = "ws://192.168.11.100:8080/OriginalChatSystem/endpoint"; //接続先URI
    var ws = new WebSocket(uri); // Websocketオブジェクト

    // イベントハンドラ登録
    ws.onopen = function() {
        console.log("websocket opened");
    };

    // Websocketからメッセージ受信時に動作
    ws.onmessage = function(e) {
        $("#log").prepend("<p>" + e.data + "</p>");
    };

    ws.onerror = function() {
        console.log("websocket error");
    };

    ws.onclose = function() {
        console.log("websocket closed");
    };

    $("#name").change(
        function() {
            if ($("#name").val() == "" || $("#name").val() == "　" || $("#name").val() == " ") return; // 名前の入力が無かったりスペース１つの時は送信ボタン活性せず
            var yourName = $("#name").val();
            var jsonName = { cmd: "name", name: yourName };
            var strJsonName = JSON.stringify(jsonName);
            $("#name").remove();
            ws.send(strJsonName);
            $("#submit").prop("disabled", false);
        });

    $("#submit").click(function() {
        if ($("#text").val() == "" || $("#text").val() == "　" || $("#text").val() == " ") return; // 入力が無かったりスペース１つの時は送信させない
        var chatText = $("#text").val();
        var jsonText = { cmd: "chat", text: chatText };
        var strJsonText = JSON.stringify(jsonText);
        ws.send(strJsonText);
        $("#text").val("");
    });

});
