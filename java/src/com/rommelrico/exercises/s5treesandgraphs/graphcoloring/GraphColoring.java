package com.rommelrico.exercises.s5treesandgraphs.graphcoloring;

import java.util.HashSet;
import java.util.Set;

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
