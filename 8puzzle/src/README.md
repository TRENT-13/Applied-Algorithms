# Sliding Puzzle Solver in Java

This project implements a solution to the sliding puzzle problem using the A* search algorithm. It consists of two main classes: `Board` and `Solver`. The `Board` class represents the state of the puzzle, while the `Solver` class attempts to solve the puzzle using A* search.

## Table of Contents

- [Overview](#overview)
- [Classes and Methods](#classes-and-methods)
  - [Board Class](#board-class)
  - [Solver Class](#solver-class)
- [Getting Started](#getting-started)
  - [Dependencies](#dependencies)
  - [Compiling and Running](#compiling-and-running)


## Overview

The sliding puzzle (also known as the 8-puzzle or 15-puzzle) consists of a square board with numbered tiles and one blank space. The goal is to rearrange the tiles to match a specific target configuration. This project solves the puzzle using A* search, an informed search algorithm.

- **Board**: Represents a state of the puzzle with methods to calculate its heuristic values, generate neighboring boards, and check for solution state.
- **Solver**: Utilizes the A* search algorithm to find the shortest sequence of moves that solves the puzzle.

## Classes and Methods

### Board Class

The `Board` class represents the current state of the sliding puzzle.

- **Constructor**: `Board(int[][] tiles)` initializes the board with a 2D array.
- **`toString()`**: Returns a string representation of the board.
- **`dimension()`**: Returns the dimension of the board.
- **`hamming()`**: Calculates the number of tiles out of place.
- **`manhattan()`**: Calculates the sum of the Manhattan distances of all tiles from their goal positions.
- **`isGoal()`**: Checks if the board is in the goal state.
- **`equals(Object y)`**: Checks if two boards are equal.
- **`neighbors()`**: Returns an iterable of all neighboring boards by moving the blank space.
- **`twin()`**: Returns a new board obtained by swapping two adjacent tiles.
- **Private Helper Methods**:
  - `isValidMove(int row, int col)`: Checks if a move is valid.
  - `copyBoard(int[][] board, int dimension)`: Creates a deep copy of the board.
  - `index(int x, int y)`: Calculates the correct position index for a tile.
  - `blankFinder()`: Finds the location of the blank tile.
  - `copyTiles()`: Creates a copy of the board tiles.

### Solver Class

The `Solver` class uses the A* search algorithm to solve the puzzle.

- **Constructor**: `Solver(Board initial)` initializes the solver with the initial board and starts the A* search.
- **`isSolvable()`**: Returns `true` if the board is solvable, `false` otherwise.
- **`moves()`**: Returns the minimum number of moves required to solve the board; returns `-1` if unsolvable.
- **`solution()`**: Returns an iterable of boards representing the shortest solution sequence; returns `null` if unsolvable.
- **Private Helper Methods**:
  - `enqueueNeighbors(SearchBoard searchBoard, MinPQ<SearchBoard> queue)`: Adds the neighboring boards of a given search board to the priority queue.

### SearchBoard Inner Class

The `SearchBoard` class is an inner class used by the `Solver` class to represent the state of a board during the search process.

- **Constructor**: `SearchBoard(Board board, int moves, SearchBoard previousBoard)` initializes a search board with the current board state, number of moves, and the previous board state.
- **`compareTo(SearchBoard that)`**: Compares two search boards based on their priority (Manhattan distance + number of moves).

## Getting Started

### Dependencies

- Java Development Kit (JDK)
- [Princeton's Standard Libraries](https://introcs.cs.princeton.edu/java/code/)
  - `StdDraw` for graphical representation (optional)
  - `In` for reading input from files
  - `StdOut` for standard output
  - `MinPQ` for the priority queue implementation
  - `Stack` for managing sequences of boards

### Compiling and Running

1. **Compile** all Java files:
   ```bash
   javac Board.java Solver.java
