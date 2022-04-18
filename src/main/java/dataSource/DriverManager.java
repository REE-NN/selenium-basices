package dataSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

import static dataSource.StaticSource.GECKO;
import static dataSource.StaticSource.LOAD_DRIVER_MESSAGE;
import static dataSource.StaticSource.UNLOAD_DRIVER_MESSAGE;

public class DriverManager {
    public static WebDriver driver;

    public static WebDriver loadDriver() {
        if (driver == null) {
            System.setProperty(GECKO, ConfProperties.getProperty("geckodriver"));
            driver = new FirefoxDriver();
            System.out.println(LOAD_DRIVER_MESSAGE);
        }
        return driver;
    }

    public static void unloadDriver() {
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
                System.out.println(UNLOAD_DRIVER_MESSAGE);
            } catch (WebDriverException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}