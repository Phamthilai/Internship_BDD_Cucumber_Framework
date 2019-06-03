package utilities;
import drivers.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

    public  static final byte[]  takeScreenshot () {

        WebDriver driver = Driver.getInstance().getDriver();
        TakesScreenshot ts = (TakesScreenshot) driver;
        final byte[] screenshot  = ts.getScreenshotAs(OutputType.BYTES);

        return screenshot;
    }

    public  static String getScreenshotPath () {

        TakesScreenshot ts = (TakesScreenshot) Driver.getInstance().getDriver();
        String unitDTS = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = "target/extent-reports/screenshot/" + unitDTS + ".png";
        File destination = new File(path);


        try {
            FileUtils.copyFile(src, destination.getAbsoluteFile());

        } catch (IOException e) {
            //Reporter.addStepLog("Captured Failed " + e.getMessage());
        }
        return  path;
    }
}
