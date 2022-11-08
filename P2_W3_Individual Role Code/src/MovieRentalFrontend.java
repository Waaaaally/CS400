import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieRentalFrontend implements IMovieRentalFrontend {

  MovieRentalBackend backend = new MovieRentalBackend();

  @Override
  public void runCommandLoop() {
    Scanner scanner = new Scanner(System.in);
    int optionInput = -1;

    System.out.println("Welcome to the Movie Rental App");
    System.out.println("===================================");

    while (optionInput != 5){
      displayMainMenu();

      optionInput = scanner.nextInt();

      while (optionInput <= 0 || optionInput > 5) { //Sanity Checking
        System.out.println("Please enter a number listed on the menu");
        optionInput = scanner.nextInt();
      }
    
      if (optionInput == 1) {
        printAllByCategory();
      } else if (optionInput == 2) {
        rentMovie();
      } else if (optionInput == 3) {
        returnMovie();
      } else if (optionInput == 4) {
        removeMovie();
      } else if (optionInput == 5) {
        save();
      }
    }
    System.out.println();
    scanner.close();
  }

  @Override
  public void displayMainMenu() {
    System.out.println(
      "\t" + "You are in the Main Menu: Select an option by entering a number"
    );
    System.out.println("\t" + "1) Display Movies by Genre");
    System.out.println("\t" + "2) Rent a Movie");
    System.out.println("\t" + "3) Return a Movie");
    System.out.println("\t" + "4) Remove a Movie Permanently");
    System.out.println("\t" + "5) Save & Exit Application");
  }

  @Override
  public void printAllByCategory() { //changed to no arg
    List<String> genres = Arrays.asList("Drama", "Comedy", "Romance", "Animation", "Action", "Fantasy");
    Scanner scanner = new Scanner(System.in);

    System.out.println("What genre do you want listed? (Drama, Comedy, Romance, Animation, Action, Fantasy)");
    String sInput = scanner.nextLine();

    while (!genres.contains(sInput)) { //todo Case Sensitivity?
        System.out.println("Please enter a listed genre");
        sInput = scanner.nextLine();
    }
    List<IMovie> movies = backend.searchByMovieGenre(sInput);

    System.out.println("The movies in the genre," + sInput + " are as follows:");
    
    for (IMovie movie : movies) {
        System.out.println(movie.getTitle());
    }

    scanner.close();
  }

  @Override
  public void rentMovie() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("What is the name of the movie you want to rent?");
    String sInput = scanner.nextLine();

    IMovie rentedMovie = backend.rentMovie(sInput); //todo Changed return type from void to IMovie. Communicate
    if (rentedMovie == null) { //null if not found? Must communicate
      System.out.println(
        "Movie wasn't found. Are you sure that was the correct name? Please re-enter it. "
      );
      sInput = scanner.nextLine();
      rentedMovie = backend.rentMovie(sInput);
    }
    //Should no long be null @ this point
    System.out.println(sInput + " has been rented! Please enjoy.");
    scanner.close();
  }

  @Override
  public void returnMovie() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("What is the name of the movie you want to return?");
    String sInput = scanner.nextLine();

    IMovie returnedMovie = backend.returnMovie(sInput);
    if (returnedMovie == null) {
      System.out.println(
        sInput + " has not been rented out, so it can’t be returned."
      );
      sInput = scanner.nextLine();
      returnedMovie = backend.returnMovie(sInput);
    }

    System.out.println("Thank you for returning " + sInput + "!");
    scanner.close();
  }

  @Override
  public void removeMovie() {
    Scanner scanner = new Scanner(System.in);

    System.out.println(
      "What movie do you want to remove? Warning: This is permanent "
    );
    String sInput = scanner.nextLine();

    System.out.println("Are you sure? (y/n)?");
    char yesNo = scanner.nextLine().charAt(0);

    if (yesNo == 'y') {
      IMovie removedMovie = backend.removeMovie(sInput);

      if (removedMovie == null) {
        System.out.println(
          sInput +
          " ins't in our list of removable movies. Are you sure of its title?"
        );
        sInput = scanner.nextLine();
        removedMovie = backend.removeMovie(sInput);
      }
      System.out.println(sInput + " has been removed from the catalog");
    }
    scanner.close();
  }

  @Override
  public void save() {
    MovieLoader loader = new MovieLoader();
    List<IMovie> movies = getMovieList();

    try {
        loader.writeMovies(movies);
    }catch (FileNotFoundException e) {
        //todo FileNotFound ig? idno what to do here tbh
    }
  }

  private List<IMovie> getMovieList() {
    List<IMovie> movies = new ArrayList<IMovie>();

    List<IMovie> dramaMovies = backend.searchByMovieGenre("Drama");
    List<IMovie> comedyMovies = backend.searchByMovieGenre("Comedy");
    List<IMovie> animationMovies = backend.searchByMovieGenre("Animation");
    List<IMovie> actionMovies = backend.searchByMovieGenre("Action");
    List<IMovie> fantasyMovies = backend.searchByMovieGenre("Fantasy");

    for (IMovie drama : dramaMovies) {
      movies.add(drama);
    }
    for (IMovie comedy : comedyMovies) {
      movies.add(comedy);
    }
    for (IMovie animation : animationMovies) {
      movies.add(animation);
    }
    for (IMovie action : actionMovies) {
      movies.add(action);
    }
    for (IMovie fantasy : fantasyMovies) {
      movies.add(fantasy);
    }

    return movies;
  }
}
