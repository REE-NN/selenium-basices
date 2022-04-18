package testCases;

import dataSource.ConfProperties;
import dataSource.DriverManager;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MailPage;
import pages.MailPageWrightLetter;
import pages.UserMenu;

import java.time.Duration;

import static dataSource.DriverManager.driver;
import static dataSource.StaticSource.WAIT_TIMEOUT_SECONDS_20;
import static org.testng.Assert.assertEquals;

public class LoginTest {
    public static LoginPage loginPage;
    public static UserMenu userMenu;
    public static MailPage mailPage;
    public static MailPageWrightLetter wrightLetter;

    @BeforeSuite(description = "load browserDriver")
    static void startMethod() {
        System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("geckodriver"));
        driver = DriverManager.loadDriver();
        loginPage = new LoginPage(driver);
        userMenu = new UserMenu(driver);
        mailPage = new MailPage(driver);
        wrightLetter = new MailPageWrightLetter(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS_20)); // Implicit Wait, неявное ожидание
        driver.get(ConfProperties.getProperty("startPage"));
    }

    @Test(groups = "logIn")
    public void loginTest() {
        loginPage.doLogin();

        String user = userMenu.getEmailAddress();
        assertEquals(user, "ivanov-autotest.post", "test login fail");
    }

    @Test(groups = {"enter menu"},
            dependsOnGroups = "logIn")
    public void openMailPage() {
        userMenu.openRightMenuItem(userMenu.getMailPage());
    }
}