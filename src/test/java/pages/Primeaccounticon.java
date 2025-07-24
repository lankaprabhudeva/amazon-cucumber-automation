package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class Primeaccounticon {

    WebDriver driver;
    Actions action;

    public Primeaccounticon(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }

    // Elements
    @FindBy(xpath = "(//button[@data-testid='pv-nav-account-and-profiles-dropdown-trigger']//span[@data-testid='inactive-profile-placeholder'])[1]")
    WebElement mouseHoverIcon;

    @FindBy(xpath = "(//a[@data-testid='pv-nav-sign-in']//span[text()='Sign In'])[1]")
    WebElement mouseHoverSignIn;

    @FindBy(id = "ap_email")
    WebElement mobileNumberField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "ap_password")
    WebElement passwordField;

    @FindBy(id = "signInSubmit")
    WebElement signInButton;

    @FindBy(xpath = "//*[@id='auth-error-message-box']//div[@id='auth-email-missing-alert']//span")
    WebElement usernameMissingError;

    @FindBy(xpath = "//*[@id='auth-error-message-box']//div[@id='auth-password-missing-alert']//span")
    WebElement passwordMissingError;

    @FindBy(xpath = "//*[@id='auth-error-message-box']//div[@id='auth-error-message-box']//span")
    WebElement genericError;

    // ---------- Actions ----------

    public void mouseHoverAtIcon(String iconLabel) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(mouseHoverIcon));
        action.moveToElement(mouseHoverIcon).perform();
    }

    public void mouseHoverAndClick(String label) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mouseHoverSignIn));
        action.moveToElement(mouseHoverSignIn).click().perform();
    }

    public void enterMobileNumber(String number) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mobileNumberField));
        mobileNumberField.clear();
        mobileNumberField.sendKeys(number);
    }

    public void clickButton(String buttonName) {
        // Add dynamic logic if needed
        if (buttonName.equalsIgnoreCase("Continue")) {
            continueButton.click();
        } else if (buttonName.equalsIgnoreCase("Sign In")) {
            signInButton.click();
        }
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public boolean verifyPasswordPageLoaded() {
        try {
            return passwordField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageStatusOrError() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            if (genericError.isDisplayed()) {
                return genericError.getText().trim();
            } else if (usernameMissingError.isDisplayed()) {
                return usernameMissingError.getText().trim();
            } else if (passwordMissingError.isDisplayed()) {
                return passwordMissingError.getText().trim();
            }
        } catch (Exception e) {
            // No error displayed
        }

        try {
            // Fallback success check: return title or URL
            return driver.getTitle(); // or driver.getCurrentUrl();
        } catch (Exception ignored) {}

        return "Unknown state";
    }
}
