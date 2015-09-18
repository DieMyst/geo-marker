package ru.diemyst;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ru.diemyst.jersey.resources.AddressResource;
import ru.diemyst.jersey.resources.PointsResource;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by diemyst on 18.09.15.
 */
public class Main {
    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig rc = new JerseyConf();
        rc.register(PointsResource.class);
        rc.register(AddressResource.class);
        rc.register(new LoggingFilter());
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
    }
}
