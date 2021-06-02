package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 *  @author Utkarsh Bahuguna
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        assertTrue(arb.isFull());
        assertEquals(1, arb.peek());
        assertEquals(1, arb.dequeue());
        arb.enqueue(5);
        assertEquals(2, arb.dequeue());

        ArrayRingBuffer arb2 = new ArrayRingBuffer(4);
        arb2.enqueue(1);
        arb2.enqueue(2);
        arb2.enqueue(3);
        Iterator<Integer> arb2t = arb2.iterator();
        assertEquals((Integer) 1, arb2t.next());
        assertEquals((Integer) 2, arb2t.next());
        assertEquals(arb2t.next(), arb.iterator().next());
        assertFalse(arb2.equals(arb));
        arb2.dequeue();
        arb2.dequeue();
        arb2.enqueue(4);
        arb2.enqueue(5);
        assertTrue(arb2.equals(arb));
    }
}
