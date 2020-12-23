package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Filip Saint, Boris Michiels
 * */

public abstract class Page {

    WebDriver driver;
    String path = "http://localhost:8080/Controller";

    public Page (WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle () {
        return driver.getTitle();
    }

    public boolean containsStatusMessageWithText(String text) {
        WebElement statusMessage = this.driver.findElement(By.id("statusMessage")).findElement(By.tagName("p"));
        return statusMessage.getText().equals(text);
    }
}