import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {

        List<Integer> N = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        for (int i = 1; i <= 1024; i = i * 2) {
            AList<Integer> testList = new AList<>();

            int ops = i * 1000;
            N.add(ops);
            Stopwatch sw = new Stopwatch();
            for(int j = 0; j < ops; j++) {
                testList.addLast(j);
            }
            times.add(sw.elapsedTime());
            opCounts.add(ops);
        }

        printTimingTable(N, times, opCounts);
    }


}
