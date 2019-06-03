package elements.controllerImpl;

import cucumber.api.java.en_old.Ac;
import drivers.Driver;
import elements.controller.IElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

import javax.annotation.Nullable;
import java.util.List;



public class Element implements IElement {

    private final WebElement element;

    public Element(final @Nullable WebElement element)
    {
        this.element = element;

    }

    public void hover() {

        Actions action = new Actions(Driver.getInstance().getCurrentDriver());
        action.moveToElement(element).perform();
    }

    public void hoverAndClick()  {
        Actions action = new Actions(Driver.getInstance().getCurrentDriver());
        action.moveToElement(element).click(element).build().perform();

    }

    @Override
    public void click() {
        element.click();

    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys((keysToSend));
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttribute((name));
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        try {
            return element.findElements(by);
        }
        catch(Exception ex)
        {
            return null;
        }
    }


    @Override
    public boolean isChildElementExists(By by) {

        return element.findElements(by).isEmpty();
    }

    @Override
    public WebElement findElement(By by) {
        try {
            return element.findElement(by);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return element.getScreenshotAs(target);
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public WebElement getWrappedElement() {
        return element;

    }

    @Override
    public void clickAndWait() {
        element.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void dragAndDropBy(int xOffset, int yOffset)
    {

        Action dragAndDropBy = new Actions(Driver.getInstance().getDriver()).dragAndDropBy(element,xOffset ,yOffset).build();

        dragAndDropBy.perform();
    }

    public void javascriptClick () {

        JavascriptExecutor js = (JavascriptExecutor)Driver.getInstance().getDriver();
        js.executeScript("arguments[0].click();", element);
    }



    @Override
    public void dragAndDrop(WebElement target)
    {

        Action dragAndDrop = new Actions(Driver.getInstance().getCurrentDriver()).dragAndDrop(element,target).build();

        dragAndDrop.perform();
    }

    @Override
    public  boolean isPresent(){

      if (element == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public  void moveToElement()
    {
        ((JavascriptExecutor)Driver.getInstance().getDriver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Override
    public  void setInnerHTML(String value)
    {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getInstance().getDriver();
        js.executeScript("arguments[0].innerHTML = '"+value+"'", element);
    }


}
