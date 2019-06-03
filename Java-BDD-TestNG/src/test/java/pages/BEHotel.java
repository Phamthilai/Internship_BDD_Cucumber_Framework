package pages;

import assertion.Asserts;
import bases.BasePage;
import elements.controllerImpl.*;
import elements.controllerImpl.Button;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.BrowserUtils;
import utilities.TestUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static utilities.BrowserUtils.*;

public class BEHotel extends BasePage {

    /* =============================================== CAPTURE INTERFACE ============================================================*/

    //Be003
    private final String lnkLeftMenuBar = "(//a[contains(text(),'%')][1])";
    private final By lnkLeftSubMenuBar = By.xpath(".//following-sibling::ul//a");
    private final String strLnkLeftMenuBar = ".//following-sibling::ul//a[text() = '%']";
    List<Map<String, String>> data;

    // BE004 - BE005 -BE006
    private final By tblHotelManagement = By.xpath("//table[contains(@class,'xcrud-list table table-striped table-hover')]");
    private final By btnXcrudAll = By.xpath("//div[contains(@class,'xcrud-limit-buttons')]//button[contains(text(),'All')]");
    private final By btnAdd = By.cssSelector(".add_button .btn-success");
    private final By btnSubmitAdd = By.id("add");
    private final By btnSubmitUpdate = By.id("update");
    private final By ifrDescription = By.cssSelector(".cke_wysiwyg_frame.cke_reset");
    private final By txtIframe = By.cssSelector(".cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders");
    private final By lstLocationResult = By.cssSelector("#select2-drop .select2-results > li");
    private final By lstHotel = By.cssSelector(".select2-drop.select2-drop-multi .select2-results > li");
    private final By txtRelatedHotels = By.xpath("//div/*[contains(text(),'Related Hotels')]/..//ul[@class='select2-choices']//input");
    private final By dllLocation = By.cssSelector(".select2-container.locationlist");
    private final By txtLocation = By.cssSelector("#select2-drop .select2-input");
    private final By txtChosenLocation = By.cssSelector(".select2-chosen");
    private final By relatedHotelsPill = By.xpath("//div/*[contains(text(),'Related Hotels')]/..//li[@class='select2-search-choice']");
    private final String inputHotelInfo = "(//div[@id='GENERAL']//div//*[contains(@name,'%')])";
    private String cellHotelManagementTable = "//table//tr//td[count(//table[contains(@class,'xcrud-list table')]//tr/th[contains(text(),'%')]/preceding-sibling::th)+1]";
    // Data
    private  List<Map<String, String>> hotelInfoData;
    private int totalOriginalRow = 0;

    //BE006
    private final By btnAddPhoto = By.cssSelector(".btn-success .fa-photo");
    private final By btnDropZone = By.id("dropzone");
    private final By tblUploadImage = By.xpath("//table[contains(@class, 'table table-striped table-hover')]");
    private final List<WebElement> lnkImageList() {
        return getListElement(By.xpath("//table[contains(@class, table-striped)]//tr//a"));
    }
    private String filePath ="src/test/resources/images/upload-test.jpg";

    //BE007
    private final List<WebElement> btnDelete() {
        return getListElement(By.xpath("//a[contains(@class,'btn-xcrud btn-danger')]"));
    }

    //BE008
    private final By btnSearch = By.className("xcrud-search-toggle");
    private final By txtNotFound = By.cssSelector(".xcrud-list-container .xcrud-row");
    private final By btnReset = By.cssSelector(".xcrud-nav .btn-group:not([class*='xcrud-limit-buttons']) .btn-default");
    private final By txtInput = By.name("phrase");
    private final By dllFields = By.name("column");
    private final By btnGo = By.cssSelector(".xcrud-action.btn-primary");

    // BE010
    private String lnkTitleTable= "//tr[@class='xcrud-th']/th[contains(text(), '%')]";
    private List<String> actualList = new ArrayList<>();
    private List<String> expectedList = new ArrayList<>();

    /* =============================================== DEFINE FUNCTION ============================================================*/

    /******************************* Common function *******************************/
    /**
     * Click on title of column of table
     * @param columnName: title column of table
     */
    public void clickOnCellTable(String columnName){
        waitForPresenceOfElement(TestUtils.getXpath(cellHotelManagementTable+"//a",columnName),10);
        getElement(Element.class,TestUtils.getXpath(cellHotelManagementTable+"//a",columnName)).javascriptClick();
    }
    /******************************* BE003 *******************************/

    public void clickHotel()
    {
        getElement(Element.class, TestUtils.getXpath(lnkLeftMenuBar,"Hotels")).clickAndWait();
    }

