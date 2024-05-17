import java.io.FileNotFoundException;


/**
 * Backend interface providing functionalities for the Social tracker application.
 */
public interface BackendInterface<NodeType, EdgeType> {

  /**
   * Constructor method for BackendInterface taking an instance of GraphADT
   */
//  public BackendInterface(GraphADT graph);

  /**
   * Reads data from a file.
   * @param file the file to read from.
   */

  public void readFile(String file) throws FileNotFoundException;

  /**
   * Gets the shortest path of friends between two participants.
   * @param participant1 the first participant.
   * @param participant2 the second participant.
   * @return the closest connection as a list of strings.
   */
  PathInterface getClosestConnection(String participant1, String participant2) throws FileNotFoundException;


  /**
   * Gets a string with the number of nodes (participants), number of edges (friendships), and the
   * average number of friends of all the participants.
   * @return a string with all the data.
   */
  public String getDataSetStats() throws FileNotFoundException;


}
