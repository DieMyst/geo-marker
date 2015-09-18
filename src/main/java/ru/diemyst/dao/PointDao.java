package ru.diemyst.dao;

import ru.diemyst.entities.Point;

import java.util.List;

/**
 * Created by diemyst on 18.09.15.
 */
public interface PointDao {

    List<Point> getPoints();
    Point getPointById(long id);
    Long createPoint(Point point);
    void updatePoint(Point point);
    void deletePoint(long id);
    void createListOfPoints(List<Point> points);

}
