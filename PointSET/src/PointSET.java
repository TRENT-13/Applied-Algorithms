import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class PointSET {
 private SET<Point2D> points;

 // construct an empty set of points
 public PointSET() {
  points = new SET<>();
 }

 // is the set empty?
 public boolean isEmpty() {
  return points.isEmpty();
 }

 // number of points in the set
 public int size() {
  return points.size();
 }

 // add the point to the set (if it is not already in the set)
 public void insert(Point2D p) {
  if (p == null) throw new IllegalArgumentException("Point cannot be null");
  points.add(p);
 }

 // does the set contain point p?
 public boolean contains(Point2D p) {
  if (p == null) throw new IllegalArgumentException("Point cannot be null");
  return points.contains(p);
 }

 // draw all points to standard draw
 public void draw() {
  StdDraw.setPenRadius(0.02); // Set the radius for drawing points
  StdDraw.setPenColor(StdDraw.BLACK); // Set color for points
  for (Point2D p : points) {
   StdDraw.point(p.x(), p.y());
  }
 }

 // all points that are inside the rectangle (or on the boundary)
 public Iterable<Point2D> range(RectHV rect) {
  if (rect == null) throw new IllegalArgumentException("Rectangle cannot be null");

  List<Point2D> result = new ArrayList<>();
  for (Point2D p : points) {
   if (rect.contains(p)) {
    result.add(p);
   }
  }
  return result;
 }

 // a nearest neighbor in the set to point p; null if the set is empty
 public Point2D nearest(Point2D p) {
  if (p == null) throw new IllegalArgumentException("Point cannot be null");

  if (isEmpty()) {
   return null;
  }

  Point2D nearest = null;
  double minDistance = Double.MAX_VALUE;

  for (Point2D point : points) {
   double distance = p.distanceTo(point);
   if (distance < minDistance) {
    minDistance = distance;
    nearest = point;
   }
  }

  return nearest;
 }

 // unit testing of the methods (optional)
 public static void main(String[] args) {
  PointSET pointSet = new PointSET();
  pointSet.insert(new Point2D(0.5, 0.5));
  pointSet.insert(new Point2D(0.2, 0.3));
  pointSet.insert(new Point2D(0.9, 0.7));

  System.out.println("Size: " + pointSet.size());
  System.out.println("Contains (0.5, 0.5): " + pointSet.contains(new Point2D(0.5, 0.5)));
  System.out.println("Nearest to (0.4, 0.4): " + pointSet.nearest(new Point2D(0.4, 0.4)));

  pointSet.draw(); // This will open a window to draw points
 }
}
