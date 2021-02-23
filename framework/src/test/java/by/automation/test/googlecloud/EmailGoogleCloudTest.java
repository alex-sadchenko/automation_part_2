package by.automation.test.googlecloud;

import by.automation.page.googlecloud.GoogleCloudCalculatorPage;
import by.automation.page.googlecloud.GoogleCloudHomePage;
import by.automation.page.tempmail.TempMailHomePage;
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
        GoogleCloudCalculatorPage googleCloudCalculatorPage = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForResults(SEARCH_TERM)
                .openGoogleCloudCalculatorPage(SEARCH_TERM);

        String amountInCalculator = googleCloudCalculatorPage
                .estimateInstance(InstanceCreator.withDataFromProperties())
                .getEstimatedSumFromCalculatedInstance();

        String amountInEmail = googleCloudCalculatorPage
                .sendEmail()
                .readMessage(MAIL_SUBJECT)
                .getSum();

        assertThat(amountInEmail, is(equalTo(amountInCalculator)));
    }
}
