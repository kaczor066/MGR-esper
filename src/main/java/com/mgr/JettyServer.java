package com.mgr;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Klasa opakowujaca serwer aplikacyjny Jetty
 *
 * @author michal
 */
public class JettyServer {
    private static final Logger LOG = LoggerFactory
            .getLogger(JettyServer.class);

    private Properties properties = new Properties();
    private Server server;

    /**
     * Metoda uruchamiajace serwer aplikacyjny wraz z ustawionymi servletami
     *
     * @param context
     * @throws Exception
     */
    public void start(ConfigurableWebApplicationContext context)
            throws Exception {
        server = createJetty(context);
        server.start();

        for (Connector connector : server.getConnectors()) {
            if (connector instanceof ServerConnector) {
                LOG.info("Server started at port {}",
                        ((ServerConnector) connector).getPort());
            }
        }

        server.join();
    }

    /**
     * Stopujemy serwer
     *
     * @throws Exception
     */
    public void stop() throws Exception {
        server.stop();
        LOG.info("Server stopped.");
    }

    /**
     * Metoda zawierajaca cala logike startowania serwera Uruchamia obiekt
     * serwera zgodnie z zaczytanymi propertisami oraz ustawia mu listenery
     *
     * @param context
     * @return
     * @throws Exception
     */
    private Server createJetty(ConfigurableWebApplicationContext context)
            throws Exception {
        try (FileInputStream fis = new FileInputStream(
                System.getProperty("cfg.path") + "app.properties")) {
            properties.load(fis);
        }

        LOG.info("Starting server...");
        int minThreads = Integer.parseInt(properties
                .getProperty("jetty.minThreads"));
        int maxThreads = Integer.parseInt(properties
                .getProperty("jetty.maxThreads"));
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads,
                minThreads);
        Server server = new Server(threadPool);

        Integer port;

        port = Integer.parseInt(properties.getProperty("jetty.port"));
        ServerConnector http = new ServerConnector(server,
                new HttpConnectionFactory());
        http.setPort(port);
        http.setIdleTimeout(Integer.parseInt(properties
                .getProperty("jetty.idleTimeout")));
        http.setAcceptQueueSize(Integer.parseInt(properties
                .getProperty("jetty.acceptQueueSize")));
        server.addConnector(http);

        server.setHandler(getServletContextHandler(context));

        return server;
    }

    /**
     * Metoda tworzaca servlety oraz listenery i rejestrujaca je w kontenerze
     * springa
     *
     * @param context
     * @return
     * @throws IOException
     */
    private ServletContextHandler getServletContextHandler(
            WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(properties
                .getProperty("jetty.contextPath"));
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(
                context)), properties.getProperty("jetty.mappingUrl"));
        contextHandler.addEventListener(new ContextLoaderListener(context));
        return contextHandler;
    }
}
