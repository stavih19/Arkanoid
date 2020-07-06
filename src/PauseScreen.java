//import Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param key the key
     */
    public PauseScreen(KeyboardSensor key) {
        keyboard = key;
        stop = false;
    }

    /**
     * doOneFrame run the frame on time.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            stop = true;
        }
    }

    /**
     * shouldStop ask if the animation is running.
     *
     * @return T or F
     */
    public boolean shouldStop() {
        return stop;
    }
}