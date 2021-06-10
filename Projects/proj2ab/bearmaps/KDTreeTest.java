package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

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
        Random rand = new Random();
        DoubleStream Xs = rand.doubles(minX, maxX);
        DoubleStream Ys = rand.doubles(minX, maxX);
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(StdRandom.uniform(minX, maxX), StdRandom.uniform(minY, maxY)));
        }
        return points;
    }

    private List<Point> createPoints(int n) {
        return createPoints(n, -1000, 1000, -1000, 1000);
    }

    public void testTimingKDTConstruction() {
        List<Integer> numPoints = new ArrayList<>();
        List<Double> KDTTimes = new ArrayList<>();
        List<Integer> ops = new ArrayList<>();

        for (int i = 1; i <= 1024; i = i * 2) {
            numPoints.add(i * 1000);
            List<Point> points = createPoints(i * 1000);
            Stopwatch sw = new Stopwatch();
            KDTree kdt = new KDTree(points);
            KDTTimes.add(sw.elapsedTime());
            ops.add(i * 1000);
        }
        System.out.println("Timing Table for KDTree Construction");
        printTimingTable(numPoints, ops, KDTTimes);
    }

    public void testTimingKDTNearest() {
        List<Integer> numPoints = new ArrayList<>();
        List<Double> KDTTimes = new ArrayList<>();
        List<Integer> ops = new ArrayList<>();
        int queries = 500000;
        List<Point> pins = createPoints(queries);

        for (int i = 1; i <= 1024; i = i * 2) {
            numPoints.add(i * 1000);
            List<Point> points = createPoints(i * 1000);
            NaivePointSet nps = new NaivePointSet(points);
            KDTree kdt = new KDTree(points);

            Stopwatch sw = new Stopwatch();
            for (Point pin : pins) {
                kdt.nearest(pin.getX(), pin.getY());
            }
            KDTTimes.add(sw.elapsedTime());
            ops.add(queries);
        }
        System.out.println("Timing Table for KDTree Nearest");
        printTimingTable(numPoints, ops, KDTTimes);
    }

    public void testTimingNPSNearest() {
        List<Integer> numPoints = new ArrayList<>();
        List<Double> NPSTimes = new ArrayList<>();
        List<Integer> ops = new ArrayList<>();
        int queries = 500000;
        List<Point> pins = createPoints(queries);

        for (int i = 1; i <= 16; i = i * 2) {
            numPoints.add(i * 100);
            List<Point> points = createPoints(i * 100);
            NaivePointSet nps = new NaivePointSet(points);

            Stopwatch sw = new Stopwatch();
            for (Point pin : pins) {
                nps.nearest(pin.getX(), pin.getY());
            }
            NPSTimes.add(sw.elapsedTime());
            ops.add(queries);
        }
        System.out.println("Timing Table for Naive Point Set Nearest");
        printTimingTable(numPoints, ops, NPSTimes);
    }

    private static void printTimingTable(List<Integer> numPoints, List<Integer> ops, List<Double> times) {
        System.out.printf("%20s %20s %20s %20s\n", "No. of Points", "# ops", "Time (s)", "microsec/query");
        System.out.printf("--------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < numPoints.size(); i += 1) {
            int N = numPoints.get(i);
            int op = ops.get(i);
            double time = times.get(i);
            double timePerQuery = time / op * 1e6;
            System.out.printf("%20d % 20d %20.2f %20.2f\n", N, op, time, timePerQuery);
        }
    }

    public static void main(String[] args) {
        KDTreeTest kdtest = new KDTreeTest();
//        kdtest.testTimingNPSNearest();
//        kdtest.testTimingKDTNearest();
        kdtest.testTimingKDTConstruction();
    }
}
