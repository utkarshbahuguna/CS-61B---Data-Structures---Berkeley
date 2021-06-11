package bearmaps;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testRandomized() {
        ArrayHeapMinPQ<Integer> ahq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> nq = new NaiveMinPQ<>();
        for(int i = 0; i < 100000; i++) {
            Double prob = StdRandom.uniform();
            if (prob > 0.40) {
                Double priority = StdRandom.uniform(0.0, 100.0);
                ahq.add(i, priority);
                nq.add(i, priority);
            }
        }

        for(int i = 0; i < 10000; i++) {
            Integer item = StdRandom.uniform(0, 1000);
            assertEquals(ahq.contains(item), nq.contains(item));
            assertEquals(ahq.getSmallest(), nq.getSmallest());
            assertEquals(ahq.size(), nq.size());
            assertEquals(ahq.removeSmallest(), nq.removeSmallest());
        }
    }

    @Test
    public void testRandomChangePriority() {
        ArrayHeapMinPQ<Integer> ahq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> nq = new NaiveMinPQ<>();
        for(int i = 0; i < 100000; i++) {
            Double priority = StdRandom.uniform(0.0, 100.0);
            ahq.add(i, priority);
            nq.add(i, priority);
        }
        for(int i = 0; i < 10000; i++) {
            Integer item = StdRandom.uniform(0, 100000);
            Double priority = StdRandom.uniform(0.0, 100.0);
            ahq.changePriority(item, priority);
            nq.changePriority(item, priority);
        }
        for(int i = 0; i < 10000; i++) {
            assertEquals(ahq.removeSmallest(), nq.removeSmallest());
        }
    }

    @Test
    public void testExtrinsicPQ() {
        ArrayHeapMinPQ<String> ahq = new ArrayHeapMinPQ<>();
        ahq.add("Dog", 1);
        ahq.add("Cat", 2);
        ahq.add("Cow", 1.5);
        ahq.add("Capybara", 2.5);
        ahq.add("Crow", 3);
        ahq.add("Eagle", 1.3);
        ahq.add("Lion", 2);
        assertEquals(7, ahq.size());
        assertEquals("Dog", ahq.getSmallest());
        assertEquals("Dog", ahq.removeSmallest());
        assertEquals("Eagle", ahq.getSmallest());
        assertEquals("Eagle", ahq.getSmallest());
        ahq.changePriority("Eagle", 1.7);
        assertEquals("Cow", ahq.getSmallest());
        assertEquals("Cow", ahq.removeSmallest());
        assertEquals(5, ahq.size());
        assertEquals("Eagle", ahq.removeSmallest());
        assertFalse(ahq.contains("Eagle"));
        assertTrue(ahq.contains("Cat"));
        ahq.removeSmallest();
        ahq.removeSmallest();
        ahq.removeSmallest();
        ahq.removeSmallest();
        assertEquals(0, ahq.size());
        ahq.add("Cat", 1);
        assertEquals("Cat", ahq.getSmallest());
    }

    @Test
    public void testDuplicateAdd() {
        ArrayHeapMinPQ<String> ahq = new ArrayHeapMinPQ<>();
        ahq.add("Dog", 1);

        expectedException.expect(IllegalArgumentException.class);
        ahq.add("Dog", 2);
    }

    @Test
    public void testRemoveEmpty() {
        ArrayHeapMinPQ<String> ahq = new ArrayHeapMinPQ<>();
        ahq.add("Dog", 1);
        ahq.add("Cat", 2);
        ahq.removeSmallest();
        ahq.removeSmallest();

        expectedException.expect(NoSuchElementException.class);
        ahq.getSmallest();
    }

    @Test
    public void testChangePriorityException() {
        ArrayHeapMinPQ<String> ahq = new ArrayHeapMinPQ<>();
        ahq.add("Dog", 1);

        expectedException.expect(NoSuchElementException.class);
        ahq.changePriority("Cat", 1);
    }

    public void testTimingAdd() {
        List<Integer> numItems = new ArrayList<>();
        List<Double> AddTimes = new ArrayList<>();
        List<Integer> ops = new ArrayList<>();

        for (int i = 64; i <= 1024; i = i * 2) {
            ArrayHeapMinPQ<Integer> ahq = new ArrayHeapMinPQ<>();
            numItems.add(i * 1000);
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < i * 1000; j++) {
                Double priority = StdRandom.uniform(0.0, 100.0);
                ahq.add(j, priority);
            }
            AddTimes.add(sw.elapsedTime());
            ops.add(i * 1000);
        }
        System.out.println("Timing Table for ArrayHeapMinPQ Additon");
        printTimingTable(numItems, ops, AddTimes);
    }

    public void testTimingRemoveSmallest() {
        List<Integer> numItems = new ArrayList<>();
        List<Double> RemoveTimes = new ArrayList<>();
        List<Integer> ops = new ArrayList<>();

        int queries = 10000;
        for (int i = 64; i <= 1024; i = i * 2) {
            ArrayHeapMinPQ<Integer> ahq = new ArrayHeapMinPQ<>();
            numItems.add(i * 1000);
            for (int j = 0; j < i * 1000; j++) {
                Double priority = StdRandom.uniform(0.0, 100.0);
                ahq.add(j, priority);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < queries; j++) {
                ahq.removeSmallest();
            }
            RemoveTimes.add(sw.elapsedTime());
            ops.add(queries);
        }
        System.out.println("Timing Table for ArrayHeapMinPQ Remove Smallest");
        printTimingTable(numItems, ops, RemoveTimes);
    }

    public void testTimingChangePriority() {
        List<Integer> numItems = new ArrayList<>();
        List<Double> ChangeTimes = new ArrayList<>();
        List<Integer> ops = new ArrayList<>();

        int queries = 100000;
        for (int i = 128; i <= 1024; i = i * 2) {
            ArrayHeapMinPQ<Integer> ahq = new ArrayHeapMinPQ<>();
            numItems.add(i * 1000);
            for (int j = 0; j < i * 1000; j++) {
                Double priority = StdRandom.uniform(0.0, 100.0);
                ahq.add(j, priority);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < queries; j++) {
                Integer item = StdRandom.uniform(0, i * 1000);
                Double priority = StdRandom.uniform(0.0, 100.0);
                ahq.changePriority(item, priority);
            }
            ChangeTimes.add(sw.elapsedTime());
            ops.add(queries);
        }
        System.out.println("Timing Table for ArrayHeapMinPQ Change Priority");
        printTimingTable(numItems, ops, ChangeTimes);
    }


    private static void printTimingTable(List<Integer> numItems, List<Integer> ops, List<Double> times) {
        System.out.printf("%20s %20s %20s %20s\n", "No. of Items", "# ops", "Time (s)", "microsec/query");
        System.out.printf("--------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < numItems.size(); i += 1) {
            int N = numItems.get(i);
            int op = ops.get(i);
            double time = times.get(i);
            double timePerQuery = time / op * 1e6;
            System.out.printf("%20d % 20d %20.2f %20.2f\n", N, op, time, timePerQuery);
        }
    }

    public static void main(String[] args) {
        ArrayHeapMinPQTest ahqt = new ArrayHeapMinPQTest();
//        ahqt.testTimingAdd();
        ahqt.testTimingRemoveSmallest();
        ahqt.testTimingChangePriority();
    }

}
