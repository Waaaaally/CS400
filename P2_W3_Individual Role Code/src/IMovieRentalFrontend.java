public interface IMovieRentalFrontend {

  /**
   * The constructor that the implementation this interface will
   * provide. It takes the Scanner that will read user input as
   * a parameter as well as the backend.
   */
  // IMovieRentalFrontend(Scanner userInputScanner, IMovieRentalBackend backend)

  /**
   * this method starts the command loop for the frontend, and will
   * terminate when the user exits the app
   */
  public void runCommandLoop();

  public void displayMainMenu(); // prints command options to System.out

  public void printAllByCategory(); // display a list of movies

  // reads movie from System.in, if movie is in Free tree transfer it to Rented tree
  public void rentMovie();

  // reads movie from System.in, if movie is in Rented tree transfer it to Free tree
  public void returnMovie();

  public void removeMovie(); // reads movie from System.in, removes it from any tree

  // reads a filepath from System.in, writes the contets of a tree to this fil
  public void save();
}