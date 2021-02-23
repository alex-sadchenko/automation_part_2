package by.automation.test.googlecloud;

import by.automation.model.Instance;
import by.automation.page.googlecloud.GoogleCloudEstimatedInstancePage;
import by.automation.page.googlecloud.GoogleCloudHomePage;
import by.automation.service.InstanceCreator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        String actualMachineClass = googleCloudEstimatePage.getMachineClassFromCalculatedInstance();

        assertThat(actualMachineClass.toLowerCase(), is(equalTo(expectedMachineClass.toLowerCase())));
    }

    @Test(description = "check instance type")
    public void instanceTypeTest() {
        String expectedInstanceType = "Instance type: n1-standard-8";
        String actualInstanceType = googleCloudEstimatePage.getInstanceTypeFromCalculatedInstance();

        assertThat(actualInstanceType, is(equalTo(expectedInstanceType)));
    }

    @Test(description = "check region")
    public void regionTest() {
        String expectedRegion = "Region: Frankfurt";
        String actualRegion = googleCloudEstimatePage.getRegionFromCalculatedInstance();

        assertThat(actualRegion, is(equalTo(expectedRegion)));
    }

    @Test(description = "check local SSD")
    public void localSsdTest() {
        String expectedLocalSsd = "Total available local SSD space 2x375 GiB";
        String actualLocalSsd = googleCloudEstimatePage.getLocalSsdFromCalculatedInstance();
        assertThat(actualLocalSsd, is(equalTo(expectedLocalSsd)));
    }

    @Test(description = "check commitment term")
    public void commitmentTermTest() {
        String expectedCommitmentTerm = "Commitment term: 1 Year";
        String actualCommitmentTerm = googleCloudEstimatePage.getCommitmentTermFromCalculatedInstance();
        assertThat(actualCommitmentTerm, is(equalTo(expectedCommitmentTerm)));
    }

    @Test(description = "check estimation sum")
    public void estimatedSumTest() {
        String expectedEstimationSum = "1,082.77";
        String actualEstimationSum = googleCloudEstimatePage.getEstimatedSumFromCalculatedInstance();

        assertThat(actualEstimationSum, is(equalTo(expectedEstimationSum)));
    }
}

