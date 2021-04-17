/**
 * An unmodifiable point in the three-dimensional space. The coordinates are specified by exactly three doubles (its
 * <code>x</code>, <code>y</code>, and <code>z</code> values).
 */
public class ThreeDPoint implements Point, Comparable<Point> {

    private double x;
    private double y;
    private double z;
    public ThreeDPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return the (x,y,z) coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return new double[]{x,y,z};
    }
    /**
     * Calculate the distance from the origin
     * @return the distance from the origin to this point
     */
    @Override
    public double distanceFromOrigin() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ", " + z +")";
    }

    @Override
    public int compareTo(Point o) {
        return (int)(this.distanceFromOrigin() - o.distanceFromOrigin());
    }
}
