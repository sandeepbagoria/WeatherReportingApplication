package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.Utils;

import static org.testng.Assert.assertEquals;

public class NDTVHomePage {
    WebDriver driver;
    String ndtv_url = "https://www.ndtv.com/news";
    By header = By.xpath("//div[@class='ndtvlogo']/a/img");
    By subMenu = By.id("h_sub_menu");
    By weatherIcon = By.xpath("//a[text()='WEATHER']");
    By notificationPopUp  = By.xpath("//div[@class='noti_wrap']");
    By denyNotification = By.xpath("//a[text()='No Thanks']");

    public NDTVHomePage(WebDriver driver){
        this.driver = driver;
    }

    public void openNDTVHomePage() {
        try{
            driver.get(ndtv_url);
        }
        catch (Exception e){
            throw (e);
        }
    }

    public void verifyHeader(){
        boolean pageLoad = Utils.waitForPageLoad(driver);
        assertEquals(pageLoad,true);
        boolean loaded = Utils.verifyImageIsDisplayed(driver,header);
        assertEquals(loaded,true);
    }

    public void menuNavigation(){
        boolean pageLoad = Utils.waitForPageLoad(driver);
        assertEquals(pageLoad,true);
        if (Utils.elementIsDisplayed(driver,notificationPopUp)){
            Utils.clickOnElement(driver,denyNotification);
        }
        Utils.clickOnElement(driver,subMenu);
        Utils.clickOnElement(driver,weatherIcon);
    }

}
