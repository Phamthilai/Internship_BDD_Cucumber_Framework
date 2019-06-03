package pages;

import assertion.Asserts;
import bases.BasePage;
import elements.controllerImpl.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestUtils;


import java.util.List;

import static utilities.TestUtils.*;

public class FESearch extends BasePage {
    private String url = "https://www.phptravels.net/";

    /* =============================================== CAPTURE INTERFACE ============================================================*/
    private String menuBar = "//span[contains(text(),'%')]";
    private final String lblSelectFilter = "//*[@name='%']/parent::div[contains(@class,'row')]";
    private final By btnSearchHome = By.xpath("//div[contains(@class,'active')]//button[contains(text(),'Search')]");
    private final String activeDateOnCalendar = "//div[contains(@class,'datepicker dropdown-menu')][contains(@style,'block')]/child::div[contains(@class,'days')]//td[contains(@class,'active')]";
    private final String ddlPickupContainer = "//li[contains(@class,'selectable')]//div[contains(text(),'%')]";

    //Filter
    private String xpathStarGtade = "//form[contains(@name,'fFilters')]//descendant::div[contains(@class,'iradio')][%]";
    private final By btnSearch = By.id("searchform");
    private String chkFilterSearch = "//form[contains(@name,'fFilters')]//descendant::label[contains(@for,'%')]/preceding::div[1]";
    private final By ddlAirPortPickUp = By.cssSelector(".selectx");
    private String chkFlightFilter = "//input[@type='checkbox'][@value='%']/parent::div[contains(@class,'square-grey')]";
    private String chkAirLine = "//*[text()[contains(.,'%')]]/preceding-sibling::div";
    private String imgAirLine = chkAirLine + "/following::img[1]";

    //PriceRange
    private final By sliderLeft = By.xpath("//div[@class='slider-handle round'][1]");
    private final By sliderRight = By.xpath("//div[@class='slider-handle round'][2]");

    //Results
    private final By rowItemsContainer = By.xpath("//table[contains(@class,'bgwhite table table-striped')]/tbody/tr");
    private final By lblAirPortPickUp = By.xpath(".//button[./text()=' Airport Pickup']");
    private final By tblFlight = By.cssSelector("#load_data");
    private final String lblActualTourInformation = ".//div/div[contains(@class,'add_info hidden-sm hidden-xs go-right RTL')]//strong[contains(text(),'%')]/following-sibling::a[1]";
    private final By lblActualStarRange = By.xpath(".//div/span[contains(@class,'go-right mob-fs10')]/descendant::i[@class ='star fa fa-star']");
    private  final  By lblActualPrice = By.xpath(".//div//div[contains(@class,'fs26 text-center')]//b");

    //Cookies notification
    private final  By btnGotIt = By.id("cookyGotItBtn");


    /* ============================================== DEFINE FUNCTION =============================================================== */

    /******************** Common *******************/
    public void landingHomePage()
    {
        openPage(url);
        closeGotItCookiesNotification();
    }

    public void chooseCalendar() {
        getElement(Element.class, By.xpath(activeDateOnCalendar)).click();

    }

    public void clickHomeSearch(){
        getElement(Button.class, btnSearchHome).clickAndWait();
    }

    public void clickSearch() {
        getElement(Element.class, btnSearch).clickAndWait();
    }

    // Requirement field need to fill on Home page
    public void selectTypeInformation(String type) {
        By lblFilter = TestUtils.getXpath(lblSelectFilter,  type);
        getElement(Element.class, lblFilter).clickAndWait();
    }

    // Choose type of object which is searching on search page
    public void chooseType(String type) {
        By optTourType = TestUtils.getXpath(chkFilterSearch, type);
        getElement(Element.class, optTourType).clickAndWait();
    }

    public void chooseStar(String star) {
        By optStar = TestUtils.getXpath(xpathStarGtade,star);
        getElement(RadioButton.class, optStar).click();
    }

    public void choosePriceRange(Float min, Float max) {
        getElement(Element.class, sliderLeft).dragAndDropBy((int) ((min -60)/4), 0);
        getElement(Element.class, sliderRight).dragAndDropBy((int) ((max - 760)/4), 0);
    }


    public Float getActualPrice(WebElement container){
        WebElement lblPrice = container.findElement(lblActualPrice);
        Float actualPrice =  Float.parseFloat(lblPrice.getText());
        return actualPrice;
    }

    public int getActualStarRange(WebElement container){
        List<WebElement> lblStarRange = container.findElements(lblActualStarRange);
        return lblStarRange.size();
    }

    // Close notification in page to avoid the "element not found" error which are covered by notification label
    public void closeGotItCookiesNotification(){

        if (getElement(Element.class,btnGotIt).isPresent()){
            getElement(Element.class,btnGotIt).clickAndWait();
        }
    }


    /******************** Tour Page*******************/
    public void goToTourPage(){
        getElement(Element.class, getXpath(menuBar,  "Tours")).clickAndWait();
        clickHomeSearch();

    }

    public void provideTourInformation(String star, Float minPrice, Float maxPrice, String type) {
        chooseStar(star);
        choosePriceRange(minPrice, maxPrice);
        chooseType(type);
    }

