import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keySensor;
    private static Frame frame;
    private GUI gui;
    private static int thick;
    private Counter lives;
    private Counter score = new Counter();
    private HighScoresTable table;

    /**
     * Instantiates a new Game flow.
     *
     * @param runnerN    the runner n
     * @param keySensorN the key sensor n
     * @param guiN       the gui n
     * @param frameN     the frame n
     * @param thickN     the thick n
     * @param livesN     the lives n
     * @param tableN     the table n
     */
    public GameFlow(AnimationRunner runnerN, KeyboardSensor keySensorN,
                    GUI guiN, Frame frameN, int thickN, Counter livesN, HighScoresTable tableN) {
        runner = runnerN;
        keySensor = keySensorN;
        gui = guiN;
        frame = frameN;
        thick = thickN;
        lives = livesN;
        table = tableN;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Frame framGame = new Frame(800, 600);
        GUI guiGame = new GUI("Arkanoid", framGame.getMaxX(), framGame.getMaxY());
        AnimationRunner animationRunner = new AnimationRunner(guiGame, 60);
        KeyboardSensor keyboardSensor = guiGame.getKeyboardSensor();
        Counter livesCounter = new Counter();
        livesCounter.increase(7);
        HighScoresTable table = new HighScoresTable(3);

        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, guiGame, framGame, 20, livesCounter, table);

        List<LevelInformation> listLevelInfo = createLevels();

        ArrayList<Integer> levels = convertAndRemove(listLevelInfo.size());

        gameFlow.runLevels(listLevelInfo, levels);
    }

    /**
     * convertAndRemove convert string array into int list.
     *
     * @param numberLvl the maximum number
     * @return the int list
     */
    public static ArrayList<Integer> convertAndRemove(int numberLvl) {
        ArrayList<Integer> levels = new ArrayList<>();

        for (int i = 0; i < numberLvl; i++) {
            levels.add(i);
        }

        return levels;
    }

    /**
     * Run levels.
     *
     * @param levels     the levels
     * @param levelOrder the level order
     */
    public void runLevels(List<LevelInformation> levels, ArrayList<Integer> levelOrder) {

        for (int i = 0; i < levelOrder.size(); i++) {
            LevelInformation levelInfo = levels.get(levelOrder.get(i));
            ArrayList<Counter> counterArrayList = new ArrayList<>();
            counterArrayList.add(lives); //0
            counterArrayList.add(score); //1
            GameLevel level = new GameLevel(levelInfo, keySensor, runner, gui, frame, counterArrayList, thick);

            while (level.keepPlay()) {
                level.playOneTurn();
            }

            if (level.lives() == 0) {
                runner.run(new KeyPressStoppableAnimation(keySensor, keySensor.SPACE_KEY,
                        new GameOver(keySensor, score)));
                break;
            }
        }
        if (lives.getValue() > 0) {
            runner.run(new KeyPressStoppableAnimation(keySensor, keySensor.SPACE_KEY,
                    new GameWin(keySensor, score)));
        }

        if (table.getIndex(score.getValue()) < table.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "what is your name?", "");
            System.out.println(name);
            table.add(new ScoreInfo(name, score.getValue()));
        }

        runner.run(new KeyPressStoppableAnimation(keySensor, keySensor.SPACE_KEY,
                new HighScoresAnimation(table, keySensor)));
    }

    /**
     * createLevels create list of the levels.
     *
     * @return level info list
     */
    public static List<LevelInformation> createLevels() {
        List<LevelInformation> list = new ArrayList<>();

        //Direct Hit level
        ArrayList<Velocity> veloList1 = new ArrayList<>();
        veloList1.add(Velocity.fromAngleAndSpeed(0, 10));
        ArrayList<Block> blockList1 = new ArrayList<>();
        double x = frame.getMaxX() / 2;
        double y = frame.getMaxY() / 4;
        double width = thick;
        double height = thick;
        Rectangle rec = new Rectangle(new Point(x, y), width, height);
        Block block = new Block(rec, "1", "color(red)");
        blockList1.add(block);

        ArrayList<Integer> sizeList1 = new ArrayList<>();
        sizeList1.add(veloList1.size()); //0
        sizeList1.add(thick); //1
        sizeList1.add(15); //2
        sizeList1.add(150); //3
        sizeList1.add(1); //4
        LevelInformation directHit = new DirectHitLevel(frame, veloList1, "Direct Hit", blockList1, sizeList1);
        list.add(directHit);

        //Wide Easy
        ArrayList<Velocity> veloList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            veloList2.add(Velocity.fromAngleAndSpeed(180, 10));
        }
        ArrayList<Block> blocksList2 = getBlocksRow2();

        ArrayList<Integer> sizeList2 = new ArrayList<>();
        sizeList2.add(veloList2.size()); //0
        sizeList2.add(thick); //1
        sizeList2.add(15); //2
        sizeList2.add(500); //3
        sizeList2.add(blocksList2.size()); //4
        LevelInformation wideEasy = new WideEasy(frame, veloList2, "Wide Easy", blocksList2, sizeList2);
        //list.add(wideEasy);

        //Green 3
        ArrayList<Velocity> veloList3 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            veloList3.add(Velocity.fromAngleAndSpeed(180, 10));
        }
        ArrayList<Block> blocksList3 = getBlocksRow3();

        ArrayList<Integer> sizeList3 = new ArrayList<>();
        sizeList3.add(veloList3.size()); //0
        sizeList3.add(thick); //1
        sizeList3.add(15); //2
        sizeList3.add(150); //3
        sizeList3.add(blocksList3.size()); //4
        LevelInformation green3 = new Green3Level(frame, veloList3, "Green 3", blocksList3, sizeList3);
        //list.add(green3);

        //Final Four
        ArrayList<Velocity> veloList4 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            veloList4.add(Velocity.fromAngleAndSpeed(180, 10));
        }
        ArrayList<Block> blocksList4 = getBlocksRow4();

        ArrayList<Integer> sizeList4 = new ArrayList<>();
        sizeList4.add(veloList4.size()); //0
        sizeList4.add(thick); //1
        sizeList4.add(15); //2
        sizeList4.add(150); //3
        sizeList4.add(blocksList4.size()); //4
        LevelInformation finalFour = new FinalFour(frame, veloList4, "Green 3", blocksList4, sizeList4);
        //list.add(finalFour);

        return list;
    }

    /**
     * getRectanglesRow creates rows of rectangle by order.
     *
     * @return array of rectangle
     */
    public static ArrayList<Block> getBlocksRow2() {
        double height = 20;
        int num = 15;

        Point[] pxS = new Point[num];
        Rectangle[] recS = new Rectangle[num];
        ArrayList<Block> blocks = new ArrayList<>();
        int counterTemp = 1;

        double recWid = (frame.getMaxX() - thick - thick) / 15.0;
        for (int i = 0; i < num; i++) {
            pxS[i] = new Point(((i + (15 - num)) * recWid + thick), (frame.getMaxY() / 2));
            recS[i] = new Rectangle(pxS[i], recWid, height);
            Block block = new Block(recS[i], counterTemp, "color(yellow)");
            blocks.add(block);
        }

        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Color> tempColor;
        colors.add(Color.red);
        colors.add(Color.red);
        colors.add(Color.orange);
        colors.add(Color.orange);
        colors.add(Color.yellow);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.green);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.blue);
        colors.add(Color.pink);
        colors.add(Color.pink);
        colors.add(Color.cyan);
        colors.add(Color.cyan);

        for (int i = 0; i < colors.size(); i++) {
            tempColor = new ArrayList<>();
            tempColor.add(colors.get(i));
            ArrayList<String> colorsStr = new ArrayList<>();
            colorsStr.add("color(" + tempColor.toString() + ")");
            blocks.get(i).setColors(colorsStr);
        }

        return blocks;
    }

    /**
     * getBlocksRow3 create the blocks for the third level.
     *
     * @return block list
     */
    public static ArrayList<Block> getBlocksRow3() {
        int height = 20;

        Point[] pxS = new Point[105];
        Rectangle[] recS = new Rectangle[105];
        ArrayList<Block> blocks = new ArrayList<>();
        int counterTemp = 1;

        double recWid = (frame.getMaxX() - thick - thick) / 15.0;
        ArrayList<String> colors = new ArrayList<>();
        colors.add("color(gray)");
        colors.add("color(red)");
        colors.add("color(yellow)");
        colors.add("color(blue)");
        colors.add("color(white)");
        for (int j = 0; j < 5; j++) {
            //double x = frame.getMaxX() * (j + 6) / 15;
            double y = thick * (j + 6);
            for (int i = 0; i < (10 - j); i++) {
                pxS[i] = new Point(((i + (15 - (10 - j))) * recWid + thick), y);
                recS[i] = new Rectangle(pxS[i], recWid, height);
                Block block = new Block(recS[i], counterTemp, colors.get(j));
                blocks.add(block);
            }
        }

        return blocks;
    }

    /**
     * getBlocksRow3 create the blocks for the third level.
     *
     * @return block list
     */
    public static ArrayList<Block> getBlocksRow4() {
        int height = 20;

        Point[] pxS = new Point[40];
        Rectangle[] recS = new Rectangle[40];
        ArrayList<Block> blocks = new ArrayList<>();
        int counterTemp = 1;

        double recWid = (frame.getMaxX() - thick - thick) / 15.0;
        ArrayList<String> colors = new ArrayList<>();
        colors.add("color(gray)");
        colors.add("color(red");
        colors.add("color(yellow)");
        colors.add("color(green)");
        colors.add("color(white)");
        colors.add("color(pink)");
        colors.add("color(cyan)");
        for (int j = 0; j < 7; j++) {
            double x = thick;
            double y = thick * 4;
            for (int i = 0; i < 15; i++) {
                pxS[i] = new Point((x + i * recWid), y + (height * j));
                recS[i] = new Rectangle(pxS[i], recWid, height);
                Block block = new Block(recS[i], counterTemp, colors.get(j));
                blocks.add(block);
            }
        }

        return blocks;
    }
}