import java.util.List;

public class MovieRentalBackend implements IMovieRentalBackend{

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
        
        return null;
    }

    @Override
    public IMovie rentMovie(String word) {
        
        return null;
    }

    @Override
    public IMovie returnMovie(String word) { //CHANGED FROM VOID TO IMOVIE RETURN TYPE
       
        return null;
    }

    @Override
    public List<IMovie> searchAvailableMovies(String word) {

        return null;
    }

    @Override
    public List<IMovie> searchByMovieGenre(String word) {
        
        return null;
    }

    @Override
    public List<IMovie> searchRentedMovies(String word) {
        
        return null;
    }
    
}
