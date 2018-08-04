package ag.ifpb.endpoint;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import ag.ifpb.messagehandler.OnBinMsgHandler;

public class BinWebSocketEndpoint extends javax.websocket.Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		session.addMessageHandler(new OnBinMsgHandler(session));
	}
}
