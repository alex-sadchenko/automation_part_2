package by.automation.test.googlecloud;

import by.automation.page.googlecloud.GoogleCloudEstimatedInstancePage;
import by.automation.page.googlecloud.GoogleCloudHomePage;
import by.automation.page.tempmail.TempMailHomePage;
import by.automation.service.InstanceCreator;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class EmailGoogleCloudTest extends CommonConditions{
    private static final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";
    private static final String MAIL_SUBJECT = "Google Cloud Platform Price Estimate";

    @Test(description = "check total estimated amount per month")
    public void estimatedAmountTest() {
        TempMailHomePage tempMailHomePage = new TempMailHomePage(driver);
        GoogleCloudEstimatedInstancePage googleCloudEstimatedInstancePage = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForResults(SEARCH_TERM)
                .openGoogleCloudCalculatorPage(SEARCH_TERM)
                .estimateInstance(InstanceCreator.withDataFromProperties());

        String amountInCalculator = googleCloudEstimatedInstancePage
                .getEstimatedSumFromCalculatedInstance();

        ((JavascriptExecutor) driver).executeScript("window.open();");
        for (String pageId : driver.getWindowHandles()){
            if(!pageId.equals(googleCloudEstimatedInstancePage.getCalculatorWindow())){
                driver.switchTo().window(pageId);
            }
        }

        String emailAddress = tempMailHomePage.openPage().copyEmailAddress();
        driver.switchTo().window(googleCloudEstimatedInstancePage.getCalculatorWindow());
        googleCloudEstimatedInstancePage.sendEmail(emailAddress);
        driver.switchTo().window(tempMailHomePage.getEmailWindow());

        String amountInEmail = tempMailHomePage
                .readMessage(MAIL_SUBJECT)
                .getSum();

        assertThat(amountInEmail, is(equalTo(amountInCalculator)));
    }
}
