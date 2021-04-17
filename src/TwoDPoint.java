import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point, Comparable<Point> {

    private double x;
    private double y;

    public TwoDPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return new double[]{x,y};
    }

    /**
     * Calculate the distance from the origin
     * @return the distance from the origin to this point
     */
    @Override
    public double distanceFromOrigin() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * A setter for the x value
     * @param x the new x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * A setter for the y value
     * @param y the new y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * A getter for the x value
     * @return x the x coordinate
     */
    public double getX() {
        return x;
    }
    /**
     * A getter for the y value
     * @return y the new y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of doubles. A valid argument must always
     * be an even number of doubles so that every pair can be used to form a single <code>TwoDPoint</code> to be added
     * to the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of doubles.
     */
    public static List<TwoDPoint> ofDoubles(double... coordinates) throws IllegalArgumentException {
        if(coordinates.length % 2 != 0)
            throw new IllegalArgumentException();
        List<TwoDPoint> temp = new ArrayList<>();
        for(int i=0; i<coordinates.length; i=i+2){
            temp.add(new TwoDPoint(coordinates[i], coordinates[i+1]));
        }
        return temp;
    }

    /**
     * Determines whether the input object is identical to the instance object
     * @param o the input object
     * @return true or false based on equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoDPoint twoDPoint = (TwoDPoint) o;
        return Double.compare(twoDPoint.x, x) == 0 &&
                Double.compare(twoDPoint.y, y) == 0;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point o) {
        return (int)(this.distanceFromOrigin() - o.distanceFromOrigin());
    }
}
