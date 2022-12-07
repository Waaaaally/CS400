import java.io.FileNotFoundException;
import java.util.List;

public interface IAirlineLoader {

  /**
   * Load the pair of cities that make up an edge and weights between cities from a DOT file
   * into a list. This will be used by the backend to be inserted into the graph
   * 
   * @param filepathToCSV filepath to a DOT
   * @return the list of edges from the DOT file
   * @throws FileNotFoundException
   */
  List<IEdge> loadCities(String filepathToDOT) throws FileNotFoundException;

}