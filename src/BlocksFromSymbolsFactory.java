import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    private Defult defult;

    /**
     * Instantiates a new Blocks from symbols factory.
     */
    public BlocksFromSymbolsFactory() {
        spacerWidths = new HashMap<>();
        blockCreators = new HashMap<>();
        defult = new Defult();
    }

    /**
     * Spacer widths map.
     *
     * @return the map
     */
    public Map<String, Integer> spacerWidths() {
        return spacerWidths;
    }

    /**
     * Block creators map.
     *
     * @return the map
     */
    public Map<String, BlockCreator> blockCreators() {

        return blockCreators;
    }

    /**
     * Defult defult.
     *
     * @return the defult
     */
    public Defult defult() {
        return defult;
    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        if (spacerWidths.get(s) != null) {
            return true;
        }


        return false;
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        if (blockCreators.get(s) != null) {
            return true;
        }

        return false;
    }

    /**
     * Gets block.
     *
     * @param s    the s
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
// Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        if (!isBlockSymbol(s)) {
            return null;
        }

        BlockCreator bdc = blockCreators.get(s);
        return bdc.create(xpos, ypos);
    }

    /**
     * Get space width int.
     *
     * @param s the s
     * @return the int
     */
// Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s) {
        if (!isSpaceSymbol(s)) {
            return 0;
        }

        return spacerWidths.get(s);
    }
}