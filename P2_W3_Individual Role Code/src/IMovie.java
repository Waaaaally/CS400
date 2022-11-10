/**
 * This interface defines getter methods for each movie's data
 * attributes and is implemented by classes that represent a
 * movie and its associated data.
 *
 */

public interface IMovie {
  /**
   * Returns the title of the movie.
   * @return title of the movie.
   */
  String getTitle();

  /**
   * Returns a string that contains the genre of the movie.
   * @return genre of movie
   */
  String getGenre();

  /**
   * Returns a string that contains is a concatenated version with the genre and title
   * @return concatenated string
   */
  String getConcat();

  /**
   * Returns a string that is the release date of the movie.
   * @return release date of movie
   */
  String getYear();

  /**
   * Returns a string that is the Rotten Tomatoes score of the movie.
   * @return Rotten Tomatoes score of movie
   */
  String getScore();

}