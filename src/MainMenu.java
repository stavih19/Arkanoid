import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 7 game.
 */
public class MainMenu {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Frame framGame = new Frame(800, 600);
        GUI guiGame = new GUI("Arkanoid", framGame.getMaxX(), framGame.getMaxY());
        KeyboardSensor keyboardSensor = guiGame.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(guiGame, 60);
        Counter lives = new Counter();
        lives.increase(7);
        int thick = 20;
        HighScoresTable table = new HighScoresTable(3);
        LevelSpecificationReader levels = new LevelSpecificationReader();
        String pathEasyLevels = linedLevelSets(args[0], "e");
        String pathHardLevels = linedLevelSets(args[0], "h");

        MenuAnimation<Task<Void>> mainMenu = new MenuAnimation<>("main menu", runner, keyboardSensor);
        MenuAnimation<Task<Void>> gameManu = new MenuAnimation<>("Hardness of levels", runner, keyboardSensor);
        gameManu.addSelection("e", "Easy", new Task<Void>() {
            @Override
            public Void run() {
                GameFlow gameFlow = new GameFlow(runner, keyboardSensor, guiGame, framGame, thick, lives, table);
                InputStream is;
                try {
                    is = new FileInputStream(pathEasyLevels);
                } catch (FileNotFoundException e) {
                    is = null;
                }
                //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(pathEasyLevels);
                List<LevelInformation> listLevelInfo = levels.fromReader(new InputStreamReader(is));
                ArrayList<Integer> levels = GameFlow.convertAndRemove(listLevelInfo.size());
                gameFlow.runLevels(listLevelInfo, levels);

                gameManu.reset();
                return null;
            }
        });
        gameManu.addSelection("h", "Hard", new Task<Void>() {
            @Override
            public Void run() {
                GameFlow gameFlow = new GameFlow(runner, keyboardSensor, guiGame, framGame, thick, lives, table);
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(pathHardLevels);
                List<LevelInformation> listLevelInfo = levels.fromReader(new InputStreamReader(is));
                ArrayList<Integer> levels = GameFlow.convertAndRemove(listLevelInfo.size());
                gameFlow.runLevels(listLevelInfo, levels);

                gameManu.reset();
                return null;
            }
        });
        mainMenu.addSubMenu("s", "Start game", gameManu);
        mainMenu.addSelection("h", "High score table", new Task<Void>() {
            @Override
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                        new HighScoresAnimation(table, keyboardSensor)));

                return null;
            }
        });
        mainMenu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                Runtime.getRuntime().exit(0);
                return null;
            }
        });

        while (true) {
            runner.run(mainMenu);
            Task<Void> task = (Task) mainMenu.getStatus();
            task.run();
            mainMenu.reset();
        }

    }

    /**
     * Lined level sets string.
     *
     * @param levelSetTxT the level set txt
     * @param p           the p
     * @return the string
     */
    public static String linedLevelSets(String levelSetTxT, String p) {
        String path = "";
        InputStream is;
        try {
            is = new FileInputStream(levelSetTxT);
        } catch (FileNotFoundException e) {
            return "";
        }
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetTxT);
        LineNumberReader readenLine = new LineNumberReader(new InputStreamReader(is));

        try {
            while (readenLine != null) {
                String line = readenLine.readLine();
                String[] keyVal = line.split(":");

                if (keyVal[0].equals(p)) {
                    line = readenLine.readLine();
                    path = line;
                    readenLine = null;
                }
                if (readenLine != null) {
                    line = readenLine.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Fail in read level set text");
        }

        return path;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    private static HighScoresTable getTable() {

        return HighScoresTable.loadFromFile(new File("highscores"));
    }
}