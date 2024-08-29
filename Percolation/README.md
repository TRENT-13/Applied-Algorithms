# Percolation Simulation and Visualization

This project implements and visualizes a percolation model using a Monte Carlo simulation. The primary goal is to determine the threshold at which a system percolates, i.e., when there exists a path from the top to the bottom in a grid of open sites.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Visualization](#visualization)
- [Class Descriptions](#class-descriptions)
- [Dependencies](#dependencies)
  
## Introduction

Percolation is a concept from statistical physics that describes the behavior of connected clusters in a random graph. This project simulates percolation in an N x N grid, where sites can either be open or blocked. It determines the threshold when the system percolates, using the Monte Carlo method to estimate the probability.

## Features

- Simulation of percolation on an N x N grid.
- Visual representation of the percolation process using the `StdDraw` library.
- Calculation of the mean, standard deviation, and 95% confidence interval of the percolation threshold.
- Real-time updates of the grid during the percolation process.

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/percolation-simulation.git
   cd percolation-simulation
Compile the Java files:
Ensure you have Java and the Princeton algs4 library installed. Then compile the files using:
bashCopyjavac -cp .:algs4.jar PercolationVisualizer.java Percolation.java PercolationStats.java


Usage
Run the PercolationVisualizer to visualize the percolation process:
bashCopyjava -cp .:algs4.jar PercolationVisualizer
To perform statistical analysis and calculate the percolation threshold, run:
bashCopyjava -cp .:algs4.jar PercolationStats <grid-size> <number-of-trials>
Replace <grid-size> and <number-of-trials> with desired values.
Visualization
The PercolationVisualizer uses the StdDraw library to draw a grid, where:

Black squares represent blocked sites.
White squares represent open sites.
Blue squares represent full sites that are connected to the top.

The visualization dynamically updates as the percolation process proceeds, providing a real-time view of how the grid percolates.
Class Descriptions

Percolation: Handles the logic for opening sites, checking if they are open or full, and determining if the system percolates.
PercolationVisualizer: Visualizes the percolation process using the StdDraw library.
PercolationStats: Performs statistical analysis to calculate the mean, standard deviation, and 95% confidence interval of the percolation threshold over multiple trials.

Dependencies

Java SE Development Kit (JDK)
Princeton Algorithms Library (algs4.jar)
