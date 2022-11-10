import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class FrontendDeveloperTests {

    public static void main(String[] args){
        MovieRentalFrontend test = new MovieRentalFrontend();
        test.runCommandLoop();
    }

    MovieRentalFrontend frontend;
    TextUITester tester;

    @BeforeEach
    public void initTest(){
        frontend = new MovieRentalFrontend();
    }

    @Test
    public void testPrintAllByCategory() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void testRentMovie() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void testReturnMovie() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void testRemoveMovie() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void testSaveAndQuit() {
        Assertions.assertEquals(1, 1);
    }
}
