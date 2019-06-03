package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public  class TestUtils {
    /**
     *
     * @param pattern
     * d MMMMM yyyy - > 2 December 2018
     * @return system date
     */
    public static String getCurrentDate(String pattern){
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(today);
    }


    public static String formatDate(String pattern, String strDate){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(strDate);
            return new SimpleDateFormat(pattern).format(date);
        }
        catch(Exception e)
        {
            return "";
        }
    }

    public static By getXpath(String currentXpath,String pattern, String replacePattern){

        return By.xpath(currentXpath.replace(pattern,replacePattern));
    }

    public static By getXpath(String currentXpath,String replacePattern){
        return By.xpath(currentXpath.replace("%",replacePattern));
    }

    public static By getId(String id){
        return By.id(id);
    }


    public static WebElement randomElement(List<WebElement> links)
    {
       return links.get(new Random().nextInt(links.size()));
    }

    public static void randomLinksClick(List<WebElement> links)
    {
         links.get(new Random().nextInt(links.size())).click();
    }

    public static void keyPress(String value)
    {
        Robot rb = null;
        try {
            rb = new Robot();
            char[] chars = value.toCharArray();
            char[] charsCaps = value.toUpperCase().toCharArray();

            for (int i =0;i<charsCaps.length;i++)
            {
                if (chars[i] == charsCaps[i]) {
                    rb.keyPress(KeyEvent.VK_SHIFT);
                    rb.keyPress(charsCaps[i]);
                    rb.keyRelease(charsCaps[i]);
                    rb.keyRelease(KeyEvent.VK_SHIFT);
                    rb.keyPress(KeyEvent.VK_ENTER);
                    rb.keyRelease(KeyEvent.VK_ENTER);
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}
