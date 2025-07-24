package Stepdefpack;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Primeaccounticon;
import utils.DriverFactory;

import static org.junit.Assert.assertTrue;

public class StepDefinition {

    WebDriver driver = DriverFactory.getDriver();
    Primeaccounticon pa = new Primeaccounticon(driver);

    @Given("the user hovers over the {string} icon")
    public void the_user_hovers_over_the_icon(String iconName) {
        pa.mouseHoverAtIcon(iconName);
    }

    @When("the user hovers over {string} and clicks it")
    public void the_user_hovers_over_and_clicks_it(String menuItem) {
        pa.mouseHoverAndClick(menuItem);
    }

    @Then("the user enters a mobile number in the {string} field")
    public void the_user_enters_a_mobile_number_in_the_field(String mobileNumber) {
        pa.enterMobileNumber(mobileNumber);
    }

    @Then("clicks the {string} button")
    public void clicks_the_button(String buttonName) {
        pa.clickButton(buttonName);
    }

    @Then("the user is redirected to the password entry page")
    public void the_user_is_redirected_to_the_password_entry_page() {
        boolean loaded = pa.verifyPasswordPageLoaded();
        assertTrue("❌ Password page did not load.", loaded);
    }

    @Then("enters the password in the {string} field")
    public void enters_the_password_in_the_field(String password) {
        pa.enterPassword(password);
    }

    @Then("the user should see {string}")
    public void the_user_should_see(String expectedMessage) {
        String actualMessage = pa.getPageStatusOrError();
        System.out.println("✅ Actual message: " + actualMessage);
        assertTrue("❌ Expected to see: '" + expectedMessage + "', but got: '" + actualMessage + "'",
                actualMessage.contains(expectedMessage));
    }
}
