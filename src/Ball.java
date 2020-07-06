import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author staviha
 */

/**
 * Ball object contains center point, radios, color and moving directions.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private Velocity volume;
    private Frame frame;
    private Line trajectory;

    /**
     * Ball constructor.
     *
     * @param x     coordinate
     * @param y     coordinate
     * @param r     radios
     * @param color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        //check if the coordinate out of the frame
        if (r > x) {
            x = r;
        }
        if (r > y) {
            y = r;
        }

        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        //set default speed
        this.volume = new Velocity(0, 0);
        this.trajectory = setTargetLine(center, volume);
        this.frame = new Frame(800, 800);
    }

    /**
     * setTargetPoint return the trajectory line of the ball.
     *
     * @param centerN the 'start point'
     * @param volumeN the direction and speed of the ball
     * @return trajectory line
     */
    private Line setTargetLine(Point centerN, Velocity volumeN) {
        Point target = new Point(centerN.getX() + volumeN.getDX(),
                centerN.getY() + volumeN.getDY());
        return new Line(centerN, target);
    }

    /**
     * getCenter return the center point.
     *
     * @return Point center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * setBall copy new ball fields.
     *
     * @param ballN Ball object
     */
    public void setBall(Ball ballN) {
        center = ballN.center;
        r = ballN.r;
        color = ballN.color;
        volume = ballN.volume;
        frame = ballN.frame;
        trajectory = ballN.trajectory;
    }

    /**
     * getX return the x coordinate.
     *
     * @return the x coordinate
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * getY return the y coordinate.
     *
     * @return the y coordinate
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * getR return the radios.
     *
     * @return the radios
     */
    public int getR() {
        return r;
    }

    /**
     * getSize return the radios if the ball.
     *
     * @return the radios of the ball
     */
    public int getSize() {
        return r;
    }

    /**
     * getColor return the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {

        return color;
    }

    /**
     * drawOn set the info to the surface.
     *
     * @param surface object
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) center.getX();
        int y = (int) center.getY();
        surface.setColor(color);
        surface.fillCircle(x, y, r);
        surface.setColor(Color.black);
        surface.drawCircle(x, y, r);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param game the game environment
     */
    public void timePassed(GameEnvironment game) {

        moveOneStep(game);
    }

    /**
     * addToGame add ball obj to the game.
     *
     * @param g the game obj
     */
    public void addToGame(GameLevel g) {

        g.addSprite(this);
    }

    /**
     * setVelocity set the center as asked.
     *
     * @param v the change delta
     */
    public void setVelocity(Velocity v) {
        volume.setDx(v.getDX());
        volume.setDY(v.getDY());

        this.trajectory = setTargetLine(center, volume);
    }

    /**
     * setVelocity set the center as asked.
     *
     * @param dx the change delta
     * @param dy the change delta
     */
    public void setVelocity(double dx, double dy) {
        volume.setDx(dx);
        volume.setDY(dy);

        this.trajectory = setTargetLine(center, volume);
    }

    /**
     * getVelocity returns the velocity.
     *
     * @return velocity velocity
     */
    public Velocity getVelocity() {

        return volume;
    }

    /**
     * moveOneStep change the point according to limits.
     *
     * @param game the game environment
     */
    public void moveOneStep(GameEnvironment game) {
        //check if the coordinate after the change is in the frame
        if ((center.getX() + volume.getDX() + r) > frame.getMaxX()) {
            volume.setDx(volume.getDX() * (-1));
        }
        if ((center.getX() + volume.getDX() - r) < frame.getMinX()) {
            volume.setDx(volume.getDX() * (-1));
        }
        if ((center.getY() + volume.getDY() + r) > frame.getMaxY()) {
            volume.setDY(volume.getDY() * (-1));
        }
        if ((center.getY() + volume.getDY() - r) < frame.getMinY()) {
            volume.setDY(volume.getDY() * (-1));
        }

        volume = checkEnviroment(trajectory, game);

        Point check = volume.applyToPoint(center);

        //in case the ball got into on of the blocks
        if (isInSide(check, game)) {
            volume.setDx(volume.getDX() * (-1));
            volume.setDY(volume.getDY() * (-1));
            center = volume.applyToPoint(center);
        } else {
            center = check;
        }

        trajectory = setTargetLine(center, volume);
    }

    /**
     * isInSide checks if the ball center point is in on of this blocks.
     *
     * @param centern the center point of the ball
     * @param game    the game environment
     * @return if is or not
     */
    private boolean isInSide(Point centern, GameEnvironment game) {
        List<Collidable> colisList = game.getCollidables();
        //checking each collidiable
        for (Collidable c : colisList) {
            Rectangle rec = c.getCollisionRectangle();
            if (isInArea(rec, centern)) {
                return true;
            }
        }

        return false;
    }

    /**
     * isInArea checking if is in the rectangle.
     *
     * @param rec     the rectangle
     * @param centerN point
     * @return if yes or not
     */
    private boolean isInArea(Rectangle rec, Point centerN) {
        double x = centerN.getX();
        double y = centerN.getY();

        double minX = rec.getUpperLeft().getX();
        double maxX = rec.getUpperLeft().getX() + rec.getWidth();
        double minY = rec.getUpperLeft().getY();
        double maxY = rec.getUpperLeft().getY() + rec.getHeight();

        if ((x > minX) && (x < maxX)) {
            if ((y > minY) && (y < maxY)) {
                return true;
            }
        }

        return false;
    }

    /**
     * checkEnvironment find the block witch the ball intersect.
     *
     * @param trajectoryN the line of the ball move
     * @param game        the game enviroment
     * @return the new direction
     */
    private Velocity checkEnviroment(Line trajectoryN, GameEnvironment game) {
        CollisionInfo interB = game.getClosestCollision(trajectoryN);
        if (interB == null) {
            return volume;
        }

        return interB.collisionObject().hit(this, interB.collisionPoint(), volume);
    }

    /**
     * setFrameSize.
     *
     * @param frameN the new frame size
     */
    public void setFrame(Frame frameN) {

        this.frame = frameN;
    }

    @Override
    public ArrayList<Color> color() {
        return null;
    }
}