package weatherapplicationtests;

import api.TriggerWeatherAPI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.BaseClass;
import pages.NDTVHomePage;
import pages.NDTVWeatherPage;
import steps.CompareWeatherData;

public class TestWeatherData extends BaseClass {
    WebDriver driver = setupApplication();
    NDTVHomePage homePage = new NDTVHomePage(driver);
    NDTVWeatherPage weatherPage = new NDTVWeatherPage(driver);
    TriggerWeatherAPI weatherAPI = new TriggerWeatherAPI();
    CompareWeatherData weatherData = new CompareWeatherData(weatherAPI,weatherPage);

    @Test
    public void validateWeatherData() throws Exception {
        //open to NDTV home page
        homePage.openNDTVHomePage();

        //verify page loaded successfully
        homePage.verifyHeader();

        //navigate to weather page
        homePage.menuNavigation();

        //verify page loaded successfully
        weatherPage.verifyHeader();

        //validate weather details pop is getting displayed when clicking upon city
        weatherPage.validateWeatherDetailsPopUp();

        //compare temperature displayed on NDTV weather page with temperature received from weather api
        weatherData.validateWeatherData();
    }

}
