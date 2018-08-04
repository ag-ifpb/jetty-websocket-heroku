package ag.ifpb;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;

public class WebTxtSocketEndpoint extends javax.websocket.Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		session.addMessageHandler(new WSTextMessageHandler(session));
	}
}
