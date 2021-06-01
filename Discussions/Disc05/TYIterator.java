// https://fa20.datastructur.es/materials/discussion/disc05.pdf

import java.util.Iterator;
import java.util.NoSuchElementException;

/** An iterator over OHRequest objects that only returns requests with good descriptions. */
public class TYIterator implements Iterator<OHRequest> {
    private OHRequest curr;

    public TYIterator(OHRequest firstInQueue) {
        curr = firstInQueue;
    }

    @Override
    public boolean hasNext() {
        if (curr == null) {
            return false;
        } else if (isGood(curr.description)) {
            return true;
        } else {
            curr = curr.next;
            return hasNext();
        }
    }

    @Override
    public OHRequest next() {
        if (curr == null) {
            throw new NoSuchElementException("No more Office Hours Requests.");
        } else {
            OHRequest temp = curr;
            if (curr.description.contains("thank u")) {
                curr = curr.next.next;
            } else {
                curr = curr.next;
            }
            return temp;
        }
    }

    public boolean isGood(String description) {
        return description != null && description.length() > 5;
    }
}
