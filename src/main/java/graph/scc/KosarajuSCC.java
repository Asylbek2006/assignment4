package graph.scc;

import java.util.*;

public class KosarajuSCC {
    public static List<List<Integer>> kosarajuSCC(int vertices, List<List<Integer>> adjList) {
        List<List<Integer>> sccList = new ArrayList<>();
        List<List<Integer>> transposedGraph = transposeGraph(vertices, adjList);
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        // Бірінші DFS арқылы стекті толтыру
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                fillOrder(i, adjList, visited, stack);
            }
        }

        // Екінші DFS арқылы SCC-ларды табу
        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> scc = new ArrayList<>();
                dfs(node, transposedGraph, visited, scc);
                sccList.add(scc);
            }
        }
        return sccList;
    }

    private static void fillOrder(int node, List<List<Integer>> adjList, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                fillOrder(neighbor, adjList, visited, stack);
            }
        }
        stack.push(node);
    }

    private static void dfs(int node, List<List<Integer>> transposedGraph, boolean[] visited, List<Integer> scc) {
        visited[node] = true;
        scc.add(node);
        for (int neighbor : transposedGraph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, transposedGraph, visited, scc);
            }
        }
    }

    private static List<List<Integer>> transposeGraph(int vertices, List<List<Integer>> adjList) {
        List<List<Integer>> transposedGraph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            transposedGraph.add(new ArrayList<>());
        }
        for (int u = 0; u < vertices; u++) {
            for (int v : adjList.get(u)) {
                transposedGraph.get(v).add(u);
            }
        }
        return transposedGraph;
    }
}
