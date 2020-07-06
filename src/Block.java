import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private String counter;
    private ArrayList<String> color;

    private List<HitListener> hitListeners;

    /**
     * Block constructor.
     *
     * @param upperLeft location of the rectangle
     * @param width     of the rectangle
     * @param height    of the rectangle
     */
    public Block(Point upperLeft, double width, double height) {
        block = new Rectangle(upperLeft, width, height);
        color = new ArrayList<>();
        color.add("color(gray)");
        hitListeners = new ArrayList<>();
    }

    /**
     * Block constructor.
     *
     * @param rectangle the rectangle pointer
     * @param input     number of hit
     * @param colorN    the request color
     */
    public Block(Rectangle rectangle, int input, String colorN) {
        block = rectangle;
        counter = String.valueOf(input);
        color = new ArrayList<>();
        color.add(colorN);
        hitListeners = new ArrayList<>();
    }

    /**
     * Block constructor.
     *
     * @param rectangle the rectangle pointer
     * @param input     number of hit
     * @param colorN    the request color
     */
    public Block(Rectangle rectangle, String input, String colorN) {
        block = rectangle;
        counter = input;
        color = new ArrayList<>();
        color.add(colorN);
        hitListeners = new ArrayList<>();
    }

    /**
     * getCounter return the counter of the block.
     *
     * @return counter number
     */
    public int getCounter() {

        return Integer.valueOf(counter);
    }

    /**
     * setCounter update the 5 devided section in the paddle.
     */
    public void setCounter() {
        int com = Integer.valueOf(counter);
        com--;
        if (com < 1) {
            com = 0;
        }

        counter = String.valueOf(com);
    }

    /**
     * Sets counter.
     *
     * @param counterN the counter n
     */
    public void setCounter(int counterN) {
        counter = String.valueOf(counterN);
    }

    /**
     * getCollisionRectangle return the rectangle.
     *
     * @return rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {

        return block;
    }

    /**
     * hit return the new velocity after hitting the block.
     *
     * @param hitter          Ball which hit
     * @param collisionPoint  the intersection point
     * @param currentVelocity the prewvies velocity
     * @return the new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line interLine = new Line(collisionPoint, collisionPoint);
        Line[] lines = block.getLines();
        Point corner = checkIsCorner(collisionPoint, currentVelocity);

        if (corner != null) {
            return getVelocity(currentVelocity, corner);
        }

        if (lines[0].intersectionWith(interLine) != null) {
            currentVelocity.setDY(currentVelocity.getDY() * (-1));
        } else if (lines[1].intersectionWith(interLine) != null) {
            currentVelocity.setDx((currentVelocity.getDX() * (-1)));
        } else if (lines[2].intersectionWith(interLine) != null) {
            currentVelocity.setDY(currentVelocity.getDY() * (-1));
        } else if (lines[3].intersectionWith(interLine) != null) {
            currentVelocity.setDx(currentVelocity.getDX() * (-1));
        }

        if (this.hitListeners.size() > 1) {
            setCounter();
        }
        notifyHit(hitter);
        if (collisionPoint.getY() == 580) {
            currentVelocity = new Velocity(0, 0);
        }
        return currentVelocity;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        double xC = block.getUpperLeft().getX();
        double yC = block.getUpperLeft().getY();
        double width = block.getWidth();
        double height = block.getHeight();

        int i = (int) ((xC + xC + width) / 2) - 3;
        int i1 = (int) ((yC + yC + height) / 2) + 7;
        int i2 = 20;
        String s;
        if (counter.length() > 3) {
            s = counter;
            i -= counter.length() * 4;
        } else {
            s = "";
        }

        if (color.get(0) != null) {
            //draw the inside of the rectangle
            int index;
            try {
                index = Integer.valueOf(counter);
            } catch (NumberFormatException n) {
                index = 1;
            }
            if (!color.get(index - 1).startsWith("image(")) {
                d.setColor(ColorsParser.blockColor(color.get(index - 1)));

                if (yC != 580) {
                    d.fillRectangle((int) xC, (int) yC, (int) width, (int) height);
                }
                if (block.getWidth() < 55 && block.getHeight() < 25) {
                    d.setColor(Color.black);
                    d.drawRectangle((int) xC, (int) yC, (int) width, (int) height);
                }
                //draw the counter of the rectangle
                d.setColor(Color.black);
                d.drawText(i, i1, s, i2);
            } else {
                Image sprite = ColorsParser.getImage(color.get(index - 1));
                d.drawImage((int) xC, (int) yC, sprite);
            }
        }
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param game environment
     */
    @Override
    public void timePassed(GameEnvironment game) {

    }

    /**
     * addToGame add blocks to the environment.
     *
     * @param g the gsme environment
     */
    @Override
    public void addToGame(GameLevel g) {

        g.addSprite(this);
    }

    /**
     * removeFromGame remove this block from game.
     *
     * @param g game object
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * getVelocity return he new velocity.
     *
     * @param currentVelocity the current velocity
     * @param corner          the point of the intersect
     * @return the new update velocity
     */
    private Velocity getVelocity(Velocity currentVelocity, Point corner) {
        Point[] points = block.getPoints();
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();

        if (corner.equals(points[0])) {
            if (dx == (dy * (-1))) {
                dx *= (-1);
                dy *= (-1);
            }
        } else if (corner.equals(points[1])) {
            if (dx == dy) {
                dx *= (-1);
                dy *= (-1);
            }
        } else if (corner.equals(points[2])) {
            if (dx == (dy * (-1))) {
                dx *= (-1);
                dy *= (-1);
            }
        } else if (corner.equals(points[3])) {
            if (dx == dy) {
                dx *= (-1);
                dy *= (-1);
            }
        }

        currentVelocity.setDx(dx);
        currentVelocity.setDY(dy);

        return currentVelocity;
    }

    /**
     * checkIsCorner checks if the intersection point is a corner point
     * and if the angle of the ball is not 45,135,225,315.
     *
     * @param collisionPoint  the intersection point
     * @param currentVelocity the velocity before the intersection
     * @return the velocity after the intersection
     */
    private Point checkIsCorner(Point collisionPoint, Velocity currentVelocity) {
        Point[] points = block.getPoints();
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        double angle = Math.abs(dx / dy);

        for (Point p : points) {
            if (p.equals(collisionPoint) && (angle == 1)) {
                return p;
            }
        }

        return null;
    }

    // Add hl as a listener to hit events.
    @Override
    public void addHitListener(HitListener hl) {
        if (hitListeners.size() >= 2) {
            hitListeners.remove(0);
            hitListeners.remove(0);
        }
        hitListeners.add(hl);

    }

    // Remove hl from the list of listeners to hit events.
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notifyHit operate the hit function for this block.
     *
     * @param hitter Ball that hit
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public ArrayList<Color> color() {

        return null;
    }

    /**
     * Sets color.
     *
     * @param colorN the color n
     */
    public void setColors(ArrayList<String> colorN) {

        color = colorN;
    }

    /**
     * Sets height.
     *
     * @param heightN the height n
     */
    public void setHeight(double heightN) {
        block.setHeight(heightN);
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return block.getHeight();
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(double width) {
        block.setWidth(width);
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return block.getWidth();
    }
}
