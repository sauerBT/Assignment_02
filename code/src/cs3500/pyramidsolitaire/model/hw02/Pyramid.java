package cs3500.pyramidsolitaire.model.hw02;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The pyramid used to represent the "triangle" in a game of pyramid solitaire: this maintains
 * the state, generates, and enforces the rules of the triangle.
 *
 * @param <K>  the type of cards this model uses
 */
public class Pyramid<K>{
    private final Graph<IPair<K>> pyramid;

    public Pyramid(int numRows, List<K> deck) {
        this.pyramid = dealDeck(numRows, deck);
    }
    private Pyramid(Graph<IPair<K>> pyramid) {
        this.pyramid = pyramid;
    }

    // TODO
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
     * @throws IllegalArgumentException if the deck is null or invalid,
     * or a full pyramid cannot be dealt with the given sizes
     */
    private static <K> Graph<IPair<K>> dealDeck(int numRows, List<K> deck) {
        if (deck == null || deck.isEmpty()) {
            throw new IllegalArgumentException("Given deck is invalid.");
        } else if (!isDeckDealable(numRows, deck.size())) {
            throw new IllegalArgumentException("Deck size is too small for the given number of rows");
        } else {
            List<IPair<K>> initAcc = new ArrayList<>(List.of(IPair.of(0, 0, deck.getFirst())));
            deck.removeFirst();
            List<IPair<K>> convDeck = Util.ListUtil.foldl(new CardToPair<>(), deck, initAcc);
            return  Util.ListUtil.foldl(new PairToGraph<>(), convDeck, new IPairGraphAcc<>(convDeck, new Graph<>())).g();
        }
    }

    /**
     * Determine the validity of the given deck size and row number.
     *
     * @param numRows The intended number of rows in the given pyramid.
     * @param sizeDeck The size of the given deck to be used to generate the pyramid
     * @return True if the given deck and number of rows are of valid size, otherwise false.
     */
    private static boolean isDeckDealable(int numRows, int sizeDeck) {
        return ((sizeDeck - Util.sumUp(numRows)) >= 0) &&
                (numRows > 0);
    }

    // TODO
    /**
     * Produce the width (number of elements) in the given row.
     *
     * @param row The given row.
     * @return The number of elements in the given row.
     * @throws IllegalArgumentException The given row is invalid.
     * @throws IllegalStateException The game has not started.
     */
    public int getRowWidth(int row) {
        if (!isRowValid(row)) {
            throw new IllegalArgumentException("Requested row is not valid.");
        } else {
            return 0;
        }
    }

    // TODO
    /**
     * Produce true if the given row is valid, otherwise false.
     *
     * @param row The given row.
     * @return True if the given row is valid.
     */
    private boolean isRowValid(int row) { return false; } // STUB

    /**
     * Produce the number of elements in this pyramid.
     *
     * @return The number of elements in this pyramid.
     */
    public int size() { return pyramid.getVertices().size(); }

    /**
     * Produce the card element at the given position and row.
     *
     * @param row The requested row.
     * @param pos The requested position.
     * @return The requested card element.
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

/**
 * A function class used to wrap elements in IPair
 *
 * @param <K>  the type of cards this function class uses
 */
class CardToPair<K> implements BiFunction<K, List<IPair<K>>, List<IPair<K>>> {
    public List<IPair<K>> apply(K c, List<IPair<K>> loi) {
        IPair<K> prev = loi.getLast();
        int prevPos = prev.position();
        int prevRowNum = prev.rowNum();
        if (prevPos == prevRowNum) {
            loi.addLast(IPair.of(0, prevRowNum + 1, c));
        } else {
            loi.addLast(IPair.of(prevPos + 1, prevRowNum, c));
        }
        return loi;
    }
}

/**
 * A bifunction class used to "fold" IPairs into vertices within a Graph.
 *
 * @param <K>  the type of cards this bifunction class uses
 */
class PairToGraph<K> implements BiFunction<IPair<K>, IPairGraphAcc<IPair<K>>, IPairGraphAcc<IPair<K>>> {
    public IPairGraphAcc<IPair<K>> apply(IPair<K> p, IPairGraphAcc<IPair<K>> acc) {
        Optional<IPair<K>> o1 = Util.ListUtil.findOne(new LeftNode<>(), acc.loi(), p);
        Optional<IPair<K>> o2 = Util.ListUtil.findOne(new RightNode<>(), acc.loi(), p);
        Graph<IPair<K>> result1 = o1.map(pair -> acc.g().addTriple(p, pair, GraphPred.Child))
                .orElse(acc.g());

        Graph<IPair<K>> result2 = o2.map(pair -> result1.addTriple(p, pair, GraphPred.Child))
                .orElse(result1);

//        Graph result = acc.g();
//        if (o1.isPresent()) { result = result.addTriple(p, o1.get(), GraphPred.Child); } // Last Step -- Generalize Graph / Vertices / Edge
//        if (o2.isPresent()) { result = result.addTriple(p, o2.get(), GraphPred.Child); } // Last Step -- Generalize Graph / Vertices / Edge
        return new IPairGraphAcc<>(acc.loi(), result2);
    }
}

class LeftNode<K> implements IPred2<IPair<K>> {
    public boolean apply(IPair<K> arg1, IPair<K> arg2) {
        return ((arg2.position() == arg1.position()) && (arg2.rowNum() == (arg1.rowNum() - 1)));
    }
}

class RightNode<K> implements IPred2<IPair<K>> {
    public boolean apply(IPair<K> arg1, IPair<K> arg2) {
        return ((arg2.position() == arg1.position() - 1) && (arg2.rowNum() == (arg1.rowNum() - 1)));
    }
}

class IPairGraphAcc<K> {
    private final List<K> loi;
    private final Graph<K> g;
    IPairGraphAcc(List<K> loi, Graph<K> g) {
        this.loi = loi;
        this.g = g;
    }
    public List<K> loi() { return this.loi; }
    public Graph<K> g() { return this.g; }
}