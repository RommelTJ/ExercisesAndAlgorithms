package com.rommelrico.exercises.s5treesandgraphs.graphcoloring;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution
 *
 * We go through the nodes in one pass, assigning each node the first legal color we find.
 *
 * How can we be sure we'll always have at least one legal color for every node? In a graph with maximum degree D, each
 * node has at most D neighbors. That means there are at most D colors taken by a node's neighbors. And we have D+1
 * colors, so there's always at least one color left to use.
 *
 * When we color each node, we're careful to stop iterating over colors as soon as we find a legal color.
 *
 * Complexity
 *
 * O(N+M) time where N is the number of nodes and M is the number of edges.
 *
 * What about space complexity? The only thing we're storing is the illegalColors hash set. In the worst case, all the
 * neighbors of a node with the maximum degree (D) have different colors, so our hash set takes up O(D) space.
 *
 * What We Learned
 *
 * We used a greedy approach to build up a correct solution in one pass through the nodes.
 *
 * This brought us close to the optimal runtime, but we also had to take that last step of iterating over the colors
 * only until we find a legal color. Sometimes stopping a loop like that is just a premature optimization that doesn't
 * bring down the final runtime, but here it actually made our runtime linear!
 *
 */
public class GraphColoring {

    public static void colorGraph(GraphNode[] graph, String[] colors) {

        for (GraphNode node : graph) {
            Set<GraphNode> neighbors = node.getNeighbors();

            if (neighbors.contains(node)) {
                throw new IllegalArgumentException(String.format(
                        "Legal coloring impossible for node with loop: %s",
                        node.getLabel()));
            }

            // get the node's neighbors' colors, as a set so we
            // can check if a color is illegal in constant time
            Set<String> illegalColors = new HashSet<>();
            for (GraphNode neighbor : neighbors) {
                if (neighbor.hasColor()) {
                    illegalColors.add(neighbor.getColor());
                }
            }

            // assign the first legal color
            for (String color : colors) {
                if (!illegalColors.contains(color)) {
                    node.setColor(color);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");

        a.addNeighbor(b);
        b.addNeighbor(a);
        b.addNeighbor(c);
        c.addNeighbor(b);

        GraphNode[] graph = new GraphNode[] { a, b, c };
        String[] colors = {"red", "green", "blue", "yellow"};

        for (GraphNode node: graph) {
            System.out.println(node);
        }
        colorGraph(graph, colors);
        for (GraphNode node: graph) {
            System.out.println(node);
        }
    }

}
