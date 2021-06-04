import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testUF() {
        UnionFind wq = new UnionFind(8);
        assertFalse(wq.isConnected(1, 2));
        wq.connect(1, 2);
        wq.connect(2, 3);
        wq.connect(5, 4);
        wq.connect(7, 0);
        assertTrue(wq.isConnected(5, 4));
        assertTrue(wq.isConnected(1, 3));
        assertFalse(wq.isConnected(2, 4));
        wq.connect(2, 5);
        wq.connect(3, 7);
        assertTrue(wq.isConnected(2, 4));
        assertTrue(wq.isConnected(1, 7));
    }
}
