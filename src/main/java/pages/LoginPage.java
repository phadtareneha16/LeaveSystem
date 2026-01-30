package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * Page object representing the Admin login page.  It contains
 * elements for the username, password and submit button along
 * with a method to perform the login action.  Locators are kept
 * as simple as possible to aid maintainability.
 */
public class LoginPage {
    private WebDriver driver;

    // Username input field
    @FindBy(name = "username")
    private WebElement usernameField;

    // Password input field
    @FindBy(name = "password")
    private WebElement passwordField;

    // Submit button
    @FindBy(xpath = "//button[contains(text(),'Submit') or contains(text(),'Login') or contains(text(),'SUBMIT')]")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Logs into the application using the given credentials.
     *
     * @param user     admin username
     * @param password admin password
     */
    @Step("Logging in with username {user}")
    public void login(String user, String password) {
        usernameField.clear();
        usernameField.sendKeys(user);
        passwordField.clear();
        passwordField.sendKeys(password);
        submitButton.click();
    }
}