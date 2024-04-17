package solution;

import java.util.Arrays;

public class PartialTest {
    public static void main(String[] args) {

        String aGraph = "7 3 4 4 2 3 2 6 4 2 1 6 1 5 1 4 6 5 50 4 5 3 3 6 1 1 2 1 2 6 50";
        String aGraph1 = "4 1 2 1 3 2 3 0 3 2 0 1 -10 2 0 1";
        String aGraph2 = "6 4 5 3 2 1 -50 3 2 3 0 3 2 0 1 -10 0 2 10";

        Graph g = new Graph(aGraph);

        System.out.println("Number of vertices in aGraph:" + g.size);

        System.out.println("Edges of aGraph: ");
        System.out.println(g.adjListString());

        System.out.print("\n");
        System.out.println("Adjacency matrix of aGraph: \n");
        int[][] a = g.adjMatrix();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                System.out.print(" " + a[i][j]);
            System.out.print("\n");
        } // end for

        System.out.println("\n Testing dijkstraSP \n");
        System.out.println(g.dijkstraSP(4, 3));
        System.out.println();
        System.out.println(g.dijkstraSP(0, 3));
        System.out.println();
        System.out.println(g.dijkstraSP(1, 2));
        System.out.println();
        System.out.println(g.dijkstraSP(2, 1));
        System.out.println();
        System.out.println(g.dijkstraSP(3, 2));
        System.out.println();
        System.out.println(g.dijkstraSP(1, 4));
        System.out.println();
        System.out.println(g.dijkstraSP(6, 0));

        System.out.println("\n Testing bellmanFordSP \n");
        System.out.println(g.bellmanFordSP(2, 1));
        System.out.println();
        System.out.println(g.dijkstraSP(6, 0));
        System.out.println();

        System.out.println("\n Testing bellmanFordSP with negative-weight cycle\n");
        Graph g1 = new Graph(aGraph1);

        System.out.println("Number of vertices in aGraph1:" + g1.size);

        System.out.println("Edges of aGraph1: ");
        System.out.println(g1.adjListString());

        System.out.print("\n");
        System.out.println("Adjacency matrix of aGraph1: \n");
        a = g1.adjMatrix();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                System.out.print(" " + a[i][j]);
            System.out.print("\n");
        } // end for
        System.out.println();

        System.out.println(g1.bellmanFordSP(3, 2));
        System.out.println();

        System.out.println("\n Testing bellmanFordSP \n");
        Graph g2 = new Graph(aGraph2);

        System.out.println("Number of vertices in aGraph2:" + g2.size);

        System.out.println("Edges of aGraph2: ");
        System.out.println(g2.adjListString());

        System.out.print("\n");
        System.out.println("Adjacency matrix of aGraph2: \n");
        a = g2.adjMatrix();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                System.out.print(" " + a[i][j]);
            System.out.print("\n");
        } // end for
        System.out.println();

        System.out.println(g2.bellmanFordSP(0, 1));
        System.out.println();
        System.out.println(g2.bellmanFordSP(3, 2));
        System.out.println();
        System.out.println(g2.bellmanFordSP(1, 0));
        System.out.println();
        System.out.println(g2.bellmanFordSP(5, 4));
        System.out.println();
        System.out.println(g2.bellmanFordSP(4, 1));
        System.out.println();

        Vertex[] topologicalOrder = g2.topologicalSort();
        if (topologicalOrder != null) {
            System.out.println("Topological Sort for g2:");
            for (Vertex vertex : topologicalOrder) {
                System.out.print(vertex.index + " ");
            }
            System.out.println();
        } else {
            System.out.println("No topological order exists for g1 (cycle detected).");
        }
    }
}