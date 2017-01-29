package originalChatSystem;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint(value = "/endpoint")
public class Websocket {
	private static HashSet<Session> sessions = new HashSet<Session>(); // 全てのセッションを保存するセット
	private static HashMap<Session, String> membersName = new HashMap<Session, String>(); //セッションごとにユーザの名前を管理

	@OnMessage
	synchronized public void onMessage(String message, Session session) throws IOException {

		HashMap<String, Object> json = new ObjectMapper().readValue(message, HashMap.class);
		String cmd = (String) json.get("cmd");

		// JSONコマンドが「name」の時の処理
		if (cmd.equals("name")) {
			membersName.put(session, (String) json.get("name")); //セッションと入力された名前をマッピング
			return;
		}

		// JSONコマンドが「chat」の時の処理
		if (cmd.equals("chat")) {
			String yourName = membersName.get(session); //セッションに紐づいている名前を取得
			message = (String) json.get("text"); //JSONコマンド「text」に入力されて文字列を取得
			for (Session s : sessions) { // すべてのセッションに対しメッセージを送信
				// s.getBasicRemote().sendText(yourName + "さん ： " + message);
				s.getBasicRemote().sendText("{\"name\":\"" + yourName + "\",\"text\":\"" + message + "\"}");
			}
			return;
		}

	}

	@OnOpen
	synchronized public void onOpen(Session session) {
		synchronized (session) {
			sessions.add(session); // セッションを追加
			System.out.println("【Method】onOpen()  " + "【current sessions】" + sessions.size());
			System.out.println();
		}
	}

	@OnClose
	synchronized public void onClose(Session session) {
		membersName.remove(session); //セッションから外れた人の名前を削除
		sessions.remove(session); // セッションを破棄
		System.out.println("【Method】onClose()  " + "【current sessions】" + sessions.size());
		System.out.println();
	}

	@OnError
	synchronized public void onError(Throwable throwable) {
		System.out.println(throwable.getCause());
		System.out.println();
	}
}
