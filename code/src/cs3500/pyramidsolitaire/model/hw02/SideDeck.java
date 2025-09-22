package cs3500.pyramidsolitaire.model.hw02;

import java.util.List;

public interface SideDeck<K> {
    /**
     * Produce the draw pile.
     *
     * @return List of draw elements.
     */
    List<K> getDraw();

    /**
     * Returns the maximum number of visible cards in the draw pile,
     * or -1 if the game hasn't been started.
     *
     * @return the number of visible cards in the draw pile
     */
    int getNumDraw();

    /**
     * Produce the element from this draw pile at the given index.
     *
     * @param drawIndex The given draw index
     * @return The element.
     * @throws IllegalArgumentException When the given draw index is invalid.
     */
    K getDrawElement(int drawIndex);

    /**
     * Produce the next card in the "Stock".
     * <p>
     *     This method "exposes" the next card in the stock and transfers it to the "Draw".
     *     Note: This method creates a new SideDeck instance.
     * </p>
     * @return The updated SideDeck
     */
    SideDeck<K> turnOver();

    /**
     * Produce the number of cards in this side deck.
     *
     * @return The size of the side deck.
     */
    int size();

    /**
     * Produce a new SideDeck with the card at the given DrawIndex removed from the draw pile.
     *
     * @param drawIndex The index containing the card to remove from the draw pile
     * @return A new SideDeck
     * @throws IllegalArgumentException When the given index is invalid or the draw pile is empty.
     */
    SideDeck<K> discardDraw(int drawIndex);
}
