import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Final four back ground.
 */
public class FinalFourBackGround implements Sprite {
    private double thick;
    private Frame frame;
    private ArrayList<Color> colorBack;

    /**
     * Instantiates a new Final four back ground.
     *
     * @param thickN the thick n
     * @param frameN the frame n
     */
    public FinalFourBackGround(double thickN, Frame frameN) {
        thick = thickN;
        frame = frameN;
        colorBack = new ArrayList<>();
        colorBack.add(Color.blue);
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

        //x = thick;
        //y = frame.getMaxY() - thick;
        //width = frame.getMaxX() - (thick * 2);
        //height = thick;
        //surface.setColor(new Color(102, 51, 0));
        //surface.fillRectangle((int) x, (int) y, (int) width, (int) height);

        double xX = 120;
        double yY = 350;

        //draw lines
        surface.setColor(Color.white);
        x = 120;
        double x2 = x - 20;
        y = 350;
        double y2 = 650;
        double distance = (210 - 120) / 10;
        for (int i = 0; i < 10; i++) {
            surface.drawLine((int) (x + distance * i), (int) y, (int) (x2 + distance * i), (int) y2);
        }

        //draw clowns
        surface.setColor(Color.white);
        x = 120;
        y = 350;
        surface.fillCircle((int) x, (int) y, 25);

        surface.setColor(Color.white);
        x = x + 20; //140
        y = y + 20; //370
        surface.fillCircle((int) x, (int) y, 25);

        surface.setColor(Color.lightGray);
        x = x + 15; //155
        y = y - 30; //340
        surface.fillCircle((int) x, (int) y, 30);

        surface.setColor(Color.gray);
        x = x + 35; //190
        surface.fillCircle((int) x, (int) y, 35);

        surface.setColor(Color.gray);
        x = x - 15; //175
        y = y + 30; //370
        surface.fillCircle((int) x, (int) y, 20);

        //clowns
        //draw lines
        surface.setColor(Color.white);
        x = 620;
        x2 = x - 20;
        y = 450;
        y2 = 650;
        distance = (210 - 120) / 10;
        for (int i = 0; i < 10; i++) {
            surface.drawLine((int) (x + distance * i), (int) y, (int) (x2 + distance * i), (int) y2);
        }

        //draw clowns
        surface.setColor(Color.white);
        x = x;
        y = y;
        surface.fillCircle((int) x, (int) y, 25);

        surface.setColor(Color.white);
        x = x + 20; //140
        y = y + 20; //370
        surface.fillCircle((int) x, (int) y, 25);

        surface.setColor(Color.lightGray);
        x = x + 15; //155
        y = y - 30; //340
        surface.fillCircle((int) x, (int) y, 30);

        surface.setColor(Color.gray);
        x = x + 35; //190
        surface.fillCircle((int) x, (int) y, 35);

        surface.setColor(Color.gray);
        x = x - 15; //175
        y = y + 30; //370
        surface.fillCircle((int) x, (int) y, 20);
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
     * Color return the color.
     *
     * @return Color
     */
    public ArrayList<Color> color() {

        return colorBack;
    }
}
