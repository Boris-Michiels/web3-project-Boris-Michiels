import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Filip Saint, Boris Michiels
 **/

public class ProfilePageGuest extends Page {
    @FindBy(id = "useridLogIn")
    private WebElement useridLogInField;

    @FindBy(id = "passwordLogIn")
    private WebElement passwordLogInField;

    @FindBy(id = "logIn")
    private WebElement logInButton;


    @FindBy(id = "userid")
    private WebElement useridRegisterField;

    @FindBy(id = "firstName")
    private WebElement firstNameRegisterField;

    @FindBy(id = "lastName")
    private WebElement lastNameRegisterField;

    @FindBy(id = "email")
    private WebElement emailRegisterField;

    @FindBy(id = "password")
    private WebElement passwordRegisterField;

    @FindBy(id = "register")
    private WebElement registerButton;

    public ProfilePageGuest(WebDriver driver) {
        super(driver);
        this.driver.get(path + "?command=ProfilePage");
    }

    public void setUseridLogInField(String useridLogIn) {
        useridLogInField.clear();
        useridLogInField.sendKeys(useridLogIn);
    }

    public void setPasswordLogInField(String passwordLogIn) {
        passwordLogInField.clear();
        passwordLogInField.sendKeys(passwordLogIn);
    }

    public void submitLogIn() {
        logInButton.click();
    }


    public void setUseridRegisterField(String useridRegister) {
        useridRegisterField.clear();
        useridRegisterField.sendKeys(useridRegister);
    }

    public void setFirstNameRegisterField(String firstNameRegister) {
        firstNameRegisterField.clear();
        firstNameRegisterField.sendKeys(firstNameRegister);
    }

    public void setLastNameRegisterField(String lastNameRegister) {
        lastNameRegisterField.clear();
        lastNameRegisterField.sendKeys(lastNameRegister);
    }

    public void setEmailRegisterField(String emailRegister) {
        emailRegisterField.clear();
        emailRegisterField.sendKeys(emailRegister);
    }

    public void setPasswordRegisterField(String passwordRegister) {
        passwordRegisterField.clear();
        passwordRegisterField.sendKeys(passwordRegister);
    }

    public void submitRegister() {
        registerButton.click();
    }

    public boolean hasLogInForm() {
        return driver.findElement(By.name("logInForm")) != null;
    }

    public boolean hasLogOutButton() {
        return driver.findElement(By.id("logOut")) != null;
    }
}