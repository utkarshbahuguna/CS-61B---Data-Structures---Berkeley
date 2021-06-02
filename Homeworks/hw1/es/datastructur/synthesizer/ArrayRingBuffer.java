package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;

    /* Index for the next enqueue. */
    private int last;

    /* Variable for the fillCount. */
    private int count;

    /* Array for storing the buffer data. */
    private T[] rb;

    /** Returns the next circular index in array rb after i,
     * i.e., if i is the last index, return 0, else return i + 1.
     */
    private int plusOne(int i) {
        if (i == capacity() - 1) { return 0; }
        else { return i + 1; }
    }

    /** Returns the capacity of the buffer. */
    @Override
    public int capacity() {
        return rb.length;
    }

    /** Returns the number of item currently in the buffer. */
    @Override
    public int fillCount() {
        return count;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        count = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        count += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T f = rb[first];
        first = plusOne(first);
        count -= 1;
        return f;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    @Override
    public boolean equals(Object o){
        ArrayRingBuffer other = (ArrayRingBuffer<T>) o;
        if(!(this.getClass() == o.getClass() && this.fillCount() == other.fillCount())) {
            return false;
        }
        Iterator<T> itr1 = this.iterator();
        Iterator<T> itr2 = other.iterator();

        for(int i = 0; i < this.fillCount(); i++) {
            if (itr1.next() != itr2.next()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator<T>();
    }

    private class ArrayRingBufferIterator<T> implements Iterator<T> {
        private int f = first;

        @Override
        public boolean hasNext() {
            return f != last;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in BoundedQueue.");
            }
            T next = (T) rb[f];
            f = plusOne(f);
            return next;
        }
    }
}
