package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TestResultPage extends Page {
    @FindBy(id = "date")
    private WebElement dateTestResultField;

    @FindBy(id = "add")
    private WebElement addTestResultButton;

    public TestResultPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "?command=TestResultPage");
    }

    public void setDateTestResultField(String dateTestResult) {
        dateTestResultField.clear();
        dateTestResultField.sendKeys(dateTestResult);
    }

    public void submitAddTestResult() {
        addTestResultButton.click();
    }

    public boolean containsTestResultWithDate(String date) {
        List<WebElement> testResults = driver.findElement(By.tagName("table")).findElements(By.tagName("td"));
        for (WebElement td : testResults) {
            if (td.getText().equals(date)) return true;
        }
        return false;
    }

    public boolean containsErrorMessageWithText(String text) {
        WebElement logInP = this.driver.findElement(By.cssSelector("div.alert-danger p"));
        if (logInP.getText().equals(text)) return true;
        return false;
    }
}
