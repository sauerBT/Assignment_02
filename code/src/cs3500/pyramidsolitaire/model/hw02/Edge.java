package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;
// INVARIANT: And Edge cannot exist without a Vertex, but a Vertex can exist without an Edge.
public class Edge<K> {
    private GraphPred predicate;
    private Vertex<K> from, to;

    Edge(GraphPred predicate, Vertex<K> from, Vertex<K> to) {
        this.predicate = predicate;
        this.from = from;
        this.to = to;
    }

    public Edge<K> setTo(Vertex<K> to) { return new Edge<>(this.predicate, this.from, to); }

    public Edge<K> setFrom(Vertex<K> from) { return new Edge<>(this.predicate, from, this.to); }

    public Edge<K> setPredicate(GraphPred predicate) { return new Edge<>(predicate, this.from, this.to); }

    public GraphPred getPredicate() { return this.predicate; }

    public Vertex<K> getFrom() { return this.from; }

    public Vertex<K> getTo() { return this.to; }

    // TODO
    @Override
    public String toString() { return this.from.toString() + "--" + this.predicate.toString() + "-->" + this.to.toString(); }

    public int hashCode() {
        return Objects.hash(predicate, from, to);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Edge)) return false;
        Edge<K> that = (Edge<K>)obj;
        return this.predicate.equals(that.predicate) &&
                this.to.equals(that.to) &&
                this.from.equals(that.from);
    }
}
