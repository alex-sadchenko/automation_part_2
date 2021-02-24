package by.automation.page.googlecloud;

import by.automation.page.AbstractPage;
import by.automation.page.tempmail.TempMailHomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = "//*[@name='emailForm']//input[@type='email']")
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

    public TempMailHomePage sendEmail() {
        openTempMail().copyEmailAddress().switchToCalculator();
        switchToFrame(iFrame);
        switchToFrame(myFrame);
        scrollToElement(emailEstimateButton).click();
        scrollToElement(emailField).sendKeys(TempMailHomePage.getEmailAddress());
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
