// will represent a path from one city to another. Data will get extracted from this and added
// to the graph
public interface IEdge {


    /**
     * Get source of this path
     * 
     * @return source city
     */
    public String getSource();
  
  
    /**
     * Get destination of this path 
     * 
     * @return destination city
     */
    public String getDestination();
  
  
    /**
     * Get distance in KM between source and destination
     * 
     * @return the distance in KM between two cities
     */
    public int getWeight();
  }