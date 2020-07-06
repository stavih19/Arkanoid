import java.util.LinkedList;
import java.util.List;

/**
 * Rectangle class represent all its points and all its lines.
 */
public class Rectangle {
    //const
    private int maxInterPoint = 4;
    //set rectangle
    private Point upperleft;
    private double width;
    private double height;
    //lines array
    private Line[] lines = new Line[maxInterPoint];
    private Point[] points = new Point[maxInterPoint];

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperleftN the base point of the Rectangle
     * @param widthN     the horizon length
     * @param heightN    the vertical length
     */
    public Rectangle(Point upperleftN, double widthN, double heightN) {
        upperleft = upperleftN;
        width = widthN;
        height = heightN;

        pointsBuild();
        linesBuild();
    }

    /**
     * pointsBuild creates the four points of the rectangle.
     */
    public void pointsBuild() {
        points[0] = upperleft;
        points[1] = new Point(upperleft.getX() + width, upperleft.getY());
        points[2] = new Point(points[1].getX(), points[1].getY() + height);
        points[3] = new Point(upperleft.getX(), points[2].getY());
    }

    /**
     * linesBuild creates the four points and lines of the Rectangle.
     */
    private void linesBuild() {
        Line upperLine = new Line(upperleft, points[1]);
        Line rightLine = new Line(points[1], points[2]);
        Line lowerLine = new Line(points[2], points[3]);
        Line leftLine = new Line(points[3], upperleft);

        lines[0] = upperLine;
        lines[1] = rightLine;
        lines[2] = lowerLine;
        lines[3] = leftLine;
    }

    /**
     * intersectionPoints creates list of the intersection points.
     *
     * @param line with it we checking
     * @return a list of the intersection points
     */
    public List intersectionPoints(Line line) {
        List<Point> listPoints = new LinkedList<>();
        Point interTemp;

        for (int i = 0; i < lines.length; i++) {
            interTemp = line.intersectionWith(lines[i]);
            if (interTemp != null) {
                listPoints.add(interTemp);
            }
        }

        return listPoints;
    }


    /**
     * Return the width length of the rectangle.
     *
     * @return the horizon length
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param widthN the width n
     */
    public void setWidth(double widthN) {
        width = widthN;
        pointsBuild();
        linesBuild();
    }

    /**
     * Return the vertical length of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param heightN the height n
     */
    public void setHeight(double heightN) {
        height = heightN;
        pointsBuild();
        linesBuild();
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper left Point
     */
    public Point getUpperLeft() {
        return upperleft;
    }

    /**
     * Returns the lines of the rectangle.
     *
     * @return its lines
     */
    public Line[] getLines() {
        return lines;
    }

    /**
     * getPoints returns the points array.
     *
     * @return its points
     */
    public Point[] getPoints() {
        return points;
    }
}
