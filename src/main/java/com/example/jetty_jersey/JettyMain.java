package com.example.jetty_jersey;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyMain {

    public static void main(String[] args) throws Exception {
	
	// Initialize the server
	Server server = new Server();
	
	// Add a connector
	ServerConnector connector = new ServerConnector(server);
	connector.setHost("0.0.0.0");
	connector.setPort(8080);
	connector.setIdleTimeout(30000);
	server.addConnector(connector);

	// Configure Jersey
	ResourceConfig rc = new ResourceConfig();
	rc.packages(true, "com.example.jetty_jersey.ws");
	rc.register(JacksonFeature.class);
	rc.register(LoggingFilter.class);

	// Register Auth Filter here
	// rc.register(AuthenticationFilter.class);

	// Add a servlet handler for web services (/blablapane/*)
	ServletHolder servletHolder = new ServletHolder(new ServletContainer(rc));
	ServletContextHandler handlerWebServices = new ServletContextHandler(ServletContextHandler.SESSIONS);
	handlerWebServices.setContextPath("/blablaplane");
	handlerWebServices.addServlet(servletHolder, "/*");

	// Add a handler for resources (/*)
	ResourceHandler handlerPortal = new ResourceHandler();
	// handlerPortal.setResourceBase("src/main/webapp");
	handlerPortal.setResourceBase("src/main/webapp/interface");
	handlerPortal.setDirectoriesListed(false);
	handlerPortal.setWelcomeFiles(new String[] { "index.html" });
	ContextHandler handlerPortalCtx = new ContextHandler();
	handlerPortalCtx.setContextPath("/");
	handlerPortalCtx.setHandler(handlerPortal);

	// Activate handlers
	ContextHandlerCollection contexts = new ContextHandlerCollection();
	contexts.setHandlers(new Handler[] { handlerWebServices, handlerPortalCtx });
	server.setHandler(contexts);
	System.out.println("server start");

	/*
	 * web service // /blablaplane/search/
	 */
	// Start server
	server.start();

    }

}