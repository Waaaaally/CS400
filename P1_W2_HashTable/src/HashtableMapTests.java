import java.util.NoSuchElementException;
// --== CS400 Project One File Header ==--
// Name: Pritish Das
// CSL Username: pritish
// Email: pdas26@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader:

public class HashtableMapTests {
  public static void main(String [] args){

    System.out.println(testHashPuts());
    System.out.println(testHashGet());
    System.out.println(testHashRemove());
    System.out.println(testHashMisc());
    System.out.println(testHashRehash());
  }

  /**
   * tests the puts method. I hope it works because it's needed in every other test.
   * Cases like valid, duplicate keys, null keys, etc.
   * @return true if tests passed
   */
  public static boolean testHashPuts() {

    try {
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(1, "Hello");
      hashtable.put(2, "World");
      hashtable.put(3, ".");

    }catch (Exception e){
      System.out.println("Exception Caught in putsTest1. There should be none");
      return false;
    }

    try{
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(1, "Hello");
      hashtable.put(2, "World");
      hashtable.put(3, ".");

      if(hashtable.put(1, "Duplicate")){
        System.out.println("put Duplicate key didn't return false.");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception Caught in puts dupe test. there should be none");
      return false;
    }

    try{
      HashtableMap hashtable = new HashtableMap(3);
      hashtable.put(1, "Hello");
      hashtable.put(2, "World");
      hashtable.put(3, ".");

      if(!hashtable.put(4, "Full Hash")){
        System.out.println("Chaining Failed");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception Caught in puts chain test. There should be none");
      return false;
    }

    try{
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(1, "Hello");
      hashtable.put(2, "World");
      hashtable.put(3, ".");

      if(hashtable.put(null, "Null Key")){
        System.out.println("put null key didn't return false.");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception Caught in putsTest1. THere should be none");
      return false;
    }


    return true;
  }

  /**
   * Tests cases for get like valid, null, invalid/dne etc.
   * @return true if tests passed
   */
  public static boolean testHashGet() {

    try {
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(1, "Hello");
      hashtable.put(2, "World");
      hashtable.put(3, ".");

      if(!hashtable.get(1).equals("Hello")){
        System.out.println("Get didn't recall the correct key value pair(1)");
      }
      if(!hashtable.get(2).equals("World")){
        System.out.println("Get didn't recall the correct key value pair (2)");
      }
      if(!hashtable.get(3).equals(".")){
        System.out.println("Get didn't recall the correct key value pair(3)");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception Caught in get Test1. There should be none");
      return false;
    }

    try{
      HashtableMap hashtable = new HashtableMap(1);
      hashtable.put(1, "Hello");
      hashtable.put(2, "World");
      hashtable.put(3, ".");
      hashtable.put(4, "Full Hash"); //also testing rehash here p much hehe

      if(!hashtable.get(4).equals("Full Hash")){
        System.out.println("Get didn't recall the correct key value pair");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception Caught in get chain test. There should be none");
      return false;
    }

    try{
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(1, "Hello");

      hashtable.get(4);
      System.out.println("No such elem exception shouldve been thrown");
      return false; //error should be ethrown by now
    }
    catch(NoSuchElementException e){}
    catch (Exception e){
      System.out.println("Exception Caught in get chain test no exist key. There should be none");
      return false;
    }

    try{
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(1, "Hello");

      hashtable.get(null);
      System.out.println("No such elem exception shouldve been thrown");
      return false; //error should be ethrown by now
    }
    catch(NoSuchElementException e){}
    catch (Exception e){
      System.out.println("Exception Caught in get chain test null. There should be none");
      return false;
    }

    return true;
  }

  /**
   * Tests cases for the remove method. Null, Valid, Returning corret val, removing 2x.
   * @return true if tests passed
   */
  public static boolean testHashRemove() {
    try {
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(1, "Hello");
      hashtable.put(2, "World");
      hashtable.put(3, ".");

      Object value = hashtable.get(2);
      if(!value.equals(hashtable.remove(2))){
        System.out.println("Remove didn't return the correct value;");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception Caught in remove test valid. There should be none");
      return false;
    }

    try {
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(2, "World");

      hashtable.remove(2);
      if(hashtable.remove(2) != null){
        System.out.println("Null was not returned when expected");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception Caught in 2x remove test. There should be none");
      return false;
    }

    try {
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(2, "World");

      if(hashtable.remove(null) != null){
        System.out.println("Null removal didnt not return null");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception Caught in 2x remove test. There should be none");
      return false;
    }

    return true;
  }

  /**
   * Tests Miscllaneous hash methods like contains key, clear, and size.
   * @return true if tests passed
   */
  public static boolean testHashMisc() {
    try {
      HashtableMap hashtable = new HashtableMap();
      if(hashtable.size() != 0){
        System.out.println("Incorrect size (@ 0 stage)");
        return false;
      }
      hashtable.put(1, "World");
      hashtable.put(2, "World");
      hashtable.put(3, "World");
      if(hashtable.size() != 3){
        System.out.println("Incorrect size (@ 3 stage)");
        return false;
      }
      hashtable.remove(2);
      if(hashtable.size() != 2){
        System.out.println("Incorrect size (@ 2 stage)");
        return false;
      }

    }catch (Exception e){
      System.out.println("Exception Caught in Size changing test. There should be none");
      return false;
    }

    try{
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(2, "World");

      if(!hashtable.containsKey(2)){
        System.out.println("Contains key didnt return true when key actually had");
        return false;
      }

      if(hashtable.containsKey(null)){
        System.out.println("Contains key returned true when key was null. Bad nono");
        return false;
      }
    }catch(Exception e){
      System.out.println("Exception Caught in containsKey have key/null test. There should be " +
              "none");
      return false;
    }

    try{
      HashtableMap hashtable = new HashtableMap();
      hashtable.put(1, "Hello");
      hashtable.put(2, "World");
      hashtable.put(3, ".");
      hashtable.put(4, "YEP");
      hashtable.put(5, "NOP");

      hashtable.clear();

      if(hashtable.size() != 0){
        System.out.println("Size not 0 after clearing");
        return false;
      }

      if(hashtable.remove(1) != null){
        System.out.println("Hashtable wasn't properly cleared");
        return false;
      }

    }catch(Exception e){
      System.out.println("Exception Caught in clear test. There should be none");
      return false;
    }

    return true;
  }

  /**
   * Tests if keys can still be "get" after rehashing occurs.
   * @return true if tests passed
   */
  public static boolean testHashRehash() {
    try{
      HashtableMap hashtable = new HashtableMap(4);
      hashtable.put(1, "1");
      hashtable.put(2, "2");
      hashtable.put(3, "3");
      //should rehash now because 3/4 slots = 75% which is above load factor threshold 70%

      if(!hashtable.get(1).equals("1")){
        System.out.println("KVP dislocated in rehashing");
        return false;
      }
      if(!hashtable.get(2).equals("2")){
        System.out.println("KVP dislocated in rehashing");
        return false;
      }
      if(!hashtable.get(3).equals("3")){
        System.out.println("KVP dislocated in rehashing");
        return false;
      }

    }catch(Exception e){
      System.out.println("Exception gotten when none should be found in rehash test.");
      return false;
    }

    return true;
  }

}
