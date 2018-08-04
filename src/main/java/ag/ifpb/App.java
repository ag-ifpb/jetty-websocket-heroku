package ag.ifpb;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import ag.ifpb.endpoint.BinWebSocketEndpoint;
import ag.ifpb.endpoint.TxtWebSocketEndpoint;


public class App{

	public static void main(String[] args) throws Exception {
		//setting http port
		Integer port = 8080;
		String portStr = System.getenv("PORT");
		if (portStr != null){
			port = Integer.valueOf(portStr);
		}
		//set jetty context handler
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/");
		//instancing jetty server
		Server server = new Server(port);
		server.setHandler(context);
		//
		try {
			//initialize javax.websocket layer
			ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);
			//add WebSocket Endpoint to javax.websocket layer
			wscontainer.addEndpoint(ServerEndpointConfig.Builder
					.create(BinWebSocketEndpoint.class, "/ws/bin/calculator").build());
			wscontainer.addEndpoint(ServerEndpointConfig.Builder
					.create(TxtWebSocketEndpoint.class, "/ws/txt/calculator").build());
			// start jetty server
			server.start();
			//server.dump(System.err, "AGDEBUG");
			server.join();
			
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}
	}

}
