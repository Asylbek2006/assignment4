package graph.utils;

import java.util.*;

public class GraphUtils {


    public static List<List<Integer>> convertToAdjList(Graph graph) {
        List<List<Integer>> adjList = new ArrayList<>();


        for (int i = 0; i < graph.n; i++) {
            adjList.add(new ArrayList<>());
        }


        for (Graph.Edge edge : graph.edges) {
            if (edge.u < graph.n && edge.v < graph.n) {
                adjList.get(edge.u).add(edge.v);
            }
        }

        return adjList;
    }


    public static int[] getNodeWeights(Graph graph) {
        int[] weights = new int[graph.n];


        for (int i = 0; i < weights.length; i++) {
            weights[i] = 1;
        }


        for (Graph.Edge edge : graph.edges) {
            if (edge.u < graph.n) {
                weights[edge.u] = edge.w;
            }
        }

        return weights;
    }


    public static boolean isDAG(List<List<Integer>> adjList, int vertices) {
        boolean[] visited = new boolean[vertices];
        boolean[] recursionStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, adjList, visited, recursionStack)) {
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean isCyclicUtil(int v, List<List<Integer>> adjList,
                                        boolean[] visited, boolean[] recursionStack) {
        if (recursionStack[v]) {
            return true;
        }

        if (visited[v]) {
            return false;
        }

        visited[v] = true;
        recursionStack[v] = true;

        for (int neighbor : adjList.get(v)) {
            if (isCyclicUtil(neighbor, adjList, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack[v] = false;
        return false;
    }


    public static void printGraph(List<List<Integer>> adjList) {
        System.out.println("Графтың көршілестік тізімі:");
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print("  " + i + " -> ");
            for (int neighbor : adjList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}