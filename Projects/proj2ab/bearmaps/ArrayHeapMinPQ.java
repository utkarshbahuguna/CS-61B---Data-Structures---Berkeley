package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> {

    private class PriorityNode<T> implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        public PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }
    }

    private class PriorityMinHeap {
        ArrayList<PriorityNode> items;      // Root of heap at index 1

        public PriorityMinHeap() {
            items = new ArrayList<>();
            items.add(null);
        }
        /** Swap items at index in1 and ind2. Also updates the node, index mapping in the nodeIndexmap. */
        private void swap(int ind1, int ind2) {
            PriorityNode item1 = items.get(ind1);
            PriorityNode item2 = items.get(ind2);
            items.set(ind1, item2);
            items.set(ind2, item1);
            nodeIndexMap.put((T) item1.getItem(), ind2);
            nodeIndexMap.put((T) item2.getItem(), ind1);
        }

        /** Swim the item up the heap until it is in its right position. */
        private void swim(int index) {
            int parent = parent(index);
            if (parent != -1 && items.get(index).compareTo(items.get(parent)) < 0) {
                    swap(parent, index);
                    swim(parent);
            }
        }

        /** Sink the item down the heap until it is in its right position. */
        private void sink(int ind) {
            PriorityNode currentNode = items.get(ind);
            if (leftChild(ind) != -1 && rightChild(ind) != -1) {
                PriorityNode leftNode = items.get(leftChild(ind));
                PriorityNode rightNode = items.get(rightChild(ind));
                if (leftNode.compareTo(currentNode) < 0 || rightNode.compareTo(currentNode) < 0) {
                    if (leftNode.compareTo(rightNode) < 0) {
                        swap(leftChild(ind), ind);
                        sink(leftChild(ind));
                    } else {
                        swap(rightChild(ind), ind);
                        sink(rightChild(ind));
                    }
                }
            } else if (leftChild(ind) != - 1) {
                    PriorityNode leftNode = items.get(leftChild(ind));
                    if (leftNode.compareTo(currentNode) < 0) {
                        swap(leftChild(ind), ind);
                        sink(leftChild(ind));
                    }
                }
            }

        /** Returns the index of the parent of the node at index ind if it exists, else returns -1. */
        private int parent(int ind) {
            return (ind / 2 >= 1) ? ind / 2 : -1;
        }

        /** Returns the index of the left child of the node at index ind if it exist, else returns -1. */
        private int leftChild(int ind) {
            return (2 * ind < items.size()) ? 2 * ind : -1;
        }

        /** Returns the index of the right child of the node at index ind if it exist, else returns -1. */
        private int rightChild(int ind) {
            return (2 * ind + 1 < items.size()) ? 2 * ind + 1 : -1;
        }

        public void add(PriorityNode p) {
            items.add(p);
            swim(size());
        }

        /** Returns the items with the least priority in the heap. */
        public PriorityNode<T> getSmallest() {
            return items.get(1);
        }

        /** Removes the item with the least priority in the heap and returns it. */
        public PriorityNode removeSmallest() {
            PriorityNode smallest = items.get(1);
            swap(1, size());
            items.remove(size());
            if (size() != 0) {
                sink(1);
            }
            return smallest;
        }

        /** Changes the priority of node at index ind. */
        public void changePriority(int ind, double priority) {
            items.get(ind).setPriority(priority);
            swim(ind);
            sink(ind);
        }

        public int size() {
            return items.size() - 1;
        }
    }

    private PriorityMinHeap minHeap = new PriorityMinHeap();
    private HashMap<T, Integer> nodeIndexMap = new HashMap<>();

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    void add(T item, double priority) {
        if (nodeIndexMap.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        nodeIndexMap.put(item, minHeap.size() + 1);
        minHeap.add(new PriorityNode<>(item, priority));
    }

    /* Returns true if the PQ contains the given item. */
    boolean contains(T item) {
        return nodeIndexMap.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return minHeap.getSmallest().getItem();
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        PriorityNode smallest = minHeap.removeSmallest();
        nodeIndexMap.remove(smallest.getItem());
        return (T) smallest.getItem();
    }

    /* Returns the number of items in the PQ. */
    int size() {
        return nodeIndexMap.size();
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    void changePriority(T item, double priority) {
        if(!nodeIndexMap.containsKey(item)) {
            throw new NoSuchElementException();
        }
        int ind = nodeIndexMap.get(item);
        minHeap.changePriority(ind, priority);
    }
}
