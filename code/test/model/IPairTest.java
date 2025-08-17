package model;
import org.junit.*;
import static org.junit.Assert.*;
import cs3500.pyramidsolitaire.model.hw02.*;

public class IPairTest {
    // Example Cards
    Card C01;
    Card C02;
    Card C03;
    Card C04;
    // Example Pairs
    IPair<Card> P00;
    IPair<Card> P01;
    IPair<Card> P02;
    IPair<Card> P03;
    IPair<Card> P04;
    IPair<Card> P05;

    @Before
    public void setupTestFixture() {
        // Initialize Example Cards
        C01 = new Card(CardType.King, Suit.Heart);
        C02 = new Card(CardType.Queen, Suit.Spade);
        C03 = new Card(CardType.Jack, Suit.Diamond);
        C04 = new Card(CardType.Ace, Suit.Club);

        // Initialize Example Pairs
        P01 = IPair.of(0, 0, C01);
        P02 = IPair.of(1, 1, C02);
        P03 = IPair.of(2, 1, C03);
        P04 = IPair.of(3, 2, C04);
        P05 = IPair.of(4, 2, new Card(CardType.Seven, Suit.Heart));
    }

    @Test
    public void toStringTest() {
        assertEquals("", P00.toString());
        assertEquals("K♥", P01.toString());
        assertEquals("Q♣", P02.toString());
        assertEquals("J♦", P03.toString());
        assertEquals("A♠", P04.toString());
        assertEquals("7♥", P05.toString());
    }

    @Test
    public void hashCodeTest() {
        assertEquals(IPair.of(0, 0, new Card(CardType.King, Suit.Heart)).hashCode(), P01.hashCode());
        assertNotEquals(IPair.of(0, 1, C02).hashCode(), P02.hashCode());
        assertNotEquals(IPair.of(1, 1, new Card(CardType.Jack, Suit.Spade)).hashCode(), P02.hashCode());
        assertNotEquals(IPair.of(1, 0, C02).hashCode(), P02.hashCode());
    }

    @Test
    public void equalsTest() {
        assertTrue(IPair.of(0, 0, new Card(CardType.King, Suit.Heart)).equals(P01));
        assertTrue(P01.equals(IPair.of(0, 0, new Card(CardType.King, Suit.Heart))));
        assertFalse(IPair.of(0, 1, C02).equals(P02));
        assertFalse(P02.equals(IPair.of(0, 1, C02)));
        assertFalse(IPair.of(1, 0, C02).equals(P02));
        assertFalse(P02.equals(IPair.of(1, 0, C02)));
        assertFalse(IPair.of(1, 1, new Card(CardType.Jack, Suit.Spade)).equals(P02));
        assertFalse(P02.equals(IPair.of(1, 1, new Card(CardType.Jack, Suit.Spade))));
        assertTrue(P01.equals(P01));
    }
}