    public void verifyTourInformation(int star, float minPrice, float maxPrice,String tourType) {
        List<WebElement> items = getListElement(rowItemsContainer);
        for (WebElement item : items) {
            WebElement lblTourType = item.findElement(TestUtils.getXpath(lblActualTourInformation,"Tour Type"));
            Asserts.verifyEquals(lblTourType.getText(), tourType,"Tour type is not as expected");
            Asserts.verifyEquals(getActualStarRange(item),star,"Number of star is not as expected");
            Asserts.verifyTrue(getActualPrice(item) >= minPrice && getActualPrice(item) <= maxPrice);
        }

        Asserts.verifyAll();
    }

    /******************** Car Page*******************/

    public void goToCarPage(String pickupLocation) {
        clickCarsMenu();
        choosePickUpLocation(pickupLocation);
        clickHomeSearch();
    }

    public void clickCarsMenu() {
        By lblCars = TestUtils.getXpath(menuBar,"Cars");
        getElement(Element.class, lblCars).click();
    }

    public void choosePickUpLocation(String pickupLocation) {
        selectTypeInformation("pickupLocation");
        getElement(Element.class, TestUtils.getXpath(ddlPickupContainer, pickupLocation)).clickAndWait();
    }

    public void provideSearchingInformation(String star, String carType,Float minPrice, Float maxPrice, String pickUp) {
        chooseStar(star);
        chooseType(carType);
        choosePriceRange(minPrice,maxPrice);
        getElement(DropDownList.class, ddlAirPortPickUp).selectByValue(pickUp);

    }

    public void verifyCarInformation(int star, float minPrice, float maxPrice, String pickUp){
        List<WebElement> items = getListElement(rowItemsContainer);
        for (WebElement item : items) {
            verifyAirportPickup(item,pickUp);
            Asserts.verifyEquals(getActualStarRange(item),star,"Number of star is not as expected");
            Asserts.verifyTrue(getActualPrice(item) >= minPrice && getActualPrice(item) <= maxPrice,"Price is not as expected");

        }
        Asserts.verifyAll();
    }

    public void verifyAirportPickup(WebElement container,String pickUp) {
        boolean isAirPortBackUpExist = getElement(Element.class, container).isChildElementExists(lblAirPortPickUp);

        if(pickUp.equals("yes")){
            Asserts.verifyFalse(isAirPortBackUpExist,"AirPortBackUp does not exist on the page");
        }else {
            Asserts.verifyTrue(isAirPortBackUpExist,"AirPortBackUp existed on the page");
        }
    }

    /******************** Flight Page*******************/

    public void clickFlights() {

        By lblTours = TestUtils.getXpath(menuBar,  "Flights");
        getElement(Element.class, lblTours).clickAndWait();
    }

    public void goToFlightsPage() {
        clickFlights();
        selectTypeInformation("departure");
        chooseCalendar();
        clickHomeSearch();
    }

    public void provideFlightInformation(String stop, String airLine) {
        getElement(RadioButton.class, TestUtils.getXpath(chkFlightFilter,stop)).clickAndWait();
        getElement(RadioButton.class, TestUtils.getXpath(chkAirLine,airLine)).clickAndWait();
    }

    /**
     * Verify the flight info display as expected: verify the logo image, and the status of bus stop
     * @param stop: status of Stop (Yes/No)
     * @param airLine: name of air line
     */
    public void verifyFlightInformation( String stop, String airLine) {
        String lnkLogo = getElement(Element.class, TestUtils.getXpath(imgAirLine,airLine)).getAttribute("src");
        List<WebElement> table = getElement(Table.class, tblFlight).getAllTableRowElement();

        for (WebElement rows : table) {
            WebElement img = rows.findElement(By.xpath(".//img"));
            Asserts.verifyEquals(lnkLogo, img.getAttribute("src"));
        }
        Asserts.verifyAll();
    }

    /******************** Hotel Page*******************/

    public void goToHotelsPage() {
        clickHotels();
        selectTypeInformation("checkin");
        chooseCalendar();
        clickHomeSearch();
    }

    public void clickHotels() {

        By lblHotels = TestUtils.getXpath(menuBar, "Hotels");
        getElement(Element.class, lblHotels).clickAndWait();
    }

    public void verifyHotelInformation(int star, float minPrice, float maxPrice){
        List<WebElement> items = getListElement(rowItemsContainer);
        for (WebElement item : items) {
            Asserts.verifyEquals(getActualStarRange(item),star,"Star range is not as expected ");
            Asserts.verifyTrue(getActualPrice(item) >= minPrice && getActualPrice(item) <= maxPrice,"Price is not as expected ");

        }
    }
    public void provideHotelsInformation(String star, Float minPrice, Float maxPrice, String properties, String amenties) {
        chooseStar(star);
        choosePriceRange(minPrice, maxPrice);
        selectMultiOption(properties);
        selectMultiOption(amenties);

    }

    /**
     * This function is to apply for selecting multi options
     * It will separate "value" into small value which match each option
     * @param value: String value that need to separate
     */
    public void selectMultiOption(String value){
        if (value.contains("&")) {
            String[] arr = value.split("&");
            for (String item : arr) {
                getElement(RadioButton.class, TestUtils.getXpath(chkFilterSearch, item.trim())).clickAndWait();
            }
        }
        else
            getElement(RadioButton.class, TestUtils.getXpath(chkFilterSearch, value.trim())).clickAndWait();
    }

}
