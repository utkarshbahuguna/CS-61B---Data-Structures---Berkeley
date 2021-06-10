package bearmaps;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet {

    private class Node {
        Point point;
        Node left;
        Node right;
        Comparator cmp;

        public Node(Point point, Node left, Node right, Comparator cmp) {
            this.point = point;
            this.left = left;
            this.right = right;
            this.cmp = cmp;
        }
    }

    private Node root;
    private int k;
    private Comparator[] comparators;

    /** Constructor for 2-dimensions. */
    public KDTree(List<Point> points) {
        this(points, 2, Point.xyComparators);
    }

    /** Generalized constructor for K-dimensions. Takes in an array of k comparators, one for each dimension. */
    public KDTree(List<Point> points, int k, Comparator[] comparators) {
        this.k = k;
        this.comparators = comparators;
        Collections.shuffle(points);        //Shuffle input to get more bushy tree.
        for(Point p: points) {
            root = insert(p, root, 0);
        }
    }

    /** Inserts point p in the KDTree rooted at node n and returns the root node. */
    private Node insert(Point p, Node n, int level) {
        if (n == null) {
            return new Node(p, null, null, comparators[level % k]);
        }
        if (n.equals(p)) {
            return n;
        } else if (n.cmp.compare(n.point, p) > 0) {
            n.left = insert(p, n.left, level + 1);
        } else {
            n.right = insert(p, n.right, level + 1);
        }
        return n;
    }

    @Override
    public Point nearest(double x, double y) {
        return nearestInSubtree(root, new Point(x, y), root.point);
    }

    /** Returns the nearest point to target in the tree rooted at n, iff it is closer than the currentBest, else returns currentBest. */
    private Point nearestInSubtree(Node n, Point target, Point currentBest) {
        if (n == null) { return currentBest; }
        if (n.point.equals(target)) { return target; }

        /** Check if this node is closer to target than currentBest. */
        if (Point.distance(n.point, target) < Point.distance(currentBest, target)) {
            currentBest = n.point;
        }

        // Define the good and the bad side to explore.
        Node goodSide, badSide;
        if (n.cmp.compare(n.point, target) < 0) { goodSide = n.right; badSide = n.left;
        } else { goodSide = n.left; badSide = n.right; }

        // Explore the good side for any points closer to target than the currentBest.
        currentBest = nearestInSubtree(goodSide, target, currentBest);

        // Define what the best point on the bad side could be.
        Point bestBadSidePoint;
        if (n.cmp.equals(Point.xyComparators[0])) { bestBadSidePoint = new Point(n.point.getX(), target.getY());
        } else { bestBadSidePoint = new Point(target.getX(), n.point.getY()); }

        // If the best point on bad side could be better than the currentBest, explore the bad side.
        if (Point.distance(bestBadSidePoint, target) < Point.distance(currentBest, target)) {
            currentBest = nearestInSubtree(badSide, target, currentBest);
        }

        return currentBest;
    }
}
