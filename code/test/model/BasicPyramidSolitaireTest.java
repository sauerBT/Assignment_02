package model;

import org.junit.*;
import cs3500.pyramidsolitaire.model.hw02.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class BasicPyramidSolitaireTest {
    // Example builders
    BasicPyramidSolitaire.Builder B01;
    // Example pyramid solitaire games
    PyramidSolitaireModel<Card> PS00;
    PyramidSolitaireModel<Card> PS01;
    @Before
    public void setupTestFixture() {
        this.PS00 = new BasicPyramidSolitaire();
        this.B01 = BasicPyramidSolitaire.builder();
        this.PS01 = B01.build();
    }

    @Test
    public void getDeckTest() {
        System.out.println(PS01.getDeck());
        assertEquals(new Card(CardType.Two, Suit.Spade), PS01.getDeck().getFirst());
        assertEquals(new Card(CardType.Ace, Suit.Heart), PS01.getDeck().get(25));
        assertEquals(new Card(CardType.Ace, Suit.Club), PS01.getDeck().get(51));
    }

    @Test(expected = IllegalArgumentException.class)
    public void startGameSetDeckEmpty() {
        PS00.startGame(new ArrayList<>(), false, 7, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startGameSetDeckTooSmall() {
        PS00.startGame(new DeckOfCards(44).toList(), false, 9, 2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void startGameSetNumOfRowsTooLarge() {
        PS00.startGame(new DeckOfCards(52).toList(), false, 10, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startGameSetNumOfRowsTooSmall01() {
        PS00.startGame(new DeckOfCards(52).toList(), false, 0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startGameSetNumOfRowsTooSmall02() {
        PS00.startGame(new DeckOfCards(52).toList(), false, -1, 2);
    }

    @Test
    public void startGameTest() {
        PS00.startGame(new DeckOfCards(52).toList(), false,7, 2);
        assertEquals(1, PS00.getRowWidth(0));
        assertEquals(4, PS00.getRowWidth(3));
        assertEquals(7, PS00.getRowWidth(6));
        assertEquals(new Card(CardType.Two, Suit.Spade), PS00.getCardAt(0, 0));
        assertEquals(new Card(CardType.Seven, Suit.Spade), PS00.getCardAt(6,6));
        assertEquals(new Card(CardType.Three, Suit.Club), PS00.getCardAt(3,1));
        assertFalse(PS00.isGameOver());
        assertEquals(7, PS00.getNumRows());
        assertEquals(2, PS00.getNumDraw());
    }

    @Test
    public void getNumRowsTest() {
        assertEquals(-1, PS00.getNumRows());
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        assertEquals(7, PS00.getNumRows());
        assertEquals(5, B01.numRows(5).build().getNumRows());
    }

    // TODO - Regular cases
    // 1. changing number of elements in rows from initial state (requires the remove function)
    // 2. Initial game state widths -- Good
    @Test
    public void getRowWidth() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        assertEquals(7, PS00.getRowWidth(6));
        assertEquals(1, PS00.getRowWidth(0));
        assertEquals(3, PS00.getRowWidth(2));
    }

    // TODO - Edge case:
    // 1. Illegal row request (Row either never existed, or no longer exists due to flow of game)
    @Test(expected = IllegalArgumentException.class)
    public void getRowWidthIllegalRowTest() {}

    // TODO - Edge case:
    // 1. The game has not yet been started.
    @Test(expected = IllegalStateException.class)
    public void getRowWidthGameNotStartedTest() {}

    // TODO - Regular cases:
    // 1. Game running -- Good
    // 2. Game ended - need to implement getScore and an ended game state first
    @Test
    public void isGameOverTest() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 3);
        assertFalse(PS00.isGameOver());
    }

    // TODO -- Regular cases
    // 1. Getting draw cards at the start of the game (where number draw cards == number initial draw cards) -- Good
    // 2. Getting draw cards during game flow when draw cards have been removed
    @Test
    public void getDrawCardsTest() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 3);
        assertEquals(new ArrayList<>(List.of(
                new Card(CardType.Seven, Suit.Heart),
                new Card(CardType.Seven, Suit.Diamond),
                new Card(CardType.Seven, Suit.Club))),
                PS00.getDrawCards());
    }

    // Edge Case:
    // Game has not yet started
    @Test(expected = IllegalStateException.class)
    public void getDrawCardsGameNotStartedTest01() { B01.build().getDrawCards(); }

    // Edge Case:
    // Game has not yet started
    @Test(expected = IllegalStateException.class)
    public void getDrawCardsGameNotStartedTest02() { new BasicPyramidSolitaire().getDrawCards(); }

    // Edge case:
    // 1. The game has not yet been started.
    @Test(expected = IllegalStateException.class)
    public void isGameOverGameNotStartedTest01() { B01.build().isGameOver(); }

    // Edge case:
    // 1. The game has not yet been started.
    @Test(expected = IllegalStateException.class)
    public void isGameOverGameNotStartedTest02() { new BasicPyramidSolitaire().isGameOver(); }

    // Regular Cases
    @Test
    public void removeTwoTest() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.remove(6, 0, 6, 6);
        PS00.remove(6, 1, 6, 5);
        PS00.remove(5, 0, 5, 5);
        PS00.remove(6, 2, 6, 4);
    }

    // Edge Case
    // Move is invalid, value != 13
    @Test
    public void removeTwoInvalidMoveTest01() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.remove(6, 0, 6, 1);
    }

    // Edge Case
    // Move is invalid, 1 cards partially covered
    @Test(expected = IllegalArgumentException.class)
    public void removeTwoInvalidMoveTest02() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.remove(6, 0, 4, 4);
    }

    // Edge Case
    // Move is invalid, both cards partially covered
    @Test(expected = IllegalArgumentException.class)
    public void removeTwoInvalidMoveTest03() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.remove(5, 5, 5, 0);
    }

    // Edge Case
    // Game not started
    @Test(expected = IllegalStateException.class)
    public void removeTwoGameNotStartedTest() {
        PS00 = new BasicPyramidSolitaire();
        PS00.remove(4, 1, 4, 2); // Card value allows removal but the card is covered
    }

    // Regular Cases
    @Test
    public void removeOneTest() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.remove(6, 3);
    }

    // Edge Case
    // Move is invalid, value != 13
    @Test(expected = IllegalArgumentException.class)
    public void removeOneInvalidMoveTest01() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.remove(7, 0);
    }

    // Edge Case
    // Move is invalid, card partially covered
    @Test(expected = IllegalArgumentException.class)
    public void removeOneInvalidMoveTest02() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.remove(4, 1); // Card value allows removal but the card is covered
    }

    // Edge Case
    // Game not started
    @Test(expected = IllegalStateException.class)
    public void removeOneGameNotStartedTest() {
        PS00 = new BasicPyramidSolitaire();
        PS00.remove(4, 1); // Card value allows removal but the card is covered
    }

    // Regular Cases
    @Test
    public void getCardAtTest() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        assertEquals(new Card(CardType.Ten, Suit.Heart), PS00.getCardAt(6, 0));
        assertEquals(new Card(CardType.Two, Suit.Spade), PS00.getCardAt(0, 0));
    }
    
    // Edge Case
    // Position does not exist
    @Test(expected = IllegalArgumentException.class)
    public void getCardAtInvalidPosTest01() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.getCardAt(6, 7);
    }

    // Edge Case
    // Position does not exist
    @Test(expected = IllegalArgumentException.class)
    public void getCardAtInvalidPosTest02() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.getCardAt(6, -1);
    }

    // Edge Case
    // Row does not exist
    @Test(expected = IllegalArgumentException.class)
    public void getCardAtInvalidRowTest01() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.getCardAt(7, 0);
    }

    // Edge Case
    // Row does not exist
    @Test(expected = IllegalArgumentException.class)
    public void getCardAtInvalidRowTest02() {
        PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        PS00.getCardAt(-1, 0);
    }

    // Edge Case
    // Game not started
    @Test(expected = IllegalArgumentException.class)
    public void getCardAtGameNotStartedTest() {
        PS00 = new BasicPyramidSolitaire();
        PS00.getCardAt(6, 0);
    }

    // TODO -- do we need to account for the number of draw cards decreasing?
    @Test
    public void getNumDrawTest() {
        assertEquals(-1, PS00.getNumDraw());
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        assertEquals(2, PS00.getNumDraw());
        assertEquals(3, B01.numDraw(3).build().getNumDraw());
    }

    @Test
    public void hashCodeTest() {
        List tempDeck = PS01.getDeck().reversed(); // Change the order of the Deck to invoke a different hash code
        assertEquals(Objects.hash(7, 3, PS01.getDeck()), PS01.hashCode());
        assertNotEquals(Objects.hash(7, 2, PS01.getDeck()), PS01.hashCode());
        assertNotEquals(Objects.hash(6, 3, PS01.getDeck()), PS01.hashCode());
        assertNotEquals(Objects.hash(7, 3, tempDeck), PS01.hashCode());
    }

    @Test
    public void equalsTest() {
        List<Card> expectedNotEqualDeck = BasicPyramidSolitaire.Util.generateDeck().reversed();
        BasicPyramidSolitaire.Builder builder = BasicPyramidSolitaire.builder();
        PyramidSolitaireModel<Card> expectedEqualsPS = builder.build();
        assertEquals(expectedEqualsPS, PS01);
        PyramidSolitaireModel<Card> expectedNotEqualsPS01 = builder.deck(expectedNotEqualDeck).build();
        assertNotEquals(expectedNotEqualsPS01, PS01);
        PyramidSolitaireModel<Card> expectedNotEqualsPS02 = builder.numRows(6).build();
        assertNotEquals(expectedNotEqualsPS02, PS01);
        PyramidSolitaireModel<Card> expectedNotEqualsPS03 = builder.numDraw(4).build();
        assertNotEquals(expectedNotEqualsPS03, PS01);
    }
    // TODO
    @Test
    public void builderTest() {}

}
