import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE = 1.96;
    private final int experimentsCount;
    private final double[] fractions;

    /**
     * T independent trials N-N Grid
     */

    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
        experimentsCount = t;
        fractions = new double[experimentsCount];
        for (int expNum = 0; expNum < experimentsCount; expNum++) {
            Percolation pr = new Percolation(n);
            int openedSites = 0;
            while (!pr.percolates()) {
                int i = StdRandom.uniformInt(n) + 1;
                int j = StdRandom.uniformInt(n) + 1;

                if (!pr.isOpen(i, j)) {
                    pr.open(i, j);
                    openedSites++;
                }
            }
            double fraction = (double) openedSites / (n * n);
            fractions[expNum] = fraction;
        }
    }

    /**
     * Mean
     */

    public double mean() {
        return StdStats.mean(fractions);
    }

    /**
     * Stadart Deviation.
     */

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    /**
     * Lower Bound.
     */

    public double confidenceLo() {
        return mean() - ((CONFIDENCE * stddev()) / Math.sqrt(experimentsCount));
    }

    /**
     * Upper bound
     */

    public double confidenceHi() {
        return mean() + ((CONFIDENCE * stddev()) / Math.sqrt(experimentsCount));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}