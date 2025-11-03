package graph.utils;

import java.util.List;
import java.util.ArrayList;

public class Graph {
    public boolean directed;
    public int n;
    public List<Edge> edges;
    public int source;
    public String weight_model;

    public static class Edge {
        public int u;
        public int v;
        public int w;

        public Edge() {}

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    public Graph() {
        this.edges = new ArrayList<>();
    }
}