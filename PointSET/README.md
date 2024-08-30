# KdTree and PointSET Implementations

This repository contains implementations of a Kd-Tree and a simple Point Set (PointSET) data structure. The Kd-Tree is a binary search tree used for organizing points in a 2-dimensional space, while PointSET is a simple set of 2D points using an internal BST.

## Overview

- **KdTree**: A 2D tree that supports efficient insertions, point searches, range searches, and nearest neighbor queries.
- **PointSET**: A straightforward set of points using a BST to provide similar functionality but without spatial partitioning.

## KdTree

### Class Structure and Key Components

1. **Node Class**:
   - Represents a single node in the KdTree.
   - Fields:
     - `Point2D point`: Stores the point associated with the node.
     - `RectHV rect`: The axis-aligned rectangle representing the node's space.
     - `boolean orientation`: Orientation of the node (vertical or horizontal).
     - `Node left, right`: Left and right children nodes.

2. **Main Methods**:
   - **`insert(Point2D point)`**: Adds a point to the KdTree. Points are recursively inserted based on alternating vertical and horizontal splits.
   - **`contains(Point2D p)`**: Checks if a point exists in the tree by traversing it according to the spatial partitioning.
   - **`draw()`**: Visualizes the KdTree, drawing each node's point and its division line (vertical in red, horizontal in blue).
   - **`range(RectHV rect)`**: Finds all points within a given rectangular region by traversing nodes and checking for rectangle intersection.
   - **`nearest(Point2D p)`**: Finds the closest point to a given query point using a recursive nearest neighbor search.

### Detailed Method Explanation

#### `insert(Node node, Point2D point, boolean orientation, RectHV rect)`
- Recursively inserts the point into the tree:
  - If the tree is empty at the target node, it creates a new node.
  - If the point already exists, no insertion is done.
  - Based on the node's orientation, it compares the point against the node's point and proceeds left or right.
  - Updates the bounding rectangle for each child node accordingly.

#### `contains(Node node, Point2D p)`
- Checks recursively whether the point exists:
  - Compares the point with the node’s point.
  - Proceeds left or right based on the orientation of the current node.

#### `draw(Node node)`
- Recursively draws the tree using `StdDraw`.
- Each point is drawn in black, vertical lines in red, and horizontal lines in blue.

#### `range(Node node, RectHV rect, List<Point2D> points)`
- Recursively finds all points within the rectangle:
  - Only considers nodes whose rectangles intersect the query rectangle.
  - Adds points to the list if they are within the range.

#### `nearest(Node node, Point2D query, Point2D nearest)`
- Recursively searches for the nearest point:
  - Starts with the nearest point being the root.
  - Prunes subtrees that can't contain closer points based on bounding box distances.
  - Checks both subtrees, starting with the subtree that is more likely to contain the nearest point.

## PointSET

### Class Structure and Key Components

1. **SET<Point2D> points**:
   - A BST-based set to store unique 2D points.

2. **Main Methods**:
   - **`insert(Point2D p)`**: Adds a point to the set if it is not already present.
   - **`contains(Point2D p)`**: Checks whether a point exists in the set.
   - **`draw()`**: Draws all the points in the set using `StdDraw`.
   - **`range(RectHV rect)`**: Returns all points within the given rectangle.
   - **`nearest(Point2D p)`**: Finds and returns the nearest point to the given query.

### Detailed Method Explanation

#### `insert(Point2D p)`
- Adds the point to the set if it is not already present. Throws an exception if the point is null.

#### `contains(Point2D p)`
- Checks if the set contains the specified point.

#### `draw()`
- Draws all points in the set using black circles.

#### `range(RectHV rect)`
- Iterates over the set and collects all points inside the given rectangle.

#### `nearest(Point2D p)`
- Iterates over all points in the set to find the one closest to the given point.

## Usage and Testing

- The KdTree and PointSET classes can be tested using sample points.
- The `draw()` methods will visually display the points and spatial divisions.
- The `nearest()` and `range()` methods can be used to validate spatial searches.

## Dependencies

The project relies on classes from the Princeton Algorithms library:
- `Point2D`: Represents a point in a 2D space.
- `RectHV`: Represents an axis-aligned rectangle in 2D space.
- `StdDraw`: Used for drawing points and lines.
- `SET`: A BST-based set implementation.

## Conclusion

This implementation of KdTree efficiently handles spatial operations like nearest neighbor and range searching, making it suitable for large datasets in 2D. The PointSET provides similar functionalities with simpler but less efficient data handling.
