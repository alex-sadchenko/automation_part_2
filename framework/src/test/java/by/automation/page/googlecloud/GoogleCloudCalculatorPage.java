package by.automation.page.googlecloud;

import by.automation.model.Instance;
import by.automation.page.AbstractPage;
import by.automation.page.tempmail.TempMailHomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GoogleCloudCalculatorPage extends AbstractPage {
    private static String calculatorWindow;

    @FindBy(xpath = "//*[@class='md-tab ng-scope ng-isolate-scope md-ink-ripple md-active']//*[@class='tab-holder compute']")
    private WebElement computeEngineButton;
    @FindBy(id = "input_63")
    private WebElement instancesField;
    @FindBy(id = "select_value_label_56")
    private WebElement operatingSystem;
    @FindBy(id = "select_80")
    private WebElement machineClass;
    @FindBy(id = "select_90")
    private WebElement machineType;
    @FindBy(id = "select_value_label_59")
    private WebElement series;
    @FindBy(xpath = "//*[@class='ng-scope layout-row']//*[contains(text(), 'Add GPUs')]")
    private WebElement gpuCheckBox;
    @FindBy(id = "select_value_label_392")
    private WebElement numberOfGpu;
    @FindBy(id = "select_value_label_393")
    private WebElement gpuType;
    @FindBy(id = "select_value_label_354")
    private WebElement localSsd;
    @FindBy(id = "select_value_label_61")
    private WebElement dataCenter;
    @FindBy(id = "select_value_label_62")
    private WebElement commitUsage;
    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to Estimate')]")
    private WebElement estimateButton;
    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;
    @FindBy(id = "input_477")
    private WebElement emailField;
    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public GoogleCloudCalculatorPage(WebDriver driver) {
        super(driver);
        calculatorWindow = driver.getWindowHandle();
    }

    private void switchToFrame() {
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe")));
        driver.switchTo().frame(driver.findElement(By.id("myFrame")));
    }

    public GoogleCloudCalculatorPage activateComputeEngine() {
        computeEngineButton.click();
        return this;
    }

    public GoogleCloudCalculatorPage fillInstancesField(String numberOfInstances) {
        instancesField.sendKeys(numberOfInstances);
        return this;
    }

    private GoogleCloudCalculatorPage dropOff(WebElement webElement, String containerNumber, String value) {
        scrollToElement(webElement);
        wait(5).until(visibilityOf(webElement)).click();
        String selectContainer = "//*[@id='select_container_%s']//div[contains(text(),'%s')]";
        wait(5)
                .until(visibilityOfElementLocated(By
                        .xpath(String.format(selectContainer, containerNumber, value)))).click();
        return this;
    }

    public GoogleCloudCalculatorPage clickAddGpuCheckBox() {
        scrollToElement(gpuCheckBox);
        gpuCheckBox.click();
        return this;
    }

    public GoogleCloudEstimatePage clickEstimate() {
        scrollToElement(estimateButton);
        estimateButton.click();
        return new GoogleCloudEstimatePage(driver);
    }

    public GoogleCloudEstimatePage estimateInstance(Instance instance) {
        switchToFrame();
        logger.info("calculate estimation for instance " + instance);
        return activateComputeEngine()
                .fillInstancesField(instance.getNumberOfInstances())
                .dropOff(operatingSystem, "77", instance.getOperatingSystem())
                .dropOff(machineClass, "81", instance.getMachineClass())
                .dropOff(series, "89", instance.getSeries())
                .dropOff(machineType, "91", instance.getMachineType())
                .clickAddGpuCheckBox()
                .dropOff(numberOfGpu, "395", instance.getNumberOfGpu())
                .dropOff(gpuType, "397", instance.getGpuType())
                .dropOff(localSsd, "356", instance.getLocalSsd())
                .dropOff(dataCenter, "93", instance.getDataCenter())
                .dropOff(commitUsage, "100", instance.getCommittedUsage())
                .clickEstimate();
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
        openTempMail().getEmailAddress().switchToCalculator();
        switchToFrame();
        emailEstimateButton.click();
        scrollToElement(emailField).sendKeys(Keys.CONTROL + "v");
        scrollToElement(sendEmailButton).click();
        switchToEmail();
        return new TempMailHomePage(driver);
    }

    public static String getCalculatorWindow() {
        return calculatorWindow;
    }

    public void switchToEmail() {
        driver.switchTo().window(TempMailHomePage.getEmailWindow());
    }
}
