//// --== CS400 Project One File Header ==--
//// Name: Philip Levin
//// CSL Username: plevin
//// Email: plevin3@wisc.edu
//// Lecture #: 003
//// Notes to Grader:
//
//
//public class HashtableMapTests {
//
//
//  public static boolean test1() {
//    /*This test method tests whether my ISBN checker works correctly. It makes sure the checker returns false when it is given an empty string, a string without
//     * 13 numbers, a string that is not a number, or a plain and simple wrong ISBN that doesn't fit it's rules. It makes sure to return true when it is given
//     * a correct ISBN or a correct ISBN that is hyphenated or has a space at the end*/
//    ISBNChecker test = new ISBNChecker();
//    if (test.check("")) {
//      return false;
//    }
//    if (test.check("Wrong value")) {
//      return false;
//    }
//    if (test.check("975055025")){
//      return false;
//    }
//    if (test.check("9789295055021")){
//      return false;
//    }
//    if (!test.check("9789295055025")){
//      return false;
//    }
//    if (!test.check("978-1-56619-909-4 ")){
//      return false;
//    }
//    return true;
//  }
//  /*This test method tests my iterators hasNext() method. It makes sure to return false when there is nothing in the hashtable or when the hasNext() is at the
//   * end of the hashtable. It makes sure to return true when there are hashtable values to be seen*/
//  public static boolean test2() {
//    IteratorHashTable testTable = new IteratorHashTable();
//    if (testTable.hasNext()) {
//      return false;
//    }
//    testTable.put("philip's number", "347");
//    testTable.put("susy's number", "646");
//    testTable.put("janette's number", "646");
//    if (!testTable.hasNext()) {
//      return false;
//    }
//    if (!testTable.hasNext()) {
//      return false;
//    }
//    if (!testTable.hasNext()) {
//      return false;
//    }
//    if (!testTable.hasNext()) {
//      return false;
//    }
//    if (testTable.hasNext()) {
//      return false;
//    }
//
//    return true;
//  }
//  /* This test method also tests the hasNext() method however it makes sure to test the method after the values in a hashtable are cleared. It then makes sure to
//   * test that after a clear, any added value will result in a hasNext() returning true even though the hashtable was cleared and even though there is only one value */
//  public static boolean test3() {
//    IteratorHashTable testTable = new IteratorHashTable();
//    testTable.put("philip's number", "347");
//    testTable.put("susy's number", "646");
//    testTable.put("janette's number", "646");
//
//    testTable.clear();
//    if (testTable.hasNext()) {
//      return false;
//    }
//    testTable.put("philip's number", "347");
//    if (!testTable.hasNext()) {
//      return false;
//    }
//    return true;
//  }
//  /*This test method tests the iterators next() method. It returns true when the next() returns the correct value in the hashtable even though it is in a random order.
//   * I checked to see where these keys and values were placed in the hashtable to see when each should be returned. It also makes sure that a NoSuchElementException
//   * is returned when next() is at the end of the hashtable.*/
//  public static boolean test4() {
//    IteratorHashTable testTable = new IteratorHashTable();
//    testTable.put("philip's number", "347");
//    testTable.put("susy's number", "646");
//    testTable.put("roberto's number", "917");
//    if (!testTable.next().equals("347")) {
//      return false;
//    }
//    if (!testTable.next().equals("917")) {
//      return false;
//    }
//    if (!testTable.next().equals("646")) {
//      return false;
//    }
//    try {
//      testTable.next();
//    }
//    catch (Exception NoSuchElemenException) {
//      return true;
//    }
//    return true;
//  }
//  /*This test method also tests the next() method however makes sure that when the hashtable is empty, a NoSuchElementException is also thrown. */
//  public static boolean test5() {
//    IteratorHashTable testTable = new IteratorHashTable();
//    testTable.put("philip's number", "347");
//    testTable.put("susy's number", "646");
//    testTable.put("janette's number", "646");
//
//    testTable.clear();
//    try {
//      testTable.next();
//    }
//    catch (Exception NoSuchElementException) {
//      return true;
//    }
//    return false;
//  }
//  /* Runs all tests methods and prints out whether they run correctly or not*/
//  public static void main(String args[]) {
//    System.out.println(test1());
//    System.out.println(test4());
//    System.out.println(test3());
//    System.out.println(test4());
//    System.out.println(test5());
//  }
//
//}
//
