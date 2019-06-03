package utilities;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static utilities.Contants.TIMEOUT_SECOND;

/**
 * @author @HongLe
 * <p>
 * Browser Utility Class
 */
public class BrowserUtils {

    /**
     * waitFor method to poll page title
     *
     * @param title
     * @param timer
     * @throws Exception
     */

    public static void waitFor(String title,
                               int timer)
            throws Exception {

        WebDriver driver = Driver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));


    }

    /**
     * waitForURL method to poll page URL
     *
     * @param url
     * @param timer
     * @throws Exception
     */
    public static void waitForURL(String url,
                                  int timer)
           {

        WebDriver driver = Driver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }

    /**
     * waitForClickable method to poll for clickable
     *
     * @param by
     * @param timer
     * @throws Exception
     */
    public static void waitForClickable(By by, int timer)
            {

        WebDriver driver = Driver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by)));
    }

    public static void waitForPresenceOfElement(By by, int timer) {
        WebDriver driver = Driver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(
                ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static void sleep(int timer)
    {

        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * click method using JavaScript API click
     *
     * @param by
     * @throws Exception
     */
    public static void click(By by) throws Exception {

        WebDriver driver = Driver.getInstance().getDriver();
        WebElement element = driver.findElement(by);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * isPageReady - method to verify that a page has completely rendered  *  * @param driver  * @return boolean
     */
    public static boolean isPageReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return document.readyState").equals("complete");
    }

    public static void waitForPageLoads(WebDriver driver){
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isPageReady(driver);
            }
        };
        try {
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
    /**
     * isAjaxReady - method to verify that an ajax control has rendered  *
     *
     * @param driver * @return boolean
     */
    public static boolean isAjaxReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return jQuery.active == 0");
    }

    public static void scrollDown() {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getInstance().getDriver();
        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
    }

}
