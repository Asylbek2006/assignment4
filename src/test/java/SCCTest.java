import graph.scc.TarjanSCC;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SCCTest {

    @Test
    public void testTarjanSCC_CyclicGraph() {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(Arrays.asList(1));
        adjList.add(Arrays.asList(2));
        adjList.add(Arrays.asList(0));

        List<List<Integer>> sccs = TarjanSCC.tarjanSCC(3, adjList);

        assertEquals(1, sccs.size());
        assertTrue(sccs.get(0).containsAll(Arrays.asList(0, 1, 2)));
    }

    @Test
    public void testTarjanSCC_DAG() {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(Arrays.asList(1));
        adjList.add(Arrays.asList(2));
        adjList.add(Arrays.asList());

        List<List<Integer>> sccs = TarjanSCC.tarjanSCC(3, adjList);

        assertEquals(3, sccs.size());
    }
}