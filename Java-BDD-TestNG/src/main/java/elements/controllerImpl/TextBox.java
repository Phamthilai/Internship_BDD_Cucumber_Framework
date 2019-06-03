package elements.controllerImpl;

import elements.controller.ITextBox;
import org.openqa.selenium.WebElement;

public class TextBox extends Element implements ITextBox {

    public TextBox (WebElement element) {

        super(element);
    }


    public String getTextInputValue() {
        return getWrappedElement().getAttribute("value");
    }

    public void clearAndSetText(String text) {

        getWrappedElement().clear();
        getWrappedElement().sendKeys(text);
    }
}