package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

/**
 * Page object for the Add Employee form.  This class maps the form
 * fields to WebElements and provides a method to fill out the form
 * using individual parameters.  A select element is used for the
 * drop‑downs.  Locators are defined by their name attributes where
 * possible; simple XPath is used as a fallback.
 */
public class AddEmployeePage {
    private WebDriver driver;

    // Form fields
    @FindBy(name = "employeeid")
    private WebElement employeeIdField;

    @FindBy(name = "firstname")
    private WebElement firstNameField;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "department")
    private WebElement departmentDropdown;

    @FindBy(name = "gender")
    private WebElement genderDropdown;

    @FindBy(name = "dob")
    private WebElement dobField;

    @FindBy(name = "contact")
    private WebElement contactNumberField;

    @FindBy(name = "country")
    private WebElement countryField;

    @FindBy(name = "address")
    private WebElement addressField;

    @FindBy(name = "city")
    private WebElement cityField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[contains(text(),'Proceed')] | //button[contains(text(),'PROCEED')] | //button[contains(text(),'Submit')]")
    private WebElement proceedButton;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Fills out the add employee form with the supplied details and submits it.
     * Drop‑down selections are made using the Select class.  Any empty
     * parameter will cause the corresponding field to remain unchanged.
     */
    @Step("Adding new employee with ID {employeeId}")
    public void addEmployee(String employeeId, String firstName, String lastName,
                            String email, String department, String gender, String dob,
                            String contact, String country, String address, String city,
                            String password, String confirmPassword) {
        // Fill text fields
        employeeIdField.clear();
        employeeIdField.sendKeys(employeeId);

        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        emailField.clear();
        emailField.sendKeys(email);

        // Select department from dropdown
        Select deptSelect = new Select(departmentDropdown);
        deptSelect.selectByVisibleText(department);

        // Select gender
        Select genderSelect = new Select(genderDropdown);
        genderSelect.selectByVisibleText(gender);

        // DOB field is a date picker; send the date string
        dobField.clear();
        dobField.sendKeys(dob);

        contactNumberField.clear();
        contactNumberField.sendKeys(contact);

        countryField.clear();
        countryField.sendKeys(country);

        addressField.clear();
        addressField.sendKeys(address);

        cityField.clear();
        cityField.sendKeys(city);

        passwordField.clear();
        passwordField.sendKeys(password);

        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);

        // Submit the form
        proceedButton.click();
    }
}