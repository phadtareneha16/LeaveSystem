package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for the Department section.  This class is intentionally
 * minimal as the requirements only specify navigation to the page.  It
 * provides a method to retrieve the page heading to confirm navigation.
 */
public class DepartmentPage {
    private WebDriver driver;

    @FindBy(xpath = "//h1 | //h2 | //h3")
    private WebElement heading;

    public DepartmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Returns the text of the page heading.
     *
     * @return String heading text
     */
    public String getHeading() {
        return heading.getText();
    }
}