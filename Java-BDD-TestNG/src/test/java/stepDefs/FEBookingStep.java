package stepDefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.FEBooking;

public class FEBookingStep {
    FEBooking booking = new FEBooking();

    @And("^Random choose Booking information$")
    public void randomChooseBookingInformation()  {
        booking.clickBookings();
    }

    @When("^Click Invoice$")
    public void clickInvoice() {
        booking.getExpectedBookingInformation();
    }

    @Then("^Verify Invoice Information$")
    public void verifyInvoiceInformation() {
        booking.verifyBookingInformation();
    }
}
