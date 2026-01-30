package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;



/**
 * Represents the Admin dashboard.  Provides methods to navigate
 * to various sections such as Employee, Department, Leave Types,
 * Manage Leave and Manage Admin.  Also exposes a method to log out.
 */
public class DashboardPage {
    private WebDriver driver;

    // Side menu links
    @FindBy(linkText = "Employee Section")
    private WebElement employeeSectionLink;

    @FindBy(linkText = "Department Section")
    private WebElement departmentSectionLink;

    @FindBy(linkText = "Leave Types")
    private WebElement leaveTypesLink;

    @FindBy(linkText = "Manage Leave")
    private WebElement manageLeaveLink;

    @FindBy(linkText = "Manage Admin")
    private WebElement manageAdminLink;

    // Top right admin dropdown and logout link
    @FindBy(xpath = "//div[contains(@class,'dropdown')]//span[contains(text(),'ADMIN')]")
    private WebElement adminDropdown;

    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigate to the employee management section.
     */
    @Step("Navigating to Employee Section")
    public void goToEmployeeSection() {
        employeeSectionLink.click();
    }

    /**
     * Navigate to the department management section.
     */
    @Step("Navigating to Department Section")
    public void goToDepartmentSection() {
        departmentSectionLink.click();
    }

    /**
     * Navigate to the leave types page.
     */
    @Step("Navigating to Leave Types")
    public void goToLeaveTypes() {
        leaveTypesLink.click();
    }

    /**
     * Navigate to the manage leave section.
     */
    @Step("Navigating to Manage Leave")
    public void goToManageLeave() {
        manageLeaveLink.click();
    }

    /**
     * Navigate to the manage admin section.
     */
    @Step("Navigating to Manage Admin")
    public void goToManageAdmin() {
        manageAdminLink.click();
    }

    /**
     * Log out of the application.
     */
    @Step("Logging out of the application")
    public void logout() {
        adminDropdown.click();
        logoutLink.click();
    }
}