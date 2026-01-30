package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for the Manage Leave section.  Only exposes a
 * method to fetch the page heading since no specific actions are
 * required at this stage.
 */
public class ManageLeavePage {
    private WebDriver driver;
    @FindBy(xpath = "//h1 | //h2 | //h3")
    private WebElement heading;

    public ManageLeavePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeading() {
        return heading.getText();
    }
}