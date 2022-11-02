// --== CS400 Project One File Header ==--
// Name: Pritish Das
// CSL Username: pritish
// Email: pdas26@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: N/A

/**
 * Book class with title author and isbn13
 */
public class Book implements IBook {
  private String title, authors, isbn13;

  public Book() {
    title = "title";
    authors = "author";
    isbn13 = "1234567890123";
  }

  public Book(String title, String authors, String isbn13) {
    this.title = title;
    this.authors = authors;
    if (isbn13.length() != 13) {
      this.isbn13 = null;
    }
    else {
      this.isbn13 = isbn13;
    }
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getAuthors() {
    return authors;
  }

  @Override
  public String getISBN13() {
    return isbn13;
  }
}

