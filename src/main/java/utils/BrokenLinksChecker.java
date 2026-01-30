package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Utility class to check for broken links on a web page.  It uses
 * Selenium WebDriver to gather all hyperlink elements and then
 * opens an HTTP connection for each URL to verify the response code.
 * This class avoids any advanced Java constructs; all loops are
 * straightforward for readability.
 */
public class BrokenLinksChecker {

    /**
     * Iterates through all links on the current page and prints out
     * any links that do not return an HTTP 200 response.  Images
     * embedded via the <img> tag are also checked via their src
     * attribute.  The method does not throw an exception when it
     * encounters a broken link; it simply prints the result.
     *
     * @param driver WebDriver instance pointed to the page under test
     */
    public static void checkBrokenLinks(WebDriver driver) {
        // Collect anchor tags
        List<WebElement> links = driver.findElements(By.tagName("a"));
        // Collect image tags for their src attributes
        List<WebElement> images = driver.findElements(By.tagName("img"));

        // Combine the lists manually (avoid using advanced collections)
        for (int i = 0; i < images.size(); i++) {
            links.add(images.get(i));
        }

        // Check each element
        for (int i = 0; i < links.size(); i++) {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");
            if (url == null || url.trim().isEmpty()) {
                // Try src attribute for images
                url = element.getAttribute("src");
            }
            if (url == null || url.trim().isEmpty()) {
                continue;
            }
            verifyLink(url);
        }
    }

    /**
     * Opens an HTTP connection to the given URL and prints if it is broken.
     *
     * @param url String representation of the link to test
     */
    private static void verifyLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setConnectTimeout(3000);
            httpConn.connect();
            int responseCode = httpConn.getResponseCode();
            if (responseCode >= 400) {
                System.out.println(url + " is a broken link. Response Code: " + responseCode);
            } else {
                System.out.println(url + " is valid. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println(url + " could not be checked. Error: " + e.getMessage());
        }
    }
}