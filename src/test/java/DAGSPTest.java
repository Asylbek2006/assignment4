import graph.dagsp.ShortestPathDAG;
import graph.dagsp.LongestPathDAG;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGSPTest {

    @Test
    public void testShortestPathDAG() {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(Arrays.asList(1, 2));
        adjList.add(Arrays.asList(2));
        adjList.add(Arrays.asList());

        int[] nodeWeights = {0, 1, 1};
        int[] shortestPaths = ShortestPathDAG.shortestPath(3, adjList, nodeWeights, 0);

        assertEquals(0, shortestPaths[0]);
        assertEquals(1, shortestPaths[1]);
        assertEquals(1, shortestPaths[2]);
    }

    @Test
    public void testLongestPathDAG() {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(Arrays.asList(1, 2));
        adjList.add(Arrays.asList(2));
        adjList.add(Arrays.asList());

        int[] nodeWeights = {0, 1, 1};
        int[] longestPaths = LongestPathDAG.longestPath(3, adjList, nodeWeights, 0);

        assertEquals(0, longestPaths[0]);
        assertEquals(1, longestPaths[1]);
        assertEquals(2, longestPaths[2]);
    }
}