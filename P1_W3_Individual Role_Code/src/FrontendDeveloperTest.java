import java.util.Scanner;

/**
 * This class contains methods that test whether the BookMapperFrontend class functions as intended.
 */
public class FrontendDeveloperTest
{
    /**
    * Tests the runCommandLoop() method and checks whether the correct message is displayed when an
    * invalid option is selected.
    * @return   true if the test succeeds and the correct message is displayed;
                false if the test fails
    */
    public static boolean test1()
    {
        TextUITester tester = new TextUITester("abcd\n4\n");
        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), new FDBookMapperBackend(), new FDISBNValidator());
        
        frontend.runCommandLoop();
        String output = tester.checkOutput();
	return output.split("\n")[10].equals("Please enter a valid option.");
    }

    /**
    * Tests the displayBooks() method and checks whether the author filter and number of books are correctly displayed.
    * @return   true if the test succeeds and the correct message is displayed;
                false if the test fails
    */
    public static boolean test2()
    {
        TextUITester tester = new TextUITester("3\nHinton\n2\n\n4\n");
        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), new FDBookMapperBackend(), new FDISBNValidator());
        
        frontend.runCommandLoop();
        String output = tester.checkOutput();
        return output.split("\n")[22].equals("Matches (author filter: Hinton) 2 of 2");
    }

    /**
    * Tests the isbnLookup() method and checks whether the correct message is displayed and ISBN lookup is done correctly.
    * @return   true if the test succeeds and the correct book is displayed;
                false if the test fails
    */
    public static boolean test3()
    {
        TextUITester tester = new TextUITester("1\n1234567890123\n4\n");
        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), new FDBookMapperBackend(), new FDISBNValidator());
        
        frontend.runCommandLoop();
        String output = tester.checkOutput();
        return output.split("\n")[12].equals("1. \"The Outsiders\" by S.E. Hinton, ISBN: 1234567890123");
    }

    /**
    * Tests the setFilter() method and checks whether the filter gets set and displayed correctly.
    * @return   true if the test succeeds and the correct filter is displayed;
                false if the test fails
    */
    public static boolean test4()
    {
        TextUITester tester = new TextUITester("3\nScott\n3\n\n4\n");
        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), new FDBookMapperBackend(), new FDISBNValidator());
        
        frontend.runCommandLoop();
        String output = tester.checkOutput();
        return output.split("\n")[21].equals("Author name must currently contain: Scott");
    }

    /**
    * Tests the titleSearch() method and checks whether the books are displayed in the intended format.
    * @return   true if the test succeeds and the correct message is displayed;
                false if the test fails
    */
    public static boolean test5()
    {
        TextUITester tester = new TextUITester("2\nLies\n4\n");
        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), new FDBookMapperBackend(), new FDISBNValidator());
        
        frontend.runCommandLoop();
        String output = tester.checkOutput();
        return output.split("\n")[15].equals("2. \"The Lies of Locke Lamora\" by Scott Lynch, ISBN: 0987654321098");
    }

    /**
    * The main method that will call all the test methods and check whether they were successful
    */
    public static void main(String[] args)
    {
        System.out.println("\nRunning test 1...");
        if(test1())
            System.out.println("Test 1 successful.");
        else
            System.out.println("Test 1 unsuccessful.");

        System.out.println("\nRunning test 2...");
        if(test2())
            System.out.println("Test 2 successful.");
        else
            System.out.println("Test 2 unsuccessful.");

        System.out.println("\nRunning test 3...");
        if(test3())
            System.out.println("Test 3 successful.");
        else
            System.out.println("Test 3 unsuccessful.");
        
        System.out.println("\nRunning test 4...");
        if(test4())
            System.out.println("Test 4 successful.");
        else
            System.out.println("Test 4 unsuccessful.");
        
        System.out.println("\nRunning test 5...");
        if(test5())
            System.out.println("Test 5 successful.");
        else
            System.out.println("Test 5 unsuccessful.");
    }
}
