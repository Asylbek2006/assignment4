package graph.utils;

import java.io.*;

public class GraphLoader {

    public static Graph loadGraph(String filePath) {
        try {

            return parseSimpleJson(filePath);
        } catch (Exception e) {
            System.out.println("Қате: " + filePath + " файлын жүктеу мүмкін емес");
            System.out.println("Қате туралы хабар: " + e.getMessage());
            return createDefaultGraph();
        }
    }

    private static Graph parseSimpleJson(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder jsonContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }
        reader.close();

        String json = jsonContent.toString();
        Graph graph = new Graph();


        if (json.contains("\"n\":")) {
            String nStr = json.split("\"n\":")[1].split(",")[0].trim();
            graph.n = Integer.parseInt(nStr);
        }

        if (json.contains("\"directed\":")) {
            String directedStr = json.split("\"directed\":")[1].split(",")[0].trim();
            graph.directed = Boolean.parseBoolean(directedStr);
        }

        if (json.contains("\"source\":")) {
            String sourceStr = json.split("\"source\":")[1].split(",")[0].trim();
            graph.source = Integer.parseInt(sourceStr);
        }


        if (json.contains("\"edges\":")) {
            String edgesPart = json.split("\"edges\":\\[")[1].split("\\]")[0];
            String[] edgeStrings = edgesPart.split("\\},\\{");

            for (String edgeStr : edgeStrings) {
                edgeStr = edgeStr.replace("{", "").replace("}", "").trim();
                String[] parts = edgeStr.split(",");

                int u = 0, v = 0, w = 1;
                for (String part : parts) {
                    if (part.contains("\"u\":")) {
                        u = Integer.parseInt(part.split(":")[1].trim());
                    } else if (part.contains("\"v\":")) {
                        v = Integer.parseInt(part.split(":")[1].trim());
                    } else if (part.contains("\"w\":")) {
                        w = Integer.parseInt(part.split(":")[1].trim());
                    }
                }
                graph.edges.add(new Graph.Edge(u, v, w));
            }
        }

        return graph;
    }

    private static Graph createDefaultGraph() {

        Graph graph = new Graph();
        graph.directed = true;
        graph.n = 4;
        graph.source = 0;
        graph.weight_model = "edge";

        graph.edges.add(new Graph.Edge(0, 1, 1));
        graph.edges.add(new Graph.Edge(1, 2, 1));
        graph.edges.add(new Graph.Edge(2, 3, 1));

        return graph;
    }
}