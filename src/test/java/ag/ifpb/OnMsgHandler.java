package ag.ifpb;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.MessageHandler;
import javax.websocket.Session;

public class OnMsgHandler implements MessageHandler.Whole<String> {
	private final Session session;

	public OnMsgHandler(Session s) {
		this.session = s;
	}

	public void onMessage(String message) {
		try {
			//convert message in result (int) 
			int x = Integer.parseInt(message);
			//log
			System.out.println("received: " + x);
			//prepare new message (byte)
			ByteBuffer bb = UtilCliente.prepareWithResult(x, ByteBuffer.allocate(10));
			ByteBuffer content = ByteBuffer.wrap(bb.array());
			//send binary
			if (session.isOpen()) {
				session.getBasicRemote().sendBinary(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
