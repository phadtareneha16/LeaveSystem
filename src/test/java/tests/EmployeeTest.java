package tests;

import base.BaseClass;
import config.ConfigReader;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.ExcelUtils;

/**
 * Test to add new employees to the system using data from an Excel file.
 * For each row of test data the test logs in, navigates to the
 * employee section, fills the add employee form and verifies the
 * record appears in the list.  Each iteration runs independently.
 */
public class EmployeeTest extends BaseClass {

    @DataProvider(name = "employeeData")
    public Object[][] employeeData() {
        String path = System.getProperty("user.dir") + "/TestData/EmployeeData.xlsx";
        return ExcelUtils.readExcelData(path);
    }

    @Test(dataProvider = "employeeData", priority = 2)
    @Description("Add a new employee and verify it appears in the employee list")
    public void addEmployeeTest(String employeeID, String firstName, String lastName,
                                String email, String department, String gender,
                                String dob, String contactNumber, String country,
                                String address, String city, String password,
                                String confirmPassword) {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // Step 2: Navigate to Employee Section
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.goToEmployeeSection();

        // Step 3: Click Add New Employee
        EmployeeSectionPage employeeSection = new EmployeeSectionPage(driver);
        employeeSection.clickAddNewEmployee();

        // Step 4: Fill out and submit the form
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.addEmployee(employeeID, firstName, lastName, email, department, gender,
                dob, contactNumber, country, address, city, password, confirmPassword);

        // Step 5: After submission, we should be returned to the list
        // Verify the new employee is present
        // It's necessary to navigate back to the employee list if not automatically redirected
        dashboard.goToEmployeeSection();
        boolean isPresent = employeeSection.isEmployeePresent(employeeID);
        Assert.assertTrue(isPresent, "Employee with ID " + employeeID + " was not found in the list.");
    }
}