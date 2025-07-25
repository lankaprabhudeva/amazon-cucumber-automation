package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver(String browser) {
        if (driver != null) {
            return driver;
        }

        System.out.println("[INFO] Initializing browser: " + browser);

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();

                    if (isHeadless()) {
                        System.out.println("[INFO] Running Chrome in headless mode");
                        chromeOptions.addArguments("--headless=new"); // fallback to "--headless" if needed
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }

                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();

                    if (isHeadless()) {
                        System.out.println("[INFO] Running Edge in headless mode");
                        edgeOptions.addArguments("--headless"); // safer than "--headless=new" for Edge
                        edgeOptions.addArguments("--disable-gpu");
                        edgeOptions.addArguments("--window-size=1920,1080");
                        edgeOptions.addArguments("--disable-dev-shm-usage");
                        edgeOptions.addArguments("--no-sandbox");
                    }

                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();

            // Add shutdown hook to quit driver on JVM exit
            Runtime.getRuntime().addShutdownHook(new Thread(DriverFactory::quitDriver));

            return driver;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("[ERROR] Failed to initialize browser: " + browser, e);
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("[ERROR] WebDriver not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                System.out.println("[INFO] Quitting WebDriver");
                driver.quit();
            } catch (Exception e) {
                System.err.println("[WARN] Error while quitting driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    private static boolean isHeadless() {
        // Enable headless mode via system property: -Dheadless=true
        String headless = System.getProperty("headless", "false");
        return headless.equalsIgnoreCase("true");
    }
}
