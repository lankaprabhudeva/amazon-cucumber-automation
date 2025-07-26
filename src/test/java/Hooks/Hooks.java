package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("---------- Starting Scenario: " + scenario.getName() + " ----------");
        DriverFactory.initDriver();  // Initialize driver
        WebDriver driver = DriverFactory.getDriver();  // Get driver
        try {
            driver.get("https://www.primevideo.com/region/eu/storefront/ref=atv_dp_mv_c_9zZ8D2_1_2?merchId=RentBuy&ie=UTF8");
        } catch (Exception e) {
            System.out.println("[ERROR] Could not load page: " + e.getMessage());
        }

    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("[FAILED] Scenario: " + scenario.getName());
        } else {
            System.out.println("[PASSED] Scenario: " + scenario.getName());
        }

        DriverFactory.quitDriver();
        System.out.println("---------- Finished Scenario: " + scenario.getName() + " ----------");
    }
}
