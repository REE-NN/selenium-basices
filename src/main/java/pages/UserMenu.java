package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static dataSource.StaticSource.WAIT_TIMEOUT_SECONDS_30;
import static dataSource.StaticSource.WAIT_TIMEOUT_SECONDS_90;

public class UserMenu extends AbstractPage {

    public UserMenu(WebDriver driver) {
        super(driver);
    }

    Actions actions = new Actions(driver);

    @FindBy(css = ".user-pic__image")
    private WebElement userMenu;

    @FindBy(css = ".personal-info-login__text_decorated")
    private WebElement emailAddress;

    @FindBy(css = ".mail-ComposeButton")
    private WebElement newLetterBtn;

    @FindBy(css = ".legouser__menu-item_action_mail")
    private WebElement mailPageBtn;

    @FindBy(css = ".legouser__menu-item_action_exit")
    private WebElement logoutBtn;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftFolder;

    @FindBy(xpath = "//span[contains(text(),'Отправленные')]")
    private WebElement sentFolder;

    @FindBy(xpath = "//span[contains(text(),'Удалённые')]")
    private WebElement trashFolder;

    public WebElement getDraftFolder() {
        return draftFolder;
    }

    public WebElement getSentFolder() {
        return sentFolder;
    }

    public WebElement getTrashFolder() {
        return trashFolder;
    }

    public WebElement getNewLetterBtn() {
        return newLetterBtn;
    }

    public WebElement getMailPage() {
        return mailPageBtn;
    }

    public WebElement getLogout() {
        return logoutBtn;
    }

    public String getEmailAddress() {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS_30))
                .until(ExpectedConditions.visibilityOf(emailAddress)).getText();
    }

    public MailPage openRightMenuItem(WebElement somePage) {
        actions
                .click(userMenu)
                .pause(WAIT_TIMEOUT_SECONDS_90)
                .click(somePage)
                .build().perform();
        return new MailPage(driver);
    }

    public MailPage openLeftMenuItem(WebElement webElement) {
        actions.click(webElement).perform();
        return new MailPage(driver);
    }
}