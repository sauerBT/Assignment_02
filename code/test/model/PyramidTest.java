package model;

import cs3500.pyramidsolitaire.model.hw02.*;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PyramidTest {
    // Example decks
//    List<Card> D00;
//    List<Card> D01;
//    List<Card> D02;
//    List<Card> D03;
//    List<Card> D04;
//    // Example triangles
//    Pyramid<Card> P00; // Empty triangle
//    Pyramid<Card> P01; // Single row triangle
//    Pyramid<Card> P03; // Multi-row triangle
//
//    @Before
//    public void setupTestFixture() {
//        // Produce example decks
//        D00 = new ArrayList<>();
//        D01 = new ArrayList<>();
//        D01.add(new Card(CardType.Queen, Suit.Heart));
//        D02 = new ArrayList<>();
//        D02.add(new Card(CardType.Ten, Suit.Spade));
//        D02.add(new Card(CardType.Seven, Suit.Club));
//        D03 = new ArrayList<>();
//        D03.add(new Card(CardType.King, Suit.Heart));
//        D03.add(new Card(CardType.Jack, Suit.Diamond));
//        D03.add(new Card(CardType.Five, Suit.Club));
//        D04 = new ArrayList<>();
//        D04.addAll(D01);
//        D04.addAll(D02);
//        D04.addAll(D03);

        // Produce example triangles
//        P00 = new Pyramid<>(0, )
//        P01 =
//        P03 =
//    }

//    @Test
//    public void generatePyramidTest() {
//
//    }
//
//    @Test
//    public void hashCodeTest() {
//        assertEquals(P00.hashCode(), P00.hashCode());
//        assertEquals(P00.hashCode(), IPyramid.empty().hashCode());
//        assertNotEquals(P00.hashCode(), P01.hashCode());
//        assertEquals(P01.hashCode(),
//                IPyramid.pyramid(1,
//                        new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.empty()).hashCode());
//        assertNotEquals(P01.hashCode(),
//                IPyramid.pyramid(1,
//                        new ArrayList<>(List.of(new Card(CardType.King, Suit.Heart))),
//                        IPyramid.empty()).hashCode());
//        assertNotEquals(P01.hashCode(),
//                IPyramid.pyramid(2,
//                        new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.empty()).hashCode());
//        assertNotEquals(P01.hashCode(),
//                IPyramid.pyramid(1, new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.pyramid(2, new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))), IPyramid.empty())).hashCode());
//        assertEquals(P03.hashCode(),
//                IPyramid.pyramid(1,
//                        new ArrayList<>(List.of(
//                                new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.pyramid(2,
//                                new ArrayList<>(List.of(
//                                        new Card(CardType.Ten, Suit.Spade),
//                                        new Card(CardType.Seven, Suit.Club))),
//                                IPyramid.pyramid(3,
//                                        new ArrayList<>(List.of(
//                                                new Card(CardType.King, Suit.Heart),
//                                                new Card(CardType.Jack, Suit.Diamond),
//                                                new Card(CardType.Five, Suit.Club))),
//                                        IPyramid.empty()))).hashCode());
//
//    }
//
//    @Test
//    public void equalsTest() {
//        assertEquals(P00, P00);
//        assertEquals(P00, IPyramid.empty());
//        assertEquals(P01,
//                IPyramid.pyramid(1,
//                        new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.empty()));
//        assertNotEquals(P01,
//                IPyramid.pyramid(1,
//                        new ArrayList<>(List.of(new Card(CardType.King, Suit.Heart))),
//                        IPyramid.empty()));
//        assertNotEquals(P01,
//                IPyramid.pyramid(2,
//                        new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.empty()));
//        assertNotEquals(P01,
//                IPyramid.pyramid(1, new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.pyramid(2, new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))), IPyramid.empty())));
//        assertEquals(P03,
//                IPyramid.pyramid(1,
//                        new ArrayList<>(List.of(
//                                new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.pyramid(2,
//                                new ArrayList<>(List.of(
//                                        new Card(CardType.Ten, Suit.Spade),
//                                        new Card(CardType.Seven, Suit.Club))),
//                                IPyramid.pyramid(3,
//                                        new ArrayList<>(List.of(
//                                                new Card(CardType.King, Suit.Heart),
//                                                new Card(CardType.Jack, Suit.Diamond),
//                                                new Card(CardType.Five, Suit.Club))),
//                                        IPyramid.empty()))));
//        assertNotEquals(P03,
//                IPyramid.pyramid(1,
//                        new ArrayList<>(List.of(
//                                new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.pyramid(2,
//                                new ArrayList<>(List.of(
//                                        new Card(CardType.Ten, Suit.Spade),
//                                        new Card(CardType.Seven, Suit.Club))),
//                                IPyramid.pyramid(3,
//                                        new ArrayList<>(List.of(
//                                                new Card(CardType.King, Suit.Heart),
//                                                new Card(CardType.Jack, Suit.Diamond),
//                                                new Card(CardType.Four, Suit.Club))),
//                                        IPyramid.empty()))));
//        assertNotEquals(P03,
//                IPyramid.pyramid(1,
//                        new ArrayList<>(List.of(
//                                new Card(CardType.Queen, Suit.Heart))),
//                        IPyramid.pyramid(2,
//                                new ArrayList<>(List.of(
//                                        new Card(CardType.Ten, Suit.Spade),
//                                        new Card(CardType.Eight, Suit.Club))),
//                                IPyramid.pyramid(3,
//                                        new ArrayList<>(List.of(
//                                                new Card(CardType.King, Suit.Heart),
//                                                new Card(CardType.Jack, Suit.Diamond),
//                                                new Card(CardType.Five, Suit.Club))),
//                                        IPyramid.empty()))));
//    }

}
