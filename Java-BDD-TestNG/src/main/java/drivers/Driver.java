package drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    // this is our driver that will be used for all selenium actions
    // local variables

    private static Driver instance = null;


    private Logger log = LogManager.getLogger(Driver.class);

    private static final int IMPLICIT_TIMEOUT = 0;

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    private ThreadLocal<String> sessionBrowser = new ThreadLocal<String>();

    private ThreadLocal<String> sessionPlatform = new ThreadLocal<String>();

    //private ThreadLocal<String> sessionVersion = new ThreadLocal<String>();


    // constructor
    private Driver() {
    }

    /**
     * getInstance method to retrieve active driver instance
     *
     * @return CreateDriver
     */
    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }

        return instance;
    }

    /**
     * getDriver method will retrieve the active WebDriver
     *
     * @return WebDriver
     */

    public WebDriver getDriver() {
       // return setDriver(getInstance().getDriver());
        return webDriver.get();
    }

    /**
     * getCurrentDriver method will retrieve the active WebDriver
     *
     * @return WebDriver
     */

    public WebDriver getCurrentDriver() {

        return getInstance().getDriver();

    }

    /**
     *  createDriver method to create create new WebDriver
     * *
     * * @param browser browerType
     */

    public WebDriver createDriver (String browser) {

        WebDriver driver = null;

        switch (browser) { // check our browser
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                //webDriver.set(new FirefoxDriver());
                break;
            }
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            }
            case "ie": {
                driver = new InternetExplorerDriver();
                break;
            }
            // if our browser is not listed, throw an error
            default: {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            }
        }

        return driver;
    }

    /**
     * overloaded setDriver method to create specific WebDriver using thread sefe
     * *
     * * @param driver WebDriver instance to switch to
     */
    public WebDriver setDriver (String browser) {

//        String getPlatform = null;
        String getPlatform = System.getProperty("os.name");
        System.out.println("===============Platform: "+getPlatform);

        if(getPlatform.startsWith("Windows")){
            switch (browser) { // check our browser
                case "firefox": {
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                    webDriver.set(new FirefoxDriver());
                    break;
                }
                case "chrome": {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                    webDriver.set(new ChromeDriver());
                    break;
                }
                case "ie": {
                    webDriver.set(new InternetExplorerDriver());
                    break;
                }
                // if our browser is not listed, throw an error
                default: {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                    webDriver.set(new ChromeDriver());
                }
            }
        }else if(getPlatform.startsWith("Mac")){
            switch (browser) { // check our browser
                case "firefox": {
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver");
                    webDriver.set(new FirefoxDriver());
                    break;
                }
                case "chrome": {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
                    webDriver.set(new ChromeDriver());
                    break;
                }
                case "ie": {
                    webDriver.set(new InternetExplorerDriver());
                    break;
                }
                // if our browser is not listed, throw an error
                default: {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
                    webDriver.set(new ChromeDriver());
                }
            }
        }


        return webDriver.get();
    }
    /**
     * overloaded setDriver method to switch driver to specific WebDriver
     * * if running concurrent drivers
     * * @param driver WebDriver instance to switch to
     */
    public void setDriver(WebDriver driver) {

        webDriver.set(driver);

        sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());

        sessionBrowser.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName());
    }

    /**
     * getSessionId method gets the browser or mobile id  * of the active session  *  * @return String
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * getSessionBrowser method gets the browser or mobile type  * of the active session  *  * @return String
     */
    public String getSessionBrowser() {
        return sessionBrowser.get();
    }

    public void wait(double seconds) throws InterruptedException {
        Thread.sleep(Double.doubleToLongBits(seconds * 1000));
    }

    /**
     * driverWait method pauses the driver in seconds
     *
     * @param seconds to pause
     */

    public void driverWait(long seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));

        } catch (InterruptedException e) {
            // do something     //
        }
    }

    /**
     * driverRefresh method reloads the current browser page
     */
    public void driverRefresh() {
        getCurrentDriver().navigate().refresh();
    }

    /**
     * closeDriver method quits the current active driver
     */
    public void closeDriver() {
        try {

//            getDriver().quit();
//            webDriver.remove();
        } catch (Exception e) {         // do something    //
        }
    }
}