    public void verifyTotalOfSubMenu() {

        WebElement mainMenu = getElement(Element.class, TestUtils.getXpath(lnkLeftMenuBar,"Hotels"));
        List<WebElement> subHotelMenu = mainMenu.findElements(lnkLeftSubMenuBar);
        Asserts.verifyEquals(subHotelMenu.size(),5);
        Asserts.verifyAll();
    }

  public void verifyHotelSubMenuNavigation(DataTable dataTable)
    {
        clickHotel();
        data = dataTable.asMaps(String.class, String.class);
        for (String key : data.get(0).keySet()) {
            clickHotel();
            clickSubMenu(data.get(0).get(key.trim()));
            verifySubPage(data.get(0).get(key.trim()));
        }
        Asserts.verifyAll();
    }

    public void clickSubMenu(String subMenuText)
    {
        WebElement mainMenu = getElement(Element.class, TestUtils.getXpath(lnkLeftMenuBar,"Hotels"));
        WebElement subMenuElement =  mainMenu.findElement(TestUtils.getXpath(strLnkLeftMenuBar,subMenuText));
        getElement(Element.class,subMenuElement).clickAndWait();

    }

    public void verifySubPage(String submenu) {
        switch (submenu) {
            case "Hotels":
                Asserts.verifyEquals(driver.getTitle(), "Hotels Management");
                break;
            case "Rooms":
                Asserts.verifyEquals(driver.getTitle(), "Rooms Management");
                break;
            case "Extras":
                Asserts.verifyEquals(driver.getTitle(), "Extras Management");
                break;
            case "Reviews":
                Asserts.verifyEquals(driver.getTitle(), "Reviews Management");
                break;
            case "Hotels Settings":
                Asserts.verifyEquals(driver.getTitle(), "Hotels Settings");
                break;
            default:
                break;
        }
    }

    /**************************** BE004 ****************************/

    public Integer getTotalRecord(){
        getElement(Button.class,btnXcrudAll).javascriptClick();
        BrowserUtils.waitForPageLoads(driver);
        return  getElement(Table.class,tblHotelManagement).getRowCount();
    }

    public void clickOnAddButton() {
        totalOriginalRow = getTotalRecord();
        getElement(Button.class, btnAdd).javascriptClick();
    }

    /**
     * Fill all hotel info follow by "name" attribute of element
     * @param dataTable: input data
     */
    public void fillHotelInformation(DataTable dataTable) {
        hotelInfoData = dataTable.asMaps(String.class, String.class);

        for (String key : hotelInfoData.get(0).keySet()) {
            String value = hotelInfoData.get(0).get(key).trim();
            switch (key) {
                case "Status":
                    selectHotelInfo("hotelstatus",value);
                    break;
                case "Hotel Name":
                    textBoxHotelInfo("hotelname",value);
                    break;
                case "Hotel Description":
                    waitForPresenceOfElement(ifrDescription,10);
                    driver.switchTo().frame(driver.findElement(ifrDescription));
                    waitForPresenceOfElement(txtIframe,10);
                    driver.findElement(txtIframe).clear();
                    driver.findElement(txtIframe).sendKeys(value);
                    driver.switchTo().defaultContent();
                    break;
                case "Stars":
                    selectHotelInfo("hotelstar",value);
                    break;
                case "Hotel Type":
                    selectHotelInfo("hoteltype",value);
                    break;
                case "Featured":
                    selectHotelInfo("isfeatured",value);
                    break;
                case "Start Date":
                    textBoxHotelInfo("ffrom",value);
                    break;
                case "End Date":
                    textBoxHotelInfo("fto",value);
                    break;
                case "HintLocation":
                    getElement(Element.class, dllLocation).click();
                    getElement(TextBox.class, txtLocation).clearAndSetText(value);
                    sleep(5000);
                    break;
                case "Location":
                    List<WebElement> options = driver.findElements(lstLocationResult);
                    for (WebElement option : options) {
                        if (option.getText().trim().equals(value)) {
                            option.click();
                            break;
                        }
                    }
                    break;
                case "Deposit":
                    selectHotelInfo("deposittype",value);
                    break;
                case "Deposit Amount":
                    textBoxHotelInfo("depositvalue",value);
                    break;
                case "Vat Tax":
                    selectHotelInfo("taxtype",value);
                    break;
                case "Vat Tax Amount":
                    textBoxHotelInfo("taxvalue",value);
                    break;
                case "Related Hotel":
                    getElement(TextBox.class, txtRelatedHotels).hoverAndClick();
                    List<WebElement> hotels = driver.findElements(lstHotel);
                    for (WebElement hotel : hotels) {
                        if (hotel.getText().trim().equals(value)) {
                            hotel.click();
                            break;
                        }
                    }
                    break;
                case "Address":
                    textBoxHotelInfo("hotelmapaddress",value);
                    break;
                case "Latitude":
                    textBoxHotelInfo("latitude",value);
                    break;
                case "Longitude":
                    textBoxHotelInfo("longitude",value);
                    break;
            }
        }
    }

