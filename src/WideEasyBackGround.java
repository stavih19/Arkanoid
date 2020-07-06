import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Wide easy back ground.
 */
public class WideEasyBackGround implements Sprite {
    private double thick;
    private Frame frame;
    private ArrayList<Color> colorBack;

    /**
     * Instantiates a new Wide easy back ground.
     *
     * @param thickN the thick n
     * @param frameN the frame n
     */
    public WideEasyBackGround(double thickN, Frame frameN) {
        thick = thickN;
        frame = frameN;
        colorBack = new ArrayList<>();
        colorBack.add(Color.white);
    }

    /**
     * draw the sprite to the screen.
     *
     * @param surface obj
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //draw blue rectangle
        double x = thick;
        double y = 2 * thick;
        double width = frame.getMaxX() - (2 * thick);
        double height = frame.getMaxY() - (2 * thick);

        surface.setColor(colorBack.get(0));
        surface.fillRectangle((int) x, (int) y, (int) width, (int) height);
        surface.drawRectangle((int) x, (int) y, (int) width, (int) height);

        //draw lines
        x = 160;
        y = x;
        double z;
        double t = frame.getMaxY() / 2;
        int numLine = 100;
        double dis = (frame.getMaxX() - 2 * thick) / numLine;
        surface.setColor(Color.orange);
        for (int i = 0; i < numLine; i++) {
            z = thick + dis * i;
            surface.drawLine((int) x, (int) y, (int) z, (int) t);
        }
        surface.setColor(Color.lightGray);
        surface.fillCircle((int) x, (int) y, 60);
        surface.setColor(Color.orange);
        surface.fillCircle((int) x, (int) y, 50);
        surface.setColor(Color.yellow);
        surface.fillCircle((int) x, (int) y, 40);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param game environment
     */
    @Override
    public void timePassed(GameEnvironment game) {
        return;
    }

    /**
     * add obj to the game.
     *
     * @param g game obj
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * color return the color of the level.
     *
     * @return Color
     */
    public ArrayList<Color> color() {
        return colorBack;
    }
}
