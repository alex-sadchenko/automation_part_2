package by.automation.page.googlecloud;

import by.automation.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudEstimatePage extends AbstractPage {
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


    public GoogleCloudEstimatePage(WebDriver driver) {
        super(driver);
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
