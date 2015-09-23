import org.glassfish.hk2.api.Factory;

/**
 * Created by diemyst on 23.09.15.
 */
public class MockPointDaoFactory implements Factory<MockPointDao> {


    @Override
    public MockPointDao provide() {
        return MockPointDao.getInstance();
    }

    @Override
    public void dispose(MockPointDao instance) {

    }
}
