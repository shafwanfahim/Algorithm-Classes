package solution;

public class KnapsackDP {
    private int num;
    private int targetWeight;
    private int[] value;
    private int[] weight;
    private int[][] x;
    private int[][] totalValue;
    private int[][] totalWeight;

    public KnapsackDP(int num, int targetWeight, int[] value, int[] weight) {
        this.num = num;
        this.targetWeight = targetWeight;
        this.value = value;
        this.weight = weight;
        this.x = new int[num + 1][targetWeight + 1];
        this.totalValue = new int[num + 1][targetWeight + 1];
        this.totalWeight = new int[num + 1][targetWeight + 1];
    }

    public static String recMemo(String s) {
        // Extract input values from the input string
        String[] tokens = s.split(" ");
        int num = Integer.parseInt(tokens[0]);
        int targetWeight = Integer.parseInt(tokens[1]);
        int[] value = new int[num];
        int[] weight = new int[num];
        for (int i = 0; i < num; i++) {
            value[i] = Integer.parseInt(tokens[2 + 2 * i]);
            weight[i] = Integer.parseInt(tokens[3 + 2 * i]);
        }

        // Create a KnapsackDP object
        KnapsackDP knapsack = new KnapsackDP(num, targetWeight, value, weight);

        // Solve the problem using recursion with memoization
        knapsack.recMemo();

        // Return the result as a formatted string
        return knapsack.formatSolution();
    }

    private void recMemo() {
        // Iterate over each item and weight combination
        for (int i = 0; i <= num; i++) { // Loop through each item
            for (int w = 0; w <= targetWeight; w++) { // Loop through each possible weight
                // Base case: if no items or weight is zero, set total value and weight to zero
                if (i == 0 || w == 0) {
                    totalValue[i][w] = totalWeight[i][w] = 0;
                } else if (weight[i - 1] <= w) { // If the current item's weight can be included
                    int val = value[i - 1]; // Value of the current item
                    int wt = weight[i - 1]; // Weight of the current item
                    // Choose the maximum value between including and excluding the current item
                    totalValue[i][w] = Math.max(val + totalValue[i - 1][w - wt], totalValue[i - 1][w]);
                    // Update totalWeight only if the new value is greater
                    if (val + totalValue[i - 1][w - wt] > totalValue[i - 1][w]) {
                        totalWeight[i][w] = wt + totalWeight[i - 1][w - wt];
                    } else {
                        totalWeight[i][w] = totalWeight[i - 1][w];
                    }
                } else { // If the current item's weight exceeds the available weight
                    // Do not include the current item, use values from the previous row
                    totalValue[i][w] = totalValue[i - 1][w];
                    totalWeight[i][w] = totalWeight[i - 1][w];
                }
            }
        }
    }

    private String formatSolution() {
        // Build the solution string
        StringBuilder solution = new StringBuilder();
        solution.append("Solution to the 0-1 knapsack problem:\n");
        solution.append("Total value: ").append(totalValue[num][targetWeight]).append("\n");
        solution.append("Total weight: ").append(totalWeight[num][targetWeight]).append("\n");
        return solution.toString();
    }

    public static String nonRec(String s) {
        // Extract input values from the input string
        String[] tokens = s.split(" ");
        int num = Integer.parseInt(tokens[0]);
        int targetWeight = Integer.parseInt(tokens[1]);
        int[] value = new int[num];
        int[] weight = new int[num];
        for (int i = 0; i < num; i++) {
            value[i] = Integer.parseInt(tokens[2 + 2 * i]);
            weight[i] = Integer.parseInt(tokens[3 + 2 * i]);
        }

        // Create a KnapsackDP object
        KnapsackDP knapsack = new KnapsackDP(num, targetWeight, value, weight);

        // Solve the problem using bottom-up dynamic programming
        knapsack.nonRec();

        // Return the result as a formatted string
        return knapsack.formatSolution();
    }

    private void nonRec() {
        // if we're taking the item then take the value on the row abve
        // if we dont take the value then we take the value of top and to the left plus
        // the current value
        for (int i = 0; i <= num; i++) {
            for (int w = 0; w <= targetWeight; w++) {
                if (i == 0 || w == 0) {
                    totalValue[i][w] = totalWeight[i][w] = 0;
                } else if (weight[i - 1] <= w) {
                    int val = value[i - 1];
                    int wt = weight[i - 1];
                    int include = val + totalValue[i - 1][w - wt];
                    int exclude = totalValue[i - 1][w];
                    if (include > exclude) {
                        totalValue[i][w] = include;
                        totalWeight[i][w] = wt + totalWeight[i - 1][w - wt];
                    } else {
                        totalValue[i][w] = exclude;
                        totalWeight[i][w] = totalWeight[i - 1][w];
                    }
                } else {
                    totalValue[i][w] = totalValue[i - 1][w];
                    totalWeight[i][w] = totalWeight[i - 1][w];
                }
            }
        }
    }

}
