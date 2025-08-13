package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;

/**
 * Represents the Pyramid Solitaire "Pair" object
 *<p>
 *     The "Pair" objects defines the position, row number, and data object to represent a "pair" in a game of Solitaire.
 *</p>
 *
 * @author Brian Sauerborn
 * @version 1.0
 * @since 1.0
 */
public interface IPair<K> {
    /**
     * This produces a Pair.
     *
     * @param position The position of the Pair in the game of Solitaire.
     * @param rowNum The row of the Pair in the game of Solitaire.
     * @param c The data object of the Pair in the game of Solitaire.
     * @return The Pair object
     * @param <K> The Pair object defined in a game of Solitaire.
     */
    static <K> IPair<K> of(int position, int rowNum, K c) { return new Pair<>(position, rowNum, c); }

    int position();

    IPair<K> position(int pos);

    int rowNum();

    IPair<K> rowNum(int rowNum);

    K element();
}

class Pair<K> implements IPair<K> {
    /**
     * The position of the Pair in the game of Solitaire.
     *
     * @since 1.0
     */
    private final int position;

    /**
     * The row of the Pair in the game of Solitaire.
     *
     * @since 1.0
     */
    private final int rowNum;

    /**
     * The data object of the Pair in the game of Solitaire.
     *
     * @since 1.0
     */
    private final K c;

    protected Pair(int position, int rowNum, K c) {
        this.position = position;
        this.rowNum = rowNum;
        this.c = c;
    }

    @Override
    public int position() { return this.position; }

    @Override
    public IPair<K> position(int pos) { return new Pair<>(pos, this.rowNum, this.c); }

    @Override
    public int rowNum() { return this.rowNum; }

    @Override
    public IPair<K> rowNum(int rowNum) { return new Pair<>(this.position, rowNum, this.c); }

    @Override
    public K element() { return this.c; }

    @Override
    public String toString() { return "P" + this.position + "R" + this.rowNum + c.toString(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pair<?>)) return false;
        Pair<?> that = (Pair<?>)obj;
        return this.c.equals(that.c) &&
                this.position == that.position &&
                this.rowNum == that.rowNum;
    }

    @Override
    public int hashCode() { return Objects.hash(this.position, this.rowNum, this.c); }
}