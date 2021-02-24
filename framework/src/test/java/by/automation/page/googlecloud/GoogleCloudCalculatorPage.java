package by.automation.page.googlecloud;

import by.automation.model.Instance;
import by.automation.page.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GoogleCloudCalculatorPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='md-tab ng-scope ng-isolate-scope md-ink-ripple md-active']//*[@class='tab-holder compute']")
    private WebElement computeEngineButton;
    @FindBy(xpath = "//*[contains(text(), 'Number of instances')]//..//input")
    private WebElement instancesField;
    @FindBy(xpath = "//*[contains(text(), 'Operating System / Software')]//..//md-select")
    private WebElement operatingSystem;
    @FindBy(xpath = "//*[@placeholder='VM Class']")
    private WebElement machineClass;
    @FindBy(xpath = "//*[@placeholder='Instance type']")
    private WebElement machineType;
    @FindBy(xpath = "//*[@placeholder='Series']")
    private WebElement series;
    @FindBy(xpath = "//*[@class='ng-scope layout-row']//*[contains(text(), 'Add GPUs')]")
    private WebElement gpuCheckBox;
    @FindBy(xpath = "//*[@placeholder='Number of GPUs']")
    private WebElement numberOfGpu;
    @FindBy(xpath = "//*[@placeholder='GPU type']")
    private WebElement gpuType;
    @FindBy(xpath = "//*[@placeholder='Local SSD']")
    private WebElement localSsd;
    @FindBy(xpath = "//*[@name='ComputeEngineForm']//*[@placeholder='Datacenter location']")
    private WebElement dataCenter;
    @FindBy(xpath = "//*[@name='ComputeEngineForm']//*[@placeholder='Committed usage']")
    private WebElement commitUsage;
    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to Estimate')]")
    private WebElement estimateButton;
    @FindBy(css = "iframe")
    private WebElement iFrame;
    @FindBy(id = "myFrame")
    private WebElement myFrame;

    public GoogleCloudCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudCalculatorPage activateComputeEngine() {
        computeEngineButton.click();
        return this;
    }

    public GoogleCloudCalculatorPage fillInstancesField(String numberOfInstances) {
        instancesField.sendKeys(numberOfInstances);
        return this;
    }

    private GoogleCloudCalculatorPage dropOff(WebElement webElement, String value) {
        scrollToElement(webElement);
        wait(5).until(visibilityOf(webElement)).click();
        String selectContainer = "//*[@class='md-select-menu-container md-active md-clickable']//div[contains(text(),'%s')]";
        wait(5)
                .until(visibilityOfElementLocated(By
                        .xpath(String.format(selectContainer, value)))).click();
        return this;
    }

    public GoogleCloudCalculatorPage clickAddGpuCheckBox() {
        scrollToElement(gpuCheckBox);
        gpuCheckBox.click();
        return this;
    }

    public GoogleCloudEstimatedInstancePage clickEstimate() {
        scrollToElement(estimateButton);
        estimateButton.click();
        return new GoogleCloudEstimatedInstancePage(driver);
    }

    public GoogleCloudEstimatedInstancePage estimateInstance(Instance instance) {
        switchToFrame(iFrame);
        switchToFrame(myFrame);
        logger.info("calculate estimation for instance " + instance);
        return activateComputeEngine()
                .fillInstancesField(instance.getNumberOfInstances())
                .dropOff(operatingSystem, instance.getOperatingSystem())
                .dropOff(machineClass, instance.getMachineClass())
                .dropOff(series, instance.getSeries())
                .dropOff(machineType, instance.getMachineType())
                .clickAddGpuCheckBox()
                .dropOff(numberOfGpu, instance.getNumberOfGpu())
                .dropOff(gpuType, instance.getGpuType())
                .dropOff(localSsd, instance.getLocalSsd())
                .dropOff(dataCenter, instance.getDataCenter())
                .dropOff(commitUsage, instance.getCommittedUsage())
                .clickEstimate();
    }
}
