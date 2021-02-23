package by.automation.page.tempmail;

import by.automation.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TempMailMessageFromGoogleCalculatorPage extends AbstractPage {

    @FindBy(xpath = "//tbody//h3[contains (text(), 'USD')]")
    private WebElement estimationSum;

    protected TempMailMessageFromGoogleCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public String getSum() {
        return scrollToElement(estimationSum)
                .getText()
                .replaceAll("[^\\d,.]+|(?<=\\s)\\d{1,2}(?=\\s)", "");
    }
}
