/* ------------------------------------------------------------------

 * ---------------------------------------------------------------*/
public class FastCollinearPoints {

    private int count;
    private Point[] list;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        count = 0;
//        list = new Point[points.length];
        list = points;
    }

    /**
     * Return should include each maximal line segment containing
     * 4 (or more) points exactly once. For example, if 5 points
     * appear on a line segment in the order p→q→r→s→t, then do
     * not include the subsegments p→s or q→t.
     *
     * @return
     */
    public LineSegment[] segments() {
        LineSegment[] segs = new LineSegment[count];

        return segs;
    }
}
