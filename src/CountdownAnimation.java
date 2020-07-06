import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private GameLevel game;
    private Frame frame;
    private Sleeper sleeper = new Sleeper();
    private int millisecondsPerFrame;
    private boolean running;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSecondsN the num of seconds n
     * @param countFromN    the count from n
     * @param gameScreenN   the game screen n
     * @param frameN        the frame n
     * @param gameN         the game n
     */
    public CountdownAnimation(double numOfSecondsN, int countFromN,
                              SpriteCollection gameScreenN, Frame frameN, GameLevel gameN) {
        numOfSeconds = numOfSecondsN;
        countFrom = countFromN;
        gameScreen = gameScreenN;
        frame = frameN;
        millisecondsPerFrame = (int) ((numOfSeconds / countFrom) * 1000);
        running = true;
        game = gameN;
    }

    /**
     * doOneFrame of this screen.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (countFrom == 0) {
            running = false;
        }

        d.setColor(Color.darkGray);
        d.fillRectangle(0, 0, frame.getMaxX(), frame.getMaxY());
        gameScreen.drawAllOn(d);
        game.drawLives(d, gameScreen.getListCollidables().get(1));
        game.drawScoreNumber(d, gameScreen.getListCollidables().get(2));

        Point countDown = new Point(frame.getMaxX() / 2, frame.getMaxY() / 4);
        d.setColor(Color.orange);
        d.drawText((int) countDown.getX() - 30, (int) countDown.getY(), String.valueOf(countFrom), 100);

        long startTime = System.currentTimeMillis(); // timing
        // timing
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }

        countFrom--;
    }

    /**
     * shouldStop ask if the game should stop.
     *
     * @return T or F
     */
    public boolean shouldStop() {

        return !running;
    }
}