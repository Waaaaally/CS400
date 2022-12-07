import java.util.List;


public interface IAirGraph<NodeType, EdgeType extends Number>
    extends GraphADT<NodeType, EdgeType> {

  /**
   * Returns the shortest path between start and end while not allowing travel on edges greater
   * than maxRange. Uses a modified version of Dijkstra's shortest path algorithm to find the
   * shortest path.
   * 
   * @param start the data item in the starting vertex for the path
   * @param end   the data item in the destination vertex for the path
   * @param maxRange the highest edge weight that can be included in the path
   * @return list of data item in vertices in order on the shortest path between
   *         vertex following range requirements
   *         with data item startingVertex and vertex with data item
   *         destinationVertex,
   *         including both startingVertex and destinationVertex
   */
  public List<NodeType> rangedShortestPath(NodeType start, NodeType end, int maxRange);
  // gets the list of paths from one city to another but does not allow smaller planes
  // to use certain edges

  /**
   * Breadth first search to list all Nodes in the graph
   * 
   * @return a list of all nodes
   */
  public List<NodeType> breadthFirstSearch();
  // used by the backend to list all nodes


  /**
   * 
   * @param start
   * @param end
   * @param maxRange
   * @return
   */
  public double rangedGetPathCost(NodeType start, NodeType end, int maxRange);
  // used to get the distance of a given path

}