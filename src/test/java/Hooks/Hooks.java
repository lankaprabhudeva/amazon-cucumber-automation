package Hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.DriverFactory;
import org.testng.Reporter;

public class Hooks {

    @Before
    public void setUp() {
        // Get browser from TestNG XML parameter or default to Chrome
        String browser = "chrome";
        try {
            browser = Reporter.getCurrentTestResult()
                              .getTestContext()
                              .getCurrentXmlTest()
                              .getParameter("browser");
        } catch (Exception e) {
            System.out.println("[WARN] Could not fetch browser parameter. Defaulting to Chrome.");
        }

        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        System.out.println("[INFO] Launching browser: " + browser);

        // Initialize driver
        DriverFactory.initDriver(browser);

        // Get target URL from system property or use default
        String url = System.getProperty("app.url", "https://www.primevideo.com/offers/nonprimehomepage/ref=atv_auth_signout");
        System.out.println("[INFO] Navigating to URL: " + url);

        DriverFactory.getDriver().get(url);
    }

    @After
    public void tearDown() {
        System.out.println("[INFO] Tearing down WebDriver");
        DriverFactory.quitDriver();
    }
}
