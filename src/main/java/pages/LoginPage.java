package pages;

import dataSource.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static dataSource.StaticSource.LOGIN;
import static dataSource.StaticSource.PASS;
import static dataSource.StaticSource.WAIT_TIMEOUT_SECONDS_90;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#passp-field-login")
    private WebElement loginField;

    @FindBy(css = "#passp\\:sign-in")
    private WebElement loginBtn;

    @FindBy(css = "#passp-field-passwd")
    private WebElement passwdField;

    public WebElement getPasswdField() {
        return passwdField;
    }

    Actions actions = new Actions(driver);

    public LoginPage inputLogin() {
        actions.sendKeys(loginField, ConfProperties.getProperty(LOGIN))
                .click(loginBtn).perform();
        return this;
    }

    public LoginPage inputPasswd() {
        actions.pause(WAIT_TIMEOUT_SECONDS_90).sendKeys(passwdField, ConfProperties.getProperty(PASS))
                .click(loginBtn).build().perform();
        return this;
    }

    public MailPage doLogin() {
        inputLogin();
        inputPasswd();
        return new MailPage(driver);
    }
}