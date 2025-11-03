package org.example;

import graph.scc.TarjanSCC;
import graph.topo.TopologicalSort;
import graph.dagsp.ShortestPathDAG;
import graph.dagsp.LongestPathDAG;
import graph.utils.Graph;
import graph.utils.GraphLoader;
import graph.utils.GraphUtils;
import graph.utils.CSVWriter;
import graph.utils.CSVWriter.GraphMetrics;
import graph.utils.CSVWriter.SCCDetails;
import graph.utils.CSVWriter.PathResult;

import java.util.*;

public class Main {
    private static List<GraphMetrics> allMetrics = new ArrayList<>();
    private static List<SCCDetails> allSCCDetails = new ArrayList<>();
    private static List<PathResult> allPathResults = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Smart City Scheduling Analysis ===");
        System.out.println();

        // Analyze all graph files
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

        // Save all results to CSV files
        saveAllResultsToCSV();

        System.out.println("=== Analysis Complete ===");
        System.out.println("CSV files generated in 'results/' folder");
    }

    public static void analyzeGraph(String filePath) {
        long startTime = System.currentTimeMillis();
        String graphName = filePath.replace("data/", "").replace(".json", "");

        System.out.println("📊 " + filePath + " analysis");
        System.out.println(createLine(50));

        // Load graph
        Graph graph = GraphLoader.loadGraph(filePath);
        if (graph == null) {
            System.out.println("❌ Graph has not loaded!");
            System.out.println();
            return;
        }

        // Graph properties
        System.out.println("📈 Graph Properties:");
        System.out.println("   - Number of vertices: " + graph.n);
        System.out.println("   - Number of edges: " + graph.edges.size());
        System.out.println("   - Directed: " + graph.directed);
        System.out.println("   - Source vertex: " + graph.source);
        System.out.println("   - Weight model: " + graph.weight_model);

        // Convert to adjacency list
        List<List<Integer>> adjList = GraphUtils.convertToAdjList(graph);
        int[] nodeWeights = GraphUtils.getNodeWeights(graph);

        // Print graph
        GraphUtils.printGraph(adjList);

        // SCC analysis
        System.out.println("\n🔍 SCC analysis:");
        List<List<Integer>> sccs = TarjanSCC.tarjanSCC(graph.n, adjList);
        System.out.println("   - SCC count: " + sccs.size());
        for (int i = 0; i < sccs.size(); i++) {
            System.out.println("   - SCC " + i + " (" + sccs.get(i).size() + " vertices): " + sccs.get(i));
            // Save SCC details for CSV
            allSCCDetails.add(new SCCDetails(graphName, i, sccs.get(i).size(), sccs.get(i)));
        }

        // Topological sort
        System.out.println("\n📋 Topo sorting:");
        boolean isDAG = GraphUtils.isDAG(adjList, graph.n);
        boolean topoSortPossible = false;

        if (isDAG) {
            try {
                List<Integer> topoOrder = TopologicalSort.topologicalSort(graph.n, adjList);
                System.out.println("   ✅ Topo order: " + topoOrder);
                topoSortPossible = true;

                // Shortest paths
                System.out.println("\n🛣️  Shortest paths from vertex " + graph.source + ":");
                int[] shortestPaths = ShortestPathDAG.shortestPath(graph.n, adjList, nodeWeights, graph.source);
                printPaths(shortestPaths, graph.source, "short");

                // Longest paths
                System.out.println("\n🏔️  Longest paths from vertex " + graph.source + ":");
                int[] longestPaths = LongestPathDAG.longestPath(graph.n, adjList, nodeWeights, graph.source);
                printPaths(longestPaths, graph.source, "long");

                // Save path results for CSV
                for (int i = 0; i < graph.n; i++) {
                    if (i != graph.source) {
                        allPathResults.add(new PathResult(
                                graphName, graph.source, i,
                                shortestPaths[i] == Integer.MAX_VALUE ? -1 : shortestPaths[i],
                                longestPaths[i] == Integer.MIN_VALUE ? -1 : longestPaths[i]
                        ));
                    }
                }

            } catch (Exception e) {
                System.out.println("   ❌ " + e.getMessage());
                topoSortPossible = false;
            }
        } else {
            System.out.println("   ❌ Graph has cycle! Topo sort is not possible.");
            topoSortPossible = false;
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Save graph metrics for CSV
        allMetrics.add(new GraphMetrics(
                graphName, graph.n, graph.edges.size(),
                sccs.size(), isDAG, topoSortPossible, executionTime
        ));

        System.out.println("\n" + createLine(50) + "\n");
    }

    // Path printing method
    private static void printPaths(int[] paths, int source, String type) {
        for (int i = 0; i < paths.length; i++) {
            if (i == source) {
                continue;
            }

            if (type.equals("short") && paths[i] == Integer.MAX_VALUE) {
                System.out.println("   - " + source + " → " + i + ": no path");
            } else if (type.equals("long") && paths[i] == Integer.MIN_VALUE) {
                System.out.println("   - " + source + " → " + i + ": no path");
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

    private static void saveAllResultsToCSV() {
        // Create results directory if it doesn't exist
        java.io.File resultsDir = new java.io.File("results");
        if (!resultsDir.exists()) {
            resultsDir.mkdir();
        }

        // Save all CSV files
        CSVWriter.saveGraphMetricsToCSV("results/graph_metrics.csv", allMetrics);
        CSVWriter.saveSCCDetailsToCSV("results/scc_details.csv", allSCCDetails);
        CSVWriter.savePathResultsToCSV("results/path_results.csv", allPathResults);
    }
}