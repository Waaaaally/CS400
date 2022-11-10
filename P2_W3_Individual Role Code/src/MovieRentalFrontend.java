import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieRentalFrontend implements IMovieRentalFrontend {
  Scanner scanner = new Scanner(System.in);
  MovieRentalBackendPH backend = new MovieRentalBackendPH();

  @Override
  public void runCommandLoop() {
    String optionInput;

    System.out.println("Welcome to the Movie Rental App");
    System.out.println("===================================");

    while (true){
//      Scanner scanner = new Scanner(System.in);
      displayMainMenu();

      optionInput = scanner.nextLine();

      if (optionInput.equals("1")) {
        printAllByCategory();
      } else if (optionInput.equals("2")) {
        rentMovie();
      } else if (optionInput.equals("3")) {
        returnMovie();
      } else if (optionInput.equals("4")) {
        removeMovie();
      } else if (optionInput.equals("5")) {
        //save(); need movie list to test
        scanner.close();
        System.out.println("Thank you for using the Movie Rental App!");
        return;
      }else{
        System.out.println("Please enter a valid option.\n");
      }
      //Loop continues
    }
  }

  @Override
  public void displayMainMenu() {
    System.out.println("You are in the Main Menu: Select an option by entering a number"
    );
    System.out.println("\t" + "1) Display Movies by Genre");
    System.out.println("\t" + "2) Rent a Movie");
    System.out.println("\t" + "3) Return a Movie");
    System.out.println("\t" + "4) Remove a Movie Permanently");
    System.out.println("\t" + "5) Save & Exit Application");
  }

  @Override
  public void printAllByCategory() { //changed to no arg
    List<String> genres = Arrays.asList("Drama", "drama", "Comedy", "comedy", "Romance", "romance",
            "Animation", "animation", "action", "Action", "Fantasy", "fantasy");
//    Scanner scanner = new Scanner(System.in);

    System.out.println("What genre do you want listed? (Drama, Comedy, Romance, Animation, Action, Fantasy)");

    String sInput = scanner.nextLine();

    while (!genres.contains(sInput)) { //todo Case Sensitivity?
        System.out.println("Please enter a listed genre");
        sInput = scanner.nextLine();
    }
    List<IMovie> movies = backend.searchByMovieGenre(sInput);

    System.out.println("The movies in the genre, " + sInput + ", are as follows:");
    
    for (IMovie movie : movies) {
        System.out.println(movie.getTitle());
    }
    System.out.println();
  }

  @Override
  public void rentMovie() {
//    Scanner scanner = new Scanner(System.in);
    System.out.println("What is the name of the movie you want to rent?");
    String sInput = null;

    sInput = scanner.nextLine();

    IMovie rentedMovie = backend.rentMovie(sInput); //todo Changed return type from void to IMovie. Communicate

    if(rentedMovie != null){
      System.out.println(sInput + " has been rented! Please enjoy.\n");
      return;
    }else{
      System.out.println("Sorry, we couldn't find that movie in our database.\n");
    }
  }

  @Override
  public void returnMovie() {
//    Scanner scanner = new Scanner(System.in);

    System.out.println("What is the name of the movie you want to return?");
    String sInput = scanner.nextLine();

    IMovie returnedMovie = backend.returnMovie(sInput);
    if(returnedMovie != null){
      System.out.println("Thank you for returning " + sInput + "!\n");
      return;
    }else{
      System.out.println("That movie hasn't been rented in our database, so it can't be marked " +
              "for return.\n");
    }
  }

  @Override
  public void removeMovie() {
//    Scanner scanner = new Scanner(System.in);
    IMovie removedMovie = null;

    System.out.println(
      "What movie do you want to remove? Warning: This is permanent.");

    String sInput = scanner.nextLine();

    System.out.println("Are you sure? (y/n)?");

    String yesNo = scanner.nextLine();

    if (yesNo.equals("y")) {
      System.out.println("Okay, proceeding with deletion.");
      removedMovie = backend.removeMovie(sInput);
    }
    else if(yesNo.equals("n")){
      System.out.println("Okay, returning to main menu.\n");
      return;
    }else{
      System.out.println("Command unrecognized. Returning to main menu.\n");
      return;
    }

    if(removedMovie != null){
      System.out.println(sInput + " has been removed from the catalog.\n");
      return;
    }
    else{
      System.out.println("That movie wasn't in our database so we couldn't remove it.\n");
    }

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

/*
while (rentedMovie == null) { //null if not found? Must communicate
      System.out.println(
        "Movie wasn't found. Are you sure that was the correct name? Please re-enter it. Or type " +
                "\"q\" to return to main menu.");
      sInput = scanner.nextLine();
      if(sInput.equals("q")){
        rentedMovie = new Movie();
      }
      else{
        rentedMovie = backend.rentMovie(sInput);
      }
    }

while (returnedMovie != null || !sInput.equals("q")) {
      System.out.println(
        sInput + " has not been rented out, so it can’t be returned. Please retype correctly or " +
                "enter \"q\" to return to the main menu.");

      sInput = scanner.nextLine();
      returnedMovie = backend.returnMovie(sInput);
    }
    if(returnedMovie == null)
      return;


if(removedMovie != null || !sInput.equals("q")) {
        System.out.println(
          sInput + " isn't in our list of removable movies. Are you sure of its title? Please retype " +
                  "correctly or enter \"q\" to return to the main menu.");
        sInput = scanner.nextLine();
        removedMovie = backend.removeMovie(sInput);
      }
      if(removedMovie == null)
        return;
 */