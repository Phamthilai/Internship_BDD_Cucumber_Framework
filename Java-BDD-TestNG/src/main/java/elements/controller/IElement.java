package elements.controller;

import elements.controllerImpl.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

public interface IElement extends WebElement, WrapsElement, Locatable {

    void hover ();
    void hoverAndClick();
    void clickAndWait();
    void dragAndDrop(WebElement target);
    void dragAndDropBy(int xOffset, int yOffset);
    boolean isPresent();
    boolean isChildElementExists (By by);
    void moveToElement();
    void javascriptClick();
    void setInnerHTML(String value);

}
