/**
 * CollisionInfo class.
 */
public class CollisionInfo {
    private Point colliPoint;
    private Collidable obj;

    /**
     * CollisionInfo constructor.
     *
     * @param colliPointN the intersection point
     * @param objN        the collidable object
     */
    public CollisionInfo(Point colliPointN, Collidable objN) {
        colliPoint = colliPointN;
        obj = objN;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return point
     */
    public Point collisionPoint() {
        return colliPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the object
     */
    public Collidable collisionObject() {
        return obj;
    }
}