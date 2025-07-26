package Hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("[INFO] Starting browser...");
        DriverFactory.initDriver();

        String url = System.getProperty("app.url", "https://www.primevideo.com/offers/nonprimehomepage/ref=atv_auth_signout");
        DriverFactory.getDriver().get(url);
    }

    @After
    public void tearDown() {
        System.out.println("[INFO] Closing browser...");
        DriverFactory.quitDriver();
    }
}
