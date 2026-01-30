package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import config.ConfigReader;
import io.qameta.allure.Step;

/**
 * Base class to initialise and quit the WebDriver.  All test classes
 * should extend this class so they inherit the setup and teardown
 * methods.  Configuration values are pulled from the ConfigReader.
 */
public class BaseClass {
    protected WebDriver driver;

    /**
     * Setup method to initialise the WebDriver before each test method.
     */
    @BeforeMethod
    @Step("Setting up the browser")
    public void setUp() {
        // Read configuration
        String browser = ConfigReader.getProperty("browser");
        String driverPath = ConfigReader.getProperty("driverPath");
        String url = ConfigReader.getProperty("url");

        // For this simple framework only Chrome is supported
        if (browser != null && browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            // Use basic options to avoid any unsupported feature
            ChromeOptions options = new ChromeOptions();
            // Disable notifications for a cleaner test run
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else {
            throw new RuntimeException("Only Chrome browser is supported in this framework.");
        }
        // Maximise and set implicit wait
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Navigate to the base URL
        driver.get(url);
    }

    /**
     * Tear down method to quit the WebDriver after each test method.
     */
    @AfterMethod
    @Step("Tearing down the browser")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}