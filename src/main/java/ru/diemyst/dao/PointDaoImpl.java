package ru.diemyst.dao;

import ru.diemyst.entities.Point;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by diemyst on 18.09.15.
 */
public class PointDaoImpl implements PointDao {

    private static volatile PointDaoImpl instance;

    private PointDaoImpl() {
    }

    public static PointDaoImpl getInstance() {
        PointDaoImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (PointDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PointDaoImpl();
                }
            }
        }
        return localInstance;
    }


    public EntityManager em = Persistence.createEntityManagerFactory("GEOMARKER").createEntityManager();

    @Override
    public List<Point> getPoints() {
        TypedQuery<Point> namedQuery = em.createNamedQuery("Point.getAll", Point.class);
        return namedQuery.getResultList();
    }

    @Override
    public Point getPointById(long id) {
        return em.find(Point.class, id);
    }

    @Override
    public Long createPoint(Point point) {
        em.getTransaction().begin();
        Point pointFromDB = em.merge(point);
        em.getTransaction().commit();
        return pointFromDB.getId();
    }

    @Override
    public void updatePoint(Point point) {
        em.getTransaction().begin();
        em.merge(point);
        em.getTransaction().commit();
    }

    @Override
    public void deletePoint(long id) {
        em.getTransaction().begin();
        em.remove(getPointById(id));
        em.getTransaction().commit();
    }

    @Override
    public void createListOfPoints(List<Point> points) {
        em.getTransaction().begin();
        for (Point point : points) {
            em.merge(point);
        }
        em.getTransaction().commit();
    }
}
