package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    public WebDriver driver;

    public WebDriver setupApplication()
    {
        System.setProperty("webdriver.chrome.driver","/Users/sandeepbagoria/Downloads/chromedriver 2");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    @AfterTest
    public void closeApplication()
    {
        driver.quit();
    }
}
