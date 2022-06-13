package pages;

import dataSource.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static dataSource.StaticSource.*;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#passp-field-login")
    private WebElement loginField;

    @FindBy(css = "#passp\\:sign-in")
    private WebElement loginBtn;


    //@FindBy(xpath = "//*[@id='passp-field-passwd']")
    @FindBy(css = "#passp-field-passwd")
    private WebElement passwdField;

    public WebElement getPasswdField() {
 //       return passwdField;
        Wait<WebDriver> waiter = new FluentWait<>(driver);
        return waiter
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("#passp-field-passwd"))
                );
    }

    Actions actions = new Actions(driver);

    public LoginPage inputLogin() {
        actions.sendKeys(loginField, ConfProperties.getProperty(LOGIN))
                .click(loginBtn)
                .perform();
        return this;
    }

    public LoginPage inputPasswd() {
        actions.sendKeys(getPasswdField(), ConfProperties.getProperty(PASS))
                .click(loginBtn).build().perform();
        return this;
    }

    public MailPage doLogin() {
        inputLogin();
        inputPasswd();
        return new MailPage(driver);
    }
}