import java.io.FileNotFoundException;
import java.util.List;


/** Instances of this interface can be used to load movies into a linked list
 * from an xml file and write movies from a linked list into an xml file.
 */
public interface IMovieLoader {

  /**
   * This method loads the movies into a linked list
   * @param xmlFilePath path to the xml file
   * @return a linked list of movies
   * @throws FileNotFoundException
   */
  List <IMovie> loadMovies (String xmlFilePath) throws FileNotFoundException;

  /**
   * This method loads the movies into a linked list
   * @param movieList linked list of movies
   * @throws FileNotFoundException
   */
  void writeMovies (List <IMovie> movieList) throws FileNotFoundException;

}