package ag.ifpb;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.MessageHandler;
import javax.websocket.OnMessage;
import javax.websocket.Session;

public class WSBinaryMessageHandler implements MessageHandler.Whole<ByteBuffer>{
	private final Session session;
	
	public WSBinaryMessageHandler(Session s) {
		this.session = s;
	}
	
	@OnMessage
	public void onMessage(ByteBuffer message) {
		//
		try {
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
