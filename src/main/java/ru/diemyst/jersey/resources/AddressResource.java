package ru.diemyst.jersey.resources;

import ru.diemyst.dao.PointDao;
import ru.diemyst.entities.Point;
import ru.diemyst.entities.google.GoogleResponse;
import ru.diemyst.entities.google.Location;
import ru.diemyst.entities.google.Result;
import ru.diemyst.jersey.client.AddressClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diemyst on 18.09.15.
 * Класс, отвечающий за запрос в GoogleMaps
 */
@Path("location")
public class AddressResource {

    private static String LINK = "https://maps.googleapis.com";
    private static String PATH = "maps/api/geocode/json";

    @Inject
    private AddressClient client;

    @Inject
    private PointDao pointDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPointByAddress(@QueryParam("address") String address,
                                      @QueryParam("name") String name) {

        GoogleResponse response = client.getGoogleResponse(LINK, PATH, address);
        if (response.getStatus().equals("OK")) {
            List<Point> points = new ArrayList<>();
            for (Result result : response.getResults()) {
                Location loc = result.getGeometry().getLocation();
                points.add(new Point(name, Double.valueOf(loc.getLat()), Double.valueOf(loc.getLng()), address));
            }
            pointDao.createListOfPoints(points);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("No such address").build();
        }
    }
}
