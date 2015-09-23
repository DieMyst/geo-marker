package ru.diemyst.jersey.factories;

import org.glassfish.hk2.api.Factory;
import ru.diemyst.dao.PointDao;
import ru.diemyst.dao.PointDaoImpl;

/**
 * Created by diemyst on 19.09.15.
 */
public class PointDaoFactory implements Factory<PointDao> {
    @Override
    public PointDao provide() {
        return PointDaoImpl.getInstance();
    }

    @Override
    public void dispose(PointDao instance) {

    }
}
