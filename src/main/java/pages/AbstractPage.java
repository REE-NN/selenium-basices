package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
    public WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isElementPresent() {
        try {
            driver.findElement(By.cssSelector(".ComposePopup-Content"));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}