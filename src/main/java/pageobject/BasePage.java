package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;
    protected WebDriverWait explicitWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        explicitWait = new WebDriverWait(driver, 3);
    }

    protected void writeText(By element, String text) { driver.findElement(element).sendKeys(text); }

    protected void waitVisibility(By by) { explicitWait.until(ExpectedConditions.visibilityOfElementLocated(by)); }

    protected boolean isDisplayed(By by) { return driver.findElement(by).isDisplayed(); }

}
