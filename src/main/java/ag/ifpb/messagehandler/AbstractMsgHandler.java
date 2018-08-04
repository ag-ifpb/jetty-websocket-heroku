package ag.ifpb.messagehandler;

import java.io.IOException;

import javax.websocket.MessageHandler;
import javax.websocket.Session;

public abstract class AbstractMsgHandler<T> implements MessageHandler.Whole<T>{
	private final Session session;
	
	public AbstractMsgHandler(Session s) {
		this.session = s;
	}
	
	public void sendMessage(String message) throws IOException{
		if (session.isOpen()){
			session.getBasicRemote().sendText(message);
		}
	}
	
}
