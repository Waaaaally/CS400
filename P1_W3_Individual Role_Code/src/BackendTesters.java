//import java.util.List;
//
//public class BackendTesters {
//
//    static IBookMapperBackend be = new BookMapperBackend();
//
//
//    /**
//     * Tests setAuthorFilter() method
//     * @return true if passes test, false if otherwise
//     */
//    public static boolean test1() {
//        BookMapperBackend tBook = new BookMapperBackend();
//
//        if (tBook.getAuthorFilter() != null) {
//            System.out.println("This was supposed to be originally set to null, and it is not null");
//            return false;
//        }
//        tBook.setAuthorFilter("Prathik Gadiraju");
//        if (tBook.getAuthorFilter() != "Prathik Gadiraju") {
//            System.out.println("This author filter is supposed to be set to \"Prathik Gadiraju\", but this is not the author filter");
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Tests resetAuthorFilter() method
//     * @return true is passes tests, false if otherwise
//     */
//    public static boolean test2() {
//        BookMapperBackend tBook = new BookMapperBackend();
//
//        if (tBook.getAuthorFilter() != null) {
//            System.out.println("This was supposed to be originally set to null, and it is not null");
//            return false;
//        }
//        tBook.setAuthorFilter("Prathik Gadiraju");
//        if (tBook.getAuthorFilter() != "Prathik Gadiraju") {
//            System.out.println("This author filter is supposed to be set to \"Prathik Gadiraju\", but this is not the author filter");
//            return false;
//        }
//
//        tBook.resetAuthorFilter();
//        if (tBook.getAuthorFilter() != null) {
//            System.out.println("This author filter should have been cleared, but this author filter is not cleared.");
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Tests addBooks method
//     * @return true if passes tests, false if otherwise
//     */
//    public static boolean test3() {
//        BookMapperBackend tBook = new BookMapperBackend();
//        IBook testBook = new BookPH("title1", "authors1", "ISBN1");
//        tBook.addBook(testBook);
//        if (tBook.getNumberOfBooks() != 1) {
//            System.out.println("size should be 1");
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Tests getByISBN method
//     * @return true if passes test, false if otherwise
//     */
//    public static boolean test4() {
//        BookMapperBackend tBookMapper1 = new BookMapperBackend();
//        IBook tBook = new BookPH("title2", "author2", "ISBN2");
//        tBookMapper1.addBook(tBook);
//        IBook tBookRet = tBookMapper1.getByISBN("ISBN2");
//
//
//        if (!(tBookRet.getISBN13().equals(tBook.getISBN13()))) {
//            System.out.println("expected getISBN13 was not returned by getByISBN.");
//            return false;
//        }
//        if (!(tBookRet.getAuthors().equals(tBook.getAuthors()))) {
//            System.out.println("expected author was not returned by getByISBN.");
//            return false;
//        }
//        if (!(tBookRet.getTitle().equals(tBook.getTitle()))) {
//            System.out.println("expected title was not returned by getByISBN.");
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Tests searchByTitleWord method
//     * @return true if passes test, false if otherwise
//     */
//    public static boolean test5() {
//        BookMapperBackend tBook = new BookMapperBackend();
//        IBook bookRet = (IBook) tBook.searchByTitleWord("title").get(0);
//
//        if ((bookRet.getISBN13().equals("isbn1"))) {
//            System.out.println("Expected ISBN13 name was not returned");
//            return false;
//        }
//        if ((bookRet.getAuthors().equals("author1"))) {
//            System.out.println("Expected author name was not returned");
//            return false;
//        }
//        if ((bookRet.getTitle().equals("title1"))) {
//            System.out.println("Expected title name was not returned");
//            return false;
//        }
//
//        return true;
//    }
//
//
//
//    /**
//     * Runs all tests
//     */
//    public static void main(String[] args) {
//        System.out.println("test1(): " + test1());
//        System.out.println("test2(): " + test2());
//        System.out.println("test3(): " + test3());
//        System.out.println("test4(): " + test4());
//        System.out.println("test5(): " + test5());
//
//    }
//}
