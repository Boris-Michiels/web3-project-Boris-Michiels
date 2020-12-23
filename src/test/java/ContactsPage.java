import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Saint, Boris Michiels
 **/

public class ContactsPage extends Page {
    @FindBy(id = "firstName")
    private WebElement firstNameContactField;

    @FindBy(id = "lastName")
    private WebElement lastNameContactField;

    @FindBy(id = "email")
    private WebElement emailContactField;

    @FindBy(id = "phoneNumber")
    private WebElement phoneNumberContactField;

    @FindBy(id = "dateTime")
    private WebElement dateTimeContactField;

    @FindBy(id = "add")
    private WebElement addContactButton;

    public ContactsPage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=ContactsPage");
    }

    public void setFirstNameContactField(String firstNameContact) {
        firstNameContactField.clear();
        firstNameContactField.sendKeys(firstNameContact);
    }

    public void setLastNameContactField(String lastNameContact) {
        lastNameContactField.clear();
        lastNameContactField.sendKeys(lastNameContact);
    }

    public void setEmailContactField(String emailContact) {
        emailContactField.clear();
        emailContactField.sendKeys(emailContact);
    }

    public void setPhoneNumberContactField(String phoneNumberContact) {
        phoneNumberContactField.clear();
        phoneNumberContactField.sendKeys(phoneNumberContact);
    }

    public void setdateTimeContactField(String dateTimeContact) {
        dateTimeContactField.clear();
        dateTimeContactField.sendKeys(dateTimeContact);
    }

    public void submitAddContact() {
        addContactButton.click();
    }

    public boolean hasStickyFirstName(String firstName) {
        return firstNameContactField.getAttribute("value").equals(firstName);
    }

    public boolean hasStickyLasttName(String lastName) {
        return lastNameContactField.getAttribute("value").equals(lastName);
    }

    public boolean hasStickyEmail(String email) {
        return emailContactField.getAttribute("value").equals(email);
    }

    public boolean hasStickyPhoneNumber(String phoneNumber) {
        return phoneNumberContactField.getAttribute("value").equals(phoneNumber);
    }

    public boolean hasStickyDateTime(String dateTime) {
        return dateTimeContactField.getAttribute("value").equals(dateTime);
    }

    public boolean hasEmptyFirstName() {
        return firstNameContactField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyDateTime() {
        return dateTimeContactField.getAttribute("value").isEmpty();
    }

    public boolean containsUserWithName(String name) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().equals(name)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsUserWithDate(String date) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(date)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsUserWithTime(String time) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(time)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsErrorMessageWithText(String text) {
        List<WebElement> errorList = this.driver.findElement(By.className("alert-danger")).findElements(By.tagName("li"));
        for (WebElement li : errorList) {
            if (li.getText().equals(text)) return true;
        }
        return false;
    }
}