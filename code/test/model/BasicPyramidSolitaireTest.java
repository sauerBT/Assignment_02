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
        this.B01 = BasicPyramidSolitaire.builder();
        this.PS01 = B01.build();
    }

    @Test
    public void builderConstructorGenerateDeckTest() {
        System.out.println(PS01.getDeck());
        assertEquals(new Card(CardType.Two, Suit.Spade), PS01.getDeck().getFirst());
        assertEquals(new Card(CardType.Ace, Suit.Heart), PS01.getDeck().get(25));
        assertEquals(new Card(CardType.Ace, Suit.Club), PS01.getDeck().get(51));
    }

    @Test(expected = IllegalArgumentException.class)
    public void builderSetDeckEmpty() {
        PyramidSolitaireModel<Card> PS00 = new BasicPyramidSolitaire();
        PS00.startGame(new ArrayList<>(), false, 7, 2);
    }

    // Too small means...
    @Test(expected = IllegalArgumentException.class)
    public void builderSetDeckTooSmall() {
        PyramidSolitaireModel<Card> PS52 = new BasicPyramidSolitaire();
        PS52.startGame(new DeckOfCards(44).toList(), false, 9, 2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void builderSetNumOfRowsTooLarge() {
        PyramidSolitaireModel<Card> PS52 = new BasicPyramidSolitaire();
        PS52.startGame(new DeckOfCards(52).toList(), false, 10, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void builderSetNumOfRowsTooSmall01() {
        PyramidSolitaireModel<Card> PS52 = new BasicPyramidSolitaire();
        PS52.startGame(new DeckOfCards(52).toList(), false, 0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void builderSetNumOfRowsTooSmall02() {
        PyramidSolitaireModel<Card> PS52 = new BasicPyramidSolitaire();
        PS52.startGame(new DeckOfCards(52).toList(), false, -1, 2);
    }

    @Test
    public void builderConstructorTest() {
        assertEquals(7, PS01.getNumRows());
        assertEquals(3, PS01.getNumDraw());
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
