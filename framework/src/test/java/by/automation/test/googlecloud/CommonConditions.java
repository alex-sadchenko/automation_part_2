package by.automation.test.googlecloud;

import by.automation.driver.DriverSingleton;
import by.automation.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class CommonConditions {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void close() {
        DriverSingleton.closeDriver();
    }
}
