/**
 * Placeholder class for ISBN validation.
 */
public class FDISBNValidator implements IISBNValidator
{
    /**
     * Checks whether the ISBN is valid. In placeholder class, always returns
     * true for a particular value, and false for all other values.
     */
    public boolean validate(String isbn13)
    {
	if(isbn13.equals("1234567890123"))
	    return true;
	return false;
    }
}
