package by.automation.page.googlecloud;

import by.automation.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com";

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchButton;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public GoogleSearchResultsPage searchForResults(String searchInput) {
        searchButton.sendKeys(searchInput);
        searchButton.submit();
        logger.info("opened search results for " + searchInput);
        return new GoogleSearchResultsPage(driver);
    }
}
