// https://fa20.datastructur.es/materials/discussion/disc04.pdf

import org.junit.Test;
import static org.junit.Assert.*;
import Disc04.IntList;

public class IntListTest {

    @Test
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.list(3, 2, 1);
        assertEquals(x, threeTwoOne);
    }

    @Test
    public void testdSquareList() {
        IntList L = IntList.list(1, 2, 3);
        IntList.dSquareList(L);
        assertEquals(L, IntList.list(1, 4, 9));
    }

}
