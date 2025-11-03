package graph.topo;

import java.util.*;

public class TopologicalSort {
    public static List<Integer> topologicalSort(int vertices, List<List<Integer>> adjList) {
        int[] inDegree = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : adjList.get(node)) {
                if (--inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Егер барлық түйіндер сұрыпталмаса, цикл бар
        if (result.size() != vertices) {
            throw new RuntimeException("Graph contains cycle! Topological sort not possible.");
        }

        return result;
    }
}