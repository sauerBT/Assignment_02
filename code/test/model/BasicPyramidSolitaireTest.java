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

    // TODO
    @Test
    public void builderConstructorGenerateDeckTest() {
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
    }

    // TODO -- do we need to account for the number of draw cards decreasing?
    @Test
    public void getNumDrawTest() {
        assertEquals(-1, PS00.getNumDraw());
        PS00.startGame(new DeckOfCards(52).toList(), false, 7, 2);
        assertEquals(2, PS00.getNumDraw());
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

    // TODO
    @Test
    public void getRowWidthTest() {

    }
}
