import java.util.List;
import java.util.Arrays;

/**
 * Placeholder class for backend implementation
 */
public class FDBookMapperBackend implements IBookMapperBackend
{
    protected String authorFilter;

    /**
     * Placeholder class for Book1
     */
        class Book1 implements IBook
	{
	    /**
	     * @return the title of the book
	     */
	            @Override
		    public String getTitle()
	    {
		return "The Outsiders";
	    }

	    /**
	     * @return the name of the authors
	     */
	            @Override
		    public String getAuthors()
	    {
		return "S.E. Hinton";
	    }

	    /**
	     * @return the ISBN of the book
	     */
	            @Override
		    public String getISBN13()
	    {
		return "1234567890123";
	    }
	}

    /**
     * Placeholder class for Book2
     */
        class Book2 implements IBook
	{
	    /**
	     * @return the title of the book
	     */
	            @Override
		    public String getTitle()
	    {
		return "The Lies of Locke Lamora";
	    }

	    /**
	     * @return the name of the authors
	     */
	            @Override
		    public String getAuthors()
	    {
		return "Scott Lynch";
	    }

	    /**
	     * @return the ISBN of the book
	     */
	            @Override
		    public String getISBN13()
	    {
		return "0987654321098";
	    }
	}

    /**
     * Adds a new book to the database.
     * @param book the IBook to be added to the database
     */
        @Override
	public void addBook(IBook book) {
	    //unimplemented because the frontend code does not need it.
	}

    /**
     * @return the number of books stored in the database
     */
        @Override
	public int getNumberOfBooks()
    {
	return 2;
    }

    /**
     * Sets the authorFilter
     * @param filterBy the String to filter the authors by
     */
        @Override
	public void setAuthorFilter(String filterBy)
    {
	this.authorFilter = filterBy;
    }

    /**
     * @return the current authorFilter
     */
        @Override
	public String getAuthorFilter()
    {
	return this.authorFilter;
    }

    /**
     * Sets the authorFilter to null
     */
        @Override
	public void resetAuthorFilter()
    {
	this.authorFilter = null;
    }

    /**
     * Searches by word in title and authorFilter. In placeholder class,
     * just returns the two hardcoded books.
     */
        @Override
	public List<IBook> searchByTitleWord(String word)
    {
	return Arrays.asList(new IBook[] {new Book1(), new Book2()});
    }

    /**
     * Searches by ISBN. In placeholder class, just returns a hardcoded book.
     */
        @Override
	public IBook getByISBN(String ISBN)
    {
	return new Book1();
    }
}
