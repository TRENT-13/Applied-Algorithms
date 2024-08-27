import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationVisualizer {

    private static final int DELAY = 10;

    public static void main(String[] args) {
        int gridSize = 20;
        Percolation percolation = new Percolation(gridSize);

        // Set up the drawing canvas
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0, gridSize);
        StdDraw.setYscale(0, gridSize);

        drawGrid(percolation, gridSize);

        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, gridSize + 1);
            int col = StdRandom.uniform(1, gridSize + 1);
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
                updateSite(percolation, gridSize, row, col);
                updateConnectedSites(percolation, gridSize);
                StdDraw.pause(DELAY);
            }
        }
    }

    private static void drawGrid(Percolation percolation, int gridSize) {
        for (int row = 1; row <= gridSize; row++) {
            for (int col = 1; col <= gridSize; col++) {
                drawSite(percolation, gridSize, row, col);
            }
        }
    }

    private static void updateSite(Percolation percolation, int gridSize, int row, int col) {
        drawSite(percolation, gridSize, row, col);
    }

    private static void updateConnectedSites(Percolation percolation, int gridSize) {
        for (int row = 1; row <= gridSize; row++) {
            for (int col = 1; col <= gridSize; col++) {
                if (percolation.isFull(row, col)) {
                    drawSite(percolation, gridSize, row, col);
                }
            }
        }
    }

    private static void drawSite(Percolation percolation, int gridSize, int row, int col) {
        if (percolation.isFull(row, col)) {
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        } else if (percolation.isOpen(row, col)) {
            StdDraw.setPenColor(StdDraw.WHITE);
        } else {
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        StdDraw.filledSquare(col - 0.5, gridSize - row + 0.5, 0.5);
    }
}

class Percolation {
    private static final int TOP = 0;
    private final boolean[][] opened;
    private final int size;
    private final int bottom;
    private int openSites;
    private final WeightedQuickUnionUF qf;

    // constructor
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
        openSites = 0;
    }

    // main logic
    public void open(int row, int col) {
        checkException(row, col);
        if (isOpen(row, col)) return; // Avoid reopening already open sites
        opened[row - 1][col - 1] = true; // Open the box
        ++openSites; // Increment the number of open sites

        // Edge Case => If any of the top row boxes are opened => Union(box, top)
        if (row == 1) {
            qf.union(getQuickFindIndex(row, col), TOP);
        }

        if (row == size) {
            qf.union(getQuickFindIndex(row, col), bottom);
        }

        if (row > 1 && isOpen(row - 1, col)) {
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row - 1, col));
        }

        if (row < size && isOpen(row + 1, col)) {
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row + 1, col));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1));
        }

        if (col < size && isOpen(row, col + 1)) {
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1));
        }
    }

    // costum exception model
    private void checkException(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
    }

    // checks if it is open
    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return opened[row - 1][col - 1];
    }

    // just return already computed open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // idk just wrote
    public boolean isFull(int row, int col) {
        if ((row > 0 && row <= size) && (col > 0 && col <= size)) {
            return qf.find(TOP) == qf.find(getQuickFindIndex(row, col));
        } else throw new IllegalArgumentException();
    }

    // index formula
    private int getQuickFindIndex(int row, int col) {
        return size * (row - 1) + col; // Adjusted to fit the valid range
    }

    // if system percolates return boolean
    public boolean percolates() {
        return qf.find(TOP) == qf.find(bottom); // If top is connected to bottom, then system percolates
    }
}
