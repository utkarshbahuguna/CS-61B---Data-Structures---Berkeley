// https://fa20.datastructur.es/materials/discussion/disc05.pdf

import java.util.Iterator;

/** Queue of OHRequests. */
public class OHQueue implements Iterable<OHRequest> {
    private OHRequest first;

    public OHQueue(OHRequest firstInQueue) {
        first = firstInQueue;
    }

    public Iterator<OHRequest> iterator() {
        return new OHIteraor(first);
    }


    public static void main(String[] args) {
        OHRequest s5 = new OHRequest("I deleted all of my files", "Alex", null);
        OHRequest s4 = new OHRequest("conceptual: what is Java", "Omar", s5);
        OHRequest s3 = new OHRequest("git: I never did lab 1", "Connor", s4);
        OHRequest s2 = new OHRequest("help", "Hug", s3);
        OHRequest s1 = new OHRequest("no I haven't tried stepping through", "Itai", s2);

        OHQueue q = new OHQueue(s1);

        for (OHRequest request : q) {
            System.out.println(request.name);
        }
    }
}
