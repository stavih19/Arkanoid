/**
 * @author staviha
 */

/**
 * point object have a x,y coordinate.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other second point to calculate the distant
     * @return the distant between the points
     */
    public double distance(Point other) {
        double dis;
        dis = Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y)));

        return dis;
    }

    /**
     * calculate the incline between two points.
     *
     * @param other Point
     * @return the incline between the two points
     */
    public double findIncline(Point other) {
        double m = (this.getY() - other.getY())
                / (this.getX() - other.getX());

        return m;
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other point to equal
     * @return equal or not
     */
    public boolean equals(Point other) {
        if (this.x == other.x) {
            if (this.y == other.y) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return the x and y values of this point.
     *
     * @return this x coridante
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the x and y values of this point.
     *
     * @return this y cordiante
     */
    public double getY() {
        return this.y;
    }

    /**
     * setX insert new x coordinate.
     *
     * @param xN the new coordinate
     */
    public void setX(double xN) {
        this.x = xN;
    }

    /**
     * setY insert new y coordinate.
     *
     * @param yN the new coordinate
     */
    public void setY(double yN) {
        this.y = yN;
    }
}