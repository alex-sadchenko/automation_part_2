package by.automation.page.tempmail;

import by.automation.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class TempMailHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://temp-mail.org/";
    private static String emailWindow;

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
    }

    public TempMailHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        emailWindow = driver.getWindowHandle();
        logger.info("opened web page " + HOMEPAGE_URL);
        return this;
    }

    public String copyEmailAddress() {
        wait(10).until(elementToBeClickable(copyEmailAddress));
        return emailAddressInput.getAttribute("value");
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

    public String getEmailWindow() {
        return emailWindow;
    }
}

