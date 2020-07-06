import java.util.ArrayList;

/**
 * The type Defult.
 */
public class Defult {
    private int height;
    private int width;
    private int hitPoint;
    private ArrayList<String> color;

    /**
     * Instantiates a new Defult.
     *
     * @param heightN   the height n
     * @param widthN    the width n
     * @param hitPointN the hit point n
     * @param colorN    the colors n
     */
    public Defult(int heightN, int widthN, int hitPointN, ArrayList<String> colorN) {
        height = heightN;
        width = widthN;
        hitPoint = hitPointN;
        color = colorN;
    }

    /**
     * Instantiates a new Defult.
     */
    public Defult() {
        height = 0;
        width = 0;
        hitPoint = 0;
        color = null;
    }

    /**
     * Heigh double.
     *
     * @return the double
     */
    public int heigh() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param heightN the height n
     */
    public void setHeight(int heightN) {
        height = heightN;
    }

    /**
     * Width double.
     *
     * @return the double
     */
    public int width() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param widthN the width n
     */
    public void setWidth(int widthN) {
        width = widthN;
    }

    /**
     * Hit point int.
     *
     * @return the int
     */
    public int hitPoint() {
        return hitPoint;
    }

    /**
     * Sets hit pointdouble.
     *
     * @param hitPointN the hit point n
     */
    public void setHitPointdouble(int hitPointN) {

        hitPoint = hitPointN;
    }

    /**
     * Colors array list.
     *
     * @return the array list
     */
    public ArrayList<String> color() {
        return color;
    }

    /**
     * Sets hit pointdouble.
     *
     * @param colorN the colors n
     */
    public void setColor(ArrayList<String> colorN) {
        color = colorN;
    }
}
