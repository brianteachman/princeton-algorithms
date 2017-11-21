import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    /**
     * pre: constructor arguments x0 and y0 are each between 0 and 32,767
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
     * Compare points by their y-coordinates, breaking ties by their
     * x-coordinates. Formally, the invoking point (x0, y0) is less
     * than the argument point (x1, y1) if and only if
     * either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * Return -1 if less than q, 0 if equal, and 1 if greater
     */
    public int compareTo(Point q) {
        if (y > q.y) return 1;
        if (y < q.y) return -1;
        return 0;
    }

    /**
     * Return the slope between the invoking point (x0, y0) and the argument
     * point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0).
     * Treat the slope of a horizontal line segment as positive zero; treat
     * the slope of a vertical line segment as positive infinity; treat the
     * slope of a degenerate line segment (between a point and itself) as
     * negative infinity.
     *
     * Return the slope to point q as a double value
     */
    public double slopeTo(Point q) {
        if (this.x == q.x && this.y == q.y) {
            return Double.NEGATIVE_INFINITY;
        }
        double denominator = q.x - this.x;
        if (denominator == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return (q.y - this.y) / denominator;
    }

    /**
     * Compare two points by slopes they make with this point
     *
     * Return a comparator that compares its two argument points by
     * the slopes they make with the invoking point (x0, y0).
     * Formally, the point (x1, y1) is less than the point (x2, y2)
     * if and only if the slope (y1 − y0) / (x1 − x0) is less than
     * the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical,
     * and degenerate line segments as in the slopeTo() method.
     */
    public final Comparator<Point> SLOPE_ORDER = this.slopeOrder;
    private Comparator<Point> slopeOrder = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            double sp1 = slopeTo(p1);
            double sp2 = slopeTo(p2);
            if (sp1 == sp2) return 0;
            if (sp1 < sp2) return -1;
            return 1;
        }
    };

    private double distance(Point q) {
        return Math.sqrt((q.x - x) * (q.x - x) + (q.y - y) * (q.y - y));
    }

    public static void main(String[] args) {

    }
}
