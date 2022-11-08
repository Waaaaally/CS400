import java.util.List;

/**
 * @author rahul and priya
 */
public interface IMovieRentalBackend {

  /**
   * inserts a movie into the available red black tree
   *
   * @param movie - movie to be inserted into the available red black tree
   */
  public void insertMovie(IMovie movie);

  /**
   * moves a movie from the available red black tree to the rented red black tree
   *
   * @param word - substring that is used to find a specific movie
   */
  public IMovie rentMovie(String word); //CHANGED FROM VOID TO IMOVIE RETURN TYPE

  /**
   * adds back the movie into the available red black tree and removes it from the rented tree
   *
   * @param word - substring that is used to find a specific movie
   */
  public IMovie returnMovie(String word); //CHANGED FROM VOID TO IMOVIE RETURN TYPE

  /**
   * removes a movie from the available and/or rented red black tree
   *
   * @param word - substring that is used to find a specific movie
   * @return the IMovie object that was removed
   */
  public IMovie removeMovie(String word);

  /**
   * returns the number of movies within the available red black tree
   *
   * @return number of movies within the available red black tree
   */
  public int getNumberOfAvailableMovies();

  /**
   * returns the number of movies within the rented red black tree
   *
   * @return number of movies within the available red black tree
   */
  public int getNumberOfRentedMovies();

  /**
   * searches the available red black tree for a movie based on it's title
   *
   * @param word - substring within the title of the movie
   * @return a list of movies with the given phrase in their title
   */
  public List<IMovie> searchAvailableMovies(String word);

  /**
   * searches the rented red black tree for a movie based on it's title
   *
   * @param word - substring within the title of the movie
   * @return a list of movies with the given phrase in their title
   */
  public List<IMovie> searchRentedMovies(String word);

  /**
   * searches the available tree for a movie based on its genre and title
   *
   * @param word - substring within the genre and title of the movie
   * @return a list of movies with the given phrase in their genre and title
   */
  public List<IMovie> searchByMovieGenre(String word);
}