package elements.controller;

/**
 * Author by thuytbnguyen on 12/3/2018.
 */
public interface IDropDownList extends IElement {

    void selectByIndex(int index);
    void selectByValue(String value);
    void selectByVisibleText(String text);
    String getSelectedText();
}
