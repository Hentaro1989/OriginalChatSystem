package gitTest;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/endpoint")
public class Websocket {

	@OnMessage
	public String onMessage(String message) {
		System.out.println("java:" + message);
		return message;
	}

	@OnOpen
	public void onOpen() {

	}

	@OnClose
	public void onClose() {

	}
}
