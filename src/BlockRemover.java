/**
 * BlockRemover class.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * BlockRemover constructor.
     *
     * @param gameN          the Game
     * @param removedBlocksN Counter of blocks object
     */
    public BlockRemover(GameLevel gameN, Counter removedBlocksN) {
        game = gameN;
        remainingBlocks = removedBlocksN;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCounter() == 0) {
            game.removeCollidable(beingHit);
            game.removeSprite(beingHit);
            remainingBlocks.decrease(1);
        }
    }
}