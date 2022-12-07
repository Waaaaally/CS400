import java.util.List;

public interface IAirlineBackend{

  // constructor
  // AirlineBackend(){
  //  this.graph = new AirGraph<String, Integer>();
  //}

  /**
   * Remove a node from the graph
   * 
   * @param node the node to be removed
   * @return true if we city was removed, false otherwise
   */
  public boolean removeNode(String node);
  // check if this node exists: call AirGraph.containsVertex(node1)
  // call AirGraph.removeNode(node)


  /**
   * Insert edge into graph
   * 
   * @param edge the IEdge object representing this edge
   */
  public void insertPath(IEdge edge);
  // extract the relevalnt information then call AirGraph.insertEdge()


  /**
   * Remove an edge from graph
   * @param node1 a node making up the edge 
   * @param node2 other node making up edge
   * @return true if we succesfully removed edge, false otherwise
   */
  public boolean removeE(String node1, String node2);
  // check if this edge exists: call AirGraph.containsEdge(node1, node2)
  // call AirGraph.removeEdge(node1, node2)


  /**
   * Get the list of shortest paths from one city to another with different types of planes.
   * Inner list is a shortest path. Outer list is the different plane sizes
   * 
   * @param source starting point of path
   * @param destination ending point of path
   * @return
   */
  //public List<List<String>> getPaths(String source, String destination);
  public List<List<String>> getPaths(String source, String destination);
  // create list of list of String
  // call AirGraph.rangedGetShortestPath() for every king of plane we have (maxRange)
  // append those to the list of list
  // return list of list


  public List<Double> getPathCosts(String source, String destination);
  // create a list of doubles
  // call AirGraph.rangedGetPathCost() for every kind of plane we have (maxRange)
  // append those to the list
  // return list


  /**
   * List the nodes present in the graph
   * 
   * @return a list of all nodes
   */
  public List<String> listNodes();
  // call IAirGraph.breadthFirstSearch()


  /**
   * Check if this node is in the graph
   * 
   * @param node name of the city
   * @return true if node is contained in the graph, false otherwise
   */
  public boolean nodeExists(String node);
  // call IAirGraph.containsVertex(node);
}