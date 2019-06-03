package pages;

import assertion.Asserts;
import bases.BasePage;
import elements.controllerImpl.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestUtils;
import java.util.List;


import static utilities.BrowserUtils.sleep;

public class FEBlogAndOffer extends BasePage {

    /* ==================Capture element======================== */

    private String menuBar ="//a[contains(text(),'%')]";
    private String  lnkCatePost  ="//div[contains(text(),'Categories and Posts')]/following-sibling::* /strong[contains(text(),'%')]";
    private String txtNumberOfCatePost = lnkCatePost +"/following-sibling::span";
    private By lblHeadingTitle = By.xpath("//div[contains(@class,'panel-heading title_rtl')]");
    private By lblPostRecord = By.xpath("//div[@class='panel-body']//div[@class='col-md-8']");
    private By lnkPaging = By.xpath("(//ul[contains(@role,'tablist')]/li/a)");
    private By lnkNextPage = By.xpath("(//ul[contains(@class,'nav-pills nav-justified')]/li/a)[last()]");

    //FE007
    private By btnReadMore = By.xpath("(//*[contains(text(),'Read More')])");
    private By image = By.className("fotorama__img");
    private By imgFullScreenIcon = By.xpath("//div[contains(@class,'fotorama__fullscreen-icon')]");

    private int normalImageWidth = 0;
    private int normalImageHeight = 0;
    private int fullImageWidth = 0;
    private int fullImageHeight = 0;


    /* ==================Define function======================== */

    /**
     * Check if has paging page and return number of paging page
     * @return
     */
    public int getNumberOfPagingPage()
    {
        if(!getElement(Element.class,lnkPaging).isPresent())
            return 0;
        else
            return getListElement(lnkPaging).size()-2;
    }


    /**************** FE006-Blog - Verify Categories and Posts **********************/

    public void chooseBlog()
    {
        getElement(Element.class, TestUtils.getXpath(menuBar,"Blog")).click();
    }

    public void chooseCategory(String category)
    {
        getElement(Element.class,TestUtils.getXpath(lnkCatePost,category)).click();
    }


    public  void verifyPostOfEachCategory(String category)
    {
        verifyHeadingTitle(category);
        verifyNumberOfPost(category);

    }


    public int getExpectedPost(String category)
    {
        return  Integer.parseInt(getElement(Element.class,TestUtils.getXpath(txtNumberOfCatePost,category)).getText());
    }

    public int getActualNumberOfPost(){
        int actualPosts =0;
        if(getElement(Element.class,lblPostRecord).isPresent()){
            actualPosts = getListElement(lblPostRecord).size();
            if (getNumberOfPagingPage()!=0)
            {
                for (int i = 2; i<= getNumberOfPagingPage();i++)
                {
                    getElement(Element.class,lnkNextPage).clickAndWait();
                    actualPosts += getListElement(lblPostRecord).size();

                }
            }
        }
        return actualPosts;
    }

    public void verifyNumberOfPost(String category){
        int expectedPosts = getExpectedPost(category);
        Asserts.verifyEquals(expectedPosts,getActualNumberOfPost(),"Verify number of post of "+category+" category");
        Asserts.verifyAll();

    }

    public void verifyHeadingTitle (String category)
    {
        String actualHeadingTitle = getElement(Element.class,lblHeadingTitle).getText();
        String expectedHeadingTitle ="POSTS FROM CATEGORY - " + category.toUpperCase();
        Asserts.verifyEquals(actualHeadingTitle,expectedHeadingTitle,"Heading title");
        Asserts.verifyAll();
    }


    /**************** FE007-Offers - Verify maximize image **********************/


    public void chooseOffer()
    {
        getElement(Element.class, TestUtils.getXpath(menuBar,"Offers")).click();
    }

    public void clickRandomReadMoreButton()
    {
        List<WebElement> listBntReadMore = getListElement(btnReadMore);
        TestUtils.randomLinksClick(listBntReadMore);

    }

    public boolean isFullScreenIconExists ()
    {
        return getElement(Element.class,imgFullScreenIcon).isPresent();
    }

    public void hoverOnImage()
    {
        //get normal image size to compare with full size
        normalImageHeight = Integer.parseInt(getImageAttributeValue("height"));
        normalImageWidth = Integer.parseInt(getImageAttributeValue("width"));

        getElement(Element.class,image).hover();
        sleep(2000);
    }

    public  void clickFullScreenIcon()
    {
        getElement(Element.class,imgFullScreenIcon).clickAndWait();
        //get full size of image to compare with normal size
        fullImageWidth = Integer.parseInt(getImageAttributeValue("width"));
        fullImageHeight = Integer.parseInt(getImageAttributeValue("height"));

    }

    public String getImageAttributeValue(String attribute){
        return (getElement(Element.class, image).getAttribute(attribute));
    }

    public boolean isImageDisplayedFullScreen()
    {
        if(normalImageWidth < fullImageWidth || normalImageHeight <fullImageHeight){
            return true;
        }else
            return false;
    }
}
