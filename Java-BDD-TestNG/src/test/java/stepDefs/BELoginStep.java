package stepDefs;

import assertion.Asserts;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.BELogin;

import java.util.Calendar;

public class BELoginStep {

    BELogin login = null;

    public BELoginStep() {
        login = new BELogin();
    }

    private Logger log = LogManager.getLogger(BELoginStep.class);



    @Given("^Login BackEnd Url$")
    public void loginBackEndUrl() {
       login.openPage();

    }


    @And("^Click BackEnd Login button$")
    public void click_BackEnd_Login_button(){
       login.click();

    }

    @Then("^BackEnd Login successfully$")
    public void backend_Login_successfully(){
        Asserts.verifyEquals(login.getUserInfomation(),"SUPER ADMIN","Actual is "+ login.getUserInfomation());
        login.verifyMenu();
        login.verifyPanelHeading();
        login.logOut();
    }

    @And("^Provide \"([^\"]*)\" and \"([^\"]*)\"$")
    public void provideUserNameAndPassWord(String username, String password){
        login.enterUserName(username);
        login.enterPassWord(password);
    }

    @Then("^Error message displayed \"([^\"]*)\"$")
    public void errorMessageDisplayed(String message) {
        login.verifyErrorMessage(message);
    }


}
