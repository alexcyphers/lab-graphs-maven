package edu.grinnell.csc207.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A simple implementation of undirected graphs.
 *
 * @author Samuel A. Rebelsky
 */
public class UndirectedGraph extends Graph {
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a basic undirected graph.
   */
  public UndirectedGraph() {
    super();
  } // UndirectedGraph()

  /**
   * Create a basic undirected graph from a file.
   *
   * @param fname
   *   The file to read from.
   */
  public UndirectedGraph(String fname) throws Exception {
    super(fname);
  } // UndirectedGraph(fname)

  // +-----------+---------------------------------------------------
  // | Overrides |
  // +-----------+

  /**
   * Add an edge between two vertices.
   *
   * @param u
   *   One end of the edge.
   * @param v
   *   The other end of the edge.
   * @param weight
   *   The weight of the edge.
   *
   * @throws Exception
   *   If either or both vertices are invalid.
   */
  @Override
  public void addEdge(String u, String v, int weight) throws Exception {
    addEdge(this.safeVertexNumber(u), this.safeVertexNumber(v), weight);
  } // addEdge(String, String, int)

  /**
   * Add an edge between two vertices.
   * Add an edge between two vertices.
   *
   * @param u
   *   One end of the edge.
   * @param v
   *   The other end of the edge.
   * @param weight
   *   The weight of the edge.
   *
   * @throws Exception
   *   If either or both vertices are invalid.
   */
  public void addEdge(int u, int v, int weight) throws Exception {
    super.addEdge(u, v, weight);
    super.addEdge(v, u, weight);
  } // addEdge(int, int, int)

  /**
   * Remove an edge. If the edge does not exist, does nothing.
   *
   * @param u
   *   One end of the edge.
   * @param v
   *   The other end of the edge.
   */
  @Override
  public void removeEdge(String u, String v) {
    super.removeEdge(u, v);
    super.removeEdge(v, u);
  } // removeEdge(String, String)

  /**
   * Remove an edge. If the edge does not exist, does nothing.
   *
   * @param u
   *   One end of the edge.
   * @param v
   *   The other end of the edge.
   */
  @Override
  public void removeEdge(int u, int v) {
    super.removeEdge(u, v);
    super.removeEdge(v, u);
  } // removeEdge(int, int)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Get the number of a vertex. If the vertex does not already exist, adds it.
   *
   * @param vertex
   *   The name of the vertex.
   *
   * @return the corresponding integer.
   */
  int safeVertexNumber(String vertex) throws Exception {
    int num = this.vertexNumber(vertex);
    if (num == -1) {
      num = this.addVertex(vertex);
    } // if
    return num;
  } // safeVertexNumber(String)


  /**
   * Prims Algorithm MST.
   *
   * @return the minimum spanning tree.
   */
  public List<Edge> prims() {
    List<Edge> primEdges = new ArrayList<>();
    PriorityQueue<Edge> edgeQueue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.weight(), e2.weight()));
    boolean[] marked = new boolean[this.numVertices];
    int startVertex = 0;
    Iterator<Integer> vertexFinder = vertices().iterator();

    // Start at random vertex.
    while (vertexFinder.hasNext()) {
      startVertex = vertexFinder.next();
      if (vertexNames[startVertex] != null) {
        break;
      } // if
    } // while

    if (vertexNames[startVertex] == null) {
      throw new IllegalStateException("No vertices in graph.");
    } // if

    // Mark Vertex
    marked[startVertex] = true;

    Iterator<Edge> edges = edgesFrom(startVertex).iterator();
    
    while (edges.hasNext()) {
      edgeQueue.add(edges.next());
    } // while-loop

    while (!edgeQueue.isEmpty() && primEdges.size() < this.numVertices - 1) {
      Edge min = edgeQueue.poll();
      int source = min.source();
      int target = min.target();

      if (marked[source] && marked[target]) {
        continue;
      } // if

      primEdges.add(min);

      int newVertex;
      if (marked[source]) {
        newVertex = target;
      } else {
        newVertex = source;
      } // if/else
      marked[newVertex] = true;

      Iterator<Edge> newEdges = edgesFrom(newVertex).iterator();
      while (newEdges.hasNext()) {
        Edge edge = newEdges.next();
        if (!marked[edge.target()]) {
          edgeQueue.add(edge);
        } // if
      } // while-loop
    } // while-loop
    return primEdges;
  } // prims()
} // class UndirectedGraph
