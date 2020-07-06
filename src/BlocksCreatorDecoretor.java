/**
 * The type Blocks creator decoretor.
 */
public abstract class BlocksCreatorDecoretor implements BlockCreator {
    private BlockCreator decoretor;

    /**
     * Instantiates a new Blocks creator decoretor.
     *
     * @param decoretorN the decoretor n
     */
    public BlocksCreatorDecoretor(BlockCreator decoretorN) {
        decoretor = decoretorN;
    }

    @Override
    public Block create(int xpos, int ypos) {
        return decoretor.create(xpos, ypos);
    }
}