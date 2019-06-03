package bases;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import drivers.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {


    private static  String browser = System.getProperty("browser");//Get browser value from cmd


    public static void initalDriver () {

        WebDriver driver = Driver.getInstance().setDriver("chrome") ;
        Driver.getInstance().setDriver(driver);


    }
    public static void closeDriver () {
        Driver.getInstance().closeDriver();

    }

}



