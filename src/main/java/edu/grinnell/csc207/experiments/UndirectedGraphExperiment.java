package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.util.Edge;
import edu.grinnell.csc207.util.Graph;
import edu.grinnell.csc207.util.UndirectedGraph;

import java.io.PrintWriter;
import java.util.List;

/**
 * A quick experiment with undirected graphs.
 */
public class UndirectedGraphExperiment {

  /**
   * Run the experiment.
   *
   * @param args
   *   Command-line arguments (ignored).
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    UndirectedGraph g = new UndirectedGraph();

    // Add a few edges
    g.addEdge("a", "b", 1);
    g.addEdge("a", "c", 2);
    g.addEdge("b", "c", 3);
    g.dumpWithNames(pen);

    // Change an edge, in the backwards direction
    pen.println("Changing edge b-a");
    g.addEdge("b", "a", 4);
    g.dumpWithNames(pen);

    List<Edge> prim = g.prims();
    pen.println("Prims: " + prim);

    // Remove a vertex
    pen.println("Removing b");
    g.removeVertex("b");
    g.dumpWithNames(pen);

    // Add another vertex
    pen.println("Adding an edge from a to d");
    g.addEdge("a", "d", 5);
    g.dumpWithNames(pen);

    // Remove an edge
    pen.println("Removing the edge from c to a");
    g.removeEdge("c", "a");
    g.dumpWithNames(pen);
  } // main(String[])

} // class UndirectedGraphExperiment
