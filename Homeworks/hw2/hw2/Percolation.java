// https://fa20.datastructur.es/materials/hw/hw2/hw2

package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashMap;
import java.util.HashSet;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private int[] sites;
    private int N;
    private int openSites;
    boolean percolates;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) { throw new IllegalArgumentException("Grid N has to be positive."); }
        this.N = N;
        openSites = 0;
        grid = new WeightedQuickUnionUF(N * N);
        sites = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            sites[i] = -1;
        }
        percolates = false;
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
        return row * N + col;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(!valid(row, col)) { throw new IndexOutOfBoundsException(); }
        if (isOpen(row, col)) { return; }

        int self = linearAddress(row, col);

        if (row == 0) { sites[self] = 1;
        } else if (row == N -1) { sites[self] = 2;
        } else { sites[self] = 0; }

        openSites += 1;
        for (int i = -1; i <= 1; i = i+1) {
            for (int j = -1; j <= 1; j = j+1) {
                if (openNeighbor(row, col , i, j)) {
                    int selfComponent = grid.find(self);
                    int neighbor = linearAddress(row + i, col + j);
                    int neighborComponent = grid.find(neighbor);

                    grid.union(self, neighbor);
                    int newValue = (sites[selfComponent] == sites[neighborComponent]) ?
                                    sites[selfComponent] :
                                    Math.min(3, sites[selfComponent] + sites[neighborComponent]);

                    sites[grid.find(self)] = newValue;

                    if (newValue == 3) {
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
        return sites[linearAddress(row, col)] != -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if(!valid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int state = sites[grid.find(linearAddress(row, col))];
        return state == 1 || state == 3;
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
