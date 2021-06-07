// https://fa20.datastructur.es/materials/hw/hw2/hw2

package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

public class PercolationStats {
    private double[] thresholds;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.T = T;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Both N and T have to be positive.");
        }
        thresholds = new double[T];
        for(int i = 0; i < T; i++) {
            percolationExperiment(i, N, thresholds, pf);
        }
    }

    private void percolationExperiment(int i, int N, double[] thresholds, PercolationFactory pf) {
        Percolation surface = pf.make(N);
        while (!surface.percolates()) {
            int row, col;
            do {
                row = StdRandom.uniform(0, N);
                col = StdRandom.uniform(0, N);
            } while (surface.isOpen(StdRandom.uniform(0, N), StdRandom.uniform(0, N)));
            surface.open(row, col);
        }
        thresholds[i] = (double) (surface.numberOfOpenSites()) / (N * N);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(20, 1000, new PercolationFactory());
        System.out.println(ps.mean());
    }
}
