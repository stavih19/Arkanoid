//import Block;
//import Sprite;
//import Velocity;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities array list.
     *
     * @return the array list
     */
// The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    ArrayList<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the string
     */
// the level name will be displayed at the top of the screen.
    String levelName();

    /**
     * Gets background.
     *
     * @return the background
     */
// Returns a sprite with the background of the level
    Sprite getBackground();

    /**
     * Blocks array list.
     *
     * @return the array list
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    ArrayList<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
// Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();

    /**
     * Color color.
     *
     * @return the color
     */
    ArrayList<Color> color();
}