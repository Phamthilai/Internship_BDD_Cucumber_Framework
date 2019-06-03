package elements.controllerImpl;

import elements.controller.IDropDownList;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Author by thuytbnguyen on 12/3/2018.
 */
public class DropDownList extends Element implements IDropDownList {


    public DropDownList(WebElement element){
        super(element);

    }
    private Select select = new Select(getWrappedElement());

    public void selectByIndex(int index){
        select.selectByIndex(index);

    }

    public void selectByValue(String value){
        select.selectByValue(value);
    }

    public void selectByVisibleText(String text){
        select.selectByVisibleText(text);
    }

    public void deSelectAll(){
        select.deselectAll();
    }

    public void deSelectByIndex(int index){
        select.deselectByIndex(index);
    }

    public void deSelectByValue(String value){
        select.deselectByValue(value);
    }

    public void deSelectByVisibleText(String text){
        select.deselectByVisibleText(text);
    }

    public String getSelectedText()
    {
       return select.getFirstSelectedOption().getText();
    }



}
