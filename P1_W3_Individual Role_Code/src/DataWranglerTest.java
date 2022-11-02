import java.io.FileNotFoundException;
import java.util.List;

// --== CS400 Project One File Header ==--
// Name: Pritish Das
// CSL Username: pritish
// Email: pdas26@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: N/A

public class DataWranglerTest {
  public static void main(String[] args) {
    runAllTests();
  }

  /**
   * Testing getters and setters of book
   * @return true if tests pass otherwise false
   */
  public static boolean bookTest() {
    Book book = new Book();
    if (!book.getTitle().equals("title")) {
      System.out.println("Title didn't match");
      return false;
    }
    if (!book.getAuthors().equals("author")) {
      System.out.println("author didn't match");
      return false;
    }
    if (!book.getISBN13().equals("1234567890123")) {
      System.out.println("isbn13 didn't match");
      return false;
    }

    Book customBook = new Book("The Giving Tree", "Shel Silverstein", "1234567890123");
    if (!customBook.getTitle().equals("The Giving Tree")) {
      System.out.println("Title didn't match");
      return false;
    }
    if (!customBook.getAuthors().equals("Shel Silverstein")) {
      System.out.println("author didn't match");
      return false;
    }
    if (!customBook.getISBN13().equals("1234567890123")) {
      System.out.println("isbn13 didn't match");
      return false;
    }

    Book badisbn = new Book("oaiwhd", "iwoadw", "23132");

    if (badisbn.getISBN13() != null) {
      System.out.println("non 13 digit isbn didnt get set to null");
      return false;
    }

    return true;
  }
  /**
   * Tests that values are loaded at all. No blank cases
   * @return true if tests pass otherwise false
   */
  public static boolean booksCSVLoadTest() {
    try {
      List<IBook> books = (new BookLoader()).loadBooks("books.csv");

      for (IBook i : books) {
        if (i.getTitle().isBlank()) {
          System.out.println("Found blank title");
          return false;
        }
        if (i.getAuthors().isBlank()) {
          System.out.println("Found blank author");
          return false;
        }
        if (i.getISBN13().isBlank()) {
          System.out.println("Found blank isbn13");
          return false;
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
      return false;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    try {
      List<IBook> fakeBooks = (new BookLoader()).loadBooks("fake.csv");
      System.out.println("FileNotFound Exception not thrown when expected");
      return false;
    } catch (FileNotFoundException e) {
    } catch (Exception e) {

      return false;
    }

    return true;
  }
  /**
   * Tests the actual values loaded to ensure they're correct. Size 1 csv
   * @return true if tests pass otherwise false
   */
  public static boolean bookCSVLoadValueTest() {
    try{
      List<IBook> books = (new BookLoader()).loadBooks("smallest.csv");

      for (IBook i : books) {
        if (!i.getTitle().equals("Harry Potter and the Half-Blood Prince (Harry Potter #6)")) {
          System.out.println("Title didn't match");
          return false;
        }
        if (!i.getAuthors().equals("J.K. Rowling/Mary GrandPré")) {
          System.out.println("author didn't match");
          return false;
        }
        if (!i.getISBN13().equals("9780439785969")) {
          System.out.println("isbn13 doesnt match");
          return false;
        }

      }
    }catch (FileNotFoundException e){
      return false;
    }catch (Exception e) {
      return false;
    }

    return true;
  }
  /**
   * Tries load algorithm against a csv with non hardcoded columns that are relevant
   * @return true if tests pass otherwise false
   */
  public static boolean randomCSVTest() {
    try{
      List<IBook> books = (new BookLoader()).loadBooks("random.csv");

      for (IBook i : books) {
        if (i.getTitle().isBlank()) {
          System.out.println("Found blank title");
          return false;
        }
        if (i.getAuthors().isBlank()) {
          System.out.println("Found blank author");
          return false;
        }
        if (i.getISBN13().isBlank()) {
          System.out.println("Found blank isbn13");
          return false;
        }
      }

    }catch (FileNotFoundException e){
      return false;
    }catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }
  /**
   * Testing removal method (only useful one I can think of) for bookList
   * @return true if tests pass otherwise false
   */
  public static boolean bookListMethods() {
    try{
      List<IBook> books = (new BookLoader()).loadBooks("small.csv");

      books.remove(0);

      if(books.size() != 3){
        System.out.println("book wasn't removed");
        return false;
      }

      for(IBook i : books){

        if(i.getTitle().equals("Harry Potter and the Half-Blood Prince (Harry Potter #6)")){
          System.out.println("Book wasn't removed when thought it would Title Wise");
          return false;
        }
      }
    }catch (FileNotFoundException e){
      return false;
    }catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }

  public static boolean DWintegrationTest1() {
    try {
      List<IBook> books = (new BookLoader()).loadBooks("books.csv");
      BookMapperBackend backend = new BookMapperBackend();

      for(int i = 0; i < 5; i++){
        backend.addBook(books.get(i));
      }

      if(backend.getNumberOfBooks() != 5){
        System.out.println("Backend did not add expected number of books from list");
        return false;
      }

    }catch (Exception e){
      System.out.println("Exception found when unexpected");
      return false;
    }

    return true;
  }

  public static boolean DWintegrationTest2(){
    try {
      List<IBook> books = (new BookLoader()).loadBooks("books.csv");
      HashtableMap<String, IBook> hashtable = new HashtableMap<String, IBook>();

      for(int i = 0; i < 5; i++){
        hashtable.put(books.get(i).getISBN13(), books.get(i));
      }

      for(int i = 0; i < 5; i++){
        IBook hashVal = hashtable.get(books.get(i).getISBN13());
        if(!hashVal.equals(books.get(i))){
          System.out.println("Hashmap couldn't retrieve correct KeyValPair");
          return false;
        }
      }
    }catch (Exception e){
      System.out.println("Exception found when unexpected");
      return false;
    }
    return true;
  }

  public static boolean BDtest1() {

    try {
      List<IBook> books = (new BookLoader()).loadBooks("books.csv");
      BookMapperBackend backend = new BookMapperBackend();

      for (int i = 0; i < 10; i++) {
        backend.addBook(books.get(i));
      }

      if(!backend.getByISBN(books.get(0).getISBN13()).equals(books.get(0))){
        System.out.println("Backend failed to get book by valid ISBN");
        return false;
      }

      if (backend.getByISBN("123") != null) {
        System.out.println("Backend code for bad isbn does not work");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception thrown when unexpected");
      return false;
    }

    return true;
  }

  public static boolean BDtest2(){
    try{
      List<IBook> books = (new BookLoader()).loadBooks("books.csv");
      BookMapperBackend backend = new BookMapperBackend();

      for(IBook book: books){
        backend.addBook(book);
      }

      List<IBook> foundBooks = backend.searchByTitleWord("Harry Potter"); //testing method

      if(foundBooks.isEmpty()){
        System.out.println("Backend search by Title didn't work when expected");
        System.out.println("Probably due to AE iterator failing to function which BD uses");
        return false;
      }

    }catch (Exception e){
      System.out.println("Exception found when unexpected");
      return false;
    }

    return true;
  }

  public static boolean runAllTests(){
    if(bookTest()){
      System.out.println("DW Individual Test 1: passed");
    } else{
      System.out.println("DW Individual Test 1: failed");
      return false;
    }

    if(booksCSVLoadTest()){
      System.out.println("DW Individual Test 2: passed");
    } else{
      System.out.println("DW Individual Test 2: failed");
      return false;
    }

    if(bookCSVLoadValueTest()){
      System.out.println("DW Individual Test 3: passed");
    } else{
      System.out.println("DW Individual Test 3: failed");
      return false;
    }

    if(randomCSVTest()){
      System.out.println("DW Individual Test 4: passed");
    } else{
      System.out.println("DW Individual Test 4: failed");
      return false;
    }
    if(bookListMethods()){
      System.out.println("DW Individual Test 5: passed");
    } else{
      System.out.println("DW Individual Test 5: failed");
      return false;
    }

    if(DWintegrationTest1()){
      System.out.println("DW Integration Test 1: passed");
    } else{
      System.out.println("DW Integration Test 1: failed");
      return false;
    }

    if(DWintegrationTest2()){
      System.out.println("DW Individual  Test 2: passed");
    } else{
      System.out.println("DW Individual Test 2: failed");
      return false;
    }

    if(BDtest1()){
      System.out.println("DW Partner BD Test 1: passed");
    } else{
      System.out.println("DW Partner BD Test 1: failed");
      return false;
    }

    if(BDtest2()){
      System.out.println("DW Partner BD Test 2: passed");
    } else{
      System.out.println("DW Partner BD Test 2: failed");
      return false;
    }

    return true;
  }
}

