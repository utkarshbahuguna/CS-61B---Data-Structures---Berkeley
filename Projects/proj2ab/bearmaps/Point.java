package bearmaps;

import java.util.Comparator;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Returns the euclidean distance (L2 norm) squared between two points
     * (x1, y1) and (x2, y2). Note: This is the square of the Euclidean distance,
     * i.e. there's no square root.
     */
    private static double distance(double x1, double x2, double y1, double y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    /**
     * Returns the euclidean distance (L2 norm) squared between two points.
     * Note: This is the square of the Euclidean distance, i.e.
     * there's no square root. 
     */
    public static double distance(Point p1, Point p2) {
        return distance(p1.getX(), p2.getX(), p1.getY(), p2.getY());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Point otherPoint = (Point) other;
        return getX() == otherPoint.getX() && getY() == otherPoint.getY();
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }

    @Override
    public String toString() {
        return String.format("Point x: %.10f, y: %.10f", x, y);
    }


    private static class comparatorX implements Comparator<Point> {
        /** Returns a negative integer if x co-ordinate of p1 is less than x co-ordinate of p2,
         * 0 if equal, and positive integer if greater.
         */
        public int compare(Point p1, Point p2) {
            if (p1.getX() == p2.getX()) {
                return 0;
            } else if (p1.getX() < p2.getX()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private static class comparatorY implements Comparator<Point> {
        /** Returns a negative integer if y co-ordinate of p1 is less than y co-ordinate of p2,
         * 0 if equal, and positive integer if greater.
         */
        public int compare(Point p1, Point p2) {
            if (p1.getY() == p2.getY()) {
                return 0;
            } else if (p1.getY() < p2.getY()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    public static Comparator[] xyComparators = {new comparatorX(), new comparatorY()};
}
