package solution;

import java.util.Scanner;

public class Graph {

    public static final int infinity = 10000; // logical infinity
    Vertex[] v; // array of vertices
    Edge[] adj; // array storing headers of adjacency lists (adj[i] is
    // a reference to the first Edge object in the adjacency
    // list of vertex v[i])
    int size = 0; // number of vertices
    // Constructor: constructs the undirected graph described by the input string;
    // the string contains only non-negative
    // integers separated by white spaces; the first integer is
    // the number of vertices (n); each of the following triples of integers
    // specifies an edge, namely end1, end2 and weight, where end1 and end2
    // are the indexes of the endpoints in the array of vertices
    // and weight is the weight of the edge; you may assume that the input string
    // respects the required format, that 0<=end1<=n-1, 0<=end2<=n-1,
    // and that the input represents a connected graph

    public Graph(String inputString) {
        Scanner input = new Scanner(inputString);
        size = input.nextInt();// reads first int from input string
        v = new Vertex[size]; // allocate the array of vertices
        adj = new Edge[size]; // alllocate the array of headers to adjacency lists

        // create the Vertex objects and place them in the array
        for (int i = 0; i < size; i++) {
            v[i] = new Vertex(i);
        }

        // read the info from the string
        int end1;
        int end2;
        int w;
        while (input.hasNext()) {
            // read next edge
            end1 = input.nextInt();
            end2 = input.nextInt();
            w = input.nextInt();
            // create an edge with endPoint=end2 and
            adj[end1] = new Edge(adj[end1], v[end2], w);
            // insert it in the adjacency list of end1
            // create an edge with endPoint=end1 and
            // insert it in the adjacency list of end2
            adj[end2] = new Edge(adj[end2], v[end1], w);

            // each insertion is made at the beginning of the list

            // ...
        } // end while
    }// end constructor

    public String adjListString() {
        Edge p; // edge pointer
        String s = " ";
        for (int i = 0; i < size; i++) {
            p = adj[i]; // p points to first edge in the adjacency list of v[i]
            // scan adjacency list of v[i]
            while (p != null) {
                s += " \n edge: (v" + i + ", v" + p.endPoint.index + "), weight: "
                        + p.weight;
                p = p.next; // move to next edge in the current list
            } // end while
        } // end for
        return s;
    } // end method

    public String minSTPrim(int r) {
        String result = "";

        // Create a min-heap for the priority queue
        MinBinHeap minHeap = new MinBinHeap(this, r);

        // Main loop of Prim's algorithm
        while (minHeap.size > 0) {
            // Extract the vertex with the smallest key
            Vertex u = minHeap.extractMin();
            u.isInQ = false;

            // Update keys of adjacent vertices
            for (Edge e = adj[u.index]; e != null; e = e.next) {
                Vertex v = e.endPoint;
                if (v.isInQ && e.weight < v.key) {
                    v.prev = u; // Update parent pointer
                    // Update key and adjust priority queue
                    minHeap.decreaseKey(v.heapIndex, u.key + e.weight); //
                }
            }
        }

        // Construct MST edges and append to result string
        for (int i = 0; i < size; i++) {
            if (i != r) { // Exclude the starting vertex
                Vertex u = v[i];
                Vertex p = u.prev;
                if (p != null) {
                    result += "(v" + p.index + ", v" + u.index + "), weight: " + u.key + "\n";
                }
            }
        }

        return result;
    }

    // adjMatrix(): returns a two-dimensional array that represents
    // the adjacency matrix of the graph
    public int[][] adjMatrix() {
        // so from my understanding, we have the adj list and each index is the header
        // so for each index we keep going until there is a next or until we reach the
        // end of the row
        int[][] matrix = new int[size][size];
        // Initialize all entries to 0
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Graph.infinity;
            }
        }

        // Fill in the adjacency matrix based on the graph's edges
        for (int i = 0; i < size; i++) {
            Edge edge = adj[i]; // Get the first edge in the adjacency list of vertex i
            while (edge != null) {
                matrix[i][edge.endPoint.index] = edge.weight; // Set the weight of the edge
                edge = edge.next; // Move to the next edge in the list
            }
        }

        return matrix;

    }// end method

}