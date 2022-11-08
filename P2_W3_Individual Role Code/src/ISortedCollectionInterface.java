import java.util.ArrayList;

public interface ISortedCollectionInterface<T extends Comparable<T>> extends
        SortedCollectionInterface<T> {
  /**
   * This method will sort through the Red-Black Tree to find a list
   * of movies of a particular genre.
   * @param genreAndTitle
   * @return a list of movies of a particular genre.
   */
  public ArrayList<T> get(String genreAndTitle);

  /**
   * This method will remove the required movie from the Red-Black Tree.
   * @param genreAndTitle
   * @return true if the required movie is able to and is removed, false if otherwise
   */
  public boolean remove(String genreAndTitle);

}