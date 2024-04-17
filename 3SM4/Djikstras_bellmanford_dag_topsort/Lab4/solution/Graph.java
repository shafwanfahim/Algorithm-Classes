package solution;

import java.util.ArrayList;
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
        int beg;
        int end;
        int w;
        Edge cur;
        while (input.hasNext()) {
            // read next edge
            beg = input.nextInt();
            end = input.nextInt();
            w = input.nextInt();
            // find head and traverse to end of linked list
            cur = adj[beg];
            while (cur != null && cur.next != null) {
                cur = cur.next;
            }
            // create an edge with endPoint=end2 and
            if (cur == null)
                adj[beg] = new Edge(null, v[end], w);
            else
                cur.next = new Edge(null, v[end], w);
            // insert it in the adjacency list of end1
            // create an edge with endPoint=end1 and
            // insert it in the adjacency list of end2

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

    }

    public String dijkstraSP(int i, int j) {
        // setting all of the keys which will represent the shortest paths to inf
        MinBinHeap priority = new MinBinHeap(this, i); // sets first key to 0
        Vertex u, vert;
        int weight;
        String output = "path: ";

        while (priority.size != 0) {
            u = priority.extractMin();
            // looking at the min distance vertex each time initially
            Edge edge = adj[u.index];
            // reference to the first edge of the desired node in the adj list
            while (edge != null) {
                // endpoint refers to what u is connected to
                vert = edge.endPoint;
                weight = edge.weight;
                if (vert.isInQ && (u.key + weight) < vert.key) {
                    priority.decreaseKey(vert.heapIndex, weight);
                    vert.key = u.key + weight; // Update distance
                    vert.prev = u;
                }
                edge = edge.next;
            }
        }
        if (v[j].prev == null) {
            return null;
        }
        vert = v[j];
        ArrayList<Integer> indices = new ArrayList<Integer>(); // Create an ArrayList to store integers
        while (vert != null) {
            // output.concat("v").concat(vert.index);
            indices.add(vert.index);
            vert = vert.prev;
            // holds indices in the reverse order in which they go
        }
        vert = v[j];
        int k = indices.size() - 1;
        while (k >= 0) {
            output += "v";
            output += indices.get(k);
            if (k != 0) {
                output += ", ";
            }
            k--;
        }

        output += ", weight: " + v[j].key;
        return output;
    }

    public String bellmanFordSP(int i, int j) {
        for (int k = 0; k < v.length; k++) {
            v[k].key = Graph.infinity;
            v[k].prev = null;
        }

        // Set the distance of the source vertex to 0
        v[i].key = 0;
        String output = "path: ";

        for (int k = 1; k < size; k++) {
            // v is the name of the LIST OF VERTICES YOU EMPHATIC HOMOSEXUAL
            // u is the current vertex
            for (Vertex u : v) {
                for (Edge e = adj[u.index]; e != null; e = e.next) {
                    // rest of bellman ford
                    if (u.key + e.weight < e.endPoint.key) {
                        e.endPoint.key = u.key + e.weight;
                        e.endPoint.prev = u;
                    }
                }
            }

        }
        for (Vertex u : v) {
            for (Edge e = adj[u.index]; e != null; e = e.next) {
                if (u.key + e.weight < e.endPoint.key) {
                    return "negative-weight cycle!";
                }
            }
        }
        if (v[j].prev == null) {
            return null;
        }

        Vertex vert = v[j];
        ArrayList<Integer> indices = new ArrayList<Integer>(); // Create an ArrayList to store integers
        while (vert != null) {
            // output.concat("v").concat(vert.index);
            indices.add(vert.index);
            vert = vert.prev;
            // holds indices in the reverse order in which they go
        }

        int k = indices.size() - 1;
        while (k >= 0) {
            output += "v";
            output += indices.get(k);
            if (k != 0) {
                output += ", ";
            }
            k--;
        }

        output += ", weight: " + v[j].key;
        return output;

    }

    Vertex[] topologicalSort() {
        // using v.key to store the indegree of all of the vertices
        boolean cycle = false;
        ArrayList<Vertex> L = new ArrayList<>();
        int[] indegree = new int[size];
        Queue queue = new Queue(size); // making queue and indegree array the size of num of vertices
        Vertex u;
        for (Vertex i : v) { // going through each vertex
            for (Edge e = adj[i.index]; e != null; e = e.next) { // going through each edge for that vertex up until we
                                                                 // reach null

                indegree[e.endPoint.index]++; // stores number of endpoints it serves for each vertex

            }
        }
        // enqueue all of the vertx with indegree 0
        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) {
                queue.enqueue(v[i]);
            }
        }

        // topsort alg
        while (queue.size != 0) {

            // if (queue.size == 0) {
            // cycle = true;
            // break;
            // }
            u = queue.dequeue();
            L.add(u);
            for (Edge e = adj[u.index]; e != null; e = e.next) {
                if (--indegree[e.endPoint.index] == 0) {
                    queue.enqueue((e.endPoint));
                }
            }
        }
        if (L.size() < size) {
            return null;
        }
        // if (cycle == true) {
        // return null;
        // }
        Vertex[] output = new Vertex[L.size()];

        for (int i = 0; i < L.size(); i++) {
            output[i] = L.get(i);
        }
        return output;

    }

    public String dagSP(int i, int j) {
        Vertex[] sorted = topologicalSort();
        if (sorted == null) {
            return null;
        }
        String output = " ";

        for (Vertex vert : v) {
            vert.key = Graph.infinity;
            vert.prev = null;
        }

        v[i].key = 0;

        for (Vertex u : sorted) {
            for (Edge e = adj[u.index]; e != null; e = e.next) {
                if (u.key + e.weight < e.endPoint.key) {
                    e.endPoint.key = u.key + e.weight;
                    e.endPoint.prev = u;
                }
            }
        }

        Vertex vert = v[j];
        ArrayList<Integer> indices = new ArrayList<Integer>(); // Create an ArrayList to store integers
        while (vert != null) {
            // output.concat("v").concat(vert.index);
            indices.add(vert.index);
            vert = vert.prev;
            // holds indices in the reverse order in which they go
        }

        int k = indices.size() - 1;
        while (k >= 0) {
            output += "v";
            output += indices.get(k);
            if (k != 0) {
                output += ", ";
            }
            k--;
        }

        output += ", weight: " + v[j].key;
        return output;
    }

    public String shortestPath(int i, int j) {
        // first check for cycles and neg edges
        String alg;
        String output;

        if (!hasCycle()) {
            alg = "dagSP, ";
            output = dagSP(i, j);
        } else if (!hasNegativeWeightEdges()) {
            alg = "dijkstraSP, ";
            output = dijkstraSP(i, j);

        } else {
            alg = "bellmanFormSP, ";
            output = bellmanFordSP(i, j);
        }
        return alg + output;
    }

    public boolean hasNegativeWeightEdges() {
        for (Vertex vertex : v) {
            for (Edge edge = adj[vertex.index]; edge != null; edge = edge.next) {
                if (edge.weight < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[size];
        for (Vertex vertex : v) {
            if (!visited[vertex.index] && hasCycleUtil(vertex, visited, -1)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleUtil(Vertex vertex, boolean[] visited, int parentIndex) {
        visited[vertex.index] = true;
        for (Edge edge = adj[vertex.index]; edge != null; edge = edge.next) {
            if (!visited[edge.endPoint.index]) {
                if (hasCycleUtil(edge.endPoint, visited, vertex.index)) {
                    return true;
                }
            } else if (edge.endPoint.index != parentIndex) {
                return true;
            }
        }
        return false;
    }

}
