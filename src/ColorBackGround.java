import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Color back ground.
 */
public class ColorBackGround implements Sprite {
    private ArrayList<Color> color;
    private int thick;

    /**
     * Instantiates a new Color back ground.
     *
     * @param coloeN the coloe n
     * @param thickN the thick n
     */
    public ColorBackGround(ArrayList<Color> coloeN, int thickN) {
        color = coloeN;
        thick = thickN;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param surface obj
     */
    public void drawOn(DrawSurface surface) {
        surface.fillRectangle(thick, (2 * thick), surface.getWidth(), surface.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param game environment
     */
    public void timePassed(GameEnvironment game) {
        return;
    }

    /**
     * add obj to the game.
     *
     * @param g game obj
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public ArrayList<Color> color() {

        return color;
    }
}
