import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * add the given collidable to the environment.
     *
     * @param c object to add
     */
    public void addCollidable(Collidable c) {

        collidables.add(c);
    }

    /**
     * removeCollodable remove a collidable from the list.
     *
     * @param c a collidasble
     */
    public void removeCollodable(Collidable c) {

        collidables.remove(c);
    }

    /**
     * getClosestCollision checks with witch block this line intersect with.
     *
     * @param trajectory the line to check
     * @return the info of the block
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo coliInfo = null;

        for (Collidable c : collidables) {
            Rectangle rec = c.getCollisionRectangle();
            Point interP = trajectory.closestIntersectionToStartOfLine(rec);
            if (interP != null) {
                coliInfo = new CollisionInfo(interP, c);
            }
        }

        return coliInfo;
    }

    /**
     * getCollidables return the list.
     *
     * @return list of collidable
     */
    public List<Collidable> getCollidables() {

        return collidables;
    }
}