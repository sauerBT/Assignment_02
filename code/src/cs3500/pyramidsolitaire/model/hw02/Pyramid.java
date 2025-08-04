package cs3500.pyramidsolitaire.model.hw02;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The pyramid used to represent the "triangle" in a game of pyramid solitaire: this maintains
 * the state, generates, and enforces the rules of the triangle.
 * @param <K>  the type of cards this model uses
 */
public class Pyramid<K>{
    private final Graph pyramid;

    public Pyramid(int numRows, List<K> deck) {
        this.pyramid = this.dealDeck(numRows, deck);
    }
    private Pyramid(Graph pyramid) {
        this.pyramid = pyramid;
    }

    /**
     * Produce a full Graph representing a game Pyramid.
     * *<p>
     *     INVARIANTS:
     *     1. The given deck must have at least as many cards required to fill a full pyramid (e.g. for three rows there must be at least 6 elements in the given deck)
     *     3. The given number of rows can not be zero or negative.
     *  *</p>
     *
     * @param deck A list of elements constituting the initial deck.
     * @return The Graph representing a game pyramid.
     */
    private static <K> Graph dealDeck(int numRows, List<K> deck) {
        return new Graph();
    }

    /**
     *
     * @param row
     * @param pos
     * @return
     */
    public K getCardAt(int row, int pos) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     *
     * @param ele
     * @return
     */
    public Pyramid<K> addElement(K ele) {
        return new Pyramid<>(0, new ArrayList<>());
    }

    /**
     *
     * @param rowNum
     * @param pos
     * @return
     */
    public Pyramid<K> removeElement(int rowNum, int pos) {
        return new Pyramid<>(0, new ArrayList<>());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pyramid<?>)) return false;
        Pyramid<?> that = (Pyramid<?>)obj;
        return this.pyramid.equals(that.pyramid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pyramid);
    }
}
