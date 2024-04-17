package solution;

import java.util.ArrayList;

public class YoungT {
    private int[][] tableau;
    private int num;
    private final int inf;

    public YoungT(int k, int n, int infinity) {
        if (n < 10) {
            n = 10;
        }
        if (k < 10) {
            k = 10;
        }
        if (infinity < 100) {
            infinity = 100;
        }
        this.tableau = new int[k][n];
        this.num = 0;
        this.inf = infinity;

        // fill the tableau up with infinity
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                tableau[i][j] = infinity;
            }
        }
    }

    public YoungT(int[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        tableau = new int[rows][cols];

        // Find the largest element in the input array a
        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (a[i][j] > maxElement) {
                    maxElement = a[i][j];
                }
            }
        }

        // Set the value for ∞ to 10 times the largest element
        inf = 10 * maxElement;

        // Store the integers from the input array a in the Young tableau
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tableau[i][j] = a[i][j];
                if (a[i][j] != inf) {
                    num += 1;
                }
            }
        }

        // Fix the Young tableau properties
        fixTableau();
        fixTableau();
    }

    private void fixTableau() {
        int rows = tableau.length;
        int cols = tableau[0].length;

        // Traverse the tableau and fix non-decreasing order properties
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                fixCell(i, j);
            }
        }
    }

    private void fixCell(int row, int col) {
        int value = tableau[row][col];
        // set above and left indices if they exist
        int above = (row > 0) ? tableau[row - 1][col] : inf;
        int left = (col > 0) ? tableau[row][col - 1] : inf;

        // If the current cell violates the non-decreasing order properties,
        // swap it with the minimum of its above and left neighbors
        if (row > 0 && value < above) {
            swap(row, col, row - 1, col);
            fixCell(row - 1, col);
        }
        if (col > 0 && value < left) {
            swap(row, col, row, col - 1);
            fixCell(row, col - 1);
        }
    }

    public int getNumElem() {
        return num;
    }

    public int getInfinity() {
        return inf;
    }

    public boolean isEmpty() {
        if (tableau[0][0] == inf) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        int k = tableau.length;
        int n = tableau[0].length;

        if (tableau[k - 1][n - 1] < inf) {
            return true;
        }
        return false;
    }

    private void swap(int a, int b, int c, int d) {
        int temp = tableau[a][b];
        tableau[a][b] = tableau[c][d];
        tableau[c][d] = temp;
    }

    private boolean max_first(int a, int b) {
        if (a > b) {
            return true;
        }

        return false;
    }

    public boolean insert(int x) {
        // check if x is >= inf or is full return false
        // first key is at max location
        // if x is greater than above and left we return true
        // if not, then we recursively call on the max of the two
        // if x is not greater we break, if key is on col 0 or row 0 and there is a max
        // we break

        if (x >= inf || this.isFull()) {
            return false;
        }
        int k = tableau.length - 1; // last row
        int n = tableau[0].length - 1; // last col

        return insertRec(k, n, x); // start at max
    }

    private boolean insertRec(int k, int n, int x) {
        // Check if indices go out of bounds
        if (k < 0 || n < 0) {
            return false;
        }

        // If x is greater than or equal to the neighbors, return true
        if (x >= tableau[k][n - 1] && x >= tableau[k - 1][n]) {
            return true;
        }

        // Determine whether to move up or left based on which neighbor is greater
        if (max_first(tableau[k - 1][n], tableau[k][n - 1])) {
            // Move up if the neighbor above is greater
            return insertRec(k - 1, n, x);
        } else {
            // otherwise move left
            return insertRec(k, n - 1, x);
        }
    }

    public int readMin() {
        if (tableau[0][0] == inf) {
            throw new RuntimeException("Tableau is empty");
        }
        return tableau[0][0];
    }

    public int deleteMin() {
        // delete the first element, as in, set it to inf
        // then fixTableau()
        int min = tableau[0][0];
        if (min == inf) {
            throw new RuntimeException("Tableau is empty");
        }
        tableau[0][0] = inf;
        fixTableau();
        // calling it twice to make sure tableau is fixed
        num -= 1;
        return min;
    }

    public boolean find(int x) {
        if (this.isEmpty()) {
            throw new RuntimeException("Tableau is empty");
        }
        int k = tableau.length; // number of rows
        int n = tableau[0].length; // number of cols
        int key = tableau[0][n - 1]; // might be unnecessary here
        ArrayList<Integer> seq = new ArrayList<>();

        boolean exists = findRec(0, n - 1, x, seq);
        System.out.println(arraytoString(seq));
        return exists;
    }

    private boolean findRec(int k, int n, int x, ArrayList<Integer> sequence) {
        if (k >= tableau.length || k < 0 || n < 0 || n >= tableau[0].length) {
            return false; // Reached the boundaries of the tableau, x not found
        }

        if (tableau[k][n] == x) {
            return true;
        }

        // Add the current value to the sequence
        sequence.add(tableau[k][n]);

        // If the current value is less than x, move downwards
        if (tableau[k][n] < x) {
            return findRec(k + 1, n, x, sequence);
        }

        // If the current value is greater than x, move to the left
        return findRec(k, n - 1, x, sequence);
    }

    private String arraytoString(ArrayList<Integer> s) {
        String output = "";
        for (int i = 0; i < s.size(); i++) {
            output += s.get(i);
            if (i != s.size() - 1) {
                output += ", ";
            }
        }
        return output;
    }

    public int findMaxElement() {
        int maxElement = Integer.MIN_VALUE; // Initialize maxElement to the smallest possible integer value

        // Iterate through each element in the tableau
        for (int[] row : tableau) {
            for (int value : row) {
                // Update maxElement if the current value is greater than the current maxElement
                if (value > maxElement) {
                    maxElement = value;
                }
            }
        }

        return maxElement;
    }

    public String toString() {
        int k = tableau.length;
        int n = tableau[0].length;
        int inf_val = findMaxElement();
        String output = "";

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                if (tableau[i][j] == inf_val) {
                    output += "inf";
                } else {
                    output += tableau[i][j]; // Concatenate each element
                }

                if (j < n - 1) {
                    output += ", "; // Add comma and space if it's not the last element in the row
                }
            }
            if (i < k - 1) {
                output += ", "; // Add comma and space if it's not the last row
            }
        }

        return output;
    }

}
