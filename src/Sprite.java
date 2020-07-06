import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param surface obj
     */
    void drawOn(DrawSurface surface);

    /**
     * notify the sprite that time has passed.
     *
     * @param game environment
     */
    void timePassed(GameEnvironment game);

    /**
     * add obj to the game.
     *
     * @param g game obj
     */
    void addToGame(GameLevel g);

    /**
     * Color color.
     *
     * @return the color
     */
    ArrayList<Color> color();
}