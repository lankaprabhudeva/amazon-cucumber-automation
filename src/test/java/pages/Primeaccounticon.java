package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
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

    // Actions

    public void mouseHoverAtIcon(String iconLabel) {
        waitForVisibility(mouseHoverIcon, 20);
        action.moveToElement(mouseHoverIcon).perform();
    }

    public void mouseHoverAndClick(String label) {
        waitForVisibility(mouseHoverSignIn, 10);
        action.moveToElement(mouseHoverSignIn).click().perform();
    }

    public void enterMobileNumber(String number) {
        waitForVisibility(mobileNumberField, 10);
        mobileNumberField.clear();
        mobileNumberField.sendKeys(number);
    }

    public void clickButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Continue")) {
            continueButton.click();
        } else if (buttonName.equalsIgnoreCase("Sign In")) {
            signInButton.click();
        }
    }

    public void enterPassword(String password) {
        waitForVisibility(passwordField, 10);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public boolean verifyPasswordPageLoaded() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(passwordField),
                ExpectedConditions.urlContains("/ap/signin")
            ));

            boolean visible = passwordField.isDisplayed();
            System.out.println("Password field visible: " + visible);
            return visible;
        } catch (TimeoutException e) {
            System.out.println("Password field not visible within timeout.");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());
            captureScreenshot("password_page_failure");
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            captureScreenshot("password_page_exception");
            return false;
        }
    }

    public String getPageStatusOrError() {
        try {
            if (genericError.isDisplayed()) {
                return genericError.getText().trim();
            } else if (usernameMissingError.isDisplayed()) {
                return usernameMissingError.getText().trim();
            } else if (passwordMissingError.isDisplayed()) {
                return passwordMissingError.getText().trim();
            }
        } catch (Exception e) {
            // No error message found
        }

        try {
            return driver.getTitle();
        } catch (Exception ignored) {}

        return "Unknown state";
    }

    // Helper Methods

    private void waitForVisibility(WebElement element, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    private void captureScreenshot(String filename) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("target/screenshots/" + filename + ".png");
            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
