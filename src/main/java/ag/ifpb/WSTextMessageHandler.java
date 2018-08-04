package ag.ifpb;

import java.io.IOException;

import javax.websocket.MessageHandler;
import javax.websocket.OnMessage;
import javax.websocket.Session;

public class WSTextMessageHandler implements MessageHandler.Whole<String>{
	private final Session session;
	
	public WSTextMessageHandler(Session s) {
		this.session = s;
	}
	
	@OnMessage
	public void onMessage(String message) {
		//
		try {
			//recebe os dados
			System.out.println("Received: " + message);
			//processa
			int r = Calculator.process(message);
			//devolve
			if (session.isOpen()){
				String msg = String.valueOf(r);
				session.getBasicRemote().sendText(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
