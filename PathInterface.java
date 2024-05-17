import java.util.List;

/**
 * This is an interface that stores the results of the shortest path search.
 */
public interface PathInterface {

  /**
   * Getter method for the path.
   * @return list of intermediary friends
   */
  List<String> getPath();

  /**
   * Getter method for the number of friends that connect two participants.
   * @return number of intermediary friends
   */
  int getNumIntermediaryFriends();
}
