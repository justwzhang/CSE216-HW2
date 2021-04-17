/**
 * An interface to represent a single point in a geometric space.
 */
public interface Point extends Comparable<Point>{

    /**
     * @return the coordinates of this point as an array of doubles, specifying its location in the geometric space.
     */
    double[] coordinates();
    /**
     * Calculate the distance from the origin
     * @return the distance from the origin to this point
     */
    double distanceFromOrigin();
}