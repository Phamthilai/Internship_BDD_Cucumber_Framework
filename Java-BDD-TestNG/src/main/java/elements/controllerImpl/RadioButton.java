package elements.controllerImpl;

import elements.controller.IRadioButton;
import org.openqa.selenium.WebElement;

public class RadioButton extends Element implements IRadioButton {

   public RadioButton(WebElement element) {
        super(element);
    }


   public void toggle() {
        getWrappedElement().click();
    }


    public void select() {
        if (!getWrappedElement().isSelected()) {
            toggle();
        }
    }

    public void unSelect() {
        if (getWrappedElement().isSelected()) {
            toggle();
        }
    }




}
