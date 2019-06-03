package stepDefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.FESearch;

import java.util.List;


public class FESearchStep {
    FESearch searchPage = new FESearch();
    Logger log = LogManager.getLogger(FESearchStep.class);
    private List<List<String>> data;

    /****************************** Background *************************************/

    @Given("^User is on homepage$")
    public void openPage() {
        searchPage.landingHomePage();

    }

    /************************* Scenario: FE_Tour002 - Verify Search Tour successfully ***********************************/

    @Then("^Go to  tours page$")
    public void goToToursPage()
    {
        searchPage.goToTourPage();
        log.info("Go to tour page");
    }

    @And("^Provide tours searching Information$")
    public void provideToursFilterInformation(DataTable userData) {
        data = userData.cells();
        searchPage.provideTourInformation(data.get(1).get(0),  Float.parseFloat(data.get(1).get(1)), Float.parseFloat(data.get(1).get(2)),data.get(1).get(3));
    }

    @Then("Verify Tours Information")
    public void verifyToursList() {
        searchPage.verifyTourInformation(Integer.parseInt(data.get(1).get(0)),  Float.parseFloat(data.get(1).get(1)), Float.parseFloat(data.get(1).get(2)),data.get(1).get(3));
    }

    /**************************** FE003-Cars - Verify Cars Filter *****************************************/

    @Then("^Go to car page$")
    public void goToCarsPage(DataTable userData)
    {
        log.info("Go to car page");
        data = userData.cells();
        searchPage.goToCarPage(data.get(1).get(0));
    }

    @And("^Provide cars searching Information$")
    public void provideCarsSearchingInformation(DataTable userData) {
        data = userData.cells();
        searchPage.provideSearchingInformation(data.get(1).get(0), data.get(1).get(1),Float.parseFloat(data.get(1).get(2)),Float.parseFloat(data.get(1).get(3)),data.get(1).get(4));

    }

    @Then("Verify Cars Information")
    public void verifyCarsInformation() {
        searchPage.verifyCarInformation(Integer.parseInt(data.get(1).get(0)),Float.parseFloat(data.get(1).get(2)),Float.parseFloat(data.get(1).get(3)),data.get(1).get(4));
    }

    /**************************** FE004-Flights - Verify Flights Filter ***********************************************/

    @Then("^Go to flight page$")
    public void goToFlightPage() {
        log.info("Go to flight page");
        searchPage.goToFlightsPage();
    }

    @And("^Provide flight searching information$")
    public void provideFlightFilterInformation(DataTable userData) {
        data = userData.cells();
        searchPage.provideFlightInformation(data.get(1).get(0), data.get(1).get(1));
    }

    @Then("^Verify flights information$")
    public void verifyFlightsList() {
        searchPage.verifyFlightInformation(data.get(1).get(0), data.get(1).get(1));
    }

    /******************************** Hotel page ***************************************/

    @Then("^Go to hotel listing page$")
    public void goToHotelPage() {
        searchPage.goToHotelsPage();
    }

    @And("^Provide hotel searching information$")
    public void provideHotelFilterInformation(DataTable userData) {
        data = userData.cells();
        searchPage.provideHotelsInformation(data.get(1).get(0), Float.parseFloat(data.get(1).get(1)), Float.parseFloat(data.get(1).get(2)), data.get(1).get(3), data.get(1).get(4));
    }

    @Then("^Verify hotels information$")
    public void verifyHotelsInformation(){
        searchPage.verifyHotelInformation(Integer.parseInt(data.get(1).get(0)),  Float.parseFloat(data.get(1).get(1)), Float.parseFloat(data.get(1).get(2)));
    }


    /************************ Common **************************/

    @Given("Click Search button")
    public void clickSearch() {
        log.info("Click on search button");
        searchPage.clickSearch();
    }


}
