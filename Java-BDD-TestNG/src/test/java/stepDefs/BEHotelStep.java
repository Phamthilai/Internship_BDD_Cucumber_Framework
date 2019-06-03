package stepDefs;

import bases.BasePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import pages.BEHotel;
import pages.BELogin;

import java.awt.*;

import static utilities.BrowserUtils.sleep;


public class BEHotelStep extends BasePage {
    BEHotel hotel = new BEHotel();
    BELogin login = new BELogin();

    /******************** BE003***********************/

    @And("^Click Hotel on left menu bar$")
    public void clickHotelOnLeftMenuBar() {
        sleep(5000);
        hotel.clickHotel();
    }

    @Then("^Verify Total SubMenu$")
    public void verifyTotalSubMenu()  {
        hotel.verifyTotalOfSubMenu();
    }

    @Then("^Verify navigation page successfully when clicking per each sub menu$")
    public void verifyNavigationPageSuccessfullyWhenClickingPerEachSubMenu(DataTable dataTable) {
        hotel.verifyHotelSubMenuNavigation(dataTable);
//        login.logOut();
    }

    /******************* BE004 ***********************/

    @When("^Navigate to hotel management page$")
    public void navigateToHotelManagementPage() {
        hotel.clickHotel();
    }

    @Then ("^Choose Hotel submenu$")
    public void chooseHotelSubmenu(){
        hotel.clickSubMenu("Hotels");
    }

    @And("^Add new hotel$")
    public void createNewHotel(DataTable dataTable){
        hotel.clickOnAddButton();
        hotel.fillHotelInformation(dataTable);
        hotel.submitAddHotelInfo();
    }

    @Then("^Verify a new hotel can be added successfully$")
    public void verifyANewHotelCanBeAddedSuccessfully() {
        hotel.verifyNewHotelInfo();
        hotel.deleteHotel();

    }

    /******************* BE005 ***********************/

    @Then("^Modify hotel information$")
    public void modifyHotelInformation(DataTable dataTable){
        hotel.clickOnEditButton();
        hotel.fillHotelInformation(dataTable);
        hotel.submitUpdateHotelInfo();
    }

    @Then("^Verify hotel information edited successfully$")
    public void verifyHotelInfoCanBeEditedSuccessfully(){
        hotel.verifyUpdatedHotelInfo();
        hotel.deleteHotel();
    }

    /******************* BE006 ***********************/

    @Then("^Upload one image$")
    public void uploadImage() throws AWTException {
        hotel.clickOnUploadGGallery();
        hotel.clickOnAddPhotoButton();
        hotel.uploadFile();
    }

    @Then("^Verify photo can be uploaded successfully$")
    public void verifyPhotoCanBeUploadedSuccessfully(){
        hotel.verifyPhotoIsUploaded();
//        hotel.deleteHotel();
    }

    /******************* BE007 ***********************/

    @Then("^Delete hotel via delete icon$")
    public void deleteHotelViaIcon(){
        hotel.deleteHotel();
    }

    @Then("^Verify hotel can be deleted successfully via delete icon$")
    public void verifyHotelDeletedSuccessfully(){
        hotel.verifyDeleteHotelSuccessFully();
    }

    /****************** BE009 ***********************/
    @And("^Search hotel by name and verify search result$")
    public void searchHotelByNameAndVerifySearchResult(DataTable dataTable){
        hotel.verifySearchHotelWorksCorrectly(dataTable);
    }

    /***************** BE010 *********************/
    @Then("^Verify sort by hotel \"([^\"]*)\" works correctly$")
    public void verifySortByHotelWorksCorrectly(String property) {
        hotel.verifyOrder(property);
    }

}
