package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * Page object for the Employee Section listing.  Provides functionality
 * to click the Add New Employee button and to verify that a given
 * employee ID appears in the table.  Locators are chosen to be
 * straightforward and rely primarily on link text and basic XPath.
 */
public class EmployeeSectionPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[contains(text(),'Add New Employee')] | //a[contains(text(),'Add New Employee')]")
    private WebElement addNewEmployeeButton;

    // Table rows for the employee list
    @FindBy(xpath = "//table//tbody//tr")
    private List<WebElement> tableRows;

    public EmployeeSectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the Add New Employee button to navigate to the add employee form.
     */
    @Step("Clicking on Add New Employee button")
    public void clickAddNewEmployee() {
        addNewEmployeeButton.click();
    }

    /**
     * Verifies that an employee with the specified ID exists in the table.
     * Performs a simple loop through all rows and checks the text of the
     * second column (Employee ID).  Returns true if found.
     *
     * @param employeeID the employee ID to search for
     * @return true if found, false otherwise
     */
    @Step("Checking if employee with ID {employeeID} is present")
    public boolean isEmployeePresent(String employeeID) {
        boolean found = false;
        for (int i = 0; i < tableRows.size(); i++) {
            WebElement row = tableRows.get(i);
            List<WebElement> cols = row.findElements(By.tagName("td"));
            if (cols.size() > 1) {
                String idText = cols.get(1).getText().trim();
                if (idText.equalsIgnoreCase(employeeID)) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
}