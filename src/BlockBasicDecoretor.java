/**
 * The type Block basic decoretor.
 */
public class BlockBasicDecoretor extends BlocksCreatorDecoretor {
    private int width;
    private int height;
    private int hitPt;


    /**
     * Instantiates a new Block basic decoretor.
     *
     * @param decoretor the decoretor
     * @param widthN    the width n
     * @param heightN   the height n
     */
    public BlockBasicDecoretor(BlockCreator decoretor, int widthN, int heightN) {
        super(decoretor);
        width = widthN;
        height = heightN;
        hitPt = 1;
    }

    /**
     * create basic block.
     *
     * @param x coordinate
     * @param y coordinate
     * @return Block
     */
    public Block create(int x, int y) {
        Block block = super.create(x, y);
        block.setWidth(width);
        block.setHeight(height);
        block.setCounter(hitPt);
        return block;
    }

    /**
     * setWidth.
     *
     * @param widthN new length
     */
    public void setWidth(int widthN) {
        width = widthN;
    }

    /**
     * setHeight.
     *
     * @param heightN new length
     */
    public void setHeight(int heightN) {
        height = heightN;
    }
}
