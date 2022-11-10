import java.util.ArrayList;
import java.util.List;

public class MovieRentalBackendPH implements IMovieRentalBackend{

    @Override
    public int getNumberOfAvailableMovies() {
        
        return 0;
    }

    @Override
    public int getNumberOfRentedMovies() {
        
        return 0;
    }

    @Override
    public void insertMovie(IMovie movie) {

    }

    @Override
    public IMovie removeMovie(String word) {
        if(word.equals("title")){
            IMovie movie = new Movie();
            return movie;
        }
        return null;
    }

    @Override
    public IMovie rentMovie(String word) {
        if(word.equals("title")){
            IMovie movie = new Movie();
            return movie;
        }
        return null;
    }

    @Override
    public IMovie returnMovie(String word) { //CHANGED FROM VOID TO IMOVIE RETURN TYPE
        if(word.equals("title")){
            IMovie movie = new Movie();
            return movie;
        }
        return null;
    }

    @Override
    public List<IMovie> searchAvailableMovies(String word) {

        return null;
    }

    @Override
    public List<IMovie> searchByMovieGenre(String word) {
        if(word.equals("Comedy") || word.equals("comedy")){
            List<IMovie> list = new ArrayList<>();
            list.add(new Movie("funny movie 1", "Comedy", "2003", "10"));
            list.add(new Movie("funny movie 2", "Comedy", "2004", "15"));
            list.add(new Movie("funny movie 3", "Comedy", "2005", "20"));

            return list;
        }
        return null;
    }

    @Override
    public List<IMovie> searchRentedMovies(String word) {
        
        return null;
    }
    
}
