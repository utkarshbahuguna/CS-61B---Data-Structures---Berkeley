package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /** Returns size of the buffer. */
    int capacity();

    /** Returns number of items currently in the buffer. */
    int fillCount();

    /** Add item x to the end. */
    void enqueue(T x);

    /** Delete and return the item from the front. */
    T dequeue();

    /** Return (but do not delete) item from the front. */
    T peek();

    /** Returns true if buffer is empty, false otherwise. */
    default  boolean isEmpty() {
        return fillCount() == 0;
    }

    /** Returns ture if buffer is full, false otherwise. */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
