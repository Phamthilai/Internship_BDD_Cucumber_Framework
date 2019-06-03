package pages;

import assertion.Asserts;
import bases.BasePage;
import elements.controllerImpl.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static utilities.BrowserUtils.sleep;

public class FEBooking extends BasePage {

    /* =============================================== CAPTURE INTERFACE ============================================================*/

    private final By lnkBooking = By.linkText("Bookings");
    private final By lnkBookingContainer = By.xpath("//*[@id='bookings']/div[@class='row']");
    private final By lnkBookingContainer5 = By.xpath("//*[@id='bookings']/div[@class='row' and ./div/a/b[contains(text(),'Madinah Moevenpick Hotel')]][1]");
    private final By hotelInformation = By.xpath(".//div[contains(@class,'col-md-5')]/descendant::*[text()]");
    private final By bookingInformation = By.xpath(".//div[contains(@class,'col-md-3')]/descendant::*[text()]");
    private final By btnInvoice = By.xpath(".//*[contains(text(),'Invoice')]");
    private final By tblInvoiceHotel = By.xpath("(//*[@id='invoiceTable']//table)[4]//tr[1]/td");
    private final By tblInvoiceBooking = By.xpath("(//*[@id='invoiceTable']//table)[1]//div");
    private static Map<String, String> actualResult = new HashMap<>();
    private static Map<String, String> expectedResult = new HashMap<>();

    private List<String> keyHotelMap = Arrays.asList("Hotel Name", "Country Name","Invoice Date");
    private List<String> keyBookingMap = Arrays.asList("Invoice Number", "Booking Code", "Due Date");

    /* =============================================== DEFINE FUNCTION ============================================================*/

    public void clickBookings() {
        getElement(Element.class, lnkBooking).click();

    }

    public void getExpectedBookingInformation() {

//        List<WebElement> lnkBooking = getListElement(lnkBookingContainer);
//        WebElement element = TestUtils.randomElement(lnkBooking);

        WebElement element5 =getElement(Element.class,lnkBookingContainer5);

        getExpectedHotelInformation(element5);
        getExpectedBookingInformation(element5);
        goToInvoicePage(element5);
    }

    /**
     * Get expected information from Booking
     * Avoid Star and USD$(Unit price) title
     * @param element: container of each hotel row
     */
    public void getExpectedHotelInformation(WebElement element){
        List<WebElement> items = element.findElements(hotelInformation);
        int i = 0;
        int key = 0;
        for (WebElement item : items) {
            //Avoid Star and USD$(Unit price) title
            if (i < items.size() && (i != 2 && i != 3)) {
                if (i ==1){
                    String[] countryName = getElement(Element.class, item).getText().trim().split("/");
                    expectedResult.put(keyHotelMap.get(key),countryName[0].trim());
                }else{
                    expectedResult.put(keyHotelMap.get(key),getElement(Element.class, item).getText().trim());
                }
                key++;

            }
            i++;
        }
    }

    /**
     * Get booking information of hotel
     * Need to replace all label title, just get the main info to be able to compare actual info
     * @param element:container of each hotel row
     */
    public void getExpectedBookingInformation(WebElement element){
        List<WebElement> listBookings = element.findElements(bookingInformation);
        String  bookingInformation = getElement(Element.class, listBookings.get(0)).getText();

        //Replace all title label into "", just get the main info
        bookingInformation = bookingInformation.toLowerCase().replaceAll("([a-zA-Z])", "").trim();
        String[] bookingInfor = bookingInformation.split("  ");
        int key = 0;
        for (String item : bookingInfor) {
            expectedResult.put(keyBookingMap.get(key),item.trim());
            key++;
        }

    }

    public void actualHotelInformation() {
        sleep(5000);
        List<WebElement> elements = getListElement(tblInvoiceHotel);
        int key = 0;
        for (WebElement item : elements) {
            actualResult.put(keyHotelMap.get(key),getElement(Element.class, item).getText().trim());
            key++;
        }

    }

    /**
     * Get actual booking: skip Invoice title
     */
    public void actualBookingInformation() {
        sleep(5000);
        List<WebElement> elements = getListElement(tblInvoiceBooking);
        // Swap key to map with the actual result
        Collections.swap(keyBookingMap,0,2);
        Collections.swap(keyBookingMap,1,2);

        int key = 0;
        int i = 0;
        for (WebElement item : elements) {
            String invoice = item.getText().replaceAll("([a-zA-Z])|([:])", "").trim();
            if(i == 0){
                actualResult.put(keyHotelMap.get(2),invoice);
            }
           else if(i!=2){
                actualResult.put(keyBookingMap.get(key),invoice);
                key++;
            }

           i++;
        }
    }

    public void goToInvoicePage(WebElement element) {
        log.info("Clicking Invoice");
        element.findElement(btnInvoice).click();
        switchToInvoiceWindow();

    }

    /**
     * Switch to new window
     */
    public void switchToInvoiceWindow() {
        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }

    public void verifyBookingInformation()
    {
        actualHotelInformation();
        actualBookingInformation();
        Asserts.verifyEquals(actualResult,expectedResult);
        Asserts.verifyAll();
    }

}
