package solution;

public class Vertex {
    Vertex prev; // previous vertex in MST (variable v.p from lecture notes)
    int index; // the index in the array of vertices
    int heapIndex; // the index in the min-heap array
    int key = Graph.infinity; // key in min-heap; initialized as logical infinity
    boolean isInQ = true; // true when the vertex is in min-heap;
    // when the Vertex object is created,
    // it is assumed to be in the min-heap

    public Vertex(int n) {
        index = n;
    }// end constructor
}// end class