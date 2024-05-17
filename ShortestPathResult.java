import java.util.List;

public class ShortestPathResult implements PathInterface {

    private final List<String> path;
    private final int numIntermediaryFriends;

    public ShortestPathResult(List<String> path, int numIntermediaryFriends) {
        this.path = path;
        this.numIntermediaryFriends = numIntermediaryFriends;
    }

    @Override
    public List<String> getPath() {
        return path;
    }

    @Override
    public int getNumIntermediaryFriends() {
        return numIntermediaryFriends;
    }
}
