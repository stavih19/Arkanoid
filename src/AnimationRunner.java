import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * Instantiates a new Animation runner.
     *
     * @param guiN             the gui n
     * @param framesPerSecondN the frames per second n
     */
    public AnimationRunner(GUI guiN, int framesPerSecondN) {
        gui = guiN;
        framesPerSecond = framesPerSecondN;
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
// ...
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d); // doOneFrame(DrawSurface) is in charge of the logic.

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        } //end while loop
    }
}