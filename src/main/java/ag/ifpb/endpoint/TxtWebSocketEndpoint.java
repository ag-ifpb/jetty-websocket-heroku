package ag.ifpb.endpoint;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import ag.ifpb.messagehandler.OnTxtMsgHandler;

public class TxtWebSocketEndpoint extends javax.websocket.Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		session.addMessageHandler(new OnTxtMsgHandler(session));
	}
}
