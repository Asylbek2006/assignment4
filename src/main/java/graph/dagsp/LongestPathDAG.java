package graph.dagsp;

import graph.topo.TopologicalSort;
import java.util.*;

public class LongestPathDAG {

    public static int[] longestPath(int vertices, List<List<Integer>> adjList, int[] nodeWeights, int source) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[source] = 0;

        List<Integer> topoOrder = TopologicalSort.topologicalSort(vertices, adjList);

        for (int node : topoOrder) {
            if (dist[node] != Integer.MIN_VALUE) {
                for (int neighbor : adjList.get(node)) {
                    int edgeWeight = 1; // Default weight
                    if (dist[node] + edgeWeight > dist[neighbor]) {
                        dist[neighbor] = dist[node] + edgeWeight;
                    }
                }
            }
        }
        return dist;
    }
}