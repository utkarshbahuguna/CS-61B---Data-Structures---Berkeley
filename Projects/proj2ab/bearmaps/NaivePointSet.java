// https://fa20.datastructur.es/materials/proj/proj2ab/proj2ab

package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Point nearest = points.get(0);
        for(Point p : points) {
            if(Point.distance(p, target) < Point.distance(nearest, target)) {
                nearest = p;
            }
        }
        return nearest;
    }
}
