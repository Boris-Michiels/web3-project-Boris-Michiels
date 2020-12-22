import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteConfirmationPage extends Page {
    @FindBy(id = "noDelete")
    private WebElement noDeleteButton;


    @FindBy(id = "delete")
    private WebElement deleteButton;

    public DeleteConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "?command=DeleteConfirmationPage");
    }

    public void submitNoDelete() {
        noDeleteButton.click();
    }


    public void submitDelete() {
        deleteButton.click();
    }
}