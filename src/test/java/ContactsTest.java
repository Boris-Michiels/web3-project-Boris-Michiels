import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Filip Saint, Boris Michiels
 **/

public class ContactsTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

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
    public void test_AllFieldsCorrect_ContactAdded() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        addContact(contactsPage, "testContact1", "testing", "test1.testing@tester.com", "0479666666", "10/09/002020-01/04");
        assertTrue(contactsPage.containsStatusMessageWithText("Your contact has been added"));
        assertTrue(contactsPage.containsUserWithName("testContact1 testing"));
        assertTrue(contactsPage.containsUserWithDate("10/09/2020"));
        assertTrue(contactsPage.containsUserWithTime("01:04"));
    }

    @Test
    public void test_NoFirstName_ErrorMessageShownAndOtherFieldsValueKept() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        addContact(contactsPage, "", "testing", "test1.testing@tester.com", "0479666666", "10/09/002020-01/04");

        assertFalse(contactsPage.containsUserWithName("testContact1 testing"));
        assertTrue(contactsPage.containsErrorMessageWithText("No first name given"));

        assertTrue(contactsPage.hasEmptyFirstName());
        assertTrue(contactsPage.hasStickyLasttName("testing"));
        assertTrue(contactsPage.hasStickyEmail("test1.testing@tester.com"));
        assertTrue(contactsPage.hasStickyPhoneNumber("0479666666"));
        assertTrue(contactsPage.hasStickyDateTime("2020-09-10T01:04"));
    }

    @Test
    public void test_NoDateTime_ErrorMessageShownAndOtherFieldsValueKept() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        addContact(contactsPage, "testContact1", "testing", "test1.testing@tester.com", "0479666666", "");

        assertFalse(contactsPage.containsUserWithName("testContact1 testing"));
        assertTrue(contactsPage.containsErrorMessageWithText("No date and/or time given"));

        assertTrue(contactsPage.hasStickyFirstName("testContact1"));
        assertTrue(contactsPage.hasStickyLasttName("testing"));
        assertTrue(contactsPage.hasStickyEmail("test1.testing@tester.com"));
        assertTrue(contactsPage.hasStickyPhoneNumber("0479666666"));
        assertTrue(contactsPage.hasEmptyDateTime());
    }

    private void addContact(ContactsPage contactsPage, String firstName, String lastName, String email, String phoneNumber, String dateTime) {
        contactsPage.setFirstNameContactField(firstName);
        contactsPage.setLastNameContactField(lastName);
        contactsPage.setEmailContactField(email);
        contactsPage.setPhoneNumberContactField(phoneNumber);
        contactsPage.setdateTimeContactField(dateTime);
        contactsPage.submitAddContact();
    }
}