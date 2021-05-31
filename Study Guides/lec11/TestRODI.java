// https://tbp.berkeley.edu/exams/6137/download/#page=8

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class TestRODI {

    @Test
    public void testRODI() {
        ReverseOddDigitIterator odi = new ReverseOddDigitIterator(12345770);
        List<Integer> reversedOddIntegers = IterableUtils.toList(odi);
        assertEquals(reversedOddIntegers, List.of(7, 7, 5, 3, 1));
    }
}
