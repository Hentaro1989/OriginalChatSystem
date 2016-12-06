package gitTest;

import java.io.IOException;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/endpoint")
public class Websocket {
	private static HashSet<Session> sessions = new HashSet<Session>(); // 全てのセッションを保存するセット

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {

		// シンクロナイズドブロックで同時処理を回避
		synchronized (message) {
			sessions.add(session); // セッションを追加
			for (Session s : sessions) { // すべてのセッションに対しメッセージを送信
				s.getBasicRemote().sendText(message);
			}
		}

	}

	@OnOpen
	public void onOpen() {

	}

	@OnClose
	public void onClose() {

	}
}
