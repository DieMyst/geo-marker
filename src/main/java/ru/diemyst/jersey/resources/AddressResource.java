package ru.diemyst.jersey.resources;

import ru.diemyst.client.AddressClient;
import ru.diemyst.dao.PointDao;
import ru.diemyst.dao.PointDaoImpl;
import ru.diemyst.entities.Point;
import ru.diemyst.entities.google.GoogleResponse;
import ru.diemyst.entities.google.Location;
import ru.diemyst.entities.google.Result;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diemyst on 18.09.15.
 */
@Path("location")
public class AddressResource {

    private static String LINK = "https://maps.googleapis.com";
    private static String PATH = "maps/api/geocode/json";

    private Client client = AddressClient.getInstance();
    private PointDao pointDao = PointDaoImpl.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPointByAddress(@QueryParam("address") String address,
                                      @QueryParam("name") String name) {
        WebTarget target = client.target(LINK).path(PATH).queryParam("address", address);
        GoogleResponse response = target.request(MediaType.APPLICATION_JSON_TYPE).get(GoogleResponse.class);
        if (response.getStatus().equals("OK")) {
            List<Point> points = new ArrayList<>();
            for (Result result : response.getResults()) {
                Location loc = result.getGeometry().getLocation();
                points.add(new Point(name, Double.valueOf(loc.getLat()), Double.valueOf(loc.getLng())));
            }
            pointDao.createListOfPoints(points);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No such address").build();
        }
    }
}
