import java.util.ArrayList;

/**
 * The type Block multi hits decoretor.
 */
public class BlockMultiHitsDecoretor extends BlocksCreatorDecoretor {
    private ArrayList<String> colors = new ArrayList<>();
    private int hits = 0;

    /**
     * Instantiates a new Block multi hits decoretor.
     *
     * @param decoretor the decoretor
     * @param hitsN     the hits n
     * @param colorsN   the colors n
     */
    public BlockMultiHitsDecoretor(BlocksCreatorDecoretor decoretor, int hitsN, ArrayList<String> colorsN) {
        super(decoretor);
        hits = hitsN;
        colors = colorsN;
    }

    /**
     * create the block and return it.
     *
     * @param x coordinate
     * @param y coordinate
     * @return Block
     */
    public Block create(int x, int y) {
        Block block = super.create(x, y);
        block.setCounter(hits);
        block.setColors(colors);

        return block;
    }

    /**
     * Add color.
     *
     * @param color the color
     */
    public void addColor(String color) {
        colors.add(color);
    }

    /**
     * Increase hits.
     */
    public void increaseHits() {
        hits++;
    }
}
