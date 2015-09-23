package ru.diemyst.jersey.binders;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.diemyst.dao.PointDao;
import ru.diemyst.jersey.factories.PointDaoFactory;

/**
 * Created by diemyst on 19.09.15.
 */
public class PointDaoBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(PointDaoFactory.class).to(PointDao.class);
    }
}
