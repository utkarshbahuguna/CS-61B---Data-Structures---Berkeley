import org.junit.Test;
import static org.junit.Assert.*;


/** Tests Array Deque implementations. */
public class TestArrayDequeGold {

    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correctDeque = new ArrayDequeSolution<>();

        StringBuilder operations = new StringBuilder();

        for (int i = 0; i < 2000; i++) {
            int picker = StdRandom.uniform(1, 7);

            switch (picker) {
                case 1:
                    Integer num1 = StdRandom.uniform(10, 20);
                    studentDeque.addFirst(num1);
                    correctDeque.addFirst(num1);
                    operations.append(String.format("\naddFirst(%d)", num1));
                    break;
                case 2:
                    Integer num2 = StdRandom.uniform(10, 20);
                    studentDeque.addLast(num2);
                    correctDeque.addLast(num2);
                    operations.append(String.format("\naddLast(%d)", num2));
                    break;
                case 3:
                    if (correctDeque.size() != 0) {
                        operations.append("\nremoveFirst()");
                        assertEquals(operations.toString(), correctDeque.removeFirst(), studentDeque.removeFirst());
                    }
                    break;
                case 4:
                    if (correctDeque.size() != 0) {
                        operations.append(String.format("\nremoveLast()"));
                        assertEquals(operations.toString(), correctDeque.removeLast(), studentDeque.removeLast());
                    }
                    break;
                case 5:
                    operations.append(String.format("\nisEmpty()"));
                    assertEquals(operations.toString(), correctDeque.isEmpty(), studentDeque.isEmpty());
                    break;
                case 6:
                    operations.append(String.format("\nsize()"));
                    assertEquals(operations.toString(), correctDeque.size(), studentDeque.size());
                    if (correctDeque.size() != 0) {
                        int index = StdRandom.uniform(0, correctDeque.size());
                        operations.append(String.format("\nget(%d)", index));
                        assertEquals(operations.toString(), correctDeque.get(index), studentDeque.get(index));
                        break;
                    }
            }
        }
    }
}