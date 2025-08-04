package model;

import cs3500.pyramidsolitaire.model.hw02.*;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilTest {
    // Example decks
    List<Card> D00;
    List<Card> D01;
    List<Card> D02;
    List<Card> D03;
    List<Card> D04;
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
    IPair<Card> P06;
    // Example Edges
    Edge E01;
    Edge E02;
    Edge E03;
    Edge E04;
    Edge E05;
    Edge E06;
    // Example Vertices
    Vertex V00;
    Vertex V001;
    Vertex V01;
    Vertex V02;
    Vertex V03;
    Vertex V04;
    Vertex V05;
    Vertex V06;
    // Example Graphs
    Graph G00;
    Graph G01;
    Graph G02;
    Graph G03;
    Graph G04;
    Graph G05;
    Graph G06;

    @Before
    public void setupTestFixture() {
        // Produce example decks
        D00 = new ArrayList<>();
        D01 = new ArrayList<>();
        D01.add(new Card(CardType.Queen, Suit.Heart));
        D02 = new ArrayList<>();
        D02.add(new Card(CardType.Ten, Suit.Spade));
        D02.add(new Card(CardType.Seven, Suit.Club));
        D03 = new ArrayList<>();
        D03.add(new Card(CardType.King, Suit.Heart));
        D03.add(new Card(CardType.Jack, Suit.Diamond));
        D03.add(new Card(CardType.Five, Suit.Club));
        D04 = new ArrayList<>();
        D04.addAll(D01);
        D04.addAll(D02);
        D04.addAll(D03);

        // Initialize Example Cards
        C01 = new Card(CardType.King, Suit.Heart);
        C02 = new Card(CardType.Queen, Suit.Spade);
        C03 = new Card(CardType.Jack, Suit.Diamond);
        C04 = new Card(CardType.Ace, Suit.Club);

        // Initialize Example Pairs
        P00 = IPair.empty();
        P01 = IPair.of(0, 0, C01);
        P02 = IPair.of(1, 1, C02);
        P03 = IPair.of(2, 1, C03);
        P04 = IPair.of(3, 2, C04);
        P05 = IPair.of(4, 2, new Card(CardType.Seven, Suit.Heart));
        P06 = IPair.of(5, 2, new Card(CardType.Six, Suit.Club));

        // Initialize Example Vertices
        // Empty
        V00 = new Vertex(P00);
        // Single Vertex
        V001 = new Vertex(P01);
        // Row 0
        V01 = new Vertex(P01);
        // Row 1
        V02 = new Vertex(P02);
        V03 = new Vertex(P03);
        // Row 2
        V04 = new Vertex(P04);
        V05 = new Vertex(P05);
        V06 = new Vertex(P06);

        // Initialize Example Edges
        // Row 0 --> Row 1
        V01.addEdge(GraphPred.Child, V02);
        E01 = V01.getEdges().getLast();
        V01.addEdge(GraphPred.Child, V03);
        E02 = V01.getEdges().getLast();
        // Row 1 --> Row 2
        V02.addEdge(GraphPred.Child, V04);
        E03 = V02.getEdges().getLast();
        V02.addEdge(GraphPred.Child, V05);
        E04 = V02.getEdges().getLast();
        V03.addEdge(GraphPred.Child, V05);
        E05 = V03.getEdges().getLast();
        V03.addEdge(GraphPred.Child, V06);
        E06 = V03.getEdges().getLast();

        // Initialize Example Graphs
        G00 = new Graph();
//        G01 = G00.addTriple(P01, P02, GraphPred.Child);
//        G02 = G01.addTriple(P01, P03, GraphPred.Child);
//        G03 = G02.addTriple(P02, P04, GraphPred.Child);
//        G04 = G03.addTriple(P02, P05, GraphPred.Child);
//        G05 = G04.addTriple(P03, P05, GraphPred.Child);
//        G06 = G05.addTriple(P03, P06, GraphPred.Child);
    }

    @Test
    public void utilGetFirstX() {
        assertEquals(new ArrayList<>(), Util.getFirstX(this.D04, 0));
        assertEquals(new ArrayList<>(List.of(new Card(CardType.Queen, Suit.Heart))), Util.getFirstX(this.D04, 1));
        assertEquals(new ArrayList<>(List.of(
                new Card(CardType.Queen, Suit.Heart),
                new Card(CardType.Ten, Suit.Spade),
                new Card(CardType.Seven, Suit.Club)
        )), Util.getFirstX(this.D04, 3));
    }

    @Test
    public void utilRemoveFirstX() {
        assertEquals(this.D04, Util.removeFirstX(this.D04, 0));
        List<Card> expected02 = Util.clone(this.D04);
        expected02.removeFirst();
        assertEquals(expected02, Util.removeFirstX(this.D04, 1));
        List<Card> expected03 = Util.clone(this.D04);
        expected03.removeFirst();
        expected03.removeFirst();
        expected03.removeFirst();
        assertEquals(expected03, Util.removeFirstX(this.D04, 3));
    }

    @Test
    public void utilContainsDuplicates() {
        IPred2<Card> pred2 = IPred2.equalsPred();
        assertFalse(Util.containsDuplicatesOf(pred2, D01, new Card(CardType.Queen, Suit.Heart)));
        D01.add(new Card(CardType.Queen, Suit.Heart));
        assertTrue(Util.containsDuplicatesOf(pred2, D01, new Card(CardType.Queen, Suit.Heart)));
        assertFalse(Util.containsDuplicatesOf(pred2, D03, new Card(CardType.Five, Suit.Club)));
        D03.add(new Card(CardType.Five, Suit.Club));
        assertTrue(Util.containsDuplicatesOf(pred2, D03, new Card(CardType.Five, Suit.Club)));
        assertFalse(Util.containsDuplicatesOf(pred2, D03, new Card(CardType.Jack, Suit.Diamond)));
        D03.add(new Card(CardType.Jack, Suit.Diamond));
        assertTrue(Util.containsDuplicatesOf(pred2, D03, new Card(CardType.Jack, Suit.Diamond)));
    }

    @Test
    public void utilCount() {
        IPred2<Card> pred2 = IPred2.equalsPred();
        assertEquals(1, Util.count(pred2, D01, new Card(CardType.Queen, Suit.Heart)));
        D01.add(new Card(CardType.Queen, Suit.Heart));
        assertEquals(2, Util.count(pred2, D01, new Card(CardType.Queen, Suit.Heart)));
        assertEquals(1, Util.count(pred2, D03, new Card(CardType.Five, Suit.Club)));
        D03.add(new Card(CardType.Five, Suit.Club));
        assertEquals(2, Util.count(pred2, D03, new Card(CardType.Five, Suit.Club)));
        assertEquals(1, Util.count(pred2, D03, new Card(CardType.Jack, Suit.Diamond)));
        D03.add(new Card(CardType.Jack, Suit.Diamond));
        assertEquals(2, Util.count(pred2, D03, new Card(CardType.Jack, Suit.Diamond)));
        assertEquals(0, Util.count(pred2, D03, new Card(CardType.Two, Suit.Diamond)));
    }

    @Test
    public void utilContains() {
        assertTrue(Util.contains(IPred2.vertexSame(), new ArrayList<>(List.of(V00)), V00)); // Empty test - vertexSame
        assertFalse(Util.contains(IPred2.vertexSame(), new ArrayList<>(), V00)); // Empty test - vertexSame

        assertFalse(Util.contains(IPred2.VertexDiff(), new ArrayList<>(List.of(V00)), V00)); // Empty test - vertexDiff
        assertFalse(Util.contains(IPred2.VertexDiff(), new ArrayList<>(), V00)); // Empty test - vertexDiff

        assertFalse(Util.contains(IPred2.VertexDiff(), new ArrayList<>(List.of(V01)), V01)); // Single - Equal test
        assertTrue(Util.contains(IPred2.VertexDiff(), new ArrayList<>(List.of(V01)), V02)); // Single - Non-Equal test

        assertTrue(Util.contains(IPred2.VertexDiff(), new ArrayList<>(List.of(V01, V02, V03)), V00)); // Multi - Non-Equal test
        assertTrue(Util.contains(IPred2.vertexSame(), new ArrayList<>(List.of(V01, V02, V03)), V01)); // Multi - Non-Equal test
        assertTrue(Util.contains(IPred2.vertexSame(), new ArrayList<>(List.of(V01, V02, V03)), V03)); // Multi - Non-Equal test
    }

    @Test
    public void utilCopyList() {
        List<Integer> LOI01 = new ArrayList<>(List.of());
        List<Integer> LOI02 = new ArrayList<>(List.of(0));
        List<Integer> LOI03 = new ArrayList<>(List.of(0,1,2));
        assertEquals(LOI01, Util.copy(LOI01));
        assertEquals(LOI02, Util.copy(LOI02));
        assertEquals(LOI03, Util.copy(LOI03));
    }

    @Test
    public void utilFindTest() {
        assertEquals(Optional.empty(), Util.findOne(IPred2.vertexSame(), new ArrayList<>(), V00)); // Empty
        assertEquals(Optional.of(V01), Util.findOne(IPred2.vertexSame(), new ArrayList<>(List.of(V01)), V01)); // Single
        assertEquals(Optional.empty(), Util.findOne(IPred2.vertexSame(), new ArrayList<>(List.of(V01)), V00)); // Single - No Find
        assertEquals(Optional.of(V03), Util.findOne(IPred2.vertexSame(), new ArrayList<>(List.of(V01, V02, V03)), V03)); // Multi
        assertEquals(Optional.empty(), Util.findOne(IPred2.vertexSame(), new ArrayList<>(List.of(V01, V02, V03)), V00)); // Multi - No Find
    }

    @Test
    public void utilfindIfExclude() {
        assertEquals(new ArrayList<>(), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(), new ArrayList<>())); // Empty test
        assertEquals(new ArrayList<>(List.of(V00)), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(List.of(V00)), new ArrayList<>())); // Empty test
        assertEquals(new ArrayList<>(), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(), new ArrayList<>(List.of(V00)))); // Empty test

        assertEquals(new ArrayList<>(), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(List.of(V01)), new ArrayList<>(List.of(V01)))); // Single - Equal test
        assertEquals(new ArrayList<>(List.of(V01)), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(List.of(V01)), new ArrayList<>(List.of(V02)))); // Single - Non-Equal test

        assertEquals(new ArrayList<>(List.of(V01, V02, V03)), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(List.of(V01, V02, V03)), new ArrayList<>(List.of(V00)))); // Multi - Non-Equal test
        assertEquals(new ArrayList<>(List.of(V02, V03)), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(List.of(V01, V02, V03)), new ArrayList<>(List.of(V01)))); // Multi - Non-Equal test
        assertEquals(new ArrayList<>(List.of(V01, V02)), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(List.of(V01, V02, V03)), new ArrayList<>(List.of(V03)))); // Multi - Non-Equal test
        assertEquals(new ArrayList<>(List.of(V02)), Util.findIfExclude(IPred2.vertexSame(), new ArrayList<>(List.of(V01, V02, V03)), new ArrayList<>(List.of(V01, V03)))); // Multi - Non-Equal test
    }

}