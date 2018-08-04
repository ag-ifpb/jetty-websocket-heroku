package ag.ifpb;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;


public class App{

	public static void main(String[] args) throws Exception {
		//
		Integer port = 8080;
		String portStr = System.getenv("PORT");
		if (portStr != null){
			port = Integer.valueOf(portStr);
		}
		//set context handler
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/");
		//set port
		Server server = new Server(port);
		server.setHandler(context);
		//
		try {
			// Initialize javax.websocket layer
			ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);
			
			
			// Add WebSocket endpoint to javax.websocket layer
			wscontainer.addEndpoint(ServerEndpointConfig.Builder
					.create(WebTxtSocketEndpoint.class, "/wstxt").build());
			wscontainer.addEndpoint(ServerEndpointConfig.Builder
					.create(WebBinSocketEndpoint.class, "/wsbin").build());

			
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}
		//
		server.start();
		server.dump(System.err, "AGDEBUG");
		server.join();

	}

}
