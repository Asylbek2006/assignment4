package metrics;

public class Metrics {
    public static long dfsTime = 0;
    public static long topoSortTime = 0;
    public static long dagSPTime = 0;
    public static int dfsVisits = 0;
    public static int edgeVisits = 0;

    public static void reset() {
        dfsTime = 0;
        topoSortTime = 0;
        dagSPTime = 0;
        dfsVisits = 0;
        edgeVisits = 0;
    }

    public static void logDfsTime(long time) {
        dfsTime += time;
    }

    public static void logTopoSortTime(long time) {
        topoSortTime += time;
    }

    public static void logDagSPTime(long time) {
        dagSPTime += time;
    }

    public static void logDfsVisit() {
        dfsVisits++;
    }

    public static void logEdgeVisit() {
        edgeVisits++;
    }
}
