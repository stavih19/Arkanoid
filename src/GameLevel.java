import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Game creates the game environment.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Frame frame;
    private GUI gui;
    private Sleeper sleeper;
    private KeyboardSensor keySensor;
    private int thick;
    private Counter counter;
    private Counter score;
    private Counter lives;
    private List<HitListener> listeners;
    private static int ballNumber;
    private AnimationRunner runner;
    private boolean running;
    private boolean finishLVL;
    private int numBorders = 4;
    private int numPaddle = 1;
    private int numAnoncedBlock = 3;
    private LevelInformation levleInfo;


    /**
     * Instantiates a new Game level.
     *
     * @param infoLvL          the info lv l
     * @param ksN              the ks n
     * @param runnerN          the runner n
     * @param guiN             the gui n
     * @param frameN           the frame n
     * @param counterArrayList the counter list
     * @param thickN           the thick n
     */
    public GameLevel(LevelInformation infoLvL, KeyboardSensor ksN, AnimationRunner runnerN, GUI guiN, Frame frameN,
                     ArrayList<Counter> counterArrayList, int thickN) {
        frame = frameN;
        gui = guiN;
        keySensor = ksN;
        runner = runnerN;
        lives = counterArrayList.get(0);
        score = counterArrayList.get(1);
        thick = thickN;
        levleInfo = infoLvL;
        counter = new Counter();
        initialize(infoLvL);
    }

    /**
     * Initialize a new game: create the Blocks and Ball
     * (and Paddle) and add them to the game.
     *
     * @param infoLvL the complete level info
     */
    public void initialize(LevelInformation infoLvL) {
        sleeper = new Sleeper();
        //keySensor = gui.getKeyboardSensor();
        running = true;
        finishLVL = false;
        //runner = new AnimationRunner(gui, framePerSecond);
        ballNumber = infoLvL.numberOfBalls();
        listeners = new ArrayList<>();

        listeners.add(new BlockRemover(this, counter));
        listeners.add(new ScoreTrackingListener(score));

        //insert the background
        infoLvL.getBackground().addToGame(this);

        //create lives place
        Rectangle recLive = new Rectangle(new Point(0, 0), frame.getMaxX() / 3, thick);
        Block liveBlock = new Block(recLive, 1, "color(white)");
        liveBlock.addToGame(this);

        //create score place
        Rectangle recScore = new Rectangle(new Point((frame.getMaxX() / 3), 0), frame.getMaxX() / 3, thick);
        Block scoreBlock = new Block(recScore, 1, "color(white)");
        scoreBlock.addToGame(this);

        //create name level place
        Rectangle recNameLvL = new Rectangle(new Point(frame.getMaxX() * 2 / 3, 0), frame.getMaxX() / 3, thick);
        Block nameLvlBlock = new Block(recNameLvL, "Level Name: " + infoLvL.levelName(), "color(white)");
        nameLvlBlock.addToGame(this);

        //initial balls
        insertBalls(infoLvL);

        //creates the paddle
        Paddle paddle = getPaddle(frame, keySensor, thick, infoLvL);
        paddle.addToGame(this);

        //creates the borders
        getBorders();

        //insert the blocks the game
        insertBlocks(infoLvL);

        //add to the collidable list
        List<Sprite> colis = sprites.getListCollidables();
        for (int i = ballNumber + numBorders; i < colis.size(); i++) {
            environment.addCollidable((Collidable) colis.get(i));
        }

        //1 = background sprite
        int hardBlock = 1 + ballNumber + numBorders + numPaddle + numAnoncedBlock;
        for (int i = hardBlock; i < colis.size(); i++) {
            for (HitListener member : listeners) {
                ((Block) colis.get(i)).addHitListener(member);
            }
        }

        counter.increase(colis.size() - hardBlock);
    }

    /**
     * insertBlocks insert all the block in toto the game.
     *
     * @param lvlInfo the level info
     */
    private void insertBlocks(LevelInformation lvlInfo) {
        for (Block spr : lvlInfo.blocks()) {
            spr.addToGame(this);
        }
    }

    /**
     * getBorders add the the borders to the game.
     */
    private void getBorders() {
        Rectangle[] borders;
        borders = getBorders(thick, frame);
        Block b;
        for (int i = 0; i < borders.length; i++) {
            if (i != 2) {
                b = new Block(borders[i], 1, "color(gray)");
            } else {
                b = new Block(borders[i], 0, null);
            }

            b.addToGame(this);
        }
    }

    /**
     * createBalls function.
     *
     * @param lvlInfo the info of the levle
     * @return ball array
     */
    private Ball[] createBalls(LevelInformation lvlInfo) {
        //intial arrays
        Ball[] balls = new Ball[ballNumber];

        //calculate the x coordinates
        double distance = (frame.getMaxX() / 2) / ballNumber;
        double firstX = frame.getMaxX() / 2 - (ballNumber / 2 * distance);

        double firstY = frame.getMaxY() * 3 / 4;

        //creates ball
        for (int i = 0; i < ballNumber; i++) {
            double x = firstX + (distance * i);
            double y = firstY + Math.abs(ballNumber / 2 - i) * 12;
            balls[i] = new Ball(x, y, 5, Color.white);
            balls[i].setFrame(frame);
            Velocity v = lvlInfo.initialBallVelocities().get(i);
            balls[i].setVelocity(v);
        }

        return balls;
    }

    /**
     * insertBalls insert the ball obj to the game.
     *
     * @param levleInfoN the level info
     */
    private void insertBalls(LevelInformation levleInfoN) {
        Ball[] balls = createBalls(levleInfoN);

        for (Ball ball : balls) {
            ball.addToGame(this);
        }
    }

    /**
     * addCollidable add coli obj to the collection.
     *
     * @param c the coli object
     */
    public void addCollidable(Collidable c) {
        Sprite s = (Sprite) c;
        sprites.addSprite(s);
    }

    /**
     * addSprite add sprite obj to the collection.
     *
     * @param s sprites obj
     */
    public void addSprite(Sprite s) {

        sprites.addSprite(s);
    }

    /**
     * getBorders creates the borders of the frame.
     *
     * @param thickN the width of the border
     * @param frameN the frames of the game
     * @return array of rectangle
     */
    private Rectangle[] getBorders(int thickN, Frame frameN) {
        Rectangle[] borders = new Rectangle[numBorders];

        Point above = new Point(0, thickN);
        borders[0] = new Rectangle(above, frameN.getMaxX(), thickN);

        Point right = new Point((frameN.getMaxX() - thickN), thickN + thickN);
        borders[1] = new Rectangle(right, thickN, (frameN.getMaxY() - thickN - thickN));

        Point under = new Point(thickN, frameN.getMaxY() - thickN);
        borders[2] = new Rectangle(under, (frameN.getMaxX() - thickN - thickN), thickN);

        Point left = new Point(0, thickN + thickN);
        borders[3] = new Rectangle(left, thickN, (frameN.getMaxY() - thickN));

        return borders;
    }

    /**
     * creates the paddle obj.
     *
     * @param frameN         borders
     * @param keyboardSensor key sensor obj
     * @param thickN         the width of the paddle
     * @param lvlInfo        the level info
     * @return paddle obj
     */
    private Paddle getPaddle(Frame frameN, KeyboardSensor keyboardSensor, int thickN, LevelInformation lvlInfo) {
        double x = ((frameN.getMinX() + frameN.getMaxX()) / 2) - (lvlInfo.paddleWidth() / 2);
        double y = frameN.getMaxY() - 10 - thickN;
        Point upperPoint = new Point(x, y);
        Rectangle rec = new Rectangle(upperPoint, lvlInfo.paddleWidth(), 5);
        Paddle paddle = new Paddle(keyboardSensor, rec, frameN.getMaxX(), frameN.getMaxY(), Color.yellow,
                lvlInfo.paddleSpeed());

        return paddle;
    }

    /**
     * SpriteCollection return the sprite collection.
     *
     * @return its of sprites
     */
    public SpriteCollection getSprites() {

        return sprites;
    }

    /**
     * run the game ome time.
     */
    public void run() {
        while (!finishLVL && lives.getValue() > 0) {
            playOneTurn();
        }
        gui.close();
    }

    /**
     * Keep play boolean.
     *
     * @return the boolean
     */
    public boolean keepPlay() {
        //the same condition as run function
        return ((!finishLVL) && (lives.getValue() > 0));
    }

    /**
     * Lives int.
     *
     * @return the int
     */
    public int lives() {
        return lives.getValue();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        //this.createBallsOnTopOfPaddle(); // or a similar method

        runner.run((new CountdownAnimation(2, 3, sprites, frame, this)));
        running = true;
        runner.run(this);

    } //end run func

    /**
     * setEnvironment set the ball and the paddle in the defult place when user killed.
     */
    private void setEnvironment() {
        //creates balls
        Ball[] balls = createBalls(levleInfo);
        for (int i = 0; i < ballNumber; i++) {
            ((Ball) sprites.getListCollidables().get(i + numAnoncedBlock + 1)).setBall(balls[i]);
        }

        //creates the paddle
        Paddle newPaddle = getPaddle(frame, keySensor, thick, levleInfo);
        ((Paddle) sprites.getListCollidables().get(numAnoncedBlock + ballNumber + 1)).setPaddle(newPaddle);
    }

    /**
     * isBallKill ask if the ball is killed(out of the frame).
     *
     * @param sprite the ball itself
     * @return T or F
     */
    private boolean isBallKill(Sprite sprite) {
        Velocity velocity = ((Ball) sprite).getVelocity();

        if (velocity.getDX() == 0 && velocity.getDY() == 0) {
            return true;
        }

        return false;
    }

    /**
     * drawScoreNumber draw the number of the score in the block.
     *
     * @param d      the drawsurface
     * @param sprite the block where to print
     */
    public void drawScoreNumber(DrawSurface d, Sprite sprite) {
        Rectangle block = ((Block) sprite).getCollisionRectangle();

        String str = String.valueOf(score.getValue());

        double xC = block.getUpperLeft().getX();
        double yC = block.getUpperLeft().getY();
        double width = block.getWidth();
        double height = block.getHeight();

        int i = (int) (xC + xC + width) / 2 - str.length() * 25;
        int i1 = (int) ((yC + yC + height) / 2) + 7;
        int i2 = 20;

        d.setColor(Color.black);
        d.drawText(i, i1, ("Score: " + str), i2);
    }

    /**
     * drawLives draw the number of the lives in the block.
     *
     * @param d      the surface
     * @param sprite the bloc kwhere to print it
     */
    public void drawLives(DrawSurface d, Sprite sprite) {
        Rectangle block = ((Block) sprite).getCollisionRectangle();

        String str = String.valueOf(lives.getValue());

        double xC = block.getUpperLeft().getX();
        double yC = block.getUpperLeft().getY();
        double width = block.getWidth();
        double height = block.getHeight();

        int i = (int) (xC + xC + width) / 2 - str.length() * 25;
        int i1 = (int) ((yC + yC + height) / 2) + 7;
        int i2 = 20;

        d.setColor(Color.black);
        d.drawText(i, i1, ("Lives: " + str), i2);
    }

    /**
     * removeCollidable remove block from the screen.
     *
     * @param c the block itself
     */
    public void removeCollidable(Collidable c) {

        environment.removeCollodable(c);
    }

    /**
     * removeSprite remove block from the list in the environment.
     *
     * @param s the block itself
     */
    public void removeSprite(Sprite s) {

        sprites.removeSprite(s);
    }

    /**
     * shouldStop return if the level should stop because some reason.
     *
     * @return T or F
     */
    public boolean shouldStop() {

        return !this.running;
    }

    /**
     * doOneFrame doing one iteration.
     *
     * @param d the drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.fillRectangle(0, 0, frame.getMaxX(), frame.getMaxY());
        sprites.drawAllOn(d);
        drawLives(d, sprites.getListCollidables().get(1));
        drawScoreNumber(d, sprites.getListCollidables().get(2));

        //checks if the ball went out of the frame
        boolean[] isBallAlive = new boolean[ballNumber];
        for (int i = 0; i < ballNumber; i++) {
            isBallAlive[i] = true;
            if (isBallKill(sprites.getListCollidables().get(i + 1 + numAnoncedBlock))) {
                //in case it went out- put it out of the frame
                ((Ball) sprites.getListCollidables().get(i + 1 + numAnoncedBlock)).getCenter().setX(frame.getMaxX());
                ((Ball) sprites.getListCollidables().get(i + 1 + numAnoncedBlock)).getCenter().setY(frame.getMaxY());
                isBallAlive[i] = false;
            }
        }

        //checks if all the balls went out
        boolean flagIsBall = false;
        for (boolean isSomeBall : isBallAlive) {
            if (isSomeBall) {
                flagIsBall = true;
            }
        }

        //in case all the balls went out
        if (!flagIsBall) {
            setEnvironment();
            lives.decrease(1);
            running = false;
        }

        //in case there is no more block to hit
        if (counter.getValue() == 0) {
            finishLVL = true;
            running = false;
            score.increase(100);
        }

        //in case there is pause
        if (keySensor.isPressed("p")) {
            runner.run(new KeyPressStoppableAnimation(keySensor, keySensor.SPACE_KEY, new PauseScreen(keySensor)));
        }

        sprites.notifyAllTimePassed(environment);
    }
} //end class