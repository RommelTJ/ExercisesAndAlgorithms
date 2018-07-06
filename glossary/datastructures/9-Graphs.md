# Graphs

## Quick Reference

A graph organizes items in an interconnected network.

Each item is a node (or vertex). Nodes are connected by edges. 
A graph is composed of nodes (or vertices) that are connected by edges.

**Strengths**:
1. Representing links. Graphs are ideal for cases where you're working with things that connect 
to other things. Nodes and edges could, for example, respectively represent cities and highways, 
routers and ethernet cables, or Facebook users and their friendships.

**Weaknesses**:
1. Scaling challenges. Most graph algorithms are O(n*lg(n)) or even slower. Depending on the size 
of your graph, running algorithms across your nodes may not be feasible.

