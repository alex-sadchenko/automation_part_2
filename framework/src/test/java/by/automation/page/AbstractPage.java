package by.automation.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {
    protected static final Logger logger = LogManager.getRootLogger();
    protected final WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriverWait wait(int duration) {
        return new WebDriverWait(driver, duration);
    }

    protected WebElement scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        return element;
    }

    protected void switchToFrame(WebElement frame) {
        driver.switchTo().frame(frame);
    }
}
