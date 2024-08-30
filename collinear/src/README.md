# Collinear Points in Java

This project provides a solution to identify collinear points in a 2D plane using two different algorithms: **Brute Force** and **Fast Collinear Points**. It includes the `Point` class to represent a point in the plane, and two classes: `BruteCollinearPoints` and `FastCollinearPoints` that find all sets of four or more collinear points.

## Table of Contents
- [Overview](#overview)
- [Algorithms](#algorithms)
  - [Brute Force Approach](#brute-force-approach)
  - [Fast Approach](#fast-approach)
- [Classes and Methods](#classes-and-methods)
  - [Point Class](#point-class)
  - [BruteCollinearPoints Class](#brutecollinearpoints-class)
  - [FastCollinearPoints Class](#fastcollinearpoints-class)
- [Getting Started](#getting-started)
  - [Dependencies](#dependencies)
  - [Compiling and Running](#compiling-and-running)
- [Usage](#usage)
- [Example](#example)
- [License](#license)

## Overview

The goal of this project is to find all line segments that connect four or more collinear points from a set of points in a 2D plane. 

- **Collinear Points**: Points are collinear if they lie on a single straight line.
- **Line Segments**: A line segment between two points is drawn using the `drawTo()` method.

Two different approaches are implemented:
1. **Brute Force Method**: Check all combinations of four points to find collinearity.
2. **Fast Method**: Sort points based on slopes to identify collinear points more efficiently.

## Algorithms

### Brute Force Approach

The `BruteCollinearPoints` class implements the brute-force approach:
- **Complexity**: O(N^4), where N is the number of points.
- **Method**: Checks all combinations of four points to determine if they are collinear.

### Fast Approach

The `FastCollinearPoints` class uses a more optimized approach:
- **Complexity**: O(N^2 log N), where N is the number of points.
- **Method**: Sorts the array of points by the slopes they form with a reference point and checks for collinearity.

## Classes and Methods

### Point Class

Represents an immutable point in a 2D plane. Key methods include:
- **Constructor**: `Point(int x, int y)` initializes a point with x and y coordinates.
- **`draw()`**: Draws the point using `StdDraw`.
- **`drawTo(Point that)`**: Draws a line segment between this point and another point.
- **`slopeTo(Point that)`**: Returns the slope between this point and another point.
- **`compareTo(Point that)`**: Compares two points by their y-coordinates, breaking ties by x-coordinates.
- **`slopeOrder()`**: Returns a comparator to compare points by the slopes they make with this point.

### BruteCollinearPoints Class

Finds all line segments containing 4 points using the brute-force method:
- **Constructor**: `BruteCollinearPoints(Point[] points)` checks input validity and finds all line segments.
- **`numberOfSegments()`**: Returns the number of line segments found.
- **`segments()`**: Returns the line segments found as an array.

### FastCollinearPoints Class

Finds all line segments containing 4 or more points using a faster method:
- **Constructor**: `FastCollinearPoints(Point[] points)` checks input validity, sorts points, and finds all line segments.
- **`numberOfSegments()`**: Returns the number of line segments found.
- **`segments()`**: Returns the line segments found as an array.

## Getting Started

### Dependencies

- Java Development Kit (JDK)
- [Princeton's Standard Libraries](https://introcs.cs.princeton.edu/java/code/)
  - `StdDraw` for drawing points and line segments
  - `In` for reading input from files
  - `StdOut` for standard output
  - java FastCollinearPoints input.txt


### Compiling and Running

1. **Compile** all Java files:
   ```bash
   javac Point.java BruteCollinearPoints.java FastCollinearPoints.java
