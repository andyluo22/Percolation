import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    int totalSites;
    int blockedSites;
    WeightedQuickUnionUF sites1;
    int[][] grid;
    int[][] updatedGrid;
    int num_col_rows;
    int openedSites;


    /**
     * Create a new Percolation instance square grid
     *
     * @param n the length of the square grid
     */
    public Percolation(int n) {
        this.num_col_rows = n;
        this.totalSites = n * n;
        this.blockedSites = n * n;
        int counter = 0;
        sites1 = new WeightedQuickUnionUF(n * n);
        grid = new int[n][n];
        updatedGrid = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = counter;
                updatedGrid[row][col] = counter;
                counter++;
            }
        }
    }

    /**
     * Given a row and column position in the square grid open the site if not already open and union/join any
     * adjacent grid index elements into a singular connected set
     *
     * @param row the row position of the square grid
     * @param col the column position of the square grid
     */
    public void open(int row, int col) {
        int p;
        int q;
        if (updatedGrid[row][col] != grid[row][col]) {
            return;
        } else {
            updatedGrid[row][col]++;
            blockedSites--;
            openedSites++;
            //now union/"connect" any adjacent grid indexes
            if (row > 0) {
                int tempRow = row;
                tempRow--;
                p = updatedGrid[tempRow][col];
                q = grid[tempRow][col];
                if (p != q) {
                    sites1.union(grid[row][col], q);
                }
            }
            if (row < this.num_col_rows - 1) {
                int tempRow = row;
                tempRow++;
                p = updatedGrid[tempRow][col];
                q = grid[tempRow][col];
                if (p != q) {
                    sites1.union(grid[row][col], q);
                }
            }
            if (col > 0) {
                int tempCol = col;
                tempCol--;
                p = updatedGrid[row][tempCol];
                q = grid[row][tempCol];
                if (p != q) {
                    sites1.union(grid[row][col], q);
                }
            }
            if (col < this.num_col_rows - 1) {
                int tempCol = col;
                tempCol++;
                p = updatedGrid[row][tempCol];
                q = grid[row][tempCol];
                if (p != q) {
                    sites1.union(grid[row][col], q);
                }
            }

        }
    }

    /**
     * Given a site's position in the square grid, check to see if the site is open
     *
     * @param row the row of the site
     * @param col the column of the site
     * @return true if the site is open and false if not open
     */
    public boolean isOpen(int row, int col) {
        if (updatedGrid[row][col] != grid[row][col]) {
            return true;
        }
        return false;
    }

    /**
     * Given a site's position in the square grid, check to see if the site is full or connected to at least one of the
     * top most sites in the upper row
     *
     * @param row the row of the site
     * @param col the column of the site
     * @return true if the site is full and false if not full
     */
    public boolean isFull(int row, int col) {
        for (int i = 0; i < this.num_col_rows; i++) {
            if (this.sites1.connected(grid[0][i], grid[row][col])) {
                return true;
            }

        }
        return false;
    }

    /** Checks how many open sites there are in the grid
     *
     * @return the number of open sites
     */
    public int numberOfOpenSites() {
        return this.openedSites;
        /*totalSites - blockedSites;*/
    }

    /** Checks the percolation status of the square grid
     *
     * @return true if the system percolates and false if it does not
     */
    public boolean percolates() {
        for (int i = 0; i < num_col_rows; i++) {
            int topRow = grid[0][i];
            for (int j = 0; j < num_col_rows; j++) {
                int bottomRow = grid[num_col_rows - 1][j];
                if (this.sites1.connected(topRow, bottomRow)) {
                    return true;
                }
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation object = new Percolation(10);
        //this means that the set is already created
        boolean statement1 = object.sites1.connected(1, 1);
        System.out.println(statement1);
        object.sites1.union(1, 2);
        object.open(0, 0);
        object.open(0, 1);
        boolean statement2 = object.sites1.connected(0, 1);
        System.out.println(statement2);
        System.out.println(object.isOpen(1, 2));
        System.out.println(object.isOpen(1, 1));
        System.out.println(object.isOpen(0, 1));
        System.out.println(object.numberOfOpenSites()); //expect 2


        //Test isFull
        System.out.println("Testing isFull method");
        object.open(0, 3);
        object.open(1, 3);
        object.open(2, 3);
        object.open(3, 3);
        object.open(4, 3);

        System.out.println(object.isFull(3, 3));
        System.out.println(object.isFull(1, 3));
        System.out.println(object.isFull(4, 4));

        System.out.println("Testing if Percolates");
        object.open(5, 3);
        object.open(6, 3);
        object.open(7, 3);
        object.open(8, 3);
        object.open(9, 3);

        System.out.println(object.percolates());


    }

}
