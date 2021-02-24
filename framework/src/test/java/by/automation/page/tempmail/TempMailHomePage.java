package by.automation.page.tempmail;

import by.automation.page.AbstractPage;
import by.automation.page.googlecloud.GoogleCloudEstimatedInstancePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.List;

public class TempMailHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://temp-mail.org/";
    private static String emailWindow;
    private static String emailAddress;

    @FindBy(id = "mail")
    private WebElement emailAddressInput;
    @FindBy(xpath = "//*[@class='temp-emailbox']//button[@class='btn-rds icon-btn bg-theme click-to-copy copyIconGreenBtn']")
    private WebElement copyEmailAddress;
    @FindBy(xpath = "//*[@class='inbox-header hidden-xs-sm']")
    private WebElement inboxMessageField;
    @FindBy(xpath = "//*[@class='inbox-dataList']//li[@class='']")
    private List<WebElement> inboxMessages;

    public TempMailHomePage(WebDriver driver) {
        super(driver);
        emailWindow = driver.getWindowHandle();
    }

    public TempMailHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("opened web page " + HOMEPAGE_URL);
        return this;
    }

    public TempMailHomePage copyEmailAddress() {
        wait(10).until(elementToBeClickable(copyEmailAddress));
        emailAddress = emailAddressInput.getAttribute("value");
        return this;
    }

    public void switchToCalculator() {
        driver.switchTo().window(GoogleCloudEstimatedInstancePage.getCalculatorWindow());
    }

    public TempMailMessageFromGoogleCalculatorPage readMessage(String subjectName) {
        scrollToElement(inboxMessageField);
        wait(60).until((ExpectedCondition<WebElement>) input -> {
            for (WebElement element : inboxMessages) {
                if (element.findElement(By.xpath("//a[contains(text(), '" + subjectName + "')]"))
                        .getText().equals(subjectName))
                    return element;
            }
            return null;
        }).click();
        logger.info("read message with subject " + subjectName);
        return new TempMailMessageFromGoogleCalculatorPage(driver);
    }

    public static String getEmailWindow() {
        return emailWindow;
    }

    public static String getEmailAddress() {
        return emailAddress;
    }
}

