import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Yarne Vandenplas
 * @author Jaison Wullaert
 **/

public class AddContactTest {
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
    public void test_AddContact_AllFieldsFilledInCorrectly() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        contactsPage.setFirstNameContactField("Tim");
        contactsPage.setLastNameContactField("Timmermans");
        contactsPage.setdateTimeContactField("10/09/002020-01/04");
        contactsPage.setPhoneNumberContactField("0123456789");
        contactsPage.setEmailContactField("tim.timmermans@hotmail.com");
        contactsPage.submitAddContact();

        String title = contactsPage.getTitle();
        assertEquals("Contacts", title);
        assertTrue(contactsPage.containsUserWithName("Tim Timmermans"));
    }

    @Test
    public void test_AddContact_FirstNameNotFilledIn() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        contactsPage.setFirstNameContactField("");
        contactsPage.setLastNameContactField("Timmermans");
        contactsPage.setdateTimeContactField("10/09/002020-01/04");
        contactsPage.setPhoneNumberContactField("0123456789");
        contactsPage.setEmailContactField("tim.timmermans@hotmail.com");
        contactsPage.submitAddContact();

        String title = contactsPage.getTitle();
        assertEquals("Contacts", title);
        assertTrue(contactsPage.hasEmptyFirstName());
        assertTrue(contactsPage.containsErrorMessageWithText("No first name given"));
    }

    @Test
    public void test_AddContact_LastNameFilledInCorrectly() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        contactsPage.setFirstNameContactField("Tim");
        contactsPage.setLastNameContactField("");
        contactsPage.setdateTimeContactField("10/09/002020-01/04");
        contactsPage.setPhoneNumberContactField("0123456789");
        contactsPage.setEmailContactField("tim.timmermans@hotmail.com");
        contactsPage.submitAddContact();

        String title = contactsPage.getTitle();
        assertEquals("Contacts", title);
        assertTrue(contactsPage.containsErrorMessageWithText("No last name given"));

    }

    @Test
    public void test_AddContact_DateTimeNotFilledInCorrectly() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        contactsPage.setFirstNameContactField("Tim");
        contactsPage.setLastNameContactField("Timmermans");
        contactsPage.setdateTimeContactField("");
        contactsPage.setPhoneNumberContactField("0123456789");
        contactsPage.setEmailContactField("tim.timmermans@hotmail.com");
        contactsPage.submitAddContact();

        String title = contactsPage.getTitle();
        assertEquals("Contacts", title);
        assertTrue(contactsPage.containsErrorMessageWithText("No date and/or time given"));
    }

    @Test
    public void test_AddContact_EmailNotFilledInCorrectly() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        contactsPage.setFirstNameContactField("Tim");
        contactsPage.setLastNameContactField("Timmermans");
        contactsPage.setdateTimeContactField("10/09/002020-01/04");
        contactsPage.setPhoneNumberContactField("0123456789");
        contactsPage.setEmailContactField("");
        contactsPage.submitAddContact();

        String title = contactsPage.getTitle();
        assertEquals("Contacts", title);
        assertTrue(contactsPage.containsErrorMessageWithText("No email given"));
    }

    @Test
    public void test_AddContact_GsmNotFilledInCorrectly() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        contactsPage.setFirstNameContactField("Tim");
        contactsPage.setLastNameContactField("Timmermans");
        contactsPage.setdateTimeContactField("10/09/002020-01/04");
        contactsPage.setPhoneNumberContactField("");
        contactsPage.setEmailContactField("tim.timmermans@hotmail.com");
        contactsPage.submitAddContact();

        String title = contactsPage.getTitle();
        assertEquals("Contacts", title);
        assertTrue(contactsPage.containsErrorMessageWithText("No phone number given"));
    }
}