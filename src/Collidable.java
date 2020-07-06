/**
 * Collidable interface.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return rectangle of the collidable
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * hit return the new velocity of the ball.
     *
     * @param hitter          Ball which hit
     * @param collisionPoint  the intersection point
     * @param currentVelocity the prewvies velocity
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}