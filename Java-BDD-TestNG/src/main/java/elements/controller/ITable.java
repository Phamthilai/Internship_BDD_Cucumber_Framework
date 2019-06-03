package elements.controller;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface ITable extends IElement {

    int getRowCount();

    int getColumnCount();

    List<WebElement> getAllTableRowElement();

    List<WebElement> getAllCellValueInRow(int rowIndex);

    WebElement getCellAtIndex(int rowIndex, int columnIndex) throws Exception;
}
