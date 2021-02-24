package by.automation.page.googlecloud;

import by.automation.driver.DriverSingleton;
import by.automation.page.AbstractPage;
import by.automation.page.tempmail.TempMailHomePage;
import by.automation.utils.TestListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GoogleCloudEstimatedInstancePage extends AbstractPage {
    private static String calculatorWindow;

    @FindBy(xpath = "//div[contains (text(), 'VM class')]")
    private WebElement machineClass;
    @FindBy(xpath = "//div[contains (text(), 'Instance type')]")
    private WebElement instanceType;
    @FindBy(xpath = "//div[contains (text(), 'Region')]")
    private WebElement region;
    @FindBy(xpath = "//div[contains (text(), 'Total available local SSD space')]")
    private WebElement localSsd;
    @FindBy(xpath = "//div[contains (text(), 'Commitment term')]")
    private WebElement commitmentTerm;
    @FindBy(xpath = "//b[contains (text(), 'Total Estimated Cost')]")
    private WebElement estimatedSum;
    @FindBy(css = "iframe")
    private WebElement iFrame;
    @FindBy(id = "myFrame")
    private WebElement myFrame;
    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;
    @FindBy(id = "input_477")
    private WebElement emailField;
    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;


    public GoogleCloudEstimatedInstancePage(WebDriver driver) {
        super(driver);
        calculatorWindow = driver.getWindowHandle();
    }

    public TempMailHomePage openTempMail() {
        ((JavascriptExecutor) driver).executeScript("window.open();");
        for (String pageId : driver.getWindowHandles()){
            if(!pageId.equals(getCalculatorWindow())){
                driver.switchTo().window(pageId);
            }
        }
        return new TempMailHomePage(driver).openPage();
    }

    public TempMailHomePage sendEmail() throws IOException, UnsupportedFlavorException {
        openTempMail().getEmailAddress().switchToCalculator();
        switchToFrame(iFrame);
        switchToFrame(myFrame);
        scrollToElement(emailEstimateButton).click();
        String textFromBuffer = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        scrollToElement(emailField).sendKeys(textFromBuffer);
        scrollToElement(sendEmailButton).click();
        logger.info("sent email");
        switchToEmail();
        return new TempMailHomePage(driver);
    }

    public void switchToEmail() {
        driver.switchTo().window(TempMailHomePage.getEmailWindow());
    }

    public static String getCalculatorWindow() {
        return calculatorWindow;
    }

    public String getMachineClassFromCalculatedInstance() {
        return machineClass.getText();
    }

    public String getInstanceTypeFromCalculatedInstance() {
        return instanceType.getText();
    }

    public String getRegionFromCalculatedInstance() {
        return region.getText();
    }

    public String getLocalSsdFromCalculatedInstance() {
        return localSsd.getText();
    }

    public String getCommitmentTermFromCalculatedInstance() {
        return commitmentTerm.getText();
    }

    public String getEstimatedSumFromCalculatedInstance() {
        return estimatedSum.getText().replaceAll("[^\\d,.]+|(?<=\\s)\\d{1,2}(?=\\s)", "");
    }
}
