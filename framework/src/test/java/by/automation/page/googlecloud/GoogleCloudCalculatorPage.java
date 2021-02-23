package by.automation.page.googlecloud;

import by.automation.model.Instance;
import by.automation.page.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GoogleCloudCalculatorPage extends AbstractPage {

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
}
