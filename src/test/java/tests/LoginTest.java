package tests;

import base.BaseClass;
import config.ConfigReader;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

/**
 * Test to verify that an administrator can log into the system
 * successfully.  It uses the credentials from the configuration
 * properties file.  After logging in, the test asserts that
 * the URL has changed away from the login page.
 */
public class LoginTest extends BaseClass {

    @Test(priority = 1)
    @Description("Verify that admin can login with valid credentials")
    public void verifyAdminLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String user = ConfigReader.getProperty("username");
        String pass = ConfigReader.getProperty("password");
        String oldUrl = driver.getCurrentUrl();
        loginPage.login(user, pass);
        // After login the URL should not remain the same as the login URL
        String newUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(newUrl, oldUrl, "Login failed: URL did not change after login.");
    }
}