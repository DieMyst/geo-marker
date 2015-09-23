import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import ru.diemyst.dao.PointDao;
import ru.diemyst.entities.Point;
import ru.diemyst.jersey.client.AddressClient;
import ru.diemyst.jersey.resources.AddressResource;
import ru.diemyst.jersey.resources.PointsResource;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by diemyst on 23.09.15.
 */
public class ResourcesTest extends JerseyTest {

    @Override
    protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
        return new InMemoryTestContainerFactory();
    }

    @Override
    protected Application configure() {
        ResourceConfig rc = new ResourceConfig();
        rc.register(AddressResource.class);
        rc.register(PointsResource.class);
        rc.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(MockPointDaoFactory.class).to(PointDao.class);
                bind(MockAddressClient.class).to(AddressClient.class);
            }
        });

        return rc;
    }

    @Test
    public void testPointResource() throws URISyntaxException {

        Response response = target("location").queryParam("address", "hello").queryParam("name", "world").request().get();
        assertEquals(response.getStatus(), 200);

        String firstListOfPoints = target("points").request().get(String.class);
        assertThat(firstListOfPoints, containsString("\"name\":\"world\""));
        assertThat(firstListOfPoints, containsString("\"address\":\"hello\""));

        Response response2 = target("location").queryParam("address", "fault").queryParam("name", "world").request().get();
        assertEquals(response2.getStatus(), 406);

        String firstListOfPoints2 = target("points").request().get(String.class);
        assertThat(firstListOfPoints2, not(containsString("\"name\":\"fault\"")));

        Point point1 = target("points").path("0").request().get(Point.class);
        assertEquals(point1.getId(), 0);
        assertEquals(point1.getAddress(), "hello");
        assertEquals(point1.getName(), "world");
        assertEquals(point1.getLatitude(), new Double(10.0));
        assertEquals(point1.getLongitude(), new Double(22.2));

        Point pointNull = target("points").path("1").request().get(Point.class);
        assertEquals(pointNull, null);
    }


}
