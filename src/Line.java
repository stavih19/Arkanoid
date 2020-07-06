import java.util.List;

/**
 * Line objects contains two points.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructor.
     *
     * @param start of the line
     * @param end   of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 coordinate of start point
     * @param y1 coordinate of start point
     * @param x2 coordinate of end point
     * @param y2 coordinate of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return distant of the line
     */
    public double length() {
        return (this.start.distance(this.end));
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle Point
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;

        return (new Point(xMid, yMid));
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point
     */
    public Point start() {
        return (this.start);
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point
     */
    public Point end() {
        return (this.end);
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other line
     * @return if there is only one intersection point
     */
    public boolean isIntersecting(Line other) {
        Point interP = intersectionWith(other);
        if (interP == null) {
            return false;
        }

        return true;
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other Line to check intersection
     * @return the point or null if there is no intersection
     */
    public Point intersectionWith(Line other) {
        Line thisLine = new Line(this.start, this.end);
        //in case one of them not linear
        if (!isFunc(thisLine)) {
            //in case both of them are not liner
            if (!isFunc(other)) {
                Point interP = isOverLapp(new Line(this.start, this.end), other);
                if (interP == null) {
                    if (isOnLine(thisLine, other, other.start) != null) {
                        return other.start;
                    }
                    return null;
                }
                return interP;
            }
            //in case the one of them not liner
            double mOther = other.start.findIncline(other.end);
            Point otherP = other.start;
            double value = mOther * (-otherP.getX()) + otherP.getY();
            Point interP = calculatePoint(this.start.getX(), mOther, value);
            interP = isOnLine(thisLine, other, interP);
            return interP;
        } else if (!isFunc(other)) {
            //in case the one of them not liner
            double mThis = this.start.findIncline(this.end);
            double value = mThis * (-this.start.getX()) + this.start.getY();
            Point interP = calculatePoint(other.start.getX(), mThis, value);
            interP = isOnLine(thisLine, other, interP);
            return interP;
        }

        //in case the are both liner
        //calculate the inclines
        double mThis = this.start.findIncline(this.end);
        double mOther = other.start.findIncline(other.end);

        //in case there parallel
        if (mThis == mOther) {
            //if there still one intersection point
            Point interP = isOverLapp(new Line(this.start, this.end), other);

            if (interP == null) {
                return null;
            }
            return interP;
        }

        Point interP = findPoint(this.start, other.start, mThis, mOther);

        interP = isOnLine(new Line(this.start, this.end), other, interP);

        return interP;
    }

    /**
     * isPtonLine checks if the line (one point line) is on the other line.
     *
     * @param other the intersection point
     * @return true if there is
     */
    public Boolean isPtonLine(Line other) {
        double mThis = this.start.findIncline(this.end);
        double value = mThis * (-this.start.getX()) + this.start.getY();
        Point interP = calculatePoint(other.start.getX(), mThis, value);

        if (other.start.equals(interP)) {
            return true;
        }

        return false;
    }

    /**
     * isOverLapp check if there is a over lapp more than one point.
     *
     * @param thisL  first line
     * @param otherL second line
     * @return the the only intersection point or null
     */
    private Point isOverLapp(Line thisL, Line otherL) {
        Point thisS = thisL.start;
        Point thisE = thisL.end;
        Point otherS = otherL.start;
        Point otherE = otherL.end;

        //in case the end of the lines are the same
        if (thisS.equals(otherS)) {
            if (thisE.equals(otherE)) {
                return null;
            }
            return thisS;
        } else if (thisS.equals(otherE)) {
            if (thisE.equals(otherS)) {
                return null;
            }
            return thisS;
        }
        return null;
    }

    /**
     * check if the line is functional(liner).
     *
     * @param line line to check
     * @return true or false
     */
    private boolean isFunc(Line line) {
        double x1 = line.start.getX();
        double x2 = line.end.getX();

        if (x1 == x2) {
            return false;
        }
        return true;
    }

    /**
     * findPoint- find the coordinate of the intersection
     * based on the Straight line equation.
     *
     * @param thisP  this Point
     * @param otherP other Point
     * @param mThis  the incline of this Line
     * @param mOther the incline of the other Line
     * @return the intersection of the Lines
     */
    private Point findPoint(Point thisP, Point otherP,
                            double mThis, double mOther) {
        /*equal the two equation of the lines
        organized the equations as y = mx+y pattern*/
        double leftValue = mThis * (-thisP.getX()) + thisP.getY();
        double rightValue = mOther * (-otherP.getX()) + otherP.getY();

        //moves the sides of the equation
        double m = mThis - mOther;
        double sumValue = rightValue - leftValue;

        Point interP = calculatePoint((sumValue / m), mThis, leftValue);

        return interP;
    }

    /**
     * isOnLine checks if the point is on the lines.
     *
     * @param thisLine one line to check
     * @param other    second line to check
     * @param interP   the exist point
     * @return point or null
     */
    private Point isOnLine(Line thisLine, Line other, Point interP) {
        if (isInRange(thisLine, interP)) {
            if (isInRange(other, interP)) {
                return interP;
            }
        }

        return null;
    }

    /**
     * isInRange checks if the point is between the range of coordinate.
     *
     * @param line  line to check in it
     * @param point point to check
     * @return is the point is in the line
     */
    private boolean isInRange(Line line, Point point) {
        double x = point.getX();
        double startX = line.start.getX();
        double endX = line.end.getX();
        double y = point.getY();
        double startY = line.start.getY();
        double endY = line.end.getY();
        boolean flag = false;

        //x range
        if (startX > endX) {
            if (x > startX || x < endX) {
                return false;
            }
        } else if (startX < endX) {
            if (x < startX || x > endX) {
                return false;
            }
        } else if (x != startX) {
            return false;
        }

        //y range
        if (startY > endY) {
            startY += 0.0005;
            endY -= 0.0005;
            if (y > startY || y < endY) {
                return false;
            }
        } else if (startY < endY) {
            startY -= 0.0005;
            endY += 0.0005;
            if (y < startY || y > endY) {
                return false;
            }
        } else {
            startY += 0.0005;
            endY -= 0.0005;
            if (y < endY || y > startY) {
                return false;
            }
        }

        return true;
    }

    /**
     * calculateLine the point coordinate.
     *
     * @param x     coordinate
     * @param m     the incline
     * @param value the other section of equstion
     * @return the intersection point
     */
    private Point calculatePoint(double x, double m, double value) {
        double y = (m * x) + value;
        if (x < 0) {
            x = Math.abs(x);
            y = -(y);
        }

        return (new Point(x, y));
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     *
     * @param other Line
     * @return if there the same lines
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start)
                && this.end.equals(other.end)) {
            return true;
        }

        return false;
    }

    /**
     * closestIntersectionToStartOfLine return the closet intersection point
     * with the rectangle.
     *
     * @param rect to check intersection
     * @return the closet point or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(start, end);
        List<Point> listPoint = rect.intersectionPoints(line);
        Point temp = null;

        if (listPoint.size() != 0) {
            double minDis = 0;
            for (Point p : listPoint) {
                if (p.distance(start) < minDis || minDis == 0) {
                    minDis = p.distance(start);
                    temp = p;
                }
            }
        }

        return temp;
    }
}