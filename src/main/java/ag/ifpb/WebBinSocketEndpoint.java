package ag.ifpb;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;

public class WebBinSocketEndpoint extends javax.websocket.Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		session.addMessageHandler(new WSBinaryMessageHandler(session));
	}
}
