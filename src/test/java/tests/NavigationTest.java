package tests;

import base.BaseClass;
import config.ConfigReader;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Test to verify that the admin can navigate to each of the major sections from
 * the dashboard. The headings on each page are checked to ensure the correct
 * page is displayed.
 */
public class NavigationTest extends BaseClass {

	@Test(priority = 3)
	@Description("Verify navigation to Department, Leave Types, Manage Leave and Manage Admin sections")
	public void verifyNavigationToAllSections() {
		// Login first
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		DashboardPage dashboard = new DashboardPage(driver);

		// Department section
		dashboard.goToDepartmentSection();
		DepartmentPage deptPage = new DepartmentPage(driver);
		Assert.assertNotNull(deptPage.getHeading(), "Department Section heading is null");

		// Leave Types
		dashboard.goToLeaveTypes();
		LeaveTypesPage leavePage = new LeaveTypesPage(driver);
		Assert.assertNotNull(leavePage.getHeading(), "Leave Types heading is null");

		// Manage Leave
		dashboard.goToManageLeave();
		ManageLeavePage manageLeavePage = new ManageLeavePage(driver);
		Assert.assertNotNull(manageLeavePage.getHeading(), "Manage Leave heading is null");

		// Manage Admin
		dashboard.goToManageAdmin();
		ManageAdminPage manageAdminPage = new ManageAdminPage(driver);
		Assert.assertNotNull(manageAdminPage.getHeading(), "Manage Admin heading is null");
	}
}