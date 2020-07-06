//import Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensorN    the sensor n
     * @param keyN       the key n
     * @param animationN the animation n
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensorN, String keyN, Animation animationN) {
        sensor = sensorN;
        key = keyN;
        animation = animationN;
        stop = false;
        isAlreadyPressed = true;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.

    /**
     * doOneFrame do one frame of the level.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
        if (sensor.isPressed(key) && !isAlreadyPressed) {
            stop = true;
        }
    }

    /**
     * shouldStop ask if the level should stop.
     *
     * @return T of F
     */
    public boolean shouldStop() {
        return stop;
    }
}