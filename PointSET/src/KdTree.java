import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private static class Node {
        private Point2D point;
        private RectHV rect;
        private boolean orientation; // true for vertical, false for horizontal
        private Node left;
        private Node right;

        public Node(Point2D p, RectHV rect, boolean orientation) {
            point = p;
            this.rect = rect;
            this.orientation = orientation;
        }
    }

    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        if (point == null) throw new IllegalArgumentException("Point cannot be null");
        root = insert(root, point, VERTICAL, new RectHV(0, 0, 1, 1));
    }

    private Node insert(Node node, Point2D point, boolean orientation, RectHV rect) {
        if (node == null) {
            size++;
            return new Node(point, rect, orientation);
        }

        if (node.point.equals(point)) return node;

        double cmp = (orientation == VERTICAL) ? Point2D.X_ORDER.compare(point, node.point)
                : Point2D.Y_ORDER.compare(point, node.point);

        if (cmp < 0) {
            RectHV nextRect = (orientation == VERTICAL)
                    ? new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax())
                    : new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
            node.left = insert(node.left, point, !orientation, nextRect);
        } else {
            RectHV nextRect = (orientation == VERTICAL)
                    ? new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax())
                    : new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
            node.right = insert(node.right, point, !orientation, nextRect);
        }

        return node;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D p) {
        if (node == null) return false;
        if (node.point.equals(p)) return true;

        if (isLessThanPoint(p, node.point, node.orientation)) {
            return contains(node.left, p);
        } else {
            return contains(node.right, p);
        }
    }

    private boolean isLessThanPoint(Point2D p1, Point2D p2, boolean orientation) {
        if (orientation == VERTICAL) return p1.x() < p2.x();
        else return p1.y() < p2.y();
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) return;

        drawPoint(node.point);
        drawDivision(node);

        draw(node.left);
        draw(node.right);
    }

    private void drawPoint(Point2D p) {
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLACK);

        p.draw();
    }

    private void drawDivision(Node node) {
        StdDraw.setPenRadius();
        if (node.orientation == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Rectangle cannot be null");
        List<Point2D> points = new ArrayList<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node node, RectHV rect, List<Point2D> points) {
        if (node == null) return;
        if (!rect.intersects(node.rect)) return;

        if (rect.contains(node.point)) points.add(node.point);

        range(node.left, rect, points);
        range(node.right, rect, points);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        if (isEmpty()) return null;
        return nearest(root, p, root.point);
    }

    private Point2D nearest(Node node, Point2D query, Point2D nearest) {
        if (node == null) return nearest;
        if (node.rect.distanceSquaredTo(query) > nearest.distanceSquaredTo(query)) return nearest;

        if (node.point.distanceSquaredTo(query) < nearest.distanceSquaredTo(query)) {
            nearest = node.point;
        }

        Node first, second;
        if (isLessThanPoint(query, node.point, node.orientation)) {
            first = node.left;
            second = node.right;
        } else {
            first = node.right;
            second = node.left;
        }

        nearest = nearest(first, query, nearest);
        nearest = nearest(second, query, nearest);

        return nearest;
    }
}