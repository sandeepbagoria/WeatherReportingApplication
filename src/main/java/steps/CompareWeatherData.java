package steps;

import api.TriggerWeatherAPI;
import exception.WeatherDataException;
import io.restassured.response.Response;
import org.json.JSONObject;
import pages.NDTVWeatherPage;
import utility.Utils;

public class CompareWeatherData implements ValidateWeatherData {
    final double variance = 2;
    TriggerWeatherAPI apiResponse;
    NDTVWeatherPage uiResponse;

    public CompareWeatherData(TriggerWeatherAPI apiResponse,NDTVWeatherPage uiResponse){
        this.apiResponse = apiResponse;
        this.uiResponse = uiResponse;
    }

    @Override
    public void validateWeatherData() throws WeatherDataException {
        Response response = apiResponse.createWeatherAPIRequest();
        JSONObject json = new JSONObject(response.asString());
        String tempValueFromAPI = Utils.fetchNode(json,"temp");
        double apiValue = Double.valueOf(tempValueFromAPI);

        double uiValue = uiResponse.fetchWeatherDetails();

        if ((Math.abs(uiValue - apiValue) > variance)){
            throw new WeatherDataException("Temperature difference between weather UI and API is greater than the set variance value");
        }
        else {
            System.out.println("data validated successfully");
        }
    }
}
