/**
 * ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener constructor.
     *
     * @param scoreCounter the counter pointer
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent operate when there is hit.
     *
     * @param beingHit the block who's been hitten
     * @param hitter   the ball who's hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCounter() > 0) {
            currentScore.increase(5);
        } else if (beingHit.getCounter() == 0) {
            currentScore.increase(10);
        }
    }
}