    public void selectHotelInfo(String nameOfElement, String value){
        getElement(DropDownList.class,TestUtils.getXpath(inputHotelInfo,nameOfElement)).selectByVisibleText(value);
    }

    public void textBoxHotelInfo(String nameOfElement, String value) {

        getElement(TextBox.class, TestUtils.getXpath(inputHotelInfo, nameOfElement)).clearAndSetText(value);

    }

    public void submitAddHotelInfo(){
        getElement(Button.class,btnSubmitAdd).javascriptClick();
        BrowserUtils.waitForPageLoads(driver);

    }

    /**
     * Total number of hotel is plus 1 more
     * Info of new hotel is correctly
     */
    public void verifyNewHotelInfo(){
        BrowserUtils.waitForPageLoads(driver);
        int totalCurrentRecord = getTotalRecord();
        Asserts.verifyEquals(totalCurrentRecord,totalOriginalRow+1);
        clickOnEditButton();
        verifyHotelInfo();
        Asserts.verifyAll();

    }

    public void verifyHotelInfo(){
        for (String key : hotelInfoData.get(0).keySet()) {
            String expectedValue = hotelInfoData.get(0).get(key).trim();
            switch (key) {
                case "Status":
                    Asserts.verifyEquals(getValueFromDropdown( "hotelstatus"),expectedValue,"Status");
                    break;
                case "Hotel Name":
                    Asserts.verifyEquals(getAttributeValue("hotelname"), expectedValue,"Hotel name");
                    break;
                case "Hotel Description":
                    waitForPresenceOfElement(ifrDescription,10);
                    driver.switchTo().frame(driver.findElement(ifrDescription));
                    Asserts.verifyEquals(driver.findElement(txtIframe).getText().trim(), expectedValue,"Hotel description");
                    driver.switchTo().defaultContent();
                    break;
                case "Stars":
                    Asserts.verifyEquals(getValueFromDropdown("hotelstar"), expectedValue,"hotel star");
                    break;
                case "Hotel Type":
                    Asserts.verifyEquals(getValueFromDropdown("hoteltype"), expectedValue,"Hotel type");
                    break;
                case "Featured":
                    Asserts.verifyEquals(getValueFromDropdown("isfeatured"), expectedValue,"Featured");
                    break;
                case "Start Date":
                    Asserts.verifyEquals(getAttributeValue("ffrom"), expectedValue,"Start date");
                    break;
                case "End Date":
                    Asserts.verifyEquals(getAttributeValue("fto"), expectedValue,"End date");
                    break;
                case "Location":
                    Asserts.verifyEquals(getTextElement(txtChosenLocation),expectedValue,"Location");
                    break;
                case "Deposit":
                    Asserts.verifyEquals(getValueFromDropdown("deposittype"), expectedValue,"Deposit");
                    break;
                case "Deposit Amount":
                    Asserts.verifyEquals(getAttributeValue("depositvalue"), expectedValue,"Deposit amount");
                    break;
                case "Vat Tax":
                    Asserts.verifyEquals(getValueFromDropdown("taxtype"), expectedValue,"Vat Tax");
                    break;
                case "Vat Tax Amount":
                    Asserts.verifyEquals(getAttributeValue("taxvalue"), expectedValue,"Vat amount");
                    break;
                case "Related Hotel":
                    Asserts.verifyEquals(getTextElement(relatedHotelsPill), expectedValue,"Related hotel");
                    break;
                case "Address":
                    Asserts.verifyEquals(getAttributeValue("hotelmapaddress"), expectedValue,"Address");
                    break;
                case "Latitude":
                    Asserts.verifyEquals(getAttributeValue("latitude"), expectedValue,"latitude");
                    break;
                case "Longitude":
                    Asserts.verifyEquals(getAttributeValue("longitude"), expectedValue,"Longitude");
                    break;
            }
            Asserts.verifyAll();
        }

        getElement(Button.class,btnSubmitUpdate).clickAndWait();

    }

    public String getAttributeValue(String nameOfElement){

            return getElement(Element.class, TestUtils.getXpath(inputHotelInfo,nameOfElement)).getAttribute("value").trim();

    }

    public String getValueFromDropdown(String nameOfElement){
        return getElement(DropDownList.class,TestUtils.getXpath(inputHotelInfo,nameOfElement)).getSelectedText().trim();
    }

    public String getTextElement(By by){
        return getElement(Element.class,by).getText().trim();
    }

    /******************************** BE005 **********************************/

    public void clickOnEditButton(){
      clickOnCellTable("Name");
    }

