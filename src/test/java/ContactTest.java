import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class ContactTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller?command=";


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\boris\\Documents\\Programs\\Selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();

        registerUser("testUser", "Test", "User", "Test.User@gmail.com", "t");
        logIn("testUser", "t");
    }

    @After
    public void clean() {
        deleteAccount();
        driver.quit();
    }

    @Test
    public void test_AllFieldsCorrect_ContactAdded() {
        addContact("testContact1", "testing", "test1.testing@tester.com", "0479666666", "10/09/002020-01/04");

        String title = driver.getTitle();
        assertEquals(title, "Contacts");

        List<WebElement> webElements = driver.findElements(By.cssSelector("table tr"));
        assertTrue(elementContainsText(webElements, "testContact1"));
    }

    @Test
    public void test_NoFirstName_ErrorMessageShownAndOtherFieldsValueKept() {
        addContact("", "testing", "test1.testing@tester.com", "0479666666", "10/09/002020-01/04");

        String title = driver.getTitle();
        assertEquals(title, "Contacts");

        List<WebElement> webElements = driver.findElements(By.cssSelector("table tr"));
        assertFalse(elementContainsText(webElements, "testContact1"));
        webElements = driver.findElements(By.className("alert-danger"));
        assertTrue(elementContainsText(webElements, "No firstname given"));

        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        assertEquals("", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        assertEquals("testing", fieldLastName.getAttribute("value"));

        WebElement fieldEmail = driver.findElement(By.id("email"));
        assertEquals("test1.testing@tester.com", fieldEmail.getAttribute("value"));

        WebElement fieldPhoneNumber = driver.findElement(By.id("phoneNumber"));
        assertEquals("0479666666", fieldPhoneNumber.getAttribute("value"));

        WebElement fieldDateTime = driver.findElement(By.id("dateTime"));
        assertEquals("2020-09-10T01:04", fieldDateTime.getAttribute("value"));
    }

    @Test
    public void test_NoDateTime_ErrorMessageShownAndOtherFieldsValueKept() {
        addContact("testContact1", "testing", "test1.testing@tester.com", "0479666666", "");

        String title = driver.getTitle();
        assertEquals(title, "Contacts");

        List<WebElement> webElements = driver.findElements(By.className("alert-danger"));
        assertTrue(elementContainsText(webElements, "No date and/or time given"));

        WebElement fieldFirstName = driver.findElement(By.id("firstName"));
        assertEquals("testContact1", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("lastName"));
        assertEquals("testing", fieldLastName.getAttribute("value"));

        WebElement fieldEmail = driver.findElement(By.id("email"));
        assertEquals("test1.testing@tester.com", fieldEmail.getAttribute("value"));

        WebElement fieldPhoneNumber = driver.findElement(By.id("phoneNumber"));
        assertEquals("0479666666", fieldPhoneNumber.getAttribute("value"));

        WebElement fieldDateTime = driver.findElement(By.id("dateTime"));
        assertEquals("", fieldDateTime.getAttribute("value"));
    }

    private void registerUser(String userid, String firstName, String lastName, String email, String password) {
        driver.get(path + "ProfilePage");

        fillOutField("userid", userid);
        fillOutField("firstName", firstName);
        fillOutField("lastName",lastName);
        fillOutField("email", email);
        fillOutField("password", password);

        driver.findElement(By.id("register")).click();
    }

    private void addContact(String firstName, String lastName, String email, String phoneNumber, String dateTime) {
        driver.get(path + "ContactsPage");

        fillOutField("firstName", firstName);
        fillOutField("lastName", lastName);
        fillOutField("phoneNumber", phoneNumber);
        fillOutField("email", email);
        fillOutField("dateTime", dateTime);

        driver.findElement(By.id("add")).click();
    }

    private void logIn(String userid, String password) {
        driver.get(path + "LogOut");
        driver.get(path + "ProfilePage");

        fillOutField("useridLogIn", userid);
        fillOutField("passwordLogIn", password);

        driver.findElement(By.id("logIn")).click();
    }

    private void deleteAccount() {
        driver.get(path + "ProfilePage");
        driver.findElement(By.id("deleteConfirmation")).click();
        driver.findElement(By.id("delete")).click();
    }

    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private boolean elementContainsText (List<WebElement> elements, String text) {
        for (WebElement e : elements) {
            if (e.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }
}