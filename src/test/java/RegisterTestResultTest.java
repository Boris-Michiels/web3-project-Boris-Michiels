import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.junit.Assert.assertTrue;

/**
 * @author Nicolas Vanden Bosch r0798086
 **/

public class RegisterTestResultTest {
    public WebDriver driver;

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
    }

    @After
    public void clean() {
        ProfilePageUser profilePageUser = PageFactory.initElements(driver, ProfilePageUser.class);
        profilePageUser.submitDelete();
        DeleteConfirmationPage deleteConfirmationPage = PageFactory.initElements(driver, DeleteConfirmationPage.class);
        deleteConfirmationPage.submitDelete();
        assertTrue(deleteConfirmationPage.containsStatusMessageWithText("Your account has been removed"));
        driver.quit();
    }

    @Test
    public void test_registerTestResult_showsTestResultInContactOverview(){
        TestResultPage testResultPage = PageFactory.initElements(driver, TestResultPage.class);
        testResultPage.setDateTestResultField("02-03-002020");
        testResultPage.submitAddTestResult();

        testResultPage = PageFactory.initElements(driver, TestResultPage.class);
        assertTrue(testResultPage.containsTestResultWithDate("02/03/2020"));
    }

    @Test
    public void test_registerTestResult_noDateGiven_givesError(){
        TestResultPage testResultPage = PageFactory.initElements(driver, TestResultPage.class);
        testResultPage.setDateTestResultField("");
        testResultPage.submitAddTestResult();

        assertTrue(testResultPage.containsErrorMessageWithText("No date given"));
    }
}