package ag.ifpb;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;

import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.eclipse.jetty.util.component.LifeCycle;

import junit.framework.TestCase;

public class WebSocketClientTest extends TestCase {

	public void testConn() {
		URI uri = URI.create("ws://localhost:8080/wsbin");

		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();

			try {
				// Attempt Connect
				Session session = container.connectToServer(new Endpoint() {					
					@Override
					public void onOpen(final Session session, EndpointConfig config) {
						session.addMessageHandler(new MessageHandler.Whole<String>() {
							public void onMessage(String message) {
								try {
									//
									int x = Integer.parseInt(message);
									//
									ByteBuffer bb = UtilCliente.sendWithResult(x, ByteBuffer.allocate(10));
									ByteBuffer content = ByteBuffer.wrap(bb.array());
									//
									if (session.isOpen()) session.getBasicRemote().sendBinary(content);
									//
									System.out.println("Client received: " + message);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
					}
				}, uri);
				//
				ByteBuffer bb = UtilCliente.prepare();
				ByteBuffer content = ByteBuffer.wrap(bb.array());
				//
				Basic b = session.getBasicRemote();
				b.sendBinary(content);
				//
				System.out.println("send...");
				Thread.sleep(10000);//up to see results
				// Close session
				session.close();
			} finally {
				// Force lifecycle stop when done with container.
				// This is to free up threads and resources that the
				// JSR-356 container allocates. But unfortunately
				// the JSR-356 spec does not handle lifecycles (yet)
				if (container instanceof LifeCycle) {
					((LifeCycle) container).stop();
				}
			}
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}

	}

}
