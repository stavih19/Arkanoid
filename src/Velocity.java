/**
 * @author staviha
 */

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Velocity is a constructor.
     *
     * @param dx the delta x
     * @param dy the delta y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getDX returns the dx value.
     *
     * @return the dx value
     */
    public double getDX() {
        return dx;
    }

    /**
     * getDY return the dy value.
     *
     * @return the dy value
     */
    public double getDY() {
        return dy;
    }

    /**
     * setDX set the change.
     *
     * @param dxN the change
     */
    public void setDx(double dxN) {
        this.dx = dxN;
    }

    /**
     * setDY set the change.
     *
     * @param dyN the change
     */
    public void setDY(double dyN) {
        this.dy = dyN;
    }

    /**
     * applyToPoint Take a point with position (x,y)
     * and return a new point with position (x+dx, y+dy).
     *
     * @param p point to change
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        p.setX(p.getX() + dx);
        p.setY(p.getY() + dy);

        return p;
    }

    /**
     * fromAngleAndSpeed convert the angle to change coordinate.
     *
     * @param angle of the direction
     * @param speed of the move
     * @return velocity as an object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle);
        double dx = Math.sin(radian) * speed;
        double dy = Math.cos(radian) * speed;
        return new Velocity(dx, dy);
    }
}