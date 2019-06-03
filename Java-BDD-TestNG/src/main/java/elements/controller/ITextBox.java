package elements.controller;

public interface ITextBox extends IElement {

    String getTextInputValue();

    void clearAndSetText(String text);

}