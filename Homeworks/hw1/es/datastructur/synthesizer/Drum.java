package es.datastructur.synthesizer;

import edu.princeton.cs.algs4.StdRandom;

public class Drum {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.0; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Drum(double frequency) {
        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        for(int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0.0);
        }

    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while(!buffer.isEmpty()) {
            buffer.dequeue();
        }
        for(int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double front = buffer.dequeue();
        double newDouble = (front + buffer.peek()) / 2 * DECAY;
        if(StdRandom.uniform() < 0.5) {
            newDouble = -newDouble;
        }

        buffer.enqueue(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}

