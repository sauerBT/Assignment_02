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
public class StockDraw<K> implements SideDeck {
    List<K> stock;
    List<K> draw;

    public StockDraw(int numDraw, List<K> deck) {
        this.stock = generateStock(deck, numDraw);
        this.draw = generateDraw(deck, numDraw);
    }

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
    private static <K> List<K> generateDraw(List<K> deck, int numDraw) {
        return new ArrayList<>();
    }

    /**
     * Produce the initial Stock for a game of pyramid solitaire.
     *
     * @param deck The initial deck provided by the game model.
     * @param numDraw The initial number of draw elements provided by the game model.
     * @return The Draw elements
     * @param <K> The type of elements in the deck.
     */
    private static <K> List<K> generateStock(List<K> deck, int numDraw) {
        return new ArrayList<>();
    }

    // TODO

    /**
     *
     * @return
     */
    public StockDraw<K> turnOver() {
        return new StockDraw<>(new ArrayList<>(), new ArrayList<>());
    }

}