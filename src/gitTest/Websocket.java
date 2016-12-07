package gitTest;

import java.io.IOException;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/endpoint")
public class Websocket {
	private static HashSet<Session> sessions = new HashSet<Session>(); // 全てのセッションを保存するセット

	@OnMessage
	synchronized public void onMessage(String message) throws IOException {

		for (Session s : sessions) { // すべてのセッションに対しメッセージを送信
			s.getBasicRemote().sendText(message);
		}

	}

	@OnOpen
	synchronized public void onOpen(Session session) {
		synchronized (session) {
			sessions.add(session); // セッションを追加
			System.out.println("【Method】onOpen()");
			System.out.println("【current sessions】" + sessions.size());
			System.out.println();
		}
	}

	@OnClose
	synchronized public void onClose(Session session) {
		sessions.remove(session); // セッションを破棄
		System.out.println("【Method】onClose()");
		System.out.println("【current sessions】" + sessions.size());
		System.out.println();
	}

	@OnError
	synchronized public void onError(Throwable throwable){
		System.out.println(throwable.getCause());
		System.out.println();
	}
}
