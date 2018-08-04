package ag.ifpb;

import java.net.URI;
import java.nio.ByteBuffer;

import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.eclipse.jetty.util.component.LifeCycle;

import junit.framework.TestCase;

public class WebSocketClientTest extends TestCase {

	public void testConn() {
		//URI uri = URI.create("ws://localhost:8080/ws/bin/calculator");
		URI uri = URI.create("ws://ag-ifpb-websocket.herokuapp.com/ws/bin/calculator");

		try {
			//create container websocket 
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			//
			try {
				// connect using anounymous endpoint
				Session session = container.connectToServer(new Endpoint() {					
					@Override
					public void onOpen(final Session session, EndpointConfig config) {
						session.addMessageHandler(new OnMsgHandler(session));
					}
				}, uri);
				//send first message
				ByteBuffer bb = UtilCliente.prepare();
				ByteBuffer content = ByteBuffer.wrap(bb.array());
				session.getBasicRemote().sendBinary(content);
				//log and sleep
				System.out.println("send...");
				Thread.sleep(10000);//up to see results
				// Close session
				session.close();
			} finally {
				if (container instanceof LifeCycle) {
					((LifeCycle) container).stop();
				}
			}
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}

	}

}
