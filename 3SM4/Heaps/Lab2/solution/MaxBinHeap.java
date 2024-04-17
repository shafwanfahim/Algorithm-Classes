package solution;

/**
 * MaxBinHeap
 */
public class MaxBinHeap {

    int[] heap;
    int size;

    public MaxBinHeap(int n) {
        if (n < 10) {
            n = 10;
        }

        heap = new int[n];
        size = 0;

    }

    public MaxBinHeap(int[] a) {

        size = a.length;
        heap = a;
        for (int i = (heap.length / 2); i >= 0; i--) {

            buildmaxheap(i, size);

        }

    }

    private void buildmaxheap(int level, int s) {
        if (heap.length <= 1) {
            return;
        }
        int l_index = 2 * level + 1;
        int r_index = 2 * level + 2;
        int largest = level;

        if (l_index < s && heap[l_index] > heap[largest]) {
            largest = l_index;
        }

        if (r_index < s && heap[r_index] > heap[largest]) {
            largest = r_index;
        }

        if (largest != level) {
            // There's a child that's larger; switch with the largest child, and
            // see if we're smaller than its children
            swap(largest, level);
            buildmaxheap(largest, s);
        }
    }

    private void swap(int pos1, int pos2) {
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    public int getSize() {
        return size;
    }

    public int readMax() {
        for (int i = 0; i < heap.length; i++) {
            if (size == 0) {
                throw new RuntimeException("Heap is empty");
            }
        }
        return heap[0];
    }

    public void insert(int x) {
        if (size >= heap.length) {
            // Double the size of the heap
            int[] newHeap = new int[heap.length * 2];
            // Copy elements from the old heap to the new heap
            for (int i = 0; i < heap.length; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
            // System.out.println(size);

        }
        // Insert the new element at the end of the heap

        heap[size] = x;
        // System.out.println(size);

        size++;
        // Fix the heap structure by calling buildmaxheap
        for (int i = (size / 2); i >= 0; i--) {
            buildmaxheap(i, size);
        }

    }

    public int deleteMax() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }

        // replace root with the last leaf (item at the last index)
        // use the buildmaxheap method to restore max heap property
        int temp = heap[0];
        swap(0, size - 1);

        // delete the last node since it has been moved to the top
        int[] newHeap = new int[size - 1];
        for (int i = 0; i < size - 1; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
        size--;
        for (int i = (size / 2); i >= 0; i--) {

            buildmaxheap(i, size);

        }
        return temp;

    }

    public static void sortArray(int[] a) {
        // first make a heap object with the array
        MaxBinHeap heap = new MaxBinHeap(a);

        // Build heap (rearrange array)
        for (int i = heap.size / 2 - 1; i >= 0; i--) {
            heap.buildmaxheap(i, heap.size); // Call buildmaxheap on the heap object
        }

        // One by one extract elements from the heap
        for (int i = 0; i < a.length; i++) {
            // Swap the root (maximum element) with the last element
            int max = heap.deleteMax();
            a[i] = max;
        }

    }

    public String toString() {
        String output = "";
        for (int i = 0; i < size; i++) {
            output = output.concat("" + heap[i]);
            if (i != size - 1) {
                output = output.concat(", ");
            }
        }
        return output;
    }
}