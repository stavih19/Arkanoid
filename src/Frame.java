import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Frame is object which contains frame limits.
 */
public class Frame {
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    /**
     * Frame constructor.
     *
     * @param minXN minimum x coordinate
     * @param maxXN maximum x coordinate
     * @param minYN minimum y coordinate
     * @param maxYN maximum y coordinate
     */
    public Frame(int minXN, int maxXN, int minYN, int maxYN) {
        minX = minXN;
        maxX = maxXN;
        minY = minYN;
        maxY = maxYN;
    }

    /**
     * Frame constructor.
     *
     * @param maxXN maximum x coordinate
     * @param maxYN maximum y coordinate
     */
    public Frame(int maxXN, int maxYN) {
        //regular sizes
        minX = 0;
        maxX = maxXN;
        minY = 0;
        maxY = maxYN;
    }

    /**
     * getMinX return the coordinate.
     *
     * @return coordinate
     */
    public int getMinX() {
        return minX;
    }

    /**
     * getMaxX return the coordinate.
     *
     * @return coordinate
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * getMinY return the coordinate.
     *
     * @return coordinate
     */
    public int getMinY() {
        return minY;
    }

    /**
     * getMaxY return the coordinate.
     *
     * @return coordinate
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * drawOn draw the frame into the surface.
     *
     * @param surface the surface object
     */
    public void drawOn(DrawSurface surface) {
        //task orders
        if (minX == 50) {
            surface.setColor(Color.gray);
        } else if (minX == 450) {
            surface.setColor(Color.yellow);
        }
        surface.drawRectangle(minX, minY, (maxX - minX), (maxY - minY));
    }
}
