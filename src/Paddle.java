import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Paddle of the game.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;

    private Rectangle block;
    private double widthFrame;
    private double heightFrame;
    private double[] sections = new double[4];
    private Color color;
    private int speed;

    /**
     * constructor.
     *
     * @param keyboardN    obj
     * @param blockN       obj
     * @param widthFrameN  the width limited
     * @param heightFrameN the height limited
     * @param colorN       the request color
     * @param speedN       the paddle speed
     */
    public Paddle(KeyboardSensor keyboardN, Rectangle blockN,
                  double widthFrameN, double heightFrameN, Color colorN, int speedN) {
        keyboard = keyboardN;
        block = blockN;
        widthFrame = widthFrameN;
        heightFrame = heightFrameN;
        getSections();
        color = colorN;
        speed = speedN;
    }

    /**
     * setBlock set the block field.
     *
     * @param blockN Rectangle
     */
    public void setBlock(Rectangle blockN) {
        block = blockN;
    }

    /**
     * Sets paddle.
     *
     * @param paddleN the paddle n
     */
    public void setPaddle(Paddle paddleN) {
        keyboard = paddleN.keyboard;
        block = paddleN.block;
        widthFrame = paddleN.widthFrame;
        heightFrame = paddleN.heightFrame;
        sections = paddleN.sections;
        color = paddleN.color;
        speed = paddleN.speed;
    }

    /**
     * getBlock return the block.
     *
     * @return Rectangle block
     */
    public Rectangle getBlock() {
        return block;
    }

    /**
     * create the sections of intersection with the paddle.
     */
    public void getSections() {
        double devideNum = block.getWidth() / 5;
        for (int i = 0; i < 4; i++) {
            sections[i] = devideNum * (i + 1) + block.getUpperLeft().getX();
        }
    }

    /**
     * move left the rectangle pointer.
     *
     * @param thickBorders the width size
     */
    public void moveLeft(int thickBorders) {
        double x = block.getUpperLeft().getX() - speed;
        double y = block.getUpperLeft().getY();
        if (x < thickBorders) {
            x = thickBorders;
        }
        Point upperLeft = new Point(x, y);

        block = new Rectangle(upperLeft, block.getWidth(), block.getHeight());
        getSections();
    }

    /**
     * move right the rectangle pointer.
     *
     * @param game         environment
     * @param thickBorders the width of the borders
     */
    public void moveRight(GameEnvironment game, int thickBorders) {
        double x = block.getUpperLeft().getX() + speed;
        double y = block.getUpperLeft().getY();
        if (x > (widthFrame - block.getWidth() - thickBorders)) {
            x = (widthFrame - block.getWidth() - thickBorders);
        }
        Point upperLeft = new Point(x, y);

        block = new Rectangle(upperLeft, block.getWidth(), block.getHeight());
        getSections();
    }

    /**
     * timePassed move the paddle.
     *
     * @param game environment
     */
    @Override
    public void timePassed(GameEnvironment game) {
        int thickBorders = 25;
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(thickBorders);
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(game, thickBorders);
        }
    }

    /**
     * draw the paddle.
     *
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        double x = block.getUpperLeft().getX();
        double y = block.getUpperLeft().getY();
        double width = block.getWidth();
        double height = block.getHeight();

        d.setColor(color);
        d.fillRectangle((int) x, (int) y, (int) width, (int) height);


        d.drawRectangle((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * return the rectangle.
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
        double x = collisionPoint.getX();
        int angleN;
        if (x < sections[0]) {
            angleN = (180 + 60);
        } else if (x < sections[1]) {
            angleN = (180 + 30);
        } else if (x < sections[2]) {
            angleN = 180;
        } else if (x < sections[3]) {
            angleN = (180 - 30);
        } else {
            angleN = (180 - 60);
        }

        return Velocity.fromAngleAndSpeed(angleN, 10);
    }

    /**
     * Add this paddle to the game.
     *
     * @param g game class
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public ArrayList<Color> color() {
        return null;
    }
}