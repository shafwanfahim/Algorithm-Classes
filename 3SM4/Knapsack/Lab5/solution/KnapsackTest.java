package solution;

public class KnapsackTest {
    public static void main(String[] args) {

        String s1 = "4 8 7 10 2 3 1 5 9 2";
        System.out.println("Input: " + s1);

        System.out.println();
        System.out.println("Solution using recursion with memoization:");
        System.out.println(KnapsackDP.recMemo(s1));

        System.out.println();
        System.out.println("Solution based on bottom-up dynamic programming:");
        System.out.println(KnapsackDP.nonRec(s1));

        System.out.println();
        System.out.println("Solution to the fractional variant:");
        System.out.println(KnapsackGreedy.fractional(s1));

        System.out.println();
        System.out.println("Result of the greedy algorithm for the 0-1 variant:");
        System.out.println(KnapsackGreedy.greedy01(s1));

        String s2 = "1 8 7 3";
        System.out.println();
        System.out.println("Input: " + s2);

        System.out.println();
        System.out.println("Solution using recursion with memoization:");
        System.out.println(KnapsackDP.recMemo(s2));

        System.out.println();
        System.out.println("Solution based on bottom-up dynamic programming:");
        System.out.println(KnapsackDP.nonRec(s2));

        System.out.println();
        System.out.println("Solution to the fractional variant:");
        System.out.println(KnapsackGreedy.fractional(s2));

        System.out.println();
        System.out.println("Result of the greedy algorithm for the 0-1 variant:");
        System.out.println(KnapsackGreedy.greedy01(s2));

        String s3 = "5 12 9 10 1 4 5 6 5 6 1 5";
        System.out.println();
        System.out.println("Input: " + s3);

        System.out.println();
        System.out.println("Solution using recursion with memoization:");
        System.out.println(KnapsackDP.recMemo(s3));

        System.out.println();
        System.out.println("Solution based on bottom-up dynamic programming:");
        System.out.println(KnapsackDP.nonRec(s3));

        System.out.println();
        System.out.println("Solution to the fractional variant:");
        System.out.println(KnapsackGreedy.fractional(s3));

        System.out.println();
        System.out.println("Result of the greedy algorithm for the 0-1 variant:");
        System.out.println(KnapsackGreedy.greedy01(s3));
        System.out.println();

        // Test Case 1: Greedy algorithm gives optimal solution
        String testCase1 = "10 20 25 18 24 15 10 30 6 4 5 8 5 4 5 12 10 5 6 2 4 3";
        System.out.println("Test Case 1: Greedy algorithm gives optimal solution");
        System.out.println("Input: " + testCase1);
        System.out.println("Result of the greedy algorithm for the 0-1 variant:");
        System.out.println(KnapsackGreedy.greedy01(testCase1));
        System.out.println();

        // Test Case 2: Greedy algorithm gives suboptimal solution
        String testCase2 = "10 20 60 18 100 15 10 20 6 4 5 8 5 4 5 12 10 5 6 2 12 2";
        System.out.println("Test Case 2: Greedy algorithm gives suboptimal solution");
        System.out.println("Input: " + testCase2);
        System.out.println("Result of the greedy algorithm for the 0-1 variant:");
        System.out.println(KnapsackGreedy.greedy01(testCase2));
        System.out.println();

        // Test Case 3: Optimal solution uses all available target weight W
        String testCase3 = "10 50 60 10 100 10 20 20 10 30 5 20 15 10 10 20 20 10 15 10 20 10";
        System.out.println("Test Case 3: Optimal solution uses all available target weight W");
        System.out.println("Input: " + testCase3);
        System.out.println("Result of the greedy algorithm for the 0-1 variant");
        System.out.println(KnapsackGreedy.greedy01(testCase3));

    }

}