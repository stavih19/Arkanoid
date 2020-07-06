//import Ball;
//import Block;

/**
 * HitListener interface.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * hitEvent operate when there is a hit.
     *
     * @param beingHit the block who's been hitten
     * @param hitter   the ball who's hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}