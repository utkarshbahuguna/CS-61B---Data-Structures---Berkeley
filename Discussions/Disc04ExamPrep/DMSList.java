// https://fa20.datastructur.es/materials/discussion/examprep04.pdf

public class DMSList {
    private IntNode sentinel;
    public DMSList() {
        sentinel = new IntNode(-1000, new backNode());
    }

    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            return Math.max(item, next.max());
        }
    }

    public class backNode extends IntNode {
        public backNode() {
            super(-1000, null);
        }

        @Override
        public int max() {
            return 0;
        }
    }

    /* Returns 0 if list is empty. Otherwise, returns the max element. */
    public int max() {
        return sentinel.next.max();
    }
    public void insertFront(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
    }
}
