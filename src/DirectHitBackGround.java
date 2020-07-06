import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Direct hit back ground.
 */
public class DirectHitBackGround implements Sprite {
    private double thick;
    private Frame frame;
    private ArrayList<Color> colorBack;

    /**
     * Instantiates a new Direct hit back ground.
     *
     * @param thickN the thick n
     * @param frameN the frame n
     */
    public DirectHitBackGround(double thickN, Frame frameN) {
        thick = thickN;
        frame = frameN;
        colorBack = new ArrayList<>();
        colorBack.add(Color.black);
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
        double width = frame.getMaxX();
        double height = frame.getMaxY();

        surface.setColor(colorBack.get(0));
        surface.fillRectangle((int) x, (int) y, (int) width, (int) height);
        surface.drawRectangle((int) x, (int) y, (int) width, (int) height);

        //draw blue circles
        x = frame.getMaxX() / 2 + thick / 2;
        y = frame.getMaxY() / 4 + thick / 2;

        surface.setColor(Color.blue);
        surface.drawCircle((int) x, (int) y, 60);
        surface.drawCircle((int) x, (int) y, 90);
        surface.drawCircle((int) x, (int) y, 120);

        y -= thick / 2 + 10;
        surface.drawLine((int) x, (int) y, (int) x, (int) (thick * 2));
        y += (thick * 2);
        surface.drawLine((int) x, (int) y, (int) x, (int) (y + thick * 6));
        x += (thick / 2 + 5);
        y -= (thick / 2 + 10);
        surface.drawLine((int) x, (int) y, (int) (x + thick * 6 + 10), (int) y);
        x -= (thick * 2 - 10);
        surface.drawLine((int) x, (int) y, (int) (x - thick * 6 - 10), (int) y);
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
     * color return the color.
     *
     * @return Color
     */
    public ArrayList<Color> color() {

        return colorBack;
    }
}