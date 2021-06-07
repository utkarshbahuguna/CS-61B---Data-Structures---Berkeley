// Implemented after using hints provided.

package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation2 {
    private WeightedQuickUnionUF grid;
    private int[] sites;
    private int openSites, N, topLayer, bottomLayer;
    private boolean percolates;

    // create N-by-N grid, with all sites initially blocked
    public Percolation2(int N) {
        if (N <= 0) { throw new IllegalArgumentException("Grid N has to be positive."); }
        this.N = N;
        openSites = 0;
        grid = new WeightedQuickUnionUF(N * N + 2);
        sites = new int[N * N + 2];
        percolates = false;
        topLayer = 0;
        bottomLayer = N * N + 1;
    }

    // Returns true if x can be part of a valid co-ordinate in grid, else false.
    private boolean valid(int... x) {
        for (int i : x) {
            if (!(i >= 0 && i < N)) {
                return false;
            }
        }
        return true;
    }

    // Returns the address of a cell if the grid was placed with rows N by N in a one-dimensional array.
    private int linearAddress(int row, int col) {
        return row * N + col + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(!valid(row, col)) { throw new IndexOutOfBoundsException(); }
        if (isOpen(row, col)) { return; }

        int self = linearAddress(row, col);

        if (row == 0) { grid.union(self, topLayer);
        } else if (row == N - 1) { grid.union(self, bottomLayer); }

        sites[self] = 1;
        openSites += 1;

        for (int i = -1; i <= 1; i = i+1) {
            for (int j = -1; j <= 1; j = j+1) {
                if (openNeighbor(row, col , i, j)) {
                    int neighbor = linearAddress(row + i, col + j);

                    grid.union(self, neighbor);

                    if (grid.connected(self, topLayer) && grid.connected(self, bottomLayer)) {
                        percolates = true;
                    }
                }
            }
        }
    }

    // is the cell in row + i, col + j a valid open neighbor?
    private boolean openNeighbor(int row, int col, int i, int j) {
        return valid(row + i, col + j) && (i == 0 || j == 0)  && isOpen(row + i, col + j);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(!valid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return sites[linearAddress(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if(!valid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int self = linearAddress(row, col);
        return grid.connected(self, topLayer);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        p.open(0, 4);
        p.open(1, 4);
    }
}
