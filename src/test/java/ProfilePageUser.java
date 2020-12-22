import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
}