// https://fa20.datastructur.es/materials/discussion/disc05.pdf

/** Represents a single office hour request. */
public class OHRequest {
    public String description;
    public String name;
    public OHRequest next;

    public OHRequest(String description, String name, OHRequest next) {
        this.description = description;
        this.name = name;
        this.next = next;
    }
}
