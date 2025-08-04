package cs3500.pyramidsolitaire.model.hw02;

public interface SideDeck {
    /**
     * Produce the next card in the "Stock".
     * <p>
     *     This method "exposes" the next card in the stock and transfers it to the "Draw".
     *     Note: This method creates a new SideDeck instance.
     * </p>
     * @return The updated SideDeck
     */
    SideDeck turnOver();
}
