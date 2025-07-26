package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {
        if (driver != null) {
            return driver;
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (isHeadless()) {
            System.out.println("[INFO] Running in headless mode");
            options.addArguments("--headless=new", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        Runtime.getRuntime().addShutdownHook(new Thread(DriverFactory::quitDriver));
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static boolean isHeadless() {
        return System.getProperty("headless", "false").equalsIgnoreCase("true");
    }
}
