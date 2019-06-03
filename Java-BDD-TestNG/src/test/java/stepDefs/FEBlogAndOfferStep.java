package stepDefs;

import assertion.Asserts;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import pages.FEBlogAndOffer;

import java.util.List;

public class FEBlogAndOfferStep {
    FEBlogAndOffer blogAndOffer = new FEBlogAndOffer();

    /**************** FE006-Blog - Verify Categories and Posts **********************/

    @And("^Choose Blog menu$")
    public void chooseBlogMenu(){
        blogAndOffer.chooseBlog();
    }

    @Then("^Choose categories and verify the number of post$")
    public void chooseCategoriesAndVerifyNumberOfPost(DataTable userData)  {
        List<List<String>> data = userData.asLists(String.class);

        for(int i=1; i<data.size();i++)
        {
            blogAndOffer.chooseCategory(data.get(i).get(0));
            blogAndOffer.verifyPostOfEachCategory(data.get(i).get(0));
        }


    }

    /**************** FE007-Offers - Verify maximize image **********************/

    @And("^Choose Offer menu$")
    public void chooseOfferMenu() {
        blogAndOffer.chooseOffer();
    }

    @When("^Random click on 'Read more' button$")
    public void randomClickOnReadMoreButton() {
        blogAndOffer.clickRandomReadMoreButton();
    }

    @When("^Mouse over on image$")
    public void mouseOverOnImage()  {
        blogAndOffer.hoverOnImage();
    }

    @Then("^Verify Fullscreen icon is  displayed on Image$")
    public void verifyFullscreenIconIsDisplayedOnImage() {
        Asserts.verifyTrue(blogAndOffer.isFullScreenIconExists());
    }

    @When("^Clicking Fullscreen icon$")
    public void clickingFullscreenIcon() {
        blogAndOffer.clickFullScreenIcon();
    }

    @Then("^Verify Image displays as full screen$")
    public void verifyImageDisplaysAsFullScreen(){
        Asserts.verifyTrue(blogAndOffer.isImageDisplayedFullScreen());
    }

    @When("^Mouse over on image and clicking on normal icon$")
    public void mouseOverOnImageAndClickingOnNormalIcon()  {
        blogAndOffer.clickFullScreenIcon();
    }

    @Then("^Image displays as normal size$")
    public void imageDisplaysAsNormalSize() {
        Asserts.verifyFalse(blogAndOffer.isImageDisplayedFullScreen());
        blogAndOffer.isFullScreenIconExists();
        Asserts.verifyAll();
    }


}
