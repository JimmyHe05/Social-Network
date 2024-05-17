import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Backend implements BackendInterface<String, Integer> {

    GraphADT<String, Integer> dataStructure; // GraphADT implementation

    /**
     * Backend constructor that takes type DijkstraGraph
     */
    public Backend(DijkstraGraph<String, Integer> graph) {
        this.dataStructure = graph;
    }

    /**
     * Reads data from a file.
     *
     * @param file the file to read from.
     * @throws FileNotFoundException when the file cannot be read
     */
    @Override
    public void readFile(String file) throws FileNotFoundException {

        // First file scan to insert nodes into Graph
        Scanner scanner = new Scanner(new FileReader(file));
        String line;

        // Skip header line
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            line = scanner.nextLine().trim();

            // break if it doesn't contain the -- separator
            if (!line.contains("--")) {
                break;
            }
            // Example line: "A -- B"
            String[] parts = line.split("--");

            if (parts.length == 2) {
                // Extract source and target nodes
                // Replace quotations and semicolons 
                String source = parts[0].trim().replace("\"", "");
                String target = parts[1].trim().replace("\"", "").replace(";", "");

                int cost = 1;

                dataStructure.insertNode(source);
                dataStructure.insertNode(target);
                dataStructure.insertEdge(source, target, cost);
            }
        }
        scanner.close();
    }

    /**
     * Gets the shortest path of friends between two participants.
     *
     * @param participant1 the first participant.
     * @param participant2 the second participant.
     * @return the closest connection as a list of strings.
     */
    public ShortestPathResult getClosestConnection(String participant1, String participant2) {
        if (!dataStructure.containsNode(participant1) || !dataStructure.containsNode(participant2)) {
            System.out.println("One or both participants are not in the graph.");
            return new ShortestPathResult(new ArrayList<>(), -1);
        }

        List<String> friendList = dataStructure.shortestPathData(participant1, participant2);

        // Check if a valid path exists
        if (friendList.isEmpty() || friendList.size() == 1) {
            System.out.println("No valid path between " + participant1 + " and " + participant2);
            return new ShortestPathResult(new ArrayList<>(), -1);
        }

        // Remove the last element (participant2)
        friendList.remove(friendList.size() - 1);

        // Calculate the shortest path cost
        int shortestPathCost = (int) dataStructure.shortestPathCost(participant1, participant2) - 1;

        // Include both participants in the friendList
        friendList.add(participant2);

        return new ShortestPathResult(friendList, shortestPathCost);
    }


    /**
     * Gets a string with the number of nodes (participants), number of edges (friendships), and the
     * average number of friends of all the participants.
     *
     * @return a string with all the data.
     */
    public String getDataSetStats() {
        int numNodes = dataStructure.getNodeCount();
        int numEdges = dataStructure.getEdgeCount();
        int connections = 0;

        for (int i = 0; i < numNodes; i++) {
            String userI = "user" + i;
            if (dataStructure.containsNode(userI)) {
                for (int j = 0; j < numNodes; j++) {
                    String userJ = "user" + j;
                    if (dataStructure.containsNode(userJ)) {
                        // Check if a path exists before trying to access its data
                        if (dataStructure.containsEdge(userI, userJ)) {
                            List<String> pathData = dataStructure.shortestPathData(userI, userJ);
                            if (pathData != null && pathData.size() == 2) {
                                connections++;
                            }
                        }
                    }
                }
            }
        }

        double average = (numNodes > 0) ? (double) connections / numNodes : 0;

        return "Total number of users: " + numNodes + "\n" +
                "Total number of connections: " + numEdges + "\n" +
                "Average number of connections per user: " + average;

    }

}



