package graph.dagsp;

import graph.topo.TopologicalSort;
import java.util.*;

public class ShortestPathDAG {

    public static int[] shortestPath(int vertices, List<List<Integer>> adjList, int[] nodeWeights, int source) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        List<Integer> topoOrder = TopologicalSort.topologicalSort(vertices, adjList);

        for (int node : topoOrder) {
            if (dist[node] != Integer.MAX_VALUE) {
                for (int neighbor : adjList.get(node)) {
                    int edgeWeight = 1; // Default weight
                    if (dist[node] + edgeWeight < dist[neighbor]) {
                        dist[neighbor] = dist[node] + edgeWeight;
                    }
                }
            }
        }
        return dist;
    }
}