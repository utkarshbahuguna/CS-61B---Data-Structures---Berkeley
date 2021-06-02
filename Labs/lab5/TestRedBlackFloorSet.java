import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
       AListFloorSet afs = new AListFloorSet();
       RedBlackFloorSet rbfs = new RedBlackFloorSet();

       for(int i = 0; i < 1e6; i++) {
           double rand = StdRandom.uniform(-5000, 5001);
           afs.add(rand);
           rbfs.add(rand);
       }

       for(int i = 0; i < 1e5; i++) {
           double ask = StdRandom.uniform(-5001, 5002);
           assertEquals(afs.floor(ask), rbfs.floor(ask), 1e-6);
       }
    }
}
