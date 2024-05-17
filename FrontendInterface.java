//import java.util.Scanner;

import java.io.FileNotFoundException;

/**
 * An interface for the frontend of the social track app project.
 */
public interface FrontendInterface {

    /**
     * A method to interactively loop prompting the user to select a command and input their data.
     */
    public void mainLoop() throws FileNotFoundException;

    /**
     * A method to display the main menu of the app.
     *
     */
    public void displayMainMenu();


    /**
     * A method to load a data file specified by the user.
     *
     * @param filename The name of the file to load.
     */
    public void loadDataFile(String filename) throws FileNotFoundException;

    /**
     * A method to show statistics about the dataset, including the number of participants (nodes),
     * the number of edges (friendships), and the average number of friends.
     *
     */
    public void showStatistics() throws FileNotFoundException;

    /**
     * A method to ask the user for two participants and list the closest connection between them,
     * including all intermediary friends.
     *
     * @param participant1 The first participant.
     * @param participant2 The second participant.
     *
     */
    public void findClosestConnection(String participant1, String participant2) throws FileNotFoundException;

    /**
     * A method to exit the app.
     */
    public void exitApp();

    /**
     * A method to display an error message.
     */
    public void displayError();
}
