import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class TestResultSearchTest {
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
    public void test_ResultBefore2ContactsSelected_Show2Contacts() {
        List<WebElement> webElements;
        String title;

        driver.get(path + "ContactsPage");
        addContact("testContact1", "testing", "test1.testing@tester.com", "0479666666", "10/09/002020-01/04");
        addContact("testContact2", "testing", "test2.testing@tester.com", "0479666666", "29/10/002020-01/04");

        title = driver.getTitle();
        assertEquals(title, "Contacts");
        webElements = driver.findElements(By.tagName("td"));
        assertTrue(elementContainsText(webElements, "testContact1"));
        assertTrue(elementContainsText(webElements, "testContact2"));

        driver.get(path + "TestResultPage");
        addTestResult("01/09/2020");
        addTestResult("11/11/2020");

        title = driver.getTitle();
        assertEquals(title, "Test Result");
        webElements = driver.findElements(By.tagName("td"));
        assertTrue(elementContainsText(webElements, "01/09/2020"));
        assertTrue(elementContainsText(webElements, "11/11/2020"));

        webElements = driver.findElements(By.id("contactsSince"));
        webElements.get(0).click();
        title = driver.getTitle();
        assertEquals(title, "Search");
        webElements = driver.findElement(By.id("testResultContacts")).findElements(By.tagName("td"));
        assertTrue(elementContainsText(webElements, "testContact1 testing"));
        assertTrue(elementContainsText(webElements, "testContact2 testing"));
        webElements = driver.findElements(By.tagName("p"));
        assertFalse(elementContainsText(webElements, "You haven't had any contacts since this test"));
        assertTrue(elementContainsText(webElements, "You haven't had any contacts since your last test"));
    }

    @Test
    public void test_ResultBefore1ContactSelected_Show1Contact() {
        List<WebElement> webElements;
        String title;

        driver.get(path + "ContactsPage");
        addContact("testContact1", "testing", "test1.testing@tester.com", "0479666666", "10/09/002020-01/04");
        addContact("testContact2", "testing", "test2.testing@tester.com", "0479666666", "29/10/002020-01/04");

        title = driver.getTitle();
        assertEquals(title, "Contacts");
        webElements = driver.findElements(By.tagName("td"));
        assertTrue(elementContainsText(webElements, "testContact1"));
        assertTrue(elementContainsText(webElements, "testContact2"));

        driver.get(path + "TestResultPage");
        addTestResult("01/10/2020");
        addTestResult("11/11/2020");

        title = driver.getTitle();
        assertEquals(title, "Test Result");
        webElements = driver.findElements(By.tagName("td"));
        assertTrue(elementContainsText(webElements, "01/10/2020"));
        assertTrue(elementContainsText(webElements, "11/11/2020"));

        webElements = driver.findElements(By.id("contactsSince"));
        webElements.get(0).click();
        title = driver.getTitle();
        assertEquals(title, "Search");
        webElements = driver.findElement(By.id("testResultContacts")).findElements(By.tagName("td"));
        assertFalse(elementContainsText(webElements, "testContact1 testing"));
        assertTrue(elementContainsText(webElements, "testContact2 testing"));
        webElements = driver.findElements(By.tagName("p"));
        assertFalse(elementContainsText(webElements, "You haven't had any contacts since this test"));
        assertTrue(elementContainsText(webElements, "You haven't had any contacts since your last test"));
    }

    @Test
    public void test_ResultBefore0ContactsSelected_Show0Contacts() {
        List<WebElement> webElements;
        String title;

        driver.get(path + "ContactsPage");
        addContact("testContact1", "testing", "test1.testing@tester.com", "0479666666", "10/09/002020-01/04");
        addContact("testContact2", "testing", "test2.testing@tester.com", "0479666666", "29/10/002020-01/04");

        title = driver.getTitle();
        assertEquals(title, "Contacts");
        webElements = driver.findElements(By.tagName("td"));
        assertTrue(elementContainsText(webElements, "testContact1"));
        assertTrue(elementContainsText(webElements, "testContact2"));

        driver.get(path + "TestResultPage");
        addTestResult("01/11/2020");
        addTestResult("11/11/2020");

        title = driver.getTitle();
        assertEquals(title, "Test Result");
        webElements = driver.findElements(By.tagName("td"));
        assertTrue(elementContainsText(webElements, "01/11/2020"));
        assertTrue(elementContainsText(webElements, "11/11/2020"));

        webElements = driver.findElements(By.id("contactsSince"));
        webElements.get(0).click();
        title = driver.getTitle();
        assertEquals(title, "Search");
        webElements = driver.findElements(By.tagName("p"));
        assertTrue(elementContainsText(webElements, "You haven't had any contacts since this test"));
        assertTrue(elementContainsText(webElements, "You haven't had any contacts since your last test"));
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

    private void addTestResult(String date) {
        driver.get(path + "TestResultPage");

        fillOutField("date", date);

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