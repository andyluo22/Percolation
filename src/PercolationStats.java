import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    int count_Experiment_Numbers = 0;
    int trials;
    int[] record_Trial_Samples;
    double mean;
    double stddev;
    double confidenceLo;
    double confidenceHi;
    int grid_Length;
    int grid_Size;


    /** Performs independent trials on an n-by-n grid
     *
     * @param n the size of the square grid
     * @param trials the number of trials to be done
     * @throws IllegalArgumentException if n<=0 or if trials <=0
     */
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        this.grid_Length = n;
        this.grid_Size = n * n;
        boolean percolates = false;
        int sites_Opened = 0;

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Please enter a valid grid number greater than 0 and a valid number of trials greater than 0");
        }
        Percolation object;
        record_Trial_Samples = new int[trials];

        for (int i = 0; i < trials; i++) {
            object = new Percolation(n);
            int rowCounter = 0;
            int colCounter = 0;
            while (sites_Opened == 0 || object.isOpen(rowCounter, colCounter) && !percolates) {
                int index_To_Open = (int) StdRandom.uniform(n * n);

                double truncate_OpenedSite = index_To_Open;
                double truncation = truncate_OpenedSite / this.grid_Length;

                if (truncation >= 1.0) {
                    rowCounter = (int) truncation;
                    colCounter = (int) ((truncation - (int) truncation) * grid_Length);
                } else {
                    rowCounter = 0;
                    colCounter = (int) (truncation * this.grid_Length);
                }

                if (!object.isOpen(rowCounter, colCounter)) {
                    object.open(rowCounter, colCounter);
                    count_Experiment_Numbers++;
                    sites_Opened++;
                }
                percolates = object.percolates();
            }

            record_Trial_Samples[i] = sites_Opened;
            sites_Opened = 0;
            percolates = false;
        }

        double sum = 0.0;
        double[] individual_means = new double[record_Trial_Samples.length];

        for (int i = 0; i < this.trials; i++) {
            individual_means[i] = (double) record_Trial_Samples[i] / this.grid_Size;
            sum += (double) record_Trial_Samples[i] / this.grid_Size;
        }
        this.mean = sum / this.record_Trial_Samples.length;
        System.out.println("Mean:" + this.mean);

        double numerator_Squared = 0;
        for (int i = 0; i < this.trials; i++) {
            numerator_Squared += Math.pow(individual_means[i] - this.mean, 2.0);
        }
        this.stddev = Math.sqrt(numerator_Squared / (this.record_Trial_Samples.length - 1));
        System.out.println("Standard Deviation:" + this.stddev);

        this.confidenceLo = this.mean - 1.96 * this.stddev / Math.sqrt(this.record_Trial_Samples.length);
        this.confidenceHi = this.mean + 1.96 * this.stddev / Math.sqrt(this.record_Trial_Samples.length);

        System.out.println("95% confidence interval" + "[" + this.confidenceLo + "," + this.confidenceHi);


    }

    /** Sample mean of percolation threshold
     *
     * @return the mean of the percolation threshold for the grid computed over n trials
     */
    public double mean() {
        return this.mean;
    }

    /** Sample standard deviation of percolation threshold
     *
     * @return the standard deviation of the percolation threshold for the grid computed over n trials
     */
    public double stddev() {
        return this.stddev();
    }

    /** Low endpoint of 95% confidence interval
     *
     * @return the low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return this.confidenceLo;
    }

    /** High endpoint of 95% confidence interval
     *
     * @return the high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return this.confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats object = new PercolationStats(10, 600);
    }
}
