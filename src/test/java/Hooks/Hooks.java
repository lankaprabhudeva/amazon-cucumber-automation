package Hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.getDriver().get("https://www.primevideo.com/region/eu/storefront/ref=atv_dp_mv_c_9zZ8D2_1_2?merchId=RentBuy&ie=UTF8");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
