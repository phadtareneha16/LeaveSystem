package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for the Leave Types section.  Provides a simple
 * accessor for the page heading.  Additional interactions can be
 * added later if required.
 */
public class LeaveTypesPage {
    private WebDriver driver;

    @FindBy(xpath = "//h1 | //h2 | //h3")
    private WebElement heading;

    public LeaveTypesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeading() {
        return heading.getText();
    }
}