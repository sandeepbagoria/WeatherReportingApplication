package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utility.Utils;

import static org.testng.Assert.assertEquals;

public class NDTVWeatherPage {
    WebDriver driver;

    By header = By.xpath("//div[@id='logo']/img");
    By city = By.id("Bengaluru");
    By temperature = By.xpath("//div[@title='Bengaluru']/div[@class='temperatureContainer']/span[@class='tempRedText']");
    By selectCheckbox = By.xpath("//input[@type='checkbox' and @id='Bengaluru']");
    By weatherDetailsPopUp = By.xpath("//div[@class='leaflet-popup-content-wrapper']");

    public NDTVWeatherPage(WebDriver driver){
        this.driver = driver;
    }

    public void verifyHeader(){
        boolean pageLoad = Utils.waitForPageLoad(driver);
        assertEquals(pageLoad,true);
        boolean loaded = Utils.verifyImageIsDisplayed(driver,header);
        assertEquals(loaded,true);
    }

    public void validateWeatherDetailsPopUp(){
        Utils.clickOnElement(driver,temperature);
        boolean verify = Utils.elementIsDisplayed(driver,weatherDetailsPopUp);
        assertEquals(verify,true);
    }

    public double fetchWeatherDetails(){
        String temp = "";
        if (Utils.elementIsSelected(driver,city)){
             temp = Utils.findElement(driver,temperature).getText();
        }
        else {
            Utils.clickOnElement(driver,selectCheckbox);
             temp = Utils.findElement(driver,temperature).getText();
        }

        StringBuilder sb = new StringBuilder(temp);
        sb.deleteCharAt(temp.length()-1);

        return Double.valueOf(String.valueOf(sb));
    }
}
