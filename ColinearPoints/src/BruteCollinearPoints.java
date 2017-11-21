/* ------------------------------------------------------------------
    Examines 4 points at a time and checks whether they all lie
    on the same line segment, returning all such line segments.

     To check whether the 4 points p, q, r, and s are collinear,
     check whether the three slopes between p and q, between p
     and r, and between p and s are all equal.
 * ---------------------------------------------------------------*/

import javax.swing.text.Segment;

public class BruteCollinearPoints {

    LineSegment[] segments = null;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null/* || any point is null*//* || any point is repeated*/) {
            throw new IllegalArgumentException();
        }
//        segments = new LineSegment();
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * Return each line segment containing 4 points exactly once.
     * If 4 points appear on a line segment in the order p→q→r→s,
     * then you should include either the line segment p→s or s→p
     * (but not both) and you should not include subsegments such
     * as p→r or q→r.
     */
//    public LineSegment[] segments() {}
}