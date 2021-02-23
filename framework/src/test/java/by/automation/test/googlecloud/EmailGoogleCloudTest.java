package by.automation.test.googlecloud;

import by.automation.page.googlecloud.GoogleCloudEstimatedInstancePage;
import by.automation.page.googlecloud.GoogleCloudHomePage;
import by.automation.service.InstanceCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class EmailGoogleCloudTest extends CommonConditions{
    private static final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";
    private static final String MAIL_SUBJECT = "Google Cloud Platform Price Estimate";

    @Test(description = "check total estimated amount per month")
    public void estimatedAmountTest() {
        GoogleCloudEstimatedInstancePage googleCloudEstimatedInstancePage = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForResults(SEARCH_TERM)
                .openGoogleCloudCalculatorPage(SEARCH_TERM)
                .estimateInstance(InstanceCreator.withDataFromProperties());

        String amountInCalculator = googleCloudEstimatedInstancePage
                .getEstimatedSumFromCalculatedInstance();

        String amountInEmail = googleCloudEstimatedInstancePage
                .sendEmail()
                .readMessage(MAIL_SUBJECT)
                .getSum();

        assertThat(amountInEmail, is(equalTo(amountInCalculator)));
    }
}
