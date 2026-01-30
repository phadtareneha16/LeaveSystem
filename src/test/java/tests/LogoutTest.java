package tests;

import base.BaseClass;
import config.ConfigReader;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

/**
 * Test to ensure the admin can log out of the application.  It
 * checks that the URL after logout matches the login page URL.
 */
public class LogoutTest extends BaseClass {

    @Test(priority = 5)
    @Description("Verify that admin can log out successfully")
    public void verifyLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        DashboardPage dashboardPage = new DashboardPage(driver);
        // Perform logout
        dashboardPage.logout();
        // After logout, the current URL should match the configured login URL
        String expectedUrl = ConfigReader.getProperty("url");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Logout failed: current URL does not match login page");
    }
}