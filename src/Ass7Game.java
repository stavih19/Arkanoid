import java.io.FileNotFoundException;

/**
 * The type Ass 7 game.
 */
public class Ass7Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length <= 0) {
            String pathLevelSets = "resources\\level_sets.txt";
            args = new String[1];
            args[0] = pathLevelSets;
        }
        MainMenu.main(args);
    }
}