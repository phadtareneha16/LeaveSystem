package tests;

import base.BaseClass;
import config.ConfigReader;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.BrokenLinksChecker;

/**
 * Test to check for broken links on the dashboard page.  After
 * logging in, the utility scans all anchor and image elements and
 * prints the HTTP response code for each.
 */
public class BrokenLinkTests extends BaseClass {

    @Test(priority = 4)
    @Description("Check for broken links on the dashboard page")
    public void verifyBrokenLinks() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        // Use the utility to print link statuses
        BrokenLinksChecker.checkBrokenLinks(driver);
    }
}