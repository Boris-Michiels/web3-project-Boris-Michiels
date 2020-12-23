package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Filip Saint, Boris Michiels
 **/

public class ProfilePageUser extends Page {
    @FindBy(id = "logOut")
    private WebElement logOutButton;


    @FindBy(id = "newPassword")
    private WebElement newPasswordField;

    @FindBy(id = "changePassword")
    private WebElement changePasswordButton;


    @FindBy(id = "deleteConfirmation")
    private WebElement deleteButton;

    public ProfilePageUser(WebDriver driver) {
        super(driver);
        this.driver.get(path + "?command=ProfilePage");
    }

    public void submitLogout() {
        logOutButton.click();
    }


    public void setNewPasswordField(String newPassword) {
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
    }

    public void submitNewPassword() {
        changePasswordButton.click();
    }

    public void submitNewPasswordInvalid() {
        changePasswordButton.click();
    }


    public void submitDelete() {
        deleteButton.click();
    }

    public boolean hasH3WithUserName(String name) {
        List<WebElement> h3List = driver.findElements(By.tagName("h3"));
        for (WebElement h3 : h3List) {
            if (h3.getText().contains(name)) return true;
        }
        return false;
    }
}