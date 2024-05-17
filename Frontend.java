import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Frontend implements FrontendInterface {
    private final Backend backend;
    private final Scanner sc;

    /**
     * Constructor for that accepts a reference to the backend
     * and a Scanner instance to read user input.
     *
     * @param backend The backend of the app.
     * @param sc      Scanner instance for user input.
     */
    public Frontend(Backend backend, Scanner sc) {
        this.backend = backend;
        this.sc = sc;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Backend backend = new Backend(new DijkstraGraph<>(new PlaceholderMap<>()));
        Scanner sc = new Scanner(System.in);
        Frontend frontend = new Frontend(backend, sc);
        frontend.mainLoop();
    }

    /**
     * A method to interactively loop prompting the user to select a command and input their data.
     */
    @Override
    public void mainLoop() throws FileNotFoundException {
        // while loop to keep prompting user for input
        while (true) {
            displayMainMenu();
            // Ask for user to input for a number from 1-4
            String input = sc.nextLine().toLowerCase().trim();

            if (input.equals("1") || input.equals("load data file")) {
                System.out.println("Please enter a valid filename: ");
                String filename = sc.nextLine();
                loadDataFile(filename);
            } else if (input.equals("2") || input.equals("find closest connection")) {
                System.out.println("Please enter the first participant name:");
                String participant1 = sc.nextLine();
                System.out.println("Please enter the second participant name:");
                String participant2 = sc.nextLine();
                findClosestConnection(participant1, participant2);
            } else if (input.equals("3")) {
                showStatistics();
            } else if (input.equals("4")) {
                // Exit the program, break out of the while loop, and end the program
                exitApp();
                break;
            } else {
                displayError();
            }
            returnMainMenu();
        }
    }

    /**
     * A method to display the main menu of the app.
     */
    @Override
    public void displayMainMenu() {
        System.out.println("Main Menu:\n1) Load Data File\n2) Find Closest Connection\n3) Show Statistics\n4) Exit\nPlease enter a number between 1 and 4:");
    }

    /**
     * A method to load a data file specified by the user.
     *
     * @param filename The name of the file to load.
     */
    @Override
    public void loadDataFile(String filename) throws FileNotFoundException {
        File file = new File(filename);

        while (!file.exists()) {
            System.out.println("File does not exist. Please provide a valid filename.");
            filename = sc.nextLine();
            file = new File(filename);
        }

        backend.readFile(filename);
        System.out.println("Loading data file from \"" + filename + "\"\n");
    }

    /**
     * A method to show statistics about the dataset, including the number of participants (nodes),
     * the number of edges (friendships), and the average number of friends.
     */
    @Override
    public void showStatistics() {
        System.out.println(backend.getDataSetStats() + "\n");
    }

    /**
     * A method to ask the user for two participants and list the closest connection between them,
     * including all intermediary friends.
     *
     * @param participant1 The first participant.
     * @param participant2 The second participant.
     */
    @Override
    public void findClosestConnection(String participant1, String participant2) {
        System.out.println("List of intermediary friends between \"" + participant1 + "\" and \"" + participant2 + "\": ");
        System.out.println(backend.getClosestConnection(participant1, participant2).getPath());
        System.out.println("Number of intermediary friends that connect \"" + participant1 + "\" and \"" + participant2 + "\": ");
        System.out.println(backend.getClosestConnection(participant1, participant2).getNumIntermediaryFriends() + "\n");
    }

    /**
     * A method to exit the app.
     */
    @Override
    public void exitApp() {
        System.out.println("Exiting Program...");
    }

    /**
     * A method to display an error message.
     */
    public void displayError() {
        System.out.println("Invalid input, please try again.");
    }

    /**
     * A method to display a message returning to the main menu
     */
    public void returnMainMenu() {
        System.out.println("Returning to the main menu\n");
    }

}
