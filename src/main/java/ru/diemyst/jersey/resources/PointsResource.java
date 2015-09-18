package ru.diemyst.jersey.resources;

import ru.diemyst.dao.PointDao;
import ru.diemyst.dao.PointDaoImpl;
import ru.diemyst.entities.Point;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by diemyst on 17.09.15.
 */
@Path("points")
public class PointsResource {

    //здесь можно сделать Autowire и описать имплементацию в конфиге, если подключить Spring, например
    private PointDao pointDao = PointDaoImpl.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Point> getAllPoints() {
        List<Point> list = pointDao.getPoints();
        System.out.println(list);
        return list;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Point getPoint(@PathParam("id") long id) {
        return pointDao.getPointById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPoint(Point point) {
        pointDao.createPoint(point);
    }
}
