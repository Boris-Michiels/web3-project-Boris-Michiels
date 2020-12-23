import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Vryghem - r0747249
 * @author Mathias Van den Cruijce - r0785409
 **/

public class LogInTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\boris\\Documents\\Programs\\Selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridRegisterField("testUser");
        profilePageGuest.setFirstNameRegisterField("Test");
        profilePageGuest.setLastNameRegisterField("User");
        profilePageGuest.setEmailRegisterField("Test.User@gmail.com");
        profilePageGuest.setPasswordRegisterField("t");
        profilePageGuest.submitRegister();
        assertTrue(profilePageGuest.containsStatusMessageWithText("Your account has been registered"));
        ProfilePageUser profilePageUser = PageFactory.initElements(driver, ProfilePageUser.class);
        profilePageUser.submitLogout();
    }

    @After
    public void clean() {
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridLogInField("testUser");
        profilePageGuest.setPasswordLogInField("t");
        profilePageGuest.submitLogIn();

        ProfilePageUser profilePageUser = PageFactory.initElements(driver, ProfilePageUser.class);
        profilePageUser.submitDelete();
        DeleteConfirmationPage deleteConfirmationPage = PageFactory.initElements(driver, DeleteConfirmationPage.class);
        deleteConfirmationPage.submitDelete();
        assertTrue(deleteConfirmationPage.containsStatusMessageWithText("Your account has been removed"));
        driver.quit();
    }

    @Test
    public void test_Login_LoginWithValidUseridAndMatchingPassword_LoginSuccessful() {
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridLogInField("testUser");
        profilePageGuest.setPasswordLogInField("t");
        profilePageGuest.submitLogIn();

        ProfilePageUser profilePageUser = PageFactory.initElements(driver, ProfilePageUser.class);
        assertTrue(profilePageUser.hasH3WithUserName("Test User"));
        profilePageUser.submitLogout();
    }

    @Test
    public void test_Login_LoginWithEmptyUserid_LoginUnsuccessful() {
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridLogInField("");
        profilePageGuest.setPasswordLogInField("t");
        profilePageGuest.submitLogIn();

        assertTrue(profilePageGuest.containsErrorMessageWithText("No userid given"));
    }

    @Test
    public void test_Login_LoginWithEmptyPassword_LoginUnsuccessful() {
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridLogInField("testUser");
        profilePageGuest.setPasswordLogInField("");
        profilePageGuest.submitLogIn();

        assertTrue(profilePageGuest.containsErrorMessageWithText("No password given"));
    }

    @Test
    public void test_Login_LoginWithInvalidPassword_LoginUnsuccessful() {
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridLogInField("testUser");
        profilePageGuest.setPasswordLogInField("fout");
        profilePageGuest.submitLogIn();

        assertTrue(profilePageGuest.containsLogInMessageWithText("No valid userid / password"));
    }

    @Test
    public void test_Login_LoginWithInvalidUserid_LoginUnsuccessful() {
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridLogInField("fout");
        profilePageGuest.setPasswordLogInField("t");
        profilePageGuest.submitLogIn();

        assertTrue(profilePageGuest.containsLogInMessageWithText("No valid userid / password"));
    }

    @Test
    public void test_Login_LoginWithValidUseridAndMatchingPasswordRegardlessOfUseridCasing_LoginSuccessful() {
        ProfilePageGuest profilePageGuest = PageFactory.initElements(driver, ProfilePageGuest.class);
        profilePageGuest.setUseridLogInField("TESTUSER");
        profilePageGuest.setPasswordLogInField("t");
        profilePageGuest.submitLogIn();

        ProfilePageUser profilePageUser = PageFactory.initElements(driver, ProfilePageUser.class);
        assertTrue(profilePageUser.hasH3WithUserName("Test User"));
        profilePageUser.submitLogout();
    }
}