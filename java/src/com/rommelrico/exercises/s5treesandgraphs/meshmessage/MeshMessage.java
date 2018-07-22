package com.rommelrico.exercises.s5treesandgraphs.meshmessage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Solution
 *
 * We treat the input user network as a graph in adjacency list format. Then we do a breadth-first search from the
 * sender, stopping once we reach the recipient.
 *
 * In order to recover the actual shortest path from the sender to the recipient, we do two things:
 * 1. during our breadth-first search, we keep track of how we reached each node, and
 * 2. after our breadth-first search reaches the end node, we use our hash map to backtrack from the recipient to the
 *    sender.
 *
 * To make sure our breadth-first search terminates, we're careful to avoid visiting any node twice. We could keep
 * track of the nodes we've already seen in a hash set, but, to save space, we reuse the hash map we've already set up
 * for recovering the path.
 *
 * Complexity
 *
 * Our solution has two main steps. First, we do a breadth-first search of the user network starting from the sender.
 * Then, we use the results of our search to backtrack and find the shortest path.
 *
 * How much work is a breadth-first search?
 *
 * In the worst case, we'll go through the BFS loop once for every node in the graph, since we only ever add each node
 * to nodesToVisit once (we check howWeReachedNodes to see if we've already added a node before). Each loop iteration
 * involves a constant amount of work to dequeue the node and check if it's our end node. If we have nnn nodes, then
 * this portion of the loop is O(N).
 *
 * But there's more to each loop iteration: we also look at the current node's neighbors. Over all of the nodes in the
 * graph, checking the neighbors is O(M), since it involves crossing each edge twice: once for each node at either end.
 *
 * Putting this together, the complexity of the breadth-first search is O(N+M).
 *
 * What about backtracking to determine the shortest path? Handling each node in the path is O(1), and we could have at
 * most N nodes in our shortest path. So, that's O(N) for building up the path. Then, it's another O(N) to reverse it.
 * So, the total time complexity of our backtracking step is O(N).
 *
 * Putting these together, the time complexity of our entire algorithm is O(N+M).
 *
 * What about space complexity? The queue of nodes to visit, the mapping of nodes to previous nodes, and the final
 * path... they all store a constant amount of information per node. So, each data structure could take up to O(N)
 * space if it stored information about all of our nodes. That means our overall space complexity is O(N).
 *
 * What We Learned
 *
 * The tricky part was backtracking to assemble the path we used to reach our endNode. In general, it's helpful to
 * think of backtracking as two steps:
 * 1. Figuring out what additional information we need to store in order to rebuild our path at the end
 *    (howWeReachedNodes, in this case).
 * 2. Figuring out how to reconstruct the path from that information.
 *
 * And in this case, something interesting happened after we added howWeReachedNodes - it made nodesAlreadySeen
 * redundant! So we were able to remove it. A good reminder to always look through your variables at the end and see
 * if there are any you can cut out.
 *
 */
public class MeshMessage {

    private static String[] reconstructPath(Map<String, String> previousNodes,
                                            String startNode, String endNode) {

        List<String> reversedShortestPath = new ArrayList<>();

        // start from the end of the path and work backwards
        String currentNode = endNode;

        while (currentNode != null) {
            reversedShortestPath.add(currentNode);
            currentNode = previousNodes.get(currentNode);
        }

        // reverse our path to get the right order
        // by flipping it around, in place
        Collections.reverse(reversedShortestPath);
        return reversedShortestPath.toArray(new String[reversedShortestPath.size()]);
    }

    public static String[] bfsGetPath(Map<String, String[]> graph, String startNode, String endNode) {

        if (!graph.containsKey(startNode)) {
            throw new IllegalArgumentException("Start node not in graph");
        }
        if (!graph.containsKey(endNode)) {
            throw new IllegalArgumentException("End node not in graph");
        }

        Queue<String> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(startNode);

        // keep track of how we got to each node
        // we'll use this to reconstruct the shortest path at the end
        // we'll ALSO use this to keep track of which nodes we've
        // already visited
        Map<String, String> howWeReachedNodes = new HashMap<>();
        howWeReachedNodes.put(startNode, null);

        while (!nodesToVisit.isEmpty()) {
            String currentNode = nodesToVisit.remove();

            // stop when we reach the end node
            if (currentNode.equals(endNode)) {
                return reconstructPath(howWeReachedNodes, startNode, endNode);
            }

            for (String neighbor : graph.get(currentNode)) {
                if (!howWeReachedNodes.containsKey(neighbor)) {
                    nodesToVisit.add(neighbor);
                    howWeReachedNodes.put(neighbor, currentNode);
                }
            }
        }

        // if we get here, then we never found the end node
        // so there's NO path from startNode to endNode
        return null;
    }

    public static void main(String[] args) {
        Map<String, String[]> network = new HashMap<String, String[]>() {{
            put("Min",     new String[] { "William", "Jayden", "Omar" });
            put("William", new String[] { "Min", "Noam" });
            put("Jayden",  new String[] { "Min", "Amelia", "Ren", "Noam" });
            put("Ren",     new String[] { "Jayden", "Omar" });
            put("Amelia",  new String[] { "Jayden", "Adam", "Miguel" });
            put("Adam",    new String[] { "Amelia", "Miguel", "Sofia", "Lucas" });
            put("Miguel",  new String[] { "Amelia", "Adam", "Liam", "Nathan" });
            put("Noam",    new String[] { "Nathan", "Jayden", "William" });
            put("Omar",    new String[] { "Ren", "Min", "Scott" });
        }};

        String[] path = bfsGetPath(network, "Jayden", "Adam");
        for (String node: path) {
            System.out.println("PATH: " + node);
        }
    }

}
