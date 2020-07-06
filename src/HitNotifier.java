/**
 * HitNotifier interface.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.

    /**
     * addHitListener add a listener to the list.
     *
     * @param hl Listener
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.

    /**
     * removeHitListener remove a listener.
     *
     * @param hl the Listener
     */
    void removeHitListener(HitListener hl);
}