package elements.controllerImpl;

import drivers.Driver;
import elements.controller.IButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import sun.rmi.runtime.Log;

public class Button extends Element implements IButton {

    private Logger log = LogManager.getLogger(Button.class);

    public Button (WebElement element) {
        super(element);
    }

    public void mouseClick () {

        log.info(" Do Mouse Click on element " + getWrappedElement().toString());
        Actions action = new Actions(Driver.getInstance().getDriver());
        action.moveToElement(getWrappedElement()).click().build().perform();
    }


    public void javascriptClick () {

        log.info(" Do javascriptClick on element " + getWrappedElement().toString());

        JavascriptExecutor js = (JavascriptExecutor)Driver.getInstance().getDriver();
        js.executeScript("arguments[0].click();", getWrappedElement());


    }

}
