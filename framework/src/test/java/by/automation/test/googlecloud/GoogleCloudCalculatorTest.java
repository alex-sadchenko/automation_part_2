package by.automation.test.googlecloud;

import by.automation.model.Instance;
import by.automation.page.googlecloud.GoogleCloudEstimatedInstancePage;
import by.automation.page.googlecloud.GoogleCloudHomePage;
import by.automation.service.InstanceCreator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GoogleCloudCalculatorTest extends CommonConditions {
    private static final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";
    private Instance instance;
    private GoogleCloudEstimatedInstancePage googleCloudEstimatePage;

    @BeforeClass
    public void openCalculator() {
        instance = InstanceCreator.withDataFromProperties();
        googleCloudEstimatePage = new GoogleCloudEstimatedInstancePage(driver);
        new GoogleCloudHomePage(driver)
                .openPage()
                .searchForResults(SEARCH_TERM)
                .openGoogleCloudCalculatorPage(SEARCH_TERM)
                .estimateInstance(instance);
    }

    @Test(description = "check machine class")
    public void machineClassTest() {
        String expectedMachineClass = "VM class: " + instance.getMachineClass();
        String expectedInstanceType = "Instance type: n1-standard-8";
        String expectedRegion = "Region: Frankfurt";
        String expectedLocalSsd = "Total available local SSD space 2x375 GiB";
        String expectedCommitmentTerm = "Commitment term: 1 Year";
        String expectedEstimationSum = "1,082.77";

        String actualMachineClass = googleCloudEstimatePage.getMachineClassFromCalculatedInstance();
        String actualInstanceType = googleCloudEstimatePage.getInstanceTypeFromCalculatedInstance();
        String actualRegion = googleCloudEstimatePage.getRegionFromCalculatedInstance();
        String actualLocalSsd = googleCloudEstimatePage.getLocalSsdFromCalculatedInstance();
        String actualCommitmentTerm = googleCloudEstimatePage.getCommitmentTermFromCalculatedInstance();
        String actualEstimationSum = googleCloudEstimatePage.getEstimatedSumFromCalculatedInstance();

        SoftAssert allAssert = new SoftAssert();

        assertThat(actualMachineClass.toLowerCase(), is(equalTo(expectedMachineClass.toLowerCase())));
        assertThat(actualInstanceType, is(equalTo(expectedInstanceType)));
        assertThat(actualRegion, is(equalTo(expectedRegion)));
        assertThat(actualLocalSsd, is(equalTo(expectedLocalSsd)));
        assertThat(actualCommitmentTerm, is(equalTo(expectedCommitmentTerm)));
        assertThat(actualEstimationSum, is(equalTo(expectedEstimationSum)));

        allAssert.assertAll();
    }
}

