import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Green 3 level back ground.
 */
public class Green3LevelBackGround implements Sprite {
    private double thick;
    private Frame frame;
    private ArrayList<Color> colorBack;

    /**
     * Instantiates a new Green 3 level back ground.
     *
     * @param thickN the thick n
     * @param frameN the frame n
     */
    public Green3LevelBackGround(double thickN, Frame frameN) {
        thick = thickN;
        frame = frameN;
        colorBack = new ArrayList<>();
        colorBack.add(Color.green);
    }

    /**
     * draw the sprite to the screen.
     *
     * @param surface obj
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //draw blue circles
        surface.setColor(Color.GREEN.darker().darker());
        surface.fillRectangle(0, 30, frame.getMaxX(), frame.getMaxY() - 30);
        int buildX = 50, buildingWidth = 110, buildingHeight = 195, buildingY = frame.getMaxY() - buildingHeight;
        int smallAntenaWidth = buildingWidth / 3;
        int smallAntenaX = buildX + (smallAntenaWidth);
        int smallAntenaY = buildingY - buildingHeight / 3;
        int smallAntenaHeigth = buildingHeight / 3;
        int tallAntenaWidth = smallAntenaWidth / 3;
        int tallAntenaX = smallAntenaX + tallAntenaWidth;
        int tallAntenaY = smallAntenaY - buildingHeight;
        int windowX = buildX + 10, windowY = buildingY + 10;
        int windowWidth = 10;
        int windowHeight = 25;

        surface.setColor(Color.GRAY.darker().darker().darker());
        surface.fillRectangle(buildX, buildingY, buildingWidth, buildingHeight);

        surface.setColor(Color.GRAY.darker().darker());
        surface.fillRectangle(smallAntenaX, smallAntenaY, smallAntenaWidth, smallAntenaHeigth);

        surface.setColor(Color.GRAY.darker());
        surface.fillRectangle(tallAntenaX, tallAntenaY, tallAntenaWidth, buildingHeight);

        surface.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                surface.fillRectangle(windowX + 10 * 2 * i, windowY + 20 * 2 * j, windowWidth, windowHeight);
            }
        }

        surface.setColor(Color.orange);
        surface.fillCircle(tallAntenaX + tallAntenaWidth / 2, tallAntenaY, 15);

        surface.setColor(Color.RED.brighter().brighter());
        surface.fillCircle(tallAntenaX + tallAntenaWidth / 2, tallAntenaY, 10);

        surface.setColor(Color.WHITE);
        surface.fillCircle(tallAntenaX + tallAntenaWidth / 2, tallAntenaY, 4);
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
