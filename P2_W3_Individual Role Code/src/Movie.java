public class Movie implements IMovie{
  private String title, genre, year, score;
  public Movie(String cTitle, String cGenre, String cYear, String cScore){
    title = cTitle;
    genre = cGenre;
    year = cYear;
    score = cScore;
  }
  public Movie(){
    title = "title";
    genre = "Comedy";
    year = "2003";
    score = "99";
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getGenre() {
    return genre;
  }

  @Override
  public String getConcat() {
    return (title + ", " + genre);
  }

  @Override
  public String getYear() {
    return year;
  }

  @Override
  public String getScore() {
    return score;
  }
}
