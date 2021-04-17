import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Quadrilateral implements TwoDShape, Positionable, Comparable<TwoDShape> {

    List<TwoDPoint> vertices;

    public Quadrilateral(List<TwoDPoint> vertices) {
        setPosition(vertices);
    }

    public List<TwoDPoint> getVertices() {
        return vertices;
    }

    /**
     * Sets the position of this quadrilateral according to the first four elements in the specified list of points. The
     * quadrilateral is formed on the basis of these four points taken in a clockwise manner on the two-dimensional
     * x-y plane. If the input list has more than four elements, the subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        List<TwoDPoint> temp = new ArrayList<>();
        for(int i=0; i< 4; i++){
            temp.add((TwoDPoint) points.get(i));
        }
        this.vertices = temp;
        List<? extends Point> clockWisePositionList = getPosition();
        for(int i=0; i< this.vertices.size(); i++){
            this.vertices.set(i, (TwoDPoint)clockWisePositionList.get(i));
        }
    }

    /**
     * Retrieve the position of an object as a list of points. The points are be retrieved and added to the returned
     * list in a clockwise manner on the two-dimensional x-y plane, starting with the point with the least x-value. If
     * two points have the same least x-value, then the clockwise direction starts with the point with the lower y-value.
     *
     * @return the retrieved list of points.
     */
    @Override
    public List<? extends Point> getPosition() {
        List<TwoDPoint> temp = vertices;
        TwoDPoint firstPoint = temp.stream().reduce(temp.get(0), (a,b) ->((TwoDPoint)a).coordinates()[0] > ((TwoDPoint)b).coordinates()[0] ? b : //finds the left most point where it is stored in a but replaced by b if it is lower
                                                                                (((TwoDPoint)a).coordinates()[0] != ((TwoDPoint)b).coordinates()[0] ? a :
                                                                                    (((TwoDPoint)a).coordinates()[1] < ((TwoDPoint)b).coordinates()[1] ? a : b)));
        temp = temp.stream().filter((a) -> ((TwoDPoint)a).coordinates()[0] != firstPoint.coordinates()[0] || ((TwoDPoint)a).coordinates()[1] != firstPoint.coordinates()[1]).collect(Collectors.toList()); //removes the left most point
        temp.add(0, firstPoint); //adds the filtered item to the start of the array
        if(temp.get(2).coordinates()[1] > pointSlopeFormula(temp.get(0), temp.get(1), temp.get(2))){
            TwoDPoint holder = temp.get(2);
            temp.set(2, temp.get(1));
            temp.set(1, holder);
        }//everything above forms a triangle from the first three points and everything under forms the quadrilateral from the final point by inserting it to the right spot of the list
        if(temp.get(3).coordinates()[1] > pointSlopeFormula(temp.get(0), temp.get(1), temp.get(3))){
            TwoDPoint holder = temp.get(3);
            temp.remove(3);
            temp.add(1, holder);
        }else if(temp.get(3).coordinates()[1] < pointSlopeFormula(temp.get(0), temp.get(1), temp.get(3)) && temp.get(3).coordinates()[1] > pointSlopeFormula(temp.get(0), temp.get(2), temp.get(3))){
            TwoDPoint holder = temp.get(3);
            temp.remove(3);
            temp.add(2, holder);
        }
        return temp;
    }
    /**
     * The slope formula
     * @param a the first point
     * @param b the second point
     * @return the slope
     */
    private double slope(TwoDPoint a, TwoDPoint b){
        return (a.coordinates()[1] - b.coordinates()[1])/( a.coordinates()[0] - b.coordinates()[0]);
    }
    /**
     * Calculates the y value resulting from the point slope formula
     * @param p1 the first point in the slope
     * @param p2 the second point in the slope
     * @param y the point where the y value is to be calculated
     * @return the y value at the x value on the line
     */
    private double pointSlopeFormula(TwoDPoint p1, TwoDPoint p2, TwoDPoint y){
        return slope(p1, p2) * (y.getX() - p1.getX()) + p1.getY();
    }

    /**
     * @return the number of sides of this quadrilateral, which is always set to four
     */
    @Override
    public int numSides() {
        return 4;
    }

    /**
     * Checks whether or not a list of vertices forms a valid quadrilateral. The <i>trivial</i> quadrilateral, where all
     * four corner vertices are the same point, is considered to be an invalid quadrilateral.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a quadrilateral, and
     * <code>false</code> otherwise. For example, if three of the four vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) {
        for (Point vertex : vertices) {
            if (!(vertex instanceof TwoDPoint)) {
                return false;
            }
        }
        if(vertices.size() != 4)
            return false;
        for(int i= 0; i< vertices.size(); i++){
            for(int j=0; j< vertices.size(); j++){
                if(i != j && ((TwoDPoint)vertices.get(i)).equals(vertices.get(j))){
                    return false;
                }
            }
        }
        for(int i=0; i< vertices.size(); i++){
            if(vertices.get((i + 2)%4).coordinates()[1] == pointSlopeFormula((TwoDPoint) vertices.get(i%4), (TwoDPoint)vertices.get((i+1)%4), (TwoDPoint)vertices.get((i+2)%4)))
                return false;
        }
        return true;
    }

    /**
     * This method snaps each vertex of this quadrilateral to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant quadrilateral will thus have all four
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * quadrilateral becoming invalid (e.g., all four corners collapse to a single point), then it is left unchanged.
     * Snapping is an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        List<TwoDPoint> temp = this.vertices;
        for(TwoDPoint point : temp){
            point.setX(Math.round(point.getX()));
            point.setY(Math.round(point.getY()));
        }
        if(isMember(temp)){
            for(int i=0; i<this.vertices.size(); i++){
                vertices.set(i, temp.get(i));
            }
        }
    }

    /**
     * @return the area of this quadrilateral
     */
    public double area() {
        Triangle t1 = new Triangle(Arrays.asList(this.vertices.get(0), this.vertices.get(1), this.vertices.get(2)));
        Triangle t2 = new Triangle(Arrays.asList(this.vertices.get(0), this.vertices.get(2), this.vertices.get(3)));
        return t1.area() + t2.area();
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this quadrilateral
     */
    public double perimeter() {
        double perimeter = 0;
        for(int i=0; i<this.vertices.size(); i++){
            perimeter += distance(this.vertices.get(i%3), this.vertices.get((i+1) % 3));
        }
        return perimeter;
    }
    /**
     * Calculates the distance between two points
     * @param first the first point
     * @param second the second point
     * @return the distance between two points
     */
    private double distance(TwoDPoint first, TwoDPoint second){
        return Math.sqrt(Math.pow(second.getX() - first.getX(), 2) + Math.pow(second.getY() - first.getY(), 2));
    }

    @Override
    public String toString() {
        return "Quadrilateral" + vertices;
    }

    @Override
    public int compareTo(TwoDShape o) {
        return (int) (this.area() - o.area());
    }
}
