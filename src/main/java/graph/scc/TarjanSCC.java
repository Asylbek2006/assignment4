package graph.scc;

import java.util.*;

public class TarjanSCC {

    public static List<List<Integer>> tarjanSCC(int vertices, List<List<Integer>> adjList) {
        List<List<Integer>> sccList = new ArrayList<>();
        int[] indices = new int[vertices];
        int[] lowlinks = new int[vertices];
        boolean[] onStack = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        int[] index = {0};

        Arrays.fill(indices, -1);

        for (int v = 0; v < vertices; v++) {
            if (indices[v] == -1) {
                strongConnect(v, adjList, indices, lowlinks, onStack, stack, index, sccList);
            }
        }
        return sccList;
    }

    private static void strongConnect(int v, List<List<Integer>> adjList,
                                      int[] indices, int[] lowlinks, boolean[] onStack,
                                      Stack<Integer> stack, int[] index, List<List<Integer>> sccList) {
        indices[v] = index[0];
        lowlinks[v] = index[0];
        index[0]++;
        stack.push(v);
        onStack[v] = true;

        for (int neighbor : adjList.get(v)) {
            if (indices[neighbor] == -1) {
                strongConnect(neighbor, adjList, indices, lowlinks, onStack, stack, index, sccList);
                lowlinks[v] = Math.min(lowlinks[v], lowlinks[neighbor]);
            } else if (onStack[neighbor]) {
                lowlinks[v] = Math.min(lowlinks[v], indices[neighbor]);
            }
        }

        if (lowlinks[v] == indices[v]) {
            List<Integer> scc = new ArrayList<>();
            int node;
            do {
                node = stack.pop();
                onStack[node] = false;
                scc.add(node);
            } while (node != v);
            sccList.add(scc);
        }
    }
}