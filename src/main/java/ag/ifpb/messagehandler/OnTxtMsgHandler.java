package ag.ifpb.messagehandler;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;

import ag.ifpb.processing.Calculator;

public class OnTxtMsgHandler extends AbstractMsgHandler<String>{
	
	public OnTxtMsgHandler(Session s) {
		super(s);
	}
	
	@OnMessage
	public void onMessage(String message) {
		try {
			//processa
			int r = Calculator.process(message);
			//convert int txt message 
			String msg = String.valueOf(r);
			//send message
			sendMessage(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
