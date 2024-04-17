package solution;

public class MinBinHeap {
    // the mst will be made out of this minheap
    Vertex[] heap;
    int size = 0;

    // Constructor: allocates the heap array, sets the key of v[r] to 0 and
    // places v[r] at the root; sets the keys of the remaining vertices
    // to logical infinity and copies them in the heap;
    // initializes heapIndex for each vertex appropriately
    // Constructor
    public MinBinHeap(Graph g, int r) {
        int numVertices = g.size;
        heap = new Vertex[numVertices];

        // Set keys of all vertices to infinity and copy them into the heap
        for (int i = 0; i < numVertices; i++) {
            g.v[i].key = Graph.infinity;
            g.v[i].prev = null;
            heap[i] = g.v[i]; // goes through all the vertex objects and creates a heap out of those objects
            heap[i].heapIndex = i; // Initialize heapIndex

        }
        // all of the vertices have been copied to the heap

        heap[r].key = 0; // make whatever the root vertice is equal to 0 since min heap
        swap(0, r); // make heap[r] into the root by swapping
        heap[r].heapIndex = 0; // Update heapIndex of starting vertex

        size = numVertices;

        // Build min-heap/fix it
        buildMinHeap();
    }

    // Method to swap two vertices in the heap
    private void swap(int i, int j) {
        Vertex temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        // Update heapIndex after swapping
        heap[i].heapIndex = i;
        heap[j].heapIndex = j;
    }

    // Method to heapify a subtree rooted at index i
    private void minHeapify(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < size && heap[left].key < heap[smallest].key)
            smallest = left;

        if (right < size && heap[right].key < heap[smallest].key)
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    // Method to build a min-heap
    private void buildMinHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }
    // end constructor
    // NOTE: When creating the min-heap in the method minSTPrim, you need to pass
    // a reference to this Graph object; use: new MinBinHeap(this, r);
    // extractMin: returns the vertex with the smallest key and removes it from
    // the heap; note that every time a change is made in the heap,
    // the heapIndex of any vertex involved in the change has to be updated

    Vertex extractMin() {

        Vertex temp = heap[0];

        swap(0, this.size - 1);
        this.size--;
        buildMinHeap();
        return temp;
    }// end method
     // decreaseKey(int i, int newKey): decreases the key of the vertex stored
     // at index i in the heap; newKey is the new value of the key and it is
     // smaller than the old key; NOTE: after the change, the heap ordering property
     // has to be restored - use percolate up

    void decreaseKey(int i, int newKey) {
        if (newKey > heap[i].key) {
            // If the new key is not smaller than the old key, return
            return;
        }
        heap[i].key = newKey;

        int parent = (i - 1) / 2;
        while (i > 0 && heap[parent].key > heap[i].key) {
            swap(i, parent); // Swap with parent if necessary
            i = parent; // Move up to the parent index
        }

    }

    // end method
    public String toString() {
        String s = "\n The heap size is " + size + "\n The items’ labels are: \n";
        for (int i = 1; i < size + 1; i++) {
            s += heap[i].index + " key: ";
            s += heap[i].key + "\n";
        } // end for
        return s;
    }// end method
}// end class
