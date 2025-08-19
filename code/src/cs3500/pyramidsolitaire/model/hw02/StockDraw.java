package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * The representative object of the Stock for playing a game of Pyramid Solitaire with a standard deck of cards.
 *<p>
 *     Object used to maintain the state of the Stock and Draw, two sets of elements that are tightly coupled together
 *     in the game of Pyramid Solitaire.
 *</p>
 *
 * @author Brian Sauerborn
 * @version 1.0
 * @since 1.0
 */
public class StockDraw<K> implements SideDeck<K> {
    List<K> stock;
    List<K> draw;

    public StockDraw(List<K> deck, int numDraw) {
        if (!isLegalDraw(numDraw)) {
            throw new IllegalArgumentException("Invalid draw number.");
        } else if (!isLegalDeck(deck, numDraw)) {
            throw new IllegalArgumentException("Invalid deck and draw number combination.");
        } else {
            this.stock = generateStock(deck, numDraw);
            this.draw = generateDraw(deck, numDraw);
        }
    }

    // TODO
    private static boolean isLegalDraw(int numDraw) { return numDraw > 0; }

    // TODO
    private static <K> boolean isLegalDeck(List<K> deck, int numDraw) { return (deck.size() - numDraw) > 0; }

    private StockDraw(List<K> stock, List<K> draw) {
        this.stock = stock;
        this.draw = draw;
    }

    /**
     * Produce the initial Stock for a game of pyramid solitaire.
     *
     * @param deck The initial deck provided by the game model.
     * @param numDraw The initial number of draw elements provided by the game model.
     * @return The Stock elements.
     * @param <K> The type of elements in the deck.
     */
    private static <K> List<K> generateDraw(List<K> deck, int numDraw) { return Util.ListUtil.getFirstX(deck, numDraw); }

    /**
     * Produce the initial Stock for a game of pyramid solitaire.
     *
     * @param deck The initial deck provided by the game model.
     * @param numDraw The initial number of draw elements provided by the game model.
     * @return The Draw elements
     * @param <K> The type of elements in the deck.
     */
    private static <K> List<K> generateStock(List<K> deck, int numDraw) {
        return Util.ListUtil.removeFirstX(deck, numDraw);
    }

    // TODO
    @Override
    public StockDraw<K> turnOver() {
        return new StockDraw<>(new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public int size() { return stock.size() + draw.size(); }
}