package cs3500.pyramidsolitaire.model.hw02;

import cs3500.pyramidsolitaire.model.hw02.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The model for playing a game of Pyramid Solitaire with a standard deck of cards.
 *<p>
 *     Model used to maintain the state and enforce the rules of the pyramid solitaire game. The API for interfacing
 *     with this leverages the standard Builder Pattern for creation of a game.
 *</p>
 *
 * @author Brian Sauerborn
 * @version 1.0
 * @since 1.0
 * @see PyramidSolitaireModel
 * @see Builder
 */
public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {
    /**
     * The state of the 'triangle' or 'pyramid' that the game is played from.
     *
     * @since 1.0
     */
    private Pyramid<Card> pyramid;
    /**
     * The state of both the 'stock' and the 'draw', which are tightly coupled together into the object 'StockDraw'
     *
     * @since 1.0
     */
    private StockDraw<Card> stockDraw;
    /**
     * The state of the deck used to initially create the game. When a game is started, this deck is distributed to
     * both the 'pyramid' and the 'stockDraw'.
     *
     * @since 1.0
     */
    private List<Card> deck;

    /**
     * The number of rows in the game
     *
     * @since 1.0
     */
    private int numRows;

    /**
     * The current number of draw cards pulled from the 'stock'.
     *
     * @since 1.0
     */
    private int numDraw;

    /**
     * The state of the game.
     *
     * @since 1.0
     */
    private PyramidSolitaireGameState state;

    /**
     * Construct a new Pyramid Solitaire game model with all null fields
     * <P>
     *     NOTE: this constructor is kept to satisfy the HW01TypeChecks file and
     *     has no functional purpose
     * </P>
     */
    public BasicPyramidSolitaire() {}

    /**
     * Construct a new Pyramid Solitaire game model with the given parameters
     *
     * @param numRows the number of rows in this pyramid solitaire game
     * @param numDraw the number of cards from the stock that are turned face-up
     */
    private BasicPyramidSolitaire(List<Card> deck, int numRows, int numDraw) {
        this.pyramid = new Pyramid<>(numRows, deck);
        this.stockDraw = new StockDraw<>(numDraw, deck);
        this.deck = deck;
        this.numRows = numRows;
        this.numDraw = numDraw;
        this.state = PyramidSolitaireGameState.Idle;
    }

    // TODO
    @Override
    public List<Card> getDeck() { return this.deck; }

    // TODO
    @Override
    public int getNumRows() { return this.numRows; }

    // TODO
    @Override
    public int getNumDraw() { return this.numDraw; }

    // TODO
    @Override
    public int getRowWidth(int row) { return row; }

    // TODO
    @Override
    public List<Card> getDrawCards() { return new ArrayList<>(); }

    // TODO
    @Override
    public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw) {}

    // TODO
    @Override
    public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {}

    // TODO
    @Override
    public void remove(int row, int card) throws IllegalStateException {}

    // TODO
    @Override
    public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {}

    // TODO
    @Override
    public void discardDraw(int drawIndex) throws IllegalStateException {}

    // TODO
    @Override
    public Card getCardAt(int row, int card) throws IllegalStateException { return null; }

    // TODO
    @Override
    public boolean isGameOver() throws IllegalStateException { return false; }

    // TODO
    @Override
    public int getScore() throws IllegalStateException { return 0; }

    // TODO
    @Override
    public String toString() { return ""; }

    // TODO
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BasicPyramidSolitaire)) return false;
        BasicPyramidSolitaire that = (BasicPyramidSolitaire)obj;
        return this.deck.equals(that.deck)  &&
                this.numRows == that.numRows &&
                this.numDraw == that.numDraw;
    }

    // TODO
    @Override
    public int hashCode() { return Objects.hash(this.numRows, this.numDraw, this.deck); }
    
    /**
     * Constructs a builder for configuring and creating a game model instance. Defaults to a standard game with ...
     *
     * @return the new builder
     */
    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private final List<Card> deck;
        private final int numRows;
        private final int numDraw;

        /**
         * Constructs the PyramidSolitaire Builder with its DEFAULT values
         */
        public Builder() {
            this.deck = Util.generateDeck();
            this.numRows = 7;
            this.numDraw = 3;
        }

        /**
         * Constructs the PyramidSolitaire Builder with a set of given values
         *
         * @param deck the initial deck of cards
         * @param numRows the number of rows in the solitaire game
         * @param numDraw the number of face-up cards in the 'stock'
         */
        private Builder(List<Card> deck, int numRows, int numDraw) {
            this.deck = deck;
            this.numRows = numRows;
            this.numDraw = numDraw;
        }

        /**
         * Set the deck of Cards manually.
         *
         * @param deck the deck of cards
         * @return the updated Builder
         */
        public Builder deck(List<Card> deck) { return new Builder(deck, this.numRows, this.numDraw); }

        /**
         * Set the number of rows in the solitaire pyramid
         *
         * @param numRows the number of rows in the solitaire game
         * @return the updated Builder
         */
        public Builder numRows(int numRows) { return new Builder(this.deck, numRows, this.numDraw); }

        /**
         * Set the number of face-up cards from the "stock" draw deck
         *
         * @param numDraw the number of face-up cards
         * @return the updated Builder
         */
        public Builder numDraw(int numDraw) { return new Builder(this.deck, this.numRows, numDraw); }

        /**
         * Produce the specified {@link BasicPyramidSolitaire}.
         *
         * @return a new {@code BasicPyramidSolitaire}
         */
        public PyramidSolitaireModel<Card> build() { return new BasicPyramidSolitaire(this.deck, this.numRows, this.numDraw); }
    }

    public static final class Util {
        /**
         * Produce a deck of 52 card with all possible combinations of Card Types and Suits
         *
         * @return the deck of Cards
         */
        public static List<Card> generateDeck() {
            List<Card> deck = new ArrayList<Card>();
            for (Suit suit : Suit.values()) {
                for (CardType rank : CardType.values()) {
                    deck.add(new Card(rank, suit));
                }
            }
            return deck;
        }
    }

}

