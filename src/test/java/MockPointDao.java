import ru.diemyst.dao.PointDao;
import ru.diemyst.entities.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by diemyst on 23.09.15.
 */
public class MockPointDao implements PointDao {
    private static MockPointDao ourInstance = new MockPointDao();

    public static MockPointDao getInstance() {
        return ourInstance;
    }

    private MockPointDao() {
    }

    Map<Long, Point> pointMap = new HashMap<>();
    long length = 0;

    @Override
    public List<Point> getPoints() {
        return new ArrayList<>(pointMap.values());
    }

    @Override
    public Point getPointById(long id) {
        return pointMap.get(id);
    }

    @Override
    public Long createPoint(Point point) {
        point.setId(length);
        pointMap.put(length, point);
        length++;
        return null;
    }

    @Override
    public void updatePoint(Point point) {
        pointMap.put(point.getId(), point);
    }

    @Override
    public void deletePoint(long id) {
        pointMap.remove(id);
    }

    @Override
    public void createListOfPoints(List<Point> points) {
        for (Point point : points) {
            pointMap.put(length++, point);
        }
    }
}
