package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for the Manage Admin section.  Keeps the
 * implementation minimal by only exposing the page heading.
 */
public class ManageAdminPage {
    private WebDriver driver;
    @FindBy(xpath = "//h1 | //h2 | //h3")
    private WebElement heading;

    public ManageAdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeading() {
        return heading.getText();
    }
}