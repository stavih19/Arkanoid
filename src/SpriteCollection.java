import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * this class charge to manage to Sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprits;

    /**
     * constructor.
     */
    public SpriteCollection() {

        sprits = new ArrayList<>();
    }

    /**
     * addSprite add to the list sprites.
     *
     * @param s sprite object
     */
    public void addSprite(Sprite s) {

        sprits.add(s);
    }

    /**
     * removeSprite remove a sprite from the list.
     *
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        sprits.remove(s);
    }

    /**
     * getListCollidables return the list.
     *
     * @return list of collidable
     */
    public List<Sprite> getListCollidables() {
        return sprits;
    }

    /**
     * call timePassed() on all sprites.
     *
     * @param game the environment
     */
    public void notifyAllTimePassed(GameEnvironment game) {
        for (int i = 0; i < sprits.size(); i++) {
            Sprite s = sprits.get(i);
            s.timePassed(game);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the exist surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprits) {
            s.drawOn(d);
        }
    }
}