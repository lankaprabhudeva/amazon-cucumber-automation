package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        if (driver.get() != null) {
            return driver.get();
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (isHeadless()) {
            System.out.println("[INFO] Running in headless mode");
            options.addArguments("--headless=new", "--disable-gpu", "--no-sandbox",
                    "--disable-dev-shm-usage", "--window-size=1920,1080");
        }

        WebDriver localDriver = new ChromeDriver(options);
        localDriver.manage().window().maximize();
        driver.set(localDriver);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            quitDriver();
        }));

        return driver.get();
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver not initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }

    private static boolean isHeadless() {
        return System.getProperty("headless", "false").equalsIgnoreCase("true");
    }
}
