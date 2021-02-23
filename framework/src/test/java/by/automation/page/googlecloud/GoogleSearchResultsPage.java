package by.automation.page.googlecloud;

import by.automation.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleSearchResultsPage extends AbstractPage {

    @FindBy(xpath = "//a[@class='gs-title']//b")
    private List<WebElement> searchResults;

    public GoogleSearchResultsPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public GoogleCloudCalculatorPage openGoogleCloudCalculatorPage(String searchTerm) {
        for (WebElement element : searchResults){
            if(element.getText().equals(searchTerm)){
                element.click();
                break;
            }
        }
        logger.info("opened page with search term " + searchTerm);
        return new GoogleCloudCalculatorPage(driver);
    }
}
