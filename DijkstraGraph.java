// --== CS400 File Header Information ==--
// Name: Jimmy He
// Email: she265@wisc.edu
// Group and Team: E06
// Group TA: Zheyang Xiong
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


//import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * Constructor that sets the map that the graph uses.
     *
     * @param map the map that the graph uses to map a data object to the node
     *            object it is stored in
     */
    public DijkstraGraph(MapADT<NodeType, Node> map) {
        super(map);
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is representing the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        if (start == null || end == null) {
            throw new NoSuchElementException("No path from start to end is found");
        }

        PriorityQueue<SearchNode> pq = new PriorityQueue<>();
        PlaceholderMap<NodeType, SearchNode> visited = new PlaceholderMap<>();

        SearchNode startNode = new SearchNode(nodes.get(start), 0, null);
        pq.add(startNode);

        while (!pq.isEmpty()) {
            SearchNode current = pq.remove();

            if (current.node.data.equals(end)) {
                return current;
            }

            if (!visited.containsKey(current.node.data)) {
                visited.put(current.node.data, current);

                for (Edge edge : current.node.edgesLeaving) {
                    Node successor = edge.successor;

                    if (!visited.containsKey(successor.data)) {
                        double newCost = current.cost + edge.data.doubleValue();
                        SearchNode next = new SearchNode(successor, newCost, current);
                        pq.add(next);
                    }
                }
            }
        }

        throw new NoSuchElementException("No path from start to end is found");
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        if (start == null || end == null) {
            throw new NoSuchElementException("No path from start to end is found");
        }

        SearchNode endNode = computeShortestPath(start, end);

        List<NodeType> pathData = new ArrayList<>();
        SearchNode current = endNode;

        while (current != null) {
            pathData.add(0, current.node.data);
            current = current.predecessor;
        }

        return pathData;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path from the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        if (start == null || end == null) {
            throw new NoSuchElementException("No path from start to end is found");
        }

        SearchNode endNode = computeShortestPath(start, end);

        if (endNode != null) {
            return endNode.cost;
        } else {
            throw new NoSuchElementException("No path from start to end is found");
        }
    }

//    /**
//     * This method checks the sequence of data along the shortest path between two nodes is correct
//     */
//    @Test
//    public void testShortestPathMatch() {
//        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
//
//        graph.insertNode("A");
//        graph.insertNode("B");
//        graph.insertNode("C");
//        graph.insertNode("D");
//        graph.insertNode("E");
//        graph.insertNode("F");
//
//        graph.insertEdge("A", "B", 1);
//        graph.insertEdge("B", "F", 6);
//        graph.insertEdge("A", "C", 2);
//        graph.insertEdge("C", "D", 2);
//        graph.insertEdge("D", "E", 2);
//        graph.insertEdge("E", "F", 2);
//
//        assertEquals(7, graph.shortestPathCost("A", "F"));
//
//    }
//
//    /**
//     * This method checks the cost and sequence of data along the shortest path between a different start and end node
//     */
////    @Test
//    public void testShortestPathCostAndSequence() {
//        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
//
//        graph.insertNode("A");
//        graph.insertNode("B");
//        graph.insertNode("C");
//        graph.insertNode("D");
//        graph.insertNode("E");
//        graph.insertNode("F");
//
//        graph.insertEdge("A", "B", 1);
//        graph.insertEdge("B", "F", 6);
//        graph.insertEdge("A", "C", 2);
//        graph.insertEdge("C", "D", 2);
//        graph.insertEdge("D", "E", 2);
//        graph.insertEdge("E", "F", 2);
//
//        double delta = 0.0001;
//        assertEquals(4, graph.shortestPathCost("A", "D"), delta);
//        assertEquals("[A, C, D]", graph.shortestPathData("A", "D").toString());
//
//
//    }
//
//    /**
//     * This method checks for a path between two nodes that do not exist in the graph
//     */
////    @Test
//    public void testNoPathBetweenNodes() {
//        DijkstraGraph<Integer, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
//        graph.insertNode(1);
//        graph.insertNode(6);
//        graph.insertNode(10);
//
//        assertThrows(NoSuchElementException.class, () -> graph.computeShortestPath(1, 10));
//    }

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in its node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referenced by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     * <p>
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

}
