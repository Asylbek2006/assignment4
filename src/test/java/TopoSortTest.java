import graph.topo.TopologicalSort;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopoSortTest {

    @Test
    public void testTopologicalSort_ValidDAG() {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(Arrays.asList(1, 2));
        adjList.add(Arrays.asList(3));
        adjList.add(Arrays.asList(3));
        adjList.add(Arrays.asList());

        List<Integer> result = TopologicalSort.topologicalSort(4, adjList);

        assertEquals(4, result.size());
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(0) < result.indexOf(2));
        assertTrue(result.indexOf(1) < result.indexOf(3));
        assertTrue(result.indexOf(2) < result.indexOf(3));
    }

    @Test
    public void testTopologicalSort_CyclicGraph() {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(Arrays.asList(1));
        adjList.add(Arrays.asList(2));
        adjList.add(Arrays.asList(0));

        try {
            TopologicalSort.topologicalSort(3, adjList);
            fail("Cycle болғанда қате болуы керек");
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }
}