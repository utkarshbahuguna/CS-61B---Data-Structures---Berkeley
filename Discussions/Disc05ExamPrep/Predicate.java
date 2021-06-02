// https://fa20.datastructur.es/materials/discussion/examprep05.pdf

public interface Predicate<T> {
    /** Returns true if we want to keep x and false otherwise. */
    boolean test(T x);
}
