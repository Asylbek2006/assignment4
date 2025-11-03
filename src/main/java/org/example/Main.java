package org.example;

import graph.scc.TarjanSCC;
import graph.topo.TopologicalSort;
import graph.dagsp.ShortestPathDAG;
import graph.dagsp.LongestPathDAG;
import graph.utils.Graph;
import graph.utils.GraphLoader;
import graph.utils.GraphUtils;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Smart City Scheduling Analysis ===");
        System.out.println();

        // Барлық граф файлдарын талдау
        String[] graphFiles = {
                "data/small_graph1.json",
                "data/small_graph2.json",
                "data/medium_graph1.json",
                "data/medium_graph2.json",
                "data/large_graph1.json",
                "data/large_graph2.json"
        };

        for (String file : graphFiles) {
            analyzeGraph(file);
        }

        System.out.println("=== Analyse is end ===");
    }

    public static void analyzeGraph(String filePath) {
        System.out.println("📊 " + filePath + " analysis");
        System.out.println(createLine(50));

        // Графты жүктеу
        Graph graph = GraphLoader.loadGraph(filePath);
        if (graph == null) {
            System.out.println("❌ Graph has not loaded!");
            System.out.println();
            return;
        }

        // Графтың негізгі ақпараты
        System.out.println("📈 Graph Properties:");
        System.out.println("   - Number of vertices: " + graph.n);
        System.out.println("   - Number of edges: " + graph.edges.size());
        System.out.println("   - Directed: " + graph.directed);
        System.out.println("   - Source vertex: " + graph.source);
        System.out.println("   - Weight model: " + graph.weight_model);

        // Графты көршілестік тізіміне айналдыру
        List<List<Integer>> adjList = GraphUtils.convertToAdjList(graph);
        int[] nodeWeights = GraphUtils.getNodeWeights(graph);

        // Графты көрсету
        GraphUtils.printGraph(adjList);

        // SCC талдауы
        System.out.println("\n🔍 SCC analysis:");
        List<List<Integer>> sccs = TarjanSCC.tarjanSCC(graph.n, adjList);
        System.out.println("   - SCC count: " + sccs.size());
        for (int i = 0; i < sccs.size(); i++) {
            System.out.println("   - SCC " + i + " (" + sccs.get(i).size() + " түйін): " + sccs.get(i));
        }

        // Топологиялық сұрыптау
        System.out.println("\n📋 Topo sorting:");
        boolean isDAG = GraphUtils.isDAG(adjList, graph.n);

        if (isDAG) {
            try {
                List<Integer> topoOrder = TopologicalSort.topologicalSort(graph.n, adjList);
                System.out.println("   ✅ Topo order: " + topoOrder);

                // Ең қысқа жолдар
                System.out.println("\n🛣️  The shortest lines (" + graph.source + " түйінінен):");
                int[] shortestPaths = ShortestPathDAG.shortestPath(graph.n, adjList, nodeWeights, graph.source);
                printPaths(shortestPaths, graph.source, "short");

                // Ең ұзын жолдар
                System.out.println("\n🏔️  The longest lines (" + graph.source + " from edges):");
                int[] longestPaths = LongestPathDAG.longestPath(graph.n, adjList, nodeWeights, graph.source);
                printPaths(longestPaths, graph.source, "long");

            } catch (Exception e) {
                System.out.println("   ❌ " + e.getMessage());
            }
        } else {
            System.out.println("   ❌ Graph has cycle! Topo sort is not possible.");
        }

        System.out.println("\n" + createLine(50) + "\n");
    }

    // Жолдарды көрсету әдісі
    private static void printPaths(int[] paths, int source, String type) {
        for (int i = 0; i < paths.length; i++) {
            if (i == source) {
                continue; // Өзіне өзі жолды көрсетпеу
            }

            if (type.equals("short") && paths[i] == Integer.MAX_VALUE) {
                System.out.println("   - " + source + " → " + i + ": no row");
            } else if (type.equals("long") && paths[i] == Integer.MIN_VALUE) {
                System.out.println("   - " + source + " → " + i + ": no row");
            } else {
                System.out.println("   - " + source + " → " + i + ": " + paths[i]);
            }
        }
    }


    private static String createLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("=");
        }
        return line.toString();
    }
}