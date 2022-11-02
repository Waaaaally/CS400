// --== CS400 Project One File Header ==--
// Name: Philip Levin
// CSL Username: plevin
// Email: plevin3@wisc.edu
// Lecture #: 003
// Notes to Grader: 

public class ISBNValidator implements IISBNValidator{
  
  public ISBNValidator() { //Empty constructor
  }
  
  public boolean validate(String isbn) {
    /*Next few lines take in string and make sure it is in the right format to be converted into a Long as well as checking the size of it to make sure it's 13*/
    String checker = isbn;
    checker = checker.trim();
    checker = checker.replaceAll("-", "");
    if (checker.length() != 13 || checker.equals("")) {
      return false;
    }
    long num = Long.parseLong(checker); //Turns string into Long
    int output = 0;
    int swtch = 0; //Creates a switch to determine when to multiply by 3 or 1
    
    while (num > 0) { //Makes sure no NullPointerException is encountered
      if (swtch == 0) {
        output = output + getDigit(num); //adds the digit to the Long of digits
        swtch = 1; //Turns switch otherway
      } else {
        output = output + getDigit(num) * 3; //adds the 3*digit to the Long of digits
        swtch = 0; //Turns switch otherway
      }
      num = num/10; //Prepares num for new cycle by shrinking it and taking away latest digit
    }
    if (output % 10 == 0) { //If the sum % 10 is 0, the ISBN is validated
      return true;
    } else {
      return false; //Not validated
    }
  }
  public static int getDigit(long isbn) { //Helped method to get the digit of the number by modding it
    long num = isbn;
    return (int) (num%10);
  }
}

