import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    /**
     * pre: constructor arguments x0 and y0 are each between 0 and 32,767
     *
     * @param x0
     * @param y0
     */
    public Point(int x0, int y0) {
        x = x0;
        y = y0;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point q) {
        StdDraw.line(x, y, q.x, q.y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * The compareTo() method should compare points by their y-coordinates,
     * breaking ties by their x-coordinates. Formally, the invoking point
     * (x0, y0) is less than the argument point (x1, y1) if and only if
     * either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param q
     * @return -1 if less than q, 0 if equal, and 1 if greater
     */
    public int compareTo(Point q) {
        return -1;
    }

    /**
     * The slopeTo() method should return the slope between the invoking point
     * (x0, y0) and the argument point (x1, y1), which is given by the formula
     * (y1 − y0) / (x1 − x0). Treat the slope of a horizontal line segment as
     * positive zero; treat the slope of a vertical line segment as positive
     * infinity; treat the slope of a degenerate line segment (between a point
     * and itself) as negative infinity.
     *
     * @param q
     * @return the slope to point q as a double value
     */
    public double slopeTo(Point q) {
        return 0.0;
    }

    /**
     * Compare two points by slopes they make with this point
     *
     * The slopeOrder() method should return a comparator that compares its
     * two argument points by the slopes they make with the invoking point
     * (x0, y0). Formally, the point (x1, y1) is less than the point (x2, y2)
     * if and only if the slope (y1 − y0) / (x1 − x0) is less than the slope
     * (y2 − y0) / (x2 − x0). Treat horizontal, vertical, and degenerate
     * line segments as in the slopeTo() method.
     *
     * @return
     */
//    public Comparator<Point> slopeOrder() {}
}
