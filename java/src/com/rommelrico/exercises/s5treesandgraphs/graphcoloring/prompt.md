### Graph Coloring

Given an undirected graph with maximum degree D, find a graph coloring using at most D+1 colors.

For example:
First described by Robert Frucht in 1939, the Frucht graph is a 3-regular graph with 12 
vertices, 18 edges, and no nontrivial symmetries.

This graph's maximum degree (D) is 3, so we have 4 colors (D+1). 

Graphs are represented by an array of N node objects, each with a label, a hash set of 
neighbors, and a color:

```
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GraphNode {

    private String label;
    private Set<GraphNode> neighbors;
    private Optional<String> color;

    public GraphNode(String label) {
        this.label = label;
        neighbors = new HashSet<GraphNode>();
        color = Optional.empty();
    }

    public String getLabel() {
        return label;
    }

    public Set<GraphNode> getNeighbors() {
        return Collections.unmodifiableSet(neighbors);
    }

    public void addNeighbor(GraphNode neighbor) {
        neighbors.add(neighbor);
    }

    public boolean hasColor() {
        return color.isPresent();
    }

    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color = Optional.ofNullable(color);
    }
}

GraphNode a = new GraphNode("a");
GraphNode b = new GraphNode("b");
GraphNode c = new GraphNode("c");

a.addNeighbor(b);
b.addNeighbor(a);
b.addNeighbor(c);
c.addNeighbor(b);

GraphNode[] graph = new GraphNode[] { a, b, c };
```

