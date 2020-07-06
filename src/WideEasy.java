import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    private Frame frame;
    private double thick;

    private int numBalls;
    private ArrayList<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String nameLevel;
    private ArrayList<Block> list;
    private Sprite backGround;
    private int numberBlocks;

    /**
     * Instantiates a new Wide easy.
     *
     * @param frameN      the frame n
     * @param velocitiesN the velocities n
     * @param nameLevelN  the name level n
     * @param listN       the list n
     * @param sizeList    list of sizes
     */
    public WideEasy(Frame frameN, ArrayList<Velocity> velocitiesN, String nameLevelN, ArrayList<Block> listN,
                    ArrayList<Integer> sizeList) {
        frame = frameN;
        thick = sizeList.get(1);

        numBalls = sizeList.get(0);
        velocities = velocitiesN;
        paddleSpeed = sizeList.get(2);
        paddleWidth = sizeList.get(3);
        nameLevel = nameLevelN;
        numberBlocks = sizeList.get(4);
        list = listN;
        backGround = new WideEasyBackGround(thick, frame);
    }

    @Override
    public int numberOfBalls() {
        return numBalls;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    // the level name will be displayed at the top of the screen.
    @Override
    public String levelName() {
        return nameLevel;
    }

    // Returns a sprite with the background of the level
    @Override
    public Sprite getBackground() {
        return backGround;
    }

    // The Blocks that make up this level, each block contains
    // its size, color and location.
    @Override
    public ArrayList<Block> blocks() {
        return list;
    }

    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    @Override
    public int numberOfBlocksToRemove() {
        return numberBlocks;
    }

    @Override
    public ArrayList<Color> color() {
        return backGround.color();
    }
}