package graph.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {

    public static void saveGraphMetricsToCSV(String filename, List<GraphMetrics> metricsList) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write CSV header
            writer.write("GraphName,Vertices,Edges,SCC_Count,IsDAG,TopoSortPossible,ExecutionTimeMs\n");

            // Write data rows
            for (GraphMetrics metrics : metricsList) {
                writer.write(String.format("%s,%d,%d,%d,%s,%s,%d\n",
                        metrics.graphName,
                        metrics.vertices,
                        metrics.edges,
                        metrics.sccCount,
                        metrics.isDAG,
                        metrics.topoSortPossible,
                        metrics.executionTimeMs
                ));
            }

            System.out.println("Metrics saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }

    public static void saveSCCDetailsToCSV(String filename, List<SCCDetails> sccDetails) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("GraphName,SCC_ID,ComponentSize,Vertices\n");

            for (SCCDetails detail : sccDetails) {
                writer.write(String.format("%s,%d,%d,%s\n",
                        detail.graphName,
                        detail.sccId,
                        detail.componentSize,
                        detail.vertices.toString()
                ));
            }

            System.out.println("SCC details saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing SCC CSV: " + e.getMessage());
        }
    }

    public static void savePathResultsToCSV(String filename, List<PathResult> pathResults) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("GraphName,Source,Target,ShortestPath,LongestPath\n");

            for (PathResult result : pathResults) {
                writer.write(String.format("%s,%d,%d,%d,%d\n",
                        result.graphName,
                        result.source,
                        result.target,
                        result.shortestPath,
                        result.longestPath
                ));
            }

            System.out.println("Path results saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing paths CSV: " + e.getMessage());
        }
    }

    // Data classes for CSV
    public static class GraphMetrics {
        public String graphName;
        public int vertices;
        public int edges;
        public int sccCount;
        public boolean isDAG;
        public boolean topoSortPossible;
        public long executionTimeMs;

        public GraphMetrics(String graphName, int vertices, int edges, int sccCount,
                            boolean isDAG, boolean topoSortPossible, long executionTimeMs) {
            this.graphName = graphName;
            this.vertices = vertices;
            this.edges = edges;
            this.sccCount = sccCount;
            this.isDAG = isDAG;
            this.topoSortPossible = topoSortPossible;
            this.executionTimeMs = executionTimeMs;
        }
    }

    public static class SCCDetails {
        public String graphName;
        public int sccId;
        public int componentSize;
        public List<Integer> vertices;

        public SCCDetails(String graphName, int sccId, int componentSize, List<Integer> vertices) {
            this.graphName = graphName;
            this.sccId = sccId;
            this.componentSize = componentSize;
            this.vertices = vertices;
        }
    }

    public static class PathResult {
        public String graphName;
        public int source;
        public int target;
        public int shortestPath;
        public int longestPath;

        public PathResult(String graphName, int source, int target, int shortestPath, int longestPath) {
            this.graphName = graphName;
            this.source = source;
            this.target = target;
            this.shortestPath = shortestPath;
            this.longestPath = longestPath;
        }
    }
}