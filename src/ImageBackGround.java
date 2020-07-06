import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The type Image back ground.
 */
public class ImageBackGround implements Sprite {
    private Image image;
    private int thick;

    /**
     * Instantiates a new Image io.
     *
     * @param imageN the image ion
     * @param thickN the thick n
     */
    public ImageBackGround(Image imageN, int thickN) {
        image = imageN;
        thick = thickN;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param surface obj
     */
    public void drawOn(DrawSurface surface) {

        surface.drawImage(thick, (2 * thick), image);
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

    /**
     * Color color.
     *
     * @return the color
     */
    public ArrayList<Color> color() {

        return null;
    }
}
