import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {
    public static void main(String... args){
        List<TwoDPoint> temp = Arrays.asList(new TwoDPoint(8.1,-0.4), new TwoDPoint(2.45,0.1), new TwoDPoint(2.2,5.3));
        Triangle t1 = new Triangle(temp);
        System.out.println(t1);
        System.out.println(t1.area());
        t1.snap();
        System.out.println(t1);
        boolean bool = t1.isMember(Arrays.asList(new TwoDPoint(0,0), new TwoDPoint(1,1),new TwoDPoint(2,2)));
        System.out.println(bool);
        System.out.println(t1.isMember(Arrays.asList(new TwoDPoint(0,0), new TwoDPoint(0,0),new TwoDPoint(2,2))));

        List<TwoDShape> shapes = new ArrayList<>();

//        shapes.add(new Circle(1, 1, 5));
//        shapes.add(new Triangle(Arrays.asList(new TwoDPoint(8,0), new TwoDPoint(2,0), new TwoDPoint(2,5))));
//        shapes.add(new Quadrilateral(Arrays.asList(new TwoDPoint(1,3), new TwoDPoint(2,0), new TwoDPoint(5,1), new TwoDPoint(4,3))));
//        for(Object shape : shapes){
//            if(shape instanceof  Circle){
//                System.out.println("Circle");
//            }
//            if(shape instanceof Triangle)
//                System.out.println("Triangle");
//            if(shape instanceof  Quadrilateral)
//                System.out.println("Quad");
//        }
        Quadrilateral q1 = new Quadrilateral(Arrays.asList(new TwoDPoint(1.4,3.1), new TwoDPoint(2.3,-0.3), new TwoDPoint(5.0,1.2), new TwoDPoint(4.4,3.1)));
        System.out.println(q1);
        System.out.println(q1.area());
        q1.snap();
        System.out.println(q1);
        System.out.println(q1.isMember(Arrays.asList(new TwoDPoint(0,0), new TwoDPoint(1,1), new TwoDPoint(2,2), new TwoDPoint(4,3))));
        System.out.println(q1.isMember(Arrays.asList(new TwoDPoint(1,1), new TwoDPoint(1,1), new TwoDPoint(1,1), new TwoDPoint(1,1))));


//        Integer one = new Integer(1);
//        Integer two = new Integer(2);
//        System.out.println(one.compareTo(2));
    }
}
