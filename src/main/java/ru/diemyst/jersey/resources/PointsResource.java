package ru.diemyst.jersey.resources;

import ru.diemyst.dao.PointDao;
import ru.diemyst.entities.Point;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by diemyst on 17.09.15.
 * Позволяет доставать точки из базы
 * В принципе, можно было реализовать полный REST, но для данной задачи в избытке
 */
@Path("points")
public class PointsResource {

    //здесь можно сделать Autowire и описать имплементацию в конфиге, если подключить Spring, например
    @Inject
    private PointDao pointDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Point> getAllPoints() {
        return pointDao.getPoints();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Point getPoint(@PathParam("id") long id) {
        return pointDao.getPointById(id);
    }

}
