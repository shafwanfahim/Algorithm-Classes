package solution;

public class Edge {
    int weight;
    Edge next; // reference (or pointer) to the next edge in the adjacency list
    Vertex endPoint; // for edge (u,v) in the adjacency list of u, endPoint=v;
    // for edge (u,v) in the adjacency list of v, endPoint=u

    public Edge(Edge e, Vertex v, int w) {
        weight = w;
        endPoint = v;
        next = e;
    }// end constructor
}// end clas
