import biuoop.DrawSurface;

/**
 * Animation class.
 */
public interface Animation {

    /**
     * Do one frame.
     *
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    boolean shouldStop();
}