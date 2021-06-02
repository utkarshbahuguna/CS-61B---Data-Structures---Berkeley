/**
 * TODO: Fill in the add and floor methods.
 */
public class AListFloorSet implements Lab5FloorSet {
    AList<Double> items;

    public AListFloorSet() {
        items = new AList<>();
    }

    public void add(double x) {
        if (contains(x)) {
            return;
        }
        items.addLast(x);
    }

    private boolean contains(double x) {
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i) == x) {
                return true;
            }
        }
        return false;
    }

    public double floor(double x) {
        double floored = Double.NEGATIVE_INFINITY;
        for(int i = 0; i < items.size(); i++) {
            double item = items.get(i);
            if(item > floored && item <= x) {
                floored = item;
            }
        }
        return floored;
    }

    public static void main(String[] args) {
        AListFloorSet afs = new AListFloorSet();
        afs.add(2.5);
        afs.add(10.0);
        afs.add(11.2);
        System.out.println(afs.floor(9));
        System.out.println(afs.floor(0));
    }
}
