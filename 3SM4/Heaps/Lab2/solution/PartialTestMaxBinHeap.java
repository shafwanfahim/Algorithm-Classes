package solution;

public class PartialTestMaxBinHeap {

    public static void main(String[] args) {

        // Data for testing
        int[] arr1 = { 1, 5, 4, 3, 20, 18, 16, 14, 10, 12 };
        int[] arr2 = { 5, 4, 3, 11, 35, 56 };
        int[] arr3 = { 11, 91, 40, 5, 111, 14, 8, 68, 27, 1, 22, 44, 28, 555, 99, 33, 69, 3, 7, 56, 78, 49, 12, 9, 101,
                232, 115, 81, 187, 333, 191, 4, 26, 439, 100, 63, 2, 568 };

        // ----------------------------------- Test Begins Here
        // ------------------------------------
        System.out.println("---------------------**TEST BEGINS**--------------------------------");
        System.out.println("\n--------------Testing MaxBinHeap class--------------------------------\n");

        // Test 1: Testing MaxBinHeap constructor for an empty Heap
        System.out.println("--------Test 1------------");
        System.out.println("Testing MaxBinHeap constructor for an empty Heap:");
        MaxBinHeap heap1 = new MaxBinHeap(10);
        System.out.println(" #keys stored in the empty heap: " + heap1.getSize());
        System.out.println(" Expected #keys stored in the empty heap = 0\n");

        // Test 2: Testing MaxBinHeap constructor with an array
        System.out.println("--------Test 2------------");
        System.out.println("Testing MaxBinHeap constructor with arr1:");
        MaxBinHeap heap2 = new MaxBinHeap(arr1);
        System.out.println(" #items stored in the heap: " + heap2.getSize());
        System.out.println(" Expected #keys stored in the heap = 10");
        System.out.println("Heap: " + heap2 + "\n");
        System.out.println("Expected Heap: 20, 14, 18, 10, 12, 4, 16, 3, 1, 5\n");

        // Test 3: Testing readMax method
        System.out.println("--------Test 3------------");
        System.out.println("Testing readMax method for the heap:");
        System.out.println(" Max value in the heap: " + heap2.readMax());
        System.out.println(" Expected max value = 20\n");

        // Test 4: Testing insert method
        System.out.println("--------Test 4------------");
        heap2.insert(30);
        System.out.println(" Testing insert method by adding 30:");
        System.out.println(" #items stored in the heap after insertion: " + heap2.getSize());
        System.out.println(" Expected #keys stored in the heap = 11");
        System.out.println("Heap after insertion: " + heap2 + "\n");

        // Test 5: Testing deleteMax method
        System.out.println("--------Test 5------------");
        int maxVal = heap2.deleteMax();
        System.out.println("Testing deleteMax method:");
        System.out.println(" Max value extracted using deleteMax: " + maxVal);
        System.out.println(" Expected value: 30");
        System.out.println(" #items stored in the heap after deleteMax: " +
                heap2.getSize());
        System.out.println(" Expected #keys stored in the heap after deleteMax = 10");
        System.out.println("Heap after deleting max: " + heap2 + "\n");

        // // Test 6: Testing sortArray method
        System.out.println("--------Test 6------------");
        System.out.println("Testing sortArray method with arr2:");
        MaxBinHeap.sortArray(arr2);
        System.out.print("Sorted arr2: ");
        for (int n : arr2)
            System.out.print(n + " ");
        System.out.println("\nExpected sorted array: 56, 35, 11, 5, 4, 3");

        System.out.println(
                "\n-----------------------------Testing Done for MaxBinHeap!-------------------------------------");
    }
}