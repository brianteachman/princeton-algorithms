import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class PointTests {

    /*------------------------------------------------------------------------
     * compareTo tests
     -----------------------------------------------------------------------*/

    @Test
    void testCompareToEquals() {
        Point p = new Point(0, 0);
        Point q = new Point(0, 0);
        assertEquals( 0, p.compareTo(q));
    }

    @Test
    void testCompareToIsLessThan() {
        Point p = new Point(0, 0);
        Point q = new Point(0, 1);
        assertEquals( -1, p.compareTo(q));
    }

    @Test
    void testCompareToIsGreaterThan() {
        Point p = new Point(0, 1);
        Point q = new Point(0, 0);
        assertEquals( 1, p.compareTo(q));
    }

    /*------------------------------------------------------------------------
     * slopeTo tests
     -----------------------------------------------------------------------*/

    @Test
    void testConstructionAndToString() {
        Point p = new Point(3,4);
        assertEquals("(3, 4)", p.toString());
    }

    @Test
    void testPsSlopeToQisOne() {
        Point p = new Point(0, 0);
        Point q = new Point(2, 2);
        assertEquals( 1.0, p.slopeTo(q));
    }

    @Test
    void testPsSlopeToQisTwo() {
        Point p = new Point(0, 0);
        Point q = new Point(1, 2);
        assertEquals( 2, p.slopeTo(q));
    }

    @Test
    void testPsSlopeToQisOneHalf() {
        Point p = new Point(0, 0);
        Point q = new Point(2, 1);
        assertEquals( 0.5, p.slopeTo(q));
    }

    @Test
    void testPsSlopeToQisHorizontalZero() {
        Point p = new Point(0, 0);
        Point q = new Point(3, 0);
        assertEquals( 0.0, p.slopeTo(q));
    }

    @Test
    void testPsSlopeToQVerticalIsInfinity() {
        Point p = new Point(0, 0);
        Point q = new Point(0, 3);
        assertEquals( Double.POSITIVE_INFINITY, p.slopeTo(q));
    }

    @Test
    void testPsSlopeToQAtNegativeVerticalIsInfinity() {
        Point p = new Point(0, 0);
        Point q = new Point(0, -3);
        assertEquals( Double.POSITIVE_INFINITY, p.slopeTo(q));
    }

    @Test
    void testPsSlopeToSelfIsDegenerate() {
        Point p = new Point(0, 0);
        Point q = new Point(0, 0);
        assertEquals( Double.NEGATIVE_INFINITY, p.slopeTo(q));
    }

    /*------------------------------------------------------------------------
     * slopeOrder tests
     -----------------------------------------------------------------------*/

    @Test
    void testComparator() {
        Point[] pnts = new Point[5];
        pnts[0] = new Point(0, 0);
        pnts[1] = new Point(2, 3);
        pnts[2] = new Point(1, 1);
        pnts[3] = new Point(1, 0);
        Arrays.sort(pnts, pnts[3].SLOPE_ORDER);
        assertEquals( 0, pnts[0]);
    }

}
