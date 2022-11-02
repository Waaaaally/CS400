import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// --== CS400 Project One File Header ==--
// Name: Pritish Das
// CSL Username: pritish
// Email: pdas26@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: N/A

public class BookLoader implements IBookLoader {
/**
 * Parses a csv file for a list of books id'd by title, authors, and isbn13 num
 * @return list of books
 */
  public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filepathToCSV));
    scanner.useDelimiter(",");

    String[] lineArr;
    int titleIndex = 1, authorIndex = 2, isbnIndex = 5; //Cols in Books.CSV
    boolean inQuotes = false; //flip flop variable to tell if in quotes or not
    List<IBook> bookList = new ArrayList<>();

    String[] headerArr = scanner.nextLine().split(","); //skip header line and get cols.
    int totalColumns = headerArr.length; //"Standard" amt of cols per line. Could be
    //messed up because of an extra "," delimeter.

    for (int i = 0; i < headerArr.length; i++) { //gather actual indexes incase non books.csv
      if (headerArr[i].equals("title"))
        titleIndex = i;
      if (headerArr[i].equals("authors"))
        authorIndex = i;
      if (headerArr[i].equals("isbn13"))
        isbnIndex = i;
    }

    while (scanner.hasNext()) { 
      String line = scanner.nextLine();
      lineArr = line.split(",");

      if (lineArr.length != totalColumns) { //if split gives funky array, manually address
        int substringMarker = 0;
        int tempCounter = 0;
        String value;

        for (int i = 0; i < line.length(); i++) {
          char character = line.charAt(i);

          if (character == '\"') {
            inQuotes = !inQuotes;
          }

          if (inQuotes == false && character == ',') { 
            value = line.substring(substringMarker, i);

            if (value.charAt(0) == '\"' && value.charAt(value.length() - 1) == '\"') {
              value = value.substring(1, value.length() - 1); //messy removal of " " for \"
            }

            lineArr[tempCounter] = value;
            substringMarker = i + 1;
            tempCounter++;
          }
        }
      }

      IBook bookToAdd = new Book(lineArr[titleIndex], lineArr[authorIndex], lineArr[isbnIndex]);
      bookList.add(bookToAdd); 
    }
    scanner.close();  //closes the scanner
    return bookList;
  }
}

