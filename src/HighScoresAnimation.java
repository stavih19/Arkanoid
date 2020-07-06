import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable table;
    private KeyboardSensor key;
    private boolean stop;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     * @param keyN   the key n
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor keyN) {
        table = scores;
        key = keyN;
        stop = false;
    }

    /**
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        drawTable(d);
        if (key.isPressed(KeyboardSensor.SPACE_KEY)) {
            stop = true;
        }
    }

    /**
     * Draw table.
     *
     * @param d the d
     */
    private void drawTable(DrawSurface d) {
        d.drawText(50, 50, "Score Table", 50);
        ArrayList<ScoreInfo> scores = table.getTable();

        for (int i = 0; i < scores.size(); i++) {
            d.setColor(Color.black);
            String text = scores.get(i).getName() + " " + scores.get(i).getScore();
            d.drawText(50, ((i + 1) * 50 + 80), text, 50);
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