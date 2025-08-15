package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Invariant: Subject Vertices cannot reference themselves, and they also cannot reference Vertices that reference themselves
public class Graph {
    private final List<Vertex> vertices;

    public Graph() { this.vertices = new ArrayList<Vertex>(); }

    private Graph(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    /**
     * Produce a Graph with a new statement added.
     *
     * @param fromPair The given Subject data object.
     * @param toPair The given Object data object.
     * @param predicate The given statement "predicate" to define the relationship between Subject and Object.
     * @return A Graph with a new triple added.
     */
    public Graph addTriple(IPair<Card> fromPair, IPair<Card> toPair, GraphPred predicate) {
        Vertex tempObject = this.getIfContains(new Vertex(toPair)); // 1
        Vertex tempSubject = this.getIfContains(new Vertex(fromPair)); // 2
        tempSubject.addEdge(predicate, tempObject); // 3
        return
                new Graph(Util.ListUtil.findIfExclude(IPred2.vertexSame(), this.vertices, new ArrayList<>(List.of(tempSubject, tempObject))))
                        .addVertex(tempObject)
                        .addVertex(tempSubject);
    }

    /**
     * Query for an existing Vertex and produce it if it exists, otherwise produce the given Vertex.
     *
     * @param v The given Vertex to query for.
     * @return The search result for the given Vertex.
     */
    private Vertex getIfContains(Vertex v) { return Util.ListUtil.findOne(IPred2.vertexSame(), this.vertices, v).orElse(v); }

    /**
     * Produce a Graph with the given Vertex added to the list of vertices
     *
     * @param v The given vertex to add to the Graph.
     * @return A Graph with the new Vertex added.  This is a copy, not a reference.
     * @throws IllegalArgumentException If the Vertex already exists, throw an exception.
     */
    public Graph addVertex(Vertex v) {
        if (Util.ListUtil.contains(IPred2.vertexSame(), this.vertices, v)) {
            throw new IllegalArgumentException("Vertex " + v.toString() + " exists for " + this.vertices.toString());
        } else {
            List<Vertex> result = Util.ListUtil.copy(this.vertices);
            result.add(v);
            return new Graph(result);
        }
    }

    public List<Vertex> getVertices() { return this.vertices; }

    // TODO
    @Override
    public String toString() {
        return this.vertices.toString();
    }

    // TODO
    public int hashCode() { return Objects.hash(this.vertices); }

    // TODO
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Graph)) return false;
        Graph that = (Graph)obj;
        return this.vertices.equals(that.vertices);
    }

}