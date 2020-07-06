/**
 * The type Block decoretor.
 */
public class BlockDecoretor implements BlockCreator {
    /**
     * Instantiates a new Block decoretor.
     */
    public BlockDecoretor() {

    }


    @Override
    public Block create(int xpos, int ypos) {
        return new Block(new Point(xpos, ypos), 0, 0);
    }
}
