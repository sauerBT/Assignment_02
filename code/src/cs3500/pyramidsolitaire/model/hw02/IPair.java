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
     * This produces an "empty" Pair, which contains no data.
     * @return The empty pair
     * @param <K> The Pair object defined in a game of Solitaire.
     */
    public static <K> IPair<K> empty() { return MtPair.getInstance(); }

    /**
     * This produces a Pair.
     *
     * @param position The position of the Pair in the game of Solitaire.
     * @param rowNum The row of the Pair in the game of Solitaire.
     * @param c The data object of the Pair in the game of Solitaire.
     * @return The Pair object
     * @param <K> The Pair object defined in a game of Solitaire.
     */
    public static <K> IPair<K> of(int position, int rowNum, K c) { return new Pair<>(position, rowNum, c); }
}

class Pair<K> implements IPair<K> {
    /**
     * The position of the Pair in the game of Solitaire.
     *
     * @since 1.0
     */
    int position;

    /**
     * The row of the Pair in the game of Solitaire.
     *
     * @since 1.0
     */
    int rowNum;

    /**
     * The data object of the Pair in the game of Solitaire.
     *
     * @since 1.0
     */
    K c;

    protected Pair(int position, int rowNum, K c) {
        this.position = position;
        this.rowNum = rowNum;
        this.c = c;
    }

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

class MtPair<K> implements IPair<K> {
    private static final MtPair<?> INSTANCE = new MtPair<>();

    private MtPair() {}

    @SuppressWarnings("unchecked")
    public static <K> MtPair<K> getInstance() {
        return (MtPair<K>) INSTANCE;
    }

    @Override
    public String toString() { return "Empty"; }

    @Override
    public boolean equals(Object obj) { return (obj instanceof MtPair<?>); }

    @Override
    public int hashCode() { return 98; }
}