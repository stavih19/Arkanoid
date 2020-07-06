//import Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
//import Counter;

/**
 * The type Game over.
 */
public class GameOver implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * Instantiates a new Game over.
     *
     * @param k      the k
     * @param scoreN the score n
     */
    public GameOver(KeyboardSensor k, Counter scoreN) {
        keyboard = k;
        stop = false;
        score = scoreN;
    }

    /**
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        d.drawText(10, (d.getHeight() / 2) + 50, "Press space to continue", 32);
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            stop = true;
        }
    }

    /**
     * shouldStop ask if the animation should stop.
     *
     * @return T or F
     */
    public boolean shouldStop() {
        return stop;
    }
}