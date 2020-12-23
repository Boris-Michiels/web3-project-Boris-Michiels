import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lennert Van Oosterwyck r0782485 & Jens Gervais r0782113;
 **/

public class LogOutTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\boris\\Documents\\Programs\\Selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_LoggedInUserLogsOut_ProfilePageShownWithLogInForm() {
        //Register and log in
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridRegisterField("testUser");
        profilePageGuest.setFirstNameRegisterField("Test");
        profilePageGuest.setLastNameRegisterField("User");
        profilePageGuest.setEmailRegisterField("Test.User@gmail.com");
        profilePageGuest.setPasswordRegisterField("t");
        profilePageGuest.submitRegister();
        assertTrue(profilePageGuest.containsStatusMessageWithText("Your account has been registered"));

        //Uitloggen
        ProfilePageUser profilePageUser = PageFactory.initElements(driver, ProfilePageUser.class);
        profilePageUser.submitLogout();

        assertTrue(profilePageGuest.hasLogInForm());

        profilePageGuest.setUseridLogInField("testUser");
        profilePageGuest.setPasswordLogInField("t");
        profilePageGuest.submitLogIn();

        profilePageUser.submitDelete();
        DeleteConfirmationPage deleteConfirmationPage = PageFactory.initElements(driver, DeleteConfirmationPage.class);
        deleteConfirmationPage.submitDelete();
        assertTrue(deleteConfirmationPage.containsStatusMessageWithText("Your account has been removed"));
    }

    @Test(expected = NoSuchElementException.class)
    public void test_GuestVisitsSite_CannotSeeLogOutButton() {
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        assertTrue(profilePageGuest.hasLogInForm());
        assertFalse(profilePageGuest.hasLogOutButton());
    }
}