    public void verifyUpdatedHotelInfo(){
        clickOnEditButton();
        verifyHotelInfo();
        Asserts.verifyAll();
    }
    public void submitUpdateHotelInfo(){
        getElement(Button.class, btnSubmitUpdate).javascriptClick();
        sleep(3000);
    }

    /******************************** BE006 **********************************/

    public void clickOnUploadGGallery(){
        clickOnCellTable("Gallery");
    }

    public void clickOnAddPhotoButton(){
        getElement(Button.class,btnAddPhoto).clickAndWait();
    }

    public void verifyPhotoIsUploaded(){
        Asserts.verifyTrue(getElement(Table.class,tblUploadImage).getRowCount()>1,"number of row table");
        Asserts.verifyTrue(lnkImageList().get(1).getAttribute("href").contains("upload-test.jpg"),"verify name of image");
        Asserts.verifyAll();
    }
    public void uploadFile() throws AWTException {
        getElement(Button.class, btnDropZone).clickAndWait();
        File file = new File(filePath);
        StringSelection selection = new StringSelection(file.getAbsolutePath());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        Robot robot;
        robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleep(3000);
        waitUntilElementNotVisible(btnDropZone);

    }

    /**************************** BE007 *******************************/

    public void clickOnDeleteBtn(int index) {
        getElement(Button.class, btnDelete().get(index)).javascriptClick();
        sleep(2000);
    }
    public void deleteHotel(){
        totalOriginalRow = getTotalRecord();
        clickOnDeleteBtn(0);
        driver.switchTo().alert().accept();
    }
    public void verifyDeleteHotelSuccessFully() {
        BrowserUtils.waitForPageLoads(driver);
        int totalCurrentRow = getTotalRecord();
        Asserts.verifyEquals(totalCurrentRow, totalOriginalRow - 1,"Number of record is not change");
    }

    /**************************** BE009 *******************************/

    public void verifySearchHotelWorksCorrectly(DataTable dataTable) {
        getElement(Button.class, btnSearch).clickAndWait();
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            switch (data.get(i).get("Type")) {
                case "invalid":
                    searchByHotelNames(data.get(i).get("HotelName"));
                    Asserts.verifyEquals(getTextElement(txtNotFound), "Entries not found.");
                    break;

                case "valid":
                    searchByHotelNames(data.get(i).get("HotelName"));
                    sleep(3000);
                    Asserts.verifyTrue(getTotalRecord() > 0);
                    List<WebElement> lnkHotelName = getListElement(TestUtils.getXpath(cellHotelManagementTable+"//a","Name"));
                    for(WebElement item:lnkHotelName){
                        Asserts.verifyTrue(item.getText().toUpperCase().contains(data.get(i).get("HotelName").trim().toUpperCase()),"search info is not as expected");
                    }
                    Asserts.verifyAll();
                    break;
            }
        }
        getElement(Button.class, btnReset).clickAndWait();

    }

    public void searchByHotelNames(String hotelName) {
        getElement(TextBox.class, txtInput).clearAndSetText(hotelName);
        getElement(DropDownList.class, dllFields).selectByVisibleText("Name");
        getElement(Button.class, btnGo).clickAndWait();
    }


    /************************* BE0010 ****************************/

    public void verifyOrder(String columnName) {
        getElement(Button.class,btnXcrudAll).clickAndWait();
        /*Get expected Data from z-a*/
        expectedList.addAll(getAllCellValue(columnName));
        sortAndRevertResult(expectedList);
        getElement(Element.class, TestUtils.getXpath(lnkTitleTable,columnName)).javascriptClick();
        BrowserUtils.waitForPageLoads(driver);

        /*verify the order of actual list with expect order from z-a*/
        actualList.addAll(getAllCellValue(columnName));
        Asserts.verifyEquals(actualList,expectedList,"Not sort z-a");

        /*verify the order of actual list with expect order from a-z*/
        getElement(Element.class, TestUtils.getXpath(lnkTitleTable,columnName)).javascriptClick();
        BrowserUtils.waitForPageLoads(driver);
        Collections.reverse(expectedList);
        actualList.clear();
        actualList.addAll(getAllCellValue(columnName));
        Asserts.verifyEquals(actualList,expectedList,"Not sort a-z");
    }

    public void sortAndRevertResult(List<String> list){
        Collections.sort(list);
        Collections.reverse(list);
    }

    /**
     * Get all value at specific column in table
     * @param columnName : header name of table
     * @return List of value in all cell
     */
    public List<String> getAllCellValue(String columnName){
        List<String> data = new ArrayList<>();
        List<WebElement> lnkHotelName = getListElement(TestUtils.getXpath(cellHotelManagementTable,columnName));
        for(WebElement item:lnkHotelName){
            data.add(item.getText().trim().toLowerCase());
        }
        return data;
    }
}
