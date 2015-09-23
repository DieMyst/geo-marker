package ru.diemyst;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Created by diemyst on 18.09.15.
 */
public class Main {

    private static final String JERSEY_SERVLET_CONTEXT_PATH = "";

    public static void main(String[] args) throws IOException {
        URI baseUri = UriBuilder.fromUri("http://localhost/api").port(9998).build();
        ResourceConfig rc = new JerseyConf();

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
        //server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("/home/diemyst/IdeaProjects/geo-marker/src/main/resources/WEB-INF/templates/index.html"), "/");
        server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("src/main/resources/WEB-INF/templates/"), "/");

        /*HttpServer server = new HttpServer();
        NetworkListener listener = new NetworkListener("example", "localhost", 8080);
        server.addListener(listener);

        ServerConfiguration config = server.getServerConfiguration();
        HttpHandler handler = ContainerFactory.createContainer(HttpHandler.class, rc);
        config.addHttpHandler(handler, "*//*");

        server.start();

        while (true) {

        }*/

    }
}
