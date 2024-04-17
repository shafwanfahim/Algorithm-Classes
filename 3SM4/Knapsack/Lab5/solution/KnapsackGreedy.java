package solution;

import java.util.Arrays;
import java.util.Scanner;

class KnapsackGreedy {
    private int totalWeight;
    private double totalValue;
    private int[] weights;
    private int[] values;
    private int[] selectedItems;

    public KnapsackGreedy(String input) {
        String[] tokens = input.split(" ");
        int n = Integer.parseInt(tokens[0]); //num of items 
        totalWeight = Integer.parseInt(tokens[1]); // capacity 
        values = new int[n]; //values of items
        weights = new int[n]; //weights of items 
        selectedItems = new int[n]; //selected items in the set

        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(tokens[2 + 2 * i]);
            weights[i] = Integer.parseInt(tokens[3 + 2 * i]);
        }
    }

    public static String fractional(String input) {
        KnapsackGreedy knapsack = new KnapsackGreedy(input);
        knapsack.solveFractionalKnapsack();
        return knapsack.formatSolution();
    }

    private void solveFractionalKnapsack() { 
        //calculates value per weight ratio
        double[] valuePerWeight = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            valuePerWeight[i] = (double) values[i] / weights[i];
        }

        //sort items based on value per weight in decreasing order 
        Integer[] indices = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Double.compare(valuePerWeight[b], valuePerWeight[a]));

        double remainingWeight = totalWeight;
        totalValue = 0;
        double totalSelectedWeight = 0; // Track total weight of selected items

        //greedily select items until knapsack is full and mark them as selected 
        for (int i : indices) {
            if (remainingWeight >= weights[i]) {
                totalValue += values[i];
                totalSelectedWeight += weights[i];
                remainingWeight -= weights[i];
            } else {
                totalValue += valuePerWeight[i] * remainingWeight;
                totalSelectedWeight += remainingWeight;
                break;
            }
        }
        totalWeight = (int) totalSelectedWeight; // Update total weight
    }

    private String formatSolution() {
        StringBuilder solution = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            double fraction = weights[i] > 0 ? (double) values[i] / weights[i] : 0.0;
            solution.append(String.format("%.1f, ", fraction));
        }
        solution.append(String.format("total value = %.2f, total weight = %d", totalValue, totalWeight));
        return solution.toString();
    }

    public static String greedy01(String input) {
        KnapsackGreedy knapsack = new KnapsackGreedy(input);
        knapsack.solve01Knapsack();
        return knapsack.formatSolution2();
    }

    private void solve01Knapsack() {
        // Create a list of items with their value-to-weight ratios
        Item[] items = new Item[values.length];
        for (int i = 0; i < values.length; i++) {
            items[i] = new Item(i, (double) values[i] / weights[i]);
        }

        // Sort items based on their value-to-weight ratios in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.valueWeightRatio, a.valueWeightRatio));

        // Initialize remaining weight in the knapsack
        int remainingWeight = totalWeight;

        // Greedily select items until knapsack is full
        for (Item item : items) {
            if (remainingWeight >= weights[item.index]) {
                selectedItems[item.index] = 1; // Select the item
                remainingWeight -= weights[item.index]; // Update remaining weight
            }
        }
        totalWeight -= remainingWeight; // Update totalWeight after selecting items
    }

    private String formatSolution2() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < selectedItems.length; i++) {
            result.append(selectedItems[i]).append(", ");
        }
        result.append(String.format("total value = %d, total weight = %d", calculateTotalValue(), totalWeight));
        return result.toString();
    }

    private int calculateTotalValue() {
        int totalValue = 0;
        for (int i = 0; i < selectedItems.length; i++) {
            totalValue += selectedItems[i] * values[i];
        }
        return totalValue;
    }

    private static class Item {
        int index;
        double valueWeightRatio;

        Item(int index, double valueWeightRatio) {
            this.index = index;
            this.valueWeightRatio = valueWeightRatio;
        }
    }

}