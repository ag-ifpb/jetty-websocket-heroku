package ag.ifpb.messagehandler;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnMessage;
import javax.websocket.Session;

import ag.ifpb.processing.Calculator;

public class OnBinMsgHandler extends AbstractMsgHandler<ByteBuffer>{
	
	public OnBinMsgHandler(Session s) {
		super(s);
	}
	
	@OnMessage
	public void onMessage(ByteBuffer message) {
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
