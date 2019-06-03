package bases;

import drivers.Driver;
import elements.controllerImpl.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import runner.TestRunner;
import utilities.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.reflect.Constructor;

import java.util.List;

import static utilities.Contants.TIMEOUT_SECOND;

public class BasePage {

    public WebDriver driver = null;
    private WebElement elementnull =null;
    public Logger log = LogManager.getLogger(TestRunner.class);

    WebDriverWait wait;

    public BasePage() {

            this.driver = Driver.getInstance().getDriver();
            wait = new WebDriverWait(driver, TIMEOUT_SECOND);
    }


    /**
     * getElement - return list of webelement  *
     * * @type Class
     * * @locators locator
     */


    public <T> T getElement(Class<T> type, By locators) {

        try {

            Constructor<T> constructor = type.getDeclaredConstructor(WebElement.class);
            return constructor.newInstance(wait.until(ExpectedConditions.visibilityOf(driver.findElement(locators))));

        } catch (Exception e) {

            log.warn(e.getMessage());
            try {
                return type.getDeclaredConstructor(WebElement.class).newInstance(elementnull);
            }
            catch(Exception ex)
            {
               // log.warn(ex);
                return  null;
            }
        }
    }

    /**
     *
     * @param type
     * @param element
     * @param <T>
     * @return
     */
    public <T> T getElement(Class<T> type, WebElement element) {


        try {

            Constructor<T> constructor = type.getDeclaredConstructor(WebElement.class);

            return constructor.newInstance((element));


        } catch (Exception e) {
            log.warn(e.getMessage());

            try {
                return type.getDeclaredConstructor(WebElement.class).newInstance(elementnull);
            }
            catch(Exception ex)
            {
                log.warn(ex.getMessage());
                return  null;
            }
        }
    }

    /**
     * getListElement - return list of webelement  *
     * * @type Class
     * * @locators locator
     */

    public <T> List<T> getListElement(By locators) {

        try
        {
            List<T> elements = (List<T>) getElement(Element.class, locators).findElements(locators);
            return elements;
        }
         catch (Exception e) {
             log.warn(e.getMessage());
             return null;
        }
    }


    /**
     * getTitle - method to return the title of the current page
     *
     * @throws Exception
     */

    public String getTitle() {
        return driver.getTitle();
    }


    /**
     * loadPage - overloaded method to load the page URL and sync  *
     * * @param pageURL
     * * @param landingUrl
     * * @throws Exception
     */
    public void openPage(String pageURL) {


        driver.manage().window().maximize();
        driver.navigate().to(pageURL);

        // wait for page download, sync.
        BrowserUtils.isPageReady(driver);

    }
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
    public void waitUntilElementNotVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}
