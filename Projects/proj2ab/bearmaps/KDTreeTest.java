package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testNearest() {
        List<Point> points = createPoints(50000);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);

        List<Point> pins = createPoints(5000);
        for (Point pin : pins) {
            assertEquals(Point.distance(kdt.nearest(pin.getX(), pin.getY()), pin),
                                        Point.distance(nps.nearest(pin.getX(), pin.getY()), pin), 1e-6);
        }
    }

    /** Create a list of n randomly generated points with given x and y ranges. */
    private List<Point> createPoints(int n, double minX, double maxX, double minY, double maxY) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(StdRandom.uniform(minX, maxX), StdRandom.uniform(minY, maxY)));
        }
        return points;
    }

    private List<Point> createPoints(int n) {
        return createPoints(n, -1000, 1000, -1000, 1000);
    }

    public void testTiming() {
        List<Integer> numPoints = new ArrayList<>();
        List<Double> KDTTimes = new ArrayList<>();
        List<Double> NPSTimes = new ArrayList<>();
        int queries = 10000;
        List<Point> pins = createPoints(queries);

        for (int i = 1; i <= 64; i = i * 2) {
            numPoints.add(i * 1000);

            List<Point> points = createPoints(i * 1000);
            NaivePointSet nps = new NaivePointSet(points);
            KDTree kdt = new KDTree(points);

            Stopwatch sw = new Stopwatch();
            for (Point pin : pins) {
                kdt.nearest(pin.getX(), pin.getY());
            }
            KDTTimes.add(sw.elapsedTime());

            Stopwatch sw2 = new Stopwatch();
            for (Point pin : pins) {
                nps.nearest(pin.getX(), pin.getY());
            }
            NPSTimes.add(sw2.elapsedTime());
        }
        printTimingTable(numPoints, queries, NPSTimes, KDTTimes);
    }

    private static void printTimingTable(List<Integer> numPoints, int queries, List<Double> NPSTimes, List<Double> KDTTimes) {
        System.out.printf("%20s %20s %20s %20s %20s %20s\n", "No. of Points", "# queries", "NPS (s)", "NPS microsec/query", "KDT (s)" , "KDT microsec/query");
        System.out.printf("-------------------------------------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < numPoints.size(); i += 1) {
            int N = numPoints.get(i);
            double NPSTime = NPSTimes.get(i);
            double KDTTime = KDTTimes.get(i);
            double NPStimePerQuery = NPSTime / queries * 1e6;
            double KDTtimePerQuery = KDTTime / queries * 1e6;
            System.out.printf("%20d % 20d %20.2f %20.2f %20.2f %20.2f\n", N, queries, NPSTime, NPStimePerQuery, KDTTime, KDTtimePerQuery);
        }
    }

    public static void main(String[] args) {
        KDTreeTest kdtest = new KDTreeTest();
        kdtest.testTiming();
    }
}
