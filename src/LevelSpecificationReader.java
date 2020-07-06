import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader implements LevelInformation {
    private int numberOfBalls;
    private ArrayList<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite backgroundSprite;
    private ArrayList<Block> blocks;
    private InputStream defineBlock;
    private int blocksStartX;
    private int blocksStartY;
    private int numberOfBlocksToRemove;
    private double rowHeight;
    private ArrayList<Color> color;
    private int thick = 20;

    /**
     * Instantiates a new Level specification reader.
     */
    public LevelSpecificationReader() {
        ballVelocities = new ArrayList<>();
        blocks = new ArrayList<>();
        color = new ArrayList<>();
    }

    /**
     * Instantiates a new Level specification reader.
     *
     * @param level the level
     */
    public LevelSpecificationReader(LevelSpecificationReader level) {
        numberOfBalls = level.numberOfBalls;
        ballVelocities = level.initialBallVelocities();
        paddleSpeed = level.paddleSpeed;
        paddleWidth = level.paddleWidth;
        levelName = level.levelName;
        backgroundSprite = level.backgroundSprite;
        blocks = level.blocks;
        defineBlock = level.defineBlock;
        blocksStartX = level.blocksStartX;
        blocksStartY = level.blocksStartY;
        numberOfBlocksToRemove = level.numberOfBlocksToRemove;
        rowHeight = level.rowHeight;
        color = level.color;
    }

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelInfoS = new ArrayList<>();
        LineNumberReader readenLine = new LineNumberReader(reader);
        String line;

        try {
            line = readenLine.readLine();
            while (line != null) {
                if (line.equals("START_LEVEL")) {
                    levelInfoS.add(getLevelInfo(readenLine));
                    clearFields();
                }
                line = readenLine.readLine();
            }

        } catch (IOException e) {
            System.out.println("Fail in reading reader");
            levelInfoS = null;
        } finally {
            try {
                readenLine.close();
            } catch (IOException i) {
                System.out.println("Fail in closing reader");
            }
        }

        return levelInfoS;
    }

    /**
     * Clear fields.
     */
    public void clearFields() {
        numberOfBalls = 0;
        ballVelocities = new ArrayList<>();
        paddleSpeed = 0;
        paddleWidth = 0;
        levelName = "";
        backgroundSprite = null;
        blocks = new ArrayList<>();
        defineBlock = null;
        blocksStartX = 0;
        blocksStartY = 0;
        numberOfBlocksToRemove = 0;
        rowHeight = 0;
        color = new ArrayList<>();
    }

    /**
     * Gets level info.
     *
     * @param readenLine the readen line
     * @return the level info
     */
    public LevelInformation getLevelInfo(LineNumberReader readenLine) {
        String line;

        try {
            line = readenLine.readLine();
            while (!line.equals("END_LEVEL")) {
                if (line.equals("START_BLOCKS")) {
                    getBlock(readenLine); //using also the blocks field
                } else {
                    updateField(line);
                }
                line = readenLine.readLine();
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Fail in reading reader");
        }

        return new LevelSpecificationReader(this);
    }

    /**
     * Gets block.
     *
     * @param reader the reader
     * @throws IOException the io exception
     */
    public void getBlock(LineNumberReader reader) throws IOException {
        ArrayList<String> rows = new ArrayList<>();
        String line = reader.readLine();
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory();

        while (!line.equals("END_BLOCKS")) {
            rows.add(line);
            line = reader.readLine();
        }

        blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(new InputStreamReader(defineBlock));

        double x = blocksStartX;
        double y = blocksStartY;
        for (String row : rows) {
            Block block = null;
            if (row.length() == 1 && row.equals("-")) {
                y += blocksFromSymbolsFactory.defult().heigh();
            } else if (row.length() > 1) {
                for (int i = 0; i < row.length(); i++) {
                    String s = row.substring(i, i + 1);
                    if (blocksFromSymbolsFactory.isBlockSymbol(s)) {
                        block = blocksFromSymbolsFactory.getBlock(s, (int) x, (int) y);
                        blocks.add(block);
                        x += block.getWidth();
                    }
                    x += blocksFromSymbolsFactory.getSpaceWidth(s);
                }
                x = blocksStartX;
                y += block.getHeight();
            }
        }
    }

    /**
     * Update field.
     *
     * @param line the line
     * @throws NoSuchFieldException   the no such field exception
     * @throws IllegalAccessException the illegal access exception
     */
    public void updateField(String line) throws NoSuchFieldException, IllegalAccessException {
        String[] mapValue = line.split(":");
        String key = mapValue[0];
        String value = mapValue[1];

        if (key.equals("level_name")) {
            levelName = value;
        } else if (key.equals("ball_velocities")) {
            String[] veolicties = value.split(" ");
            for (String veolicty : veolicties) {
                String[] details = veolicty.split(",");
                ballVelocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(details[0]),
                        Double.parseDouble(details[1])));
            }
            numberOfBalls = initialBallVelocities().size();
        } else if (key.equals("background")) {
            ArrayList<Color> colors = new ArrayList<>();
            if (value.startsWith("image(") && value.endsWith(")")) {
                String path = value.substring("image(".length(), value.length() - ")".length());
                backgroundSprite = new ImageBackGround(ColorsParser.getImage(path), thick);
            } else if (value.startsWith("color(RGB(") && value.endsWith("))")) {
                colors.add(ColorsParser.blockColor(value));

                backgroundSprite = new ColorBackGround(colors, thick);
                color.add(colors.get(0));
            } else if (value.startsWith("color(") && value.endsWith(")")) {
                colors.add(ColorsParser.blockColor(value));

                backgroundSprite = new ColorBackGround(colors, thick);
                color.add(colors.get(0));
            }
        } else if (key.equals("paddle_speed")) {
            paddleSpeed = Integer.parseInt(value);
        } else if (key.equals("paddle_width")) {
            paddleWidth = Integer.parseInt(value);
        } else if (key.equals("block_definitions")) {
            InputStream path;
            try {
                path = new FileInputStream(value);
            } catch (FileNotFoundException e) {
                path = null;
            }
            //InputStream path = ClassLoader.getSystemClassLoader().getResourceAsStream(value);
            defineBlock = path;
        } else if (key.equals("blocks_start_x")) {
            blocksStartX = Integer.parseInt(value);
        } else if (key.equals("blocks_start_y")) {
            blocksStartY = Integer.parseInt(value);
        } else if (key.equals("row_height")) {
            rowHeight = Integer.parseInt(value);
        } else if (key.equals("num_blocks")) {
            numberOfBlocksToRemove = Integer.parseInt(value);
        }
    }

    /**
     * Number of balls int.
     *
     * @return the int
     */
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     * Initial ball velocities array list.
     *
     * @return the array list
     */
// The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public ArrayList<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
// the level name will be displayed at the top of the screen.
    public String levelName() {
        return levelName;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
// Returns a sprite with the background of the level
    public Sprite getBackground() {
        return backgroundSprite;
    }

    /**
     * Blocks array list.
     *
     * @return the array list
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    public ArrayList<Block> blocks() {
        return blocks;
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
// Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }

    /**
     * color retrun the color of the sprite.
     *
     * @return Color
     */
    public ArrayList<Color> color() {
        return color;
    }
